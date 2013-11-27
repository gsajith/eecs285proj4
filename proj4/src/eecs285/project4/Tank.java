package eecs285.project4;

import javax.swing.JLabel;

public abstract class Tank extends JLabel {

    protected final int UP = 0;
    protected final int DOWN = 1;
    protected final int LEFT = 2;
    protected final int RIGHT = 3;

    protected int healthPoint;
    protected int bulletStrength;
    protected int speed;
    // Coordinates of the tank
    protected int xCoordinate;
    protected int yCoordinate;

    public Tank(final int healthPoint, final int bulletStrength, final int speed,
                final int xCoordinate, final int yCoordinate) {
        this.healthPoint = healthPoint;
        this.bulletStrength = bulletStrength;
        this.speed = speed;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }
}
