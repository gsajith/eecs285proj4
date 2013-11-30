package eecs285.project4;

import static eecs285.project4.Constants.*;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

/**
 * A player tank that moves in response to key strokes.
 * Press arrows for moving and space for firing.
 */
public class PlayerTank extends Tank { 

    // images for the tank in four different directions
    private Image[] tankImages;

    public PlayerTank(final int healthPoint, final int bulletStrength, final int speed, 
                      final Model model) {
        super(healthPoint, bulletStrength, speed, 0, 0, model);

        tankImages = new Image[4];
		tankImages[UP] = new ImageIcon("eecs285/project4/tankImage/tankDraftUp.png").getImage();
		tankImages[DOWN] = new ImageIcon("eecs285/project4/tankImage/tankDraftDown.png").getImage();
		tankImages[LEFT] = new ImageIcon("eecs285/project4/tankImage/tankDraftLeft.png").getImage();
		tankImages[RIGHT] = new ImageIcon("eecs285/project4/tankImage/tankDraftRight.png").getImage();
        image = tankImages[UP];

        setUpMove();
    }

    // Specify how the tank moves when certain keys are pressed.
    private void setUpMove() {
		getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), UP);
		getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), DOWN);
		getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), LEFT);
		getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), RIGHT);
		getActionMap().put(UP, new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
                if(model.notifyLocation(PlayerTank.this, UP)) {
                    --row;
                    image = tankImages[UP];
                }
			}
		});
		getActionMap().put(DOWN, new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
                if(model.notifyLocation(PlayerTank.this, DOWN)) {
                    ++row;
                    image = tankImages[DOWN];
                }
			}
		});
		getActionMap().put(LEFT, new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
                if(model.notifyLocation(PlayerTank.this, LEFT)) {
                    --column;
                    image = tankImages[LEFT];
                }
			}
		});
		getActionMap().put(RIGHT, new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
                if(model.notifyLocation(PlayerTank.this, RIGHT)) {
                    ++column;
                    image = tankImages[RIGHT];
                }
			}
		});
	}
}

