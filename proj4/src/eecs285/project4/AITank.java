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

    public AITank(final int healthPoint, final int bulletStrength, final int speed, 
            final Model model) {
        super(AI_REG_TANK, healthPoint, bulletStrength, speed, 0, 0, model);

        tankImages = new Image[4];
        /*tankImages[UP] = new ImageIcon("C:/Users/Chermine/workspace/eecs285proj4/proj4/bin/eecs285/project4/tankImage/tankDraftUp.png").getImage();
        tankImages[DOWN] = new ImageIcon("C:/Users/Chermine/workspace/eecs285proj4/proj4/bin/eecs285/project4/tankImage/tankDraftDown.png").getImage();
        tankImages[LEFT] = new ImageIcon("C:/Users/Chermine/workspace/eecs285proj4/proj4/bin/eecs285/project4/tankImage/tankDraftLeft.png").getImage();
        tankImages[RIGHT] = new ImageIcon("C:/Users/Chermine/workspace/eecs285proj4/proj4/bin/eecs285/project4/tankImage/tankDraftRight.png").getImage();*/

        tankImages[UP] = new ImageIcon("eecs285/project4/tankImage/tankDraftUp.png").getImage();
        tankImages[DOWN] = new ImageIcon("eecs285/project4/tankImage/tankDraftDown.png").getImage();
        tankImages[LEFT] = new ImageIcon("eecs285/project4/tankImage/tankDraftLeft.png").getImage();
        tankImages[RIGHT] = new ImageIcon("eecs285/project4/tankImage/tankDraftRight.png").getImage();

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
            direction = generator.nextInt(4);
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
