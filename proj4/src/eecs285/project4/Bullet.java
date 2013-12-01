package eecs285.project4;

import static eecs285.project4.Constants.*;

public class Bullet {
    protected int bulletStrength;
    protected int bulletSpeed;
    protected int bulletDirection;
    protected int row;
    protected int column;

    public Bullet(final int bulletStrength, final int bulletSpeed, final int bulletDirection, 
                  final int row, final int column) {
        this.bulletStrength = bulletStrength;
        this.bulletSpeed = bulletSpeed;
        this.bulletDirection = bulletDirection;
        this.row = row;
        this.column = column;
        fixLocation(this.bulletDirection, this.row, this.column);
    }

    public void move() {
        switch(bulletDirection) {
            case UP:
                row -= bulletSpeed;
                break;
            case DOWN:
                row += bulletSpeed;
                break;
            case LEFT:
                column -= bulletSpeed;
                break;
            case RIGHT:
                column += bulletSpeed;
                break;
            default:
                assert(false);
        }
    }

    /*
     * Given input row and col as top-left corner of firing tank,
     * convert it to be coordinate corresponding to location where
     * bullet would be if it was fired from the tank's gun in the
     * direction that it's facing.
     */
    private void fixLocation(final int bulletDirection, final int row, final int column) {
        switch(bulletDirection) {
            case UP:
                this.row = row - BULLET_SIZE;
                this.column = column + BLOCK_SIZE/2 - 1;
                break;
            case DOWN:
                this.row = row + BLOCK_SIZE;
                this.column = column + BLOCK_SIZE/2 - 1;
                break;
            case LEFT:
                this.row = row + BLOCK_SIZE/2 - 1;
                this.column = column - BULLET_SIZE;
                break;
            case RIGHT:
                this.row = row + BLOCK_SIZE/2 - 1;
                this.column = column + BLOCK_SIZE;
                break;
            default:
                assert(false);
        }
    }
}
