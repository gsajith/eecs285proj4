package eecs285.project4;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.JFrame;

/**
 * A player tank that moves in response to key strokes.
 * Press arrows for moving and space for firing.
 *
 * @author Jiatu Liu ljiatu
 */
public class PlayerTank extends Tank { 

    // images for the tank in four different directions
    private ImageIcon[] tankImages;

    public PlayerTank(final int healthPoint, final int bulletStrength, final int speed) {
        super(healthPoint, bulletStrength, speed, 0, 0);

        tankImages = new ImageIcon[4];
		tankImages[UP] = new ImageIcon("eecs285/project4/tankImage/tankDraft1.png");
		tankImages[RIGHT] = new ImageIcon("eecs285/project4/tankImage/tankDraft2.png");
		tankImages[DOWN] = new ImageIcon("eecs285/project4/tankImage/tankDraft3.png");
		tankImages[LEFT] = new ImageIcon("eecs285/project4/tankImage/tankDraft4.png");
        setIcon(tankImages[UP]);

        setUpMove();
    }

    // Specify how the tank moves when certain keys are pressed.
    private void setUpMove() {
		getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), LEFT);
		getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), RIGHT);
		getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), UP);
		getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), DOWN);
		getActionMap().put(UP, new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
                setIcon(tankImages[UP]);
			}
		});
		getActionMap().put(DOWN, new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
                setIcon(tankImages[DOWN]);
			}
		});
		getActionMap().put(LEFT, new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
                setIcon(tankImages[LEFT]);
			}
		});
		getActionMap().put(RIGHT, new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
                setIcon(tankImages[RIGHT]);
			}
		});
	}

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        PlayerTank tank = new PlayerTank(5, 5, 5);
        frame.add(tank);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

