package eecs285.project4;

import java.awt.Image;
import javax.swing.JComponent;

public abstract class Tank extends JComponent {
    protected int number;
    protected int healthPoint;
    protected int bulletStrength;
    protected int speed;
    protected boolean canShoot;
    protected int direction;
    // Coordinates of the tank
    protected int row;
    protected int column;
    // image of the tank
    protected Image image;
    protected Model model;

    public Tank(final int number, final int healthPoint, final int bulletStrength, final int speed,
                final int row, final int column, final Model model) {
        this.number = number;
        this.healthPoint = healthPoint;
        this.bulletStrength = bulletStrength;
        this.speed = speed;
        this.direction = direction;
        this.row = row;
        this.column = column;
        this.model = model;
        this.canShoot = true;
    }

    /**
     * Return the number that is used to represent the tank on the map.
     */
    public int getNumber() {
        return number;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public Image getImage() {
        return image;
    }
    
    public boolean canShoot() {
    	return canShoot;
    }
}
