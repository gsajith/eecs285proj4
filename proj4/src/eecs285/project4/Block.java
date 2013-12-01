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
    private int destructableLvl; 

    public Block(int Type, int X, int Y) {
        type = Type;

        if (X < NUM_BLOCKS * BLOCK_SIZE - 1 && X >= 0) {
            x = X;
        } else {
            System.out.print("Error in Block: x-coor not in bounds");
            x = 0;
        }

        if (Y < NUM_BLOCKS * BLOCK_SIZE - 1 && Y >= 0) {
            y = Y;
        } else {
            System.out.println("Error in Block: y-coor not in bounds");
            y = 0;
        }

        destructableLvl = 0;
        switch (type) {
            case TREE_BLOCK:
                image = new ImageIcon("eecs285/project4/blockImage/tree.png").getImage();
                break;
            case ICE_BLOCK:
                image = new ImageIcon("eecs285/project4blockImage/ice.png").getImage();  
                break;
            case WATER_BLOCK:
                image = new ImageIcon("eecs285/project4/blockImage/water.png").getImage();  
                break;
            case BRICK_BLOCK:
                image = new ImageIcon("eecs285/project4/blockImage/brick.png").getImage();
                break;
            case STEEL_BLOCK:
                break;
            case BASE_BLOCK:
                image = new ImageIcon("/eecs285/project4/blockImage/water.png").getImage();
                destructableLvl = 1;
                break;
        }
    }

    public int getx() { return x; }
    public int gety() { return y; }
    public int getType() { return type; }
    public int getDestructableLvl() { return destructableLvl; }
    public Image getImage() { return image; }
}
