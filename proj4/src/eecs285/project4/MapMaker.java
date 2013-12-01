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
		makeBlock(BASE_BLOCK, 12, 6);
		makeBlock(BRICK_BLOCK, 12, 5);
		makeBlock(BRICK_BLOCK, 11, 5);
		makeBlock(BRICK_BLOCK, 11, 6);
		makeBlock(BRICK_BLOCK, 11, 7);
		makeBlock(BRICK_BLOCK, 12, 7);
/*		blocks.add(new Block(BASE_BLOCK, 12*BLOCK_SIZE, 6*BLOCK_SIZE));
		blocks.add(new Block(BASE_BLOCK, 12*BLOCK_SIZE + MINI_BLOCK_SIZE, 6*BLOCK_SIZE));
		blocks.add(new Block(BASE_BLOCK, 12*BLOCK_SIZE, 6*BLOCK_SIZE + MINI_BLOCK_SIZE));
		blocks.add(new Block(BASE_BLOCK, 12*BLOCK_SIZE + MINI_BLOCK_SIZE, 6*BLOCK_SIZE + MINI_BLOCK_SIZE)); */
	}

	public void makeMap(int lvl) {
		switch (lvl) {
		case 0:
			testMap();
			break;
		case 1:
			map1();
			break;
		}
	}
	
	public void testMap() {
		makeBlock(WATER_BLOCK, 6, 12);
		makeBlock(WATER_BLOCK, 6, 11);
		makeBlock(WATER_BLOCK, 6, 10);
		makeBlock(WATER_BLOCK, 6, 9);
		makeBlock(WATER_BLOCK, 6, 8);
		makeBlock(WATER_BLOCK, 6, 7);
		makeBlock(WATER_BLOCK, 6, 6);
		makeBlock(WATER_BLOCK, 6, 5);
		makeBlock(WATER_BLOCK, 6, 4);
		makeBlock(WATER_BLOCK, 6, 3);
		makeBlock(WATER_BLOCK, 6, 2);
		makeBlock(WATER_BLOCK, 6, 1);
		makeBlock(WATER_BLOCK, 6, 0);
		makeBlock(ICE_BLOCK, 12, 0);
		makeBlock(ICE_BLOCK, 11, 0);
		makeBlock(ICE_BLOCK, 10, 0);
		makeBlock(ICE_BLOCK, 9, 0);
		makeBlock(TREE_BLOCK, 12, 12);
		makeBlock(TREE_BLOCK, 11, 12);
		makeBlock(TREE_BLOCK, 10, 12);
		makeBlock(TREE_BLOCK, 9, 12);
		makeBlock(STEEL_BLOCK, 7, 10);
		makeBlock(STEEL_BLOCK, 7, 11);
		makeBlock(STEEL_BLOCK, 7, 2);
		makeBlock(STEEL_BLOCK, 7, 1);
	}
	
	public void map1() {
		makeBlock(BRICK_BLOCK, 1, 1);
		makeBlock(BRICK_BLOCK, 1, 3);
		makeBlock(BRICK_BLOCK, 1, 5);
		makeBlock(BRICK_BLOCK, 1, 7);
		makeBlock(BRICK_BLOCK, 1, 9);
		makeBlock(BRICK_BLOCK, 1, 11);
		makeBlock(BRICK_BLOCK, 2, 1);
		makeBlock(BRICK_BLOCK, 2, 3);
		makeBlock(BRICK_BLOCK, 2, 5);
		makeBlock(BRICK_BLOCK, 2, 7);
		makeBlock(BRICK_BLOCK, 2, 9);
		makeBlock(BRICK_BLOCK, 2, 11);
		makeBlock(BRICK_BLOCK, 3, 1);
		makeBlock(BRICK_BLOCK, 3, 3);
		makeBlock(BRICK_BLOCK, 3, 5);
		makeBlock(BRICK_BLOCK, 3, 7);
		makeBlock(BRICK_BLOCK, 3, 9);
		makeBlock(BRICK_BLOCK, 3, 11);
		makeBlock(BRICK_BLOCK, 4, 1);
		makeBlock(BRICK_BLOCK, 4, 3);
		makeBlock(BRICK_BLOCK, 4, 9);
		makeBlock(BRICK_BLOCK, 4, 11);
		makeBlock(BRICK_BLOCK, 5, 5);
		makeBlock(BRICK_BLOCK, 5, 7);
		makeBlock(STEEL_BLOCK, 6, 0);
		makeBlock(BRICK_BLOCK, 6, 2);
		makeBlock(BRICK_BLOCK, 6, 3);
		makeBlock(BRICK_BLOCK, 6, 9);
		makeBlock(BRICK_BLOCK, 6, 10);
		makeBlock(STEEL_BLOCK, 6, 12);
		makeBlock(BRICK_BLOCK, 7, 5);
		makeBlock(BRICK_BLOCK, 7, 6);
		makeBlock(BRICK_BLOCK, 7, 7);
		makeBlock(BRICK_BLOCK, 8, 1);
		makeBlock(BRICK_BLOCK, 8, 3);
		makeBlock(BRICK_BLOCK, 8, 5);
		makeBlock(BRICK_BLOCK, 8, 7);
		makeBlock(BRICK_BLOCK, 8, 9);
		makeBlock(BRICK_BLOCK, 8, 11);
		makeBlock(BRICK_BLOCK, 9, 1);
		makeBlock(BRICK_BLOCK, 9, 3);
		makeBlock(BRICK_BLOCK, 9, 5);
		makeBlock(BRICK_BLOCK, 9, 7);
		makeBlock(BRICK_BLOCK, 9, 9);
		makeBlock(BRICK_BLOCK, 9, 11);
		makeBlock(BRICK_BLOCK, 10, 1);
		makeBlock(BRICK_BLOCK, 10, 3);
		makeBlock(BRICK_BLOCK, 10, 9);
		makeBlock(BRICK_BLOCK, 10, 11);
		makeBlock(BRICK_BLOCK, 11, 1);
		makeBlock(BRICK_BLOCK, 11, 3);
		makeBlock(BRICK_BLOCK, 11, 9);
		makeBlock(BRICK_BLOCK, 11, 11);
	}
	
	private void makeBlock(int type, int x, int y) {
		blocks.add(new Block(type, x * BLOCK_SIZE, y * BLOCK_SIZE));
		blocks.add(new Block(type, x * BLOCK_SIZE + MINI_BLOCK_SIZE, y * BLOCK_SIZE));
		blocks.add(new Block(type, x * BLOCK_SIZE, y * BLOCK_SIZE + MINI_BLOCK_SIZE));
		blocks.add(new Block(type, x * BLOCK_SIZE + MINI_BLOCK_SIZE, y * BLOCK_SIZE + MINI_BLOCK_SIZE));
	}
	
	public void addAllBlocks(Model model) {
		model.addBlocks(blocks);
	}
	
	public ArrayList<Block> getBlocks() {
		return blocks;
	}
}
