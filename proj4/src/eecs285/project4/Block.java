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
	
	private BlockType type;
	private int x;
	private int y;
	private boolean canMoveThru;
	private boolean canShootThru;
	
	// Level at which block can be partially destroyed.
	// Equals 0 if N/A for block.
	private int destructableLvl; 
	
	
	public Block(BlockType Type, int X, int Y) {
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
		
		if (type == BlockType.BLANK &&
			type == BlockType.BUSH &&
			type == BlockType.ICE) {
			canMoveThru = true;
		} else {
			canMoveThru = false;
		}
	}
	
	public boolean setParams() {
		if (type == BlockType.BLANK)
		
		return true;
	}
	
	public int getx() { return x; }
	public int gety() { return y; }
	public BlockType getType() { return type; }
}