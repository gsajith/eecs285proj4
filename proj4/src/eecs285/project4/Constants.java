package eecs285.project4;

public class Constants {
	public static final int BLOCK_SIZE = 8;
    public static final int NUM_BLOCKS = 13;
	public static final int MAP_SIZE = NUM_BLOCKS * BLOCK_SIZE;
	public static final int PIXEL_SIZE = 4;
    
    // tank directions
    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;

	// frame size we're adding to 416 x 416 to account for size of border
	public static final int SIDE_BORDER = 2;
	public static final int TOP_BORDER = 24;
	
// numbers used to represent each kind of object on the map
	
	// numbers for types of blocks.
	public static final int BLANK_BLOCK = 0;
	public static final int BUSH_BLOCK = 1;
	public static final int ICE_BLOCK = 2;
	public static final int WATER_BLOCK = 3;
	public static final int BRICK_BLOCK = 4;
	public static final int STEEL_BLOCK = 5;
	public static final int BASE_BLOCK = 6;
	
	// tanks as represented by int on the 2D int map
	public static final int PLAYER1_TANK = 7;
	public static final int PLAYER2_TANK = 8;
	public static final int PLAYER3_TANK = 9;
	public static final int PLAYER4_TANK = 10;
	public static final int AI_REG_TANK = 11;
	public static final int AI_FAST_TANK = 12;
	public static final int AI_REG2_TANK = 13;
	public static final int AI_FAST2_TANK = 14;
	
}
