package eecs285.project4;

import static eecs285.project4.Constants.*;

import java.applet.*;
import java.net.*;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

/**
 * A player tank that moves in response to key strokes.
 * Press arrows for moving and space for firing.
 */
public class PlayerTank extends Tank { 
    // images for the tank in four different directions
    private Image[] tankImages;
    
    //Bool for player1 tank or player2 tank
    private boolean isPlayerOne;
    
    //Button press flags to allow multiple buttons pressed at once
    private boolean upPressed;
    private boolean downPressed;
    private boolean leftPressed;
    private boolean rightPressed;
    private boolean shootPressed;    
    private static AudioClip shoot;
        

    public PlayerTank(final int healthPoint, final int bulletStrength, final int speed, 
                      final Model model, boolean isPlayerOne) {
        //Create Tank with either Player1Tank or Player2tank configuration
        super(isPlayerOne?PLAYER1_TANK:PLAYER2_TANK, healthPoint, bulletStrength, speed, 
                isPlayerOne?INITIAL_PLAYER1_ROW:INITIAL_PLAYER2_ROW, 
                        isPlayerOne?INITIAL_PLAYER1_COLUMN:INITIAL_PLAYER2_COLUMN, model);
        this.isPlayerOne = isPlayerOne;
        try {
            shoot = Applet.newAudioClip(new URL("file:" + BASE_PATH + SOUND_PATH + "shoot.wav"));
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        upPressed = false;
        downPressed = false;
        leftPressed = false;
        rightPressed = false;
        shootPressed = false;
        
        tankImages = new Image[4];
        tankImages[UP] = new ImageIcon(BASE_PATH + TANK_PATH + "PlayerTankUp.png").getImage();
        tankImages[DOWN] = new ImageIcon(BASE_PATH + TANK_PATH + "PlayerTankDown.png").getImage();
        tankImages[LEFT] = new ImageIcon(BASE_PATH + TANK_PATH + "PlayerTankLeft.png").getImage();
        tankImages[RIGHT] = new ImageIcon(BASE_PATH + TANK_PATH + "PlayerTankRight.png").getImage();

        image = tankImages[UP];

        setUpMove();
        setUpShoot();
    }
    
    /*
     * Add set amount of armor to this tank
     */
    public void addArmor(int armor) {
        this.healthPoint += armor;
    }
    
    /*
     * Add set amount of firepower to this tank
     */
    public void addFirepower(int firePower) {
        this.bulletStrength = firePower;
    }
    
    /*
     * Moves and/or shoots for this tank based on 
     * which keys have been designated as pressed.
     * Called every 50 milliseconds from model
     */
    public void moveAndShoot() {
        //System.out.println("Start moveandshoot");
        if(upPressed) {
            direction = UP;
            image = tankImages[UP];
            if(model.notifyLocation(PlayerTank.this, UP)) {
                --row;
            }
        } else if(downPressed) {
            direction = DOWN;
            image = tankImages[DOWN];
            if(model.notifyLocation(PlayerTank.this, DOWN)) {
                ++row;
            }            
        } else if(leftPressed) {
            direction = LEFT;
            image = tankImages[LEFT];
            if(model.notifyLocation(PlayerTank.this, LEFT)) {
                --column;
            }
        } else if(rightPressed) {
            direction = RIGHT;
            image = tankImages[RIGHT];
            if(model.notifyLocation(PlayerTank.this, RIGHT)) {
                ++column;
            }            
        }
        //System.out.println("After direction checks");
        
        if(shootPressed) {
            if(canShoot) {
                canShoot = false; //canShoot flag is set to false until this thread is ended by Model    
                
                SoundThread sThread = new SoundThread(shoot);
                sThread.start();   
                //System.out.println("After play");
                
                BulletThread bThread = new BulletThread(PlayerTank.this, model, 
                    bulletStrength, BULLET_SPEED, direction, row, column);
                bThread.start();
                //System.out.println("After bthread start");
            }
        }
        //System.out.println("After bullet check");
    }

    /*
     *  Specify how the tank shoots when key is pressed
     */
    private void setUpShoot() {
        if(isPlayerOne) {
            //Separate key pressed (shoot) and key released (shootr) actions
            //in order to set/unset shootPressed flag
            //Same logic follows in setUpMove() for movement flags
            getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true), "shootr");
            getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), "shoot");
        } else {
            getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true), "shootr");
            getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false), "shoot");        
        }
        getActionMap().put("shoot", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent arg0) {                
                shootPressed = true;
            }
        });
        getActionMap().put("shootr", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent arg0) {                
                shootPressed = false;
            }
        });
    }

    // Specify how the tank moves when certain keys are pressed.
    private void setUpMove() {
        if(isPlayerOne) {
            getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, true), UP+"r");
            getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, false), UP);            
            getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, true), DOWN+"r");
            getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, false), DOWN);
            getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, true), LEFT+"r");
            getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, false), LEFT);
            getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, true), RIGHT+"r");
            getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, false), RIGHT);
        } else {
            getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, true), UP+"r");
            getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, false), UP);
            getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, true), DOWN+"r");
            getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, false), DOWN);
            getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, true), LEFT+"r");
            getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, false), LEFT);
            getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, true), RIGHT+"r");
            getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, false), RIGHT);
        }        
		getActionMap().put(UP, new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent arg0) {                
			    upPressed = true;
			}
		});
		getActionMap().put(UP+"r", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                upPressed = false;
            }
        });
		getActionMap().put(DOWN, new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			    downPressed = true;
			}
		});
		getActionMap().put(DOWN+"r", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                downPressed = false;
            }
        });
		getActionMap().put(LEFT, new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			    leftPressed = true;
			}
		});
		getActionMap().put(LEFT+"r", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                leftPressed = false;
            }
        });
		getActionMap().put(RIGHT, new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			    rightPressed = true;
			}
		});
		getActionMap().put(RIGHT+"r", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                rightPressed = false;
            }
        });
	}
}

