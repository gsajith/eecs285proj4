package eecs285.project4;

import static eecs285.project4.Constants.*;

abstract public class Block {
/* class BLOCK	defines a block of the 13x13 block map.
 * 
 * Syntax:
 * 
 * 
 * Description:
 * 
 * 
 * Input:
 * 
 * 
 * Output:
 * 
 */
	
	private int type;
	private int x;
	private int y;
	private boolean canMoveThru;
	
	// We can't shoot thru bricks and steel and the base, but they
	// are destructable, so they will have a non-zero value for destructableLvl.
	private boolean canShootThru;
	
	// Level at which block can be partially destroyed.
	// Equals 0 if N/A for block.
	private int destructableLvl; 
	
	
	public Block(int Type, int X, int Y) {
		type = Type;
		
		if (X < NUM_BLOCKS - 1 && X >= 0) {
			x = X;
		} else {
			System.out.print("Error in Block: x-coor not in bounds");
			x = 0;
		}
		
		if (Y < NUM_BLOCKS - 1 && Y < 0) {
			y = Y;
		} else {
			System.out.println("Error in Block: y-coor not in bounds");
			y = 0;
		}
		
		if (type == BLANK_BLOCK ||
				type == BUSH_BLOCK ||
				type == ICE_BLOCK) {
			canMoveThru = true;
		} else {
			canMoveThru = false;
		}
		
		if (type == BRICK_BLOCK ||
				type == STEEL_BLOCK ||
				type == BASE_BLOCK) {
			canShootThru = false;
		} else {
			canShootThru = true;
		}
		
		switch (type) {
			case BRICK_BLOCK:
			case BASE_BLOCK:
				destructableLvl = 1;
				break;
			case STEEL_BLOCK:
				destructableLvl = 2;
				break;
			default:
				destructableLvl = 0;	
		}	
	}
	
	public int getx() { return x; }
	public int gety() { return y; }
	public int getType() { return type; }
	public boolean getCanMoveThru() { return canMoveThru; }
	public boolean getCanShootThru() { return canShootThru; }
	public int getDestructableLvl() { return destructableLvl; }
}