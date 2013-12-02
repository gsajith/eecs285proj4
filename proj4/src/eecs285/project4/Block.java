package eecs285.project4;

import static eecs285.project4.Constants.*;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Block {
    private int type;
    private int x;
    private int y;
    private Image image;

    // Level at which block can be partially destroyed.
    // Equals 0 if N/A for block.
    private int destructibleLvl; 

    public Block(final int type, final int x, final int y) {
        this.type = type;

        if (x < NUM_BLOCKS * BLOCK_SIZE - 1 && x >= 0) {
            this.x = x;
        } else {
            System.out.print("Error in Block: x-coor not in bounds");
            this.x = 0;
        }

        if (y < NUM_BLOCKS * BLOCK_SIZE - 1 && y >= 0) {
           this.y = y;
        } else {
            System.out.println("Error in Block: y-coor not in bounds");
            this.y = 0;
        }

        destructibleLvl = NON_DESTRUCTIBLE;
        switch (type) {
            case TREE_BLOCK:
                image = new ImageIcon("C:\\Users\\Chermine\\workspace\\eecs285proj4\\proj4\\bin\\eecs285\\project4\\blockImage\\tree.png").getImage();
                break;
            case ICE_BLOCK:
                image = new ImageIcon("C:\\Users\\Chermine\\workspace\\eecs285proj4\\proj4\\bin\\eecs285\\project4\\blockImage\\ice.png").getImage();  
                break;
            case WATER_BLOCK:
                image = new ImageIcon("C:\\Users\\Chermine\\workspace\\eecs285proj4\\proj4\\bin\\eecs285\\project4\\blockImage\\water.png").getImage();  
                break;
            case BRICK_BLOCK:
                image = new ImageIcon("C:\\Users\\Chermine\\workspace\\eecs285proj4\\proj4\\bin\\eecs285\\project4\\blockImage\\brick.png").getImage();
                destructibleLvl = EASY_DESTRUCTIBLE;
                break;
            case STEEL_BLOCK:
                image = new ImageIcon("C:\\Users\\Chermine\\workspace\\eecs285proj4\\proj4\\bin\\eecs285\\project4\\blockImage\\steel.png").getImage();
                destructibleLvl = HARD_DESTRUCTIBLE;
                break;
            case BASE_BLOCK:
                image = new ImageIcon("C:\\Users\\Chermine\\workspace\\eecs285proj4\\proj4\\bin\\eecs285\\project4\\blockImage\\water.png").getImage();
                destructibleLvl = EASY_DESTRUCTIBLE;
                break;
            default:
                assert(false);
        }
    }

    public int getx() { return x; }
    public int gety() { return y; }
    public int getType() { return type; }
    public int getDestructibleLvl() { return destructibleLvl; }
    public Image getImage() { return image; }
}
