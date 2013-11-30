package eecs285.project4;

import static eecs285.project4.Constants.*;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * AI tanks are controlled by the computer.
 */
public class AITank extends Tank {
    private Image[] tankImages;

    public AITank(final int healthPoint, final int bulletStrength, final int speed, 
                  final Model model) {
        super(healthPoint, bulletStrength, speed, 10, 10, model);

        tankImages = new Image[4];
		tankImages[UP] = new ImageIcon("eecs285/project4/tankImage/tankDraft1.png").getImage();
		tankImages[RIGHT] = new ImageIcon("eecs285/project4/tankImage/tankDraft2.png").getImage();
		tankImages[DOWN] = new ImageIcon("eecs285/project4/tankImage/tankDraft3.png").getImage();
		tankImages[LEFT] = new ImageIcon("eecs285/project4/tankImage/tankDraft4.png").getImage();
        image = tankImages[UP];

        //setUpMove();
    }
}
