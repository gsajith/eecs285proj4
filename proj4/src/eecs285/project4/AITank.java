package eecs285.project4;

import static eecs285.project4.Constants.*;

import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

/**
 * AI tanks are controlled by the computer.
 */
public class AITank extends Tank {
    private final Random generator = new Random();
    private Image[] tankImages;
    // determines whether the AI tank should generate a new location
    private boolean shouldTurnAround;
    private int direction;
    private int noMoveCounter;

    public AITank(final int healthPoint, final int bulletStrength, final int speed, 
            final int x, final int y, final Model model) {
        super(AI_REG_TANK, healthPoint, bulletStrength, speed, x, y, model);

        tankImages = new Image[4];
        tankImages[UP] = new ImageIcon(BASE_PATH + TANK_PATH + "OSUTankUp.png").getImage();
        tankImages[DOWN] = new ImageIcon(BASE_PATH + TANK_PATH + "OSUTankDown.png").getImage();
        tankImages[LEFT] = new ImageIcon(BASE_PATH + TANK_PATH + "OSUTankLeft.png").getImage();
        tankImages[RIGHT] = new ImageIcon(BASE_PATH + TANK_PATH + "OSUTankRight.png").getImage();

        image = tankImages[UP];

        shouldTurnAround = true;
        direction = UP;
    }

    /**
     * The AI tank goes towards a single direction until there are obstacles.
     * If the tank is stumped, it chooses a new direction randomly
     * and turns to that new direction.
     */
    public void go() {
        // UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3
        if(shouldTurnAround) {
            noMoveCounter++;
            System.out.println(noMoveCounter);
            if(noMoveCounter > 10) {
                direction = generator.nextInt(4);  
                noMoveCounter = 0;
            } else {
                direction = generator.nextInt(12);  
                if(direction >= 8 && direction <= 11 || row >= MAP_SIZE - BLOCK_SIZE) {
                    if(this.column < MAP_SIZE/2) {
                        direction = RIGHT;
                    } else {
                        direction = LEFT;
                    }
                } else {
                    direction = DOWN;
                }
            }
            
            shouldTurnAround = false;
            // we only have to change the image if we turn to a new direciotn
            image = tankImages[direction];
        }
        int shouldShoot = generator.nextInt(10);
        if(shouldShoot == 0) {
            if(canShoot) {
                canShoot = false;
                BulletThread thread = new BulletThread(AITank.this, model, 
                        bulletStrength, BULLET_SPEED, direction, row, column);
                thread.start();
            }
        }
        switch(direction) {
            case 0:
                if(model.notifyLocation(AITank.this, UP)) {
                    --row;
                    return;
                }
                break;
            case 1:
                if(model.notifyLocation(AITank.this, DOWN)) {
                    ++row;
                    return;
                }
                break;
            case 2:
                if(model.notifyLocation(AITank.this, LEFT)) {
                    --column;
                    return;
                }
                break;
            case 3:
                if(model.notifyLocation(AITank.this, RIGHT)) {
                    ++column;
                    return;
                }
                break;
            default:
                assert(false);
        }
        // If the tank cannot move any further in the current direction,
        // we need to turn it around in the next update.
        shouldTurnAround = true;
    }
}
