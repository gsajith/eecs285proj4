package eecs285.project4;

import java.awt.Image;
import javax.swing.JComponent;

public abstract class Tank extends JComponent {
    protected int type;
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
    private int originalRow;
    private int originalColumn;

    public Tank(final int type, final int healthPoint, final int bulletStrength, final int speed,
            final int row, final int column, final Model model) {
        this.type = type;
        this.healthPoint = healthPoint;
        this.bulletStrength = bulletStrength;
        this.speed = speed;
        this.direction = direction;
        this.row = row;
        this.column = column;
        this.model = model;
        this.canShoot = true;
        this.originalRow = row;
        this.originalColumn = column;
    }

    /**
     * Return the number that is used to represent the tank on the map.
     */
    public int getType() {
        return type;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
    
    public void resetLocation() {
    	row = this.originalRow;
    	column = this.originalColumn;
    }
    
    public void decrementHealth() {
    	healthPoint--;
    }
    
    public int getHealth() {
    	return healthPoint;
    }

    public Image getImage() {
        return image;
    }

    public boolean canShoot() {
        return canShoot;
    }
}
