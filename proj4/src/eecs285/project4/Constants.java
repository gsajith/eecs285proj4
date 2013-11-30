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
    public static final int PLAYER_TANK = 1;
    public static final int AI_TANK = 2;
}
