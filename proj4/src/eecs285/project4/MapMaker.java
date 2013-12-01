package eecs285.project4;

import static eecs285.project4.Constants.*;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class MapMaker {
	/* class MAPMAKER	makes block maps for view.
	 * 
	 * Description: 
	 * 	MapMaker stores the blocks for each level map.
	 * 
	 */
	private ArrayList<Block> blocks;
	
	public MapMaker() {
		blocks = new ArrayList<Block>();
		blocks.add(new Block(BASE_BLOCK, 12*BLOCK_SIZE, 6*BLOCK_SIZE));
		blocks.add(new Block(BASE_BLOCK, 12*BLOCK_SIZE + MINI_BLOCK_SIZE, 6*BLOCK_SIZE));
		blocks.add(new Block(BASE_BLOCK, 12*BLOCK_SIZE, 6*BLOCK_SIZE + MINI_BLOCK_SIZE));
		blocks.add(new Block(BASE_BLOCK, 12*BLOCK_SIZE + MINI_BLOCK_SIZE, 6*BLOCK_SIZE + MINI_BLOCK_SIZE));
	}

	public void makeMap(int lvl) {
		switch (lvl) {
		case 1:
			blocks.add(new Block(BRICK_BLOCK, 6 * BLOCK_SIZE, 6 * BLOCK_SIZE));
			blocks.add(new Block(BRICK_BLOCK, 6 * BLOCK_SIZE + MINI_BLOCK_SIZE, 6 * BLOCK_SIZE));
			blocks.add(new Block(BRICK_BLOCK, 6 * BLOCK_SIZE, 6 * BLOCK_SIZE + MINI_BLOCK_SIZE));
			blocks.add(new Block(BRICK_BLOCK, 6 * BLOCK_SIZE + MINI_BLOCK_SIZE, 6 * BLOCK_SIZE + MINI_BLOCK_SIZE));
			break;
		}
	}
	
	public void addAllBlocks(Model model) {
		model.addBlocks(blocks);
	}
	
	public ArrayList<Block> getBlocks() {
		return blocks;
	}
}
