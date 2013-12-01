package eecs285.project4;

import static eecs285.project4.Constants.*;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.HashSet;

public class MapMaker {
    /* class MAPMAKER	makes block maps for view.
     * 
     * Description: 
     * 	MapMaker stores the blocks for each level map.
     * 
     */
    private Block base;
    private HashSet<Block> blocks;
    private HashSet<Block> bricks;
    private HashSet<Block> steelBlocks;

    public MapMaker() {
        blocks = new HashSet<Block>();
        bricks = new HashSet<Block>();
        steelBlocks = new HashSet<Block>();
        makeBlock(BASE_BLOCK, 12, 6);
        makeBlock(BRICK_BLOCK, 12, 5);
        makeBlock(BRICK_BLOCK, 11, 5);
        makeBlock(BRICK_BLOCK, 11, 6);
        makeBlock(BRICK_BLOCK, 11, 7);
        makeBlock(BRICK_BLOCK, 12, 7);
    }

    public void makeMap(final int lvl) {
        switch (lvl) {
            case 0:
                testMap();
                break;
            case 1:
                map1();
                break;
            case 2:
            	map2();
            	break;
            case 3:
            	map3();
            	break;
            case 4:
            	map4();
            	break;
            case 5:
            	map5();
            	break;
        }
    }

    private void testMap() {
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

    private void map1() {
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

    private void map2() {
    	makeBlock(WATER_BLOCK, 1, 1);
    	makeBlock(WATER_BLOCK, 1, 2);
    	makeBlock(WATER_BLOCK, 1, 3);
    	makeBlock(TREE_BLOCK, 1, 4);
    	makeBlock(TREE_BLOCK, 1, 5);
    	makeBlock(TREE_BLOCK, 1, 6);
    	makeBlock(TREE_BLOCK, 1, 7);
    	makeBlock(TREE_BLOCK, 1, 8);
    	makeBlock(WATER_BLOCK, 1, 9);
    	makeBlock(WATER_BLOCK, 1, 10);
    	makeBlock(WATER_BLOCK, 1, 11);
    	makeBlock(STEEL_BLOCK, 2, 5);
    	makeBlock(STEEL_BLOCK, 2, 6);
    	makeBlock(STEEL_BLOCK, 2, 7);
    	makeBlock(BRICK_BLOCK, 3, 1);
    	makeBlock(TREE_BLOCK, 3, 2);
    	makeBlock(BRICK_BLOCK, 3, 3);
    	makeBlock(STEEL_BLOCK, 3, 5);
    	makeBlock(STEEL_BLOCK, 3, 6);
    	makeBlock(STEEL_BLOCK, 3, 7);
    	makeBlock(BRICK_BLOCK, 3, 9);
    	makeBlock(TREE_BLOCK, 3, 10);
    	makeBlock(BRICK_BLOCK, 3, 11);
    	makeBlock(BRICK_BLOCK, 4, 1);
    	makeBlock(TREE_BLOCK, 4, 2);
    	makeBlock(BRICK_BLOCK, 4, 3);
    	makeBlock(BRICK_BLOCK, 4, 9);
    	makeBlock(TREE_BLOCK, 4, 10);
    	makeBlock(BRICK_BLOCK, 4, 11);
    	makeBlock(TREE_BLOCK, 5, 2);
    	makeBlock(BRICK_BLOCK, 5, 3);
    	makeBlock(WATER_BLOCK, 5, 4);
    	makeBlock(WATER_BLOCK, 5, 8);
    	makeBlock(BRICK_BLOCK, 5, 9);
    	makeBlock(TREE_BLOCK, 5, 10);
    	makeBlock(TREE_BLOCK, 6, 2);
    	makeBlock(BRICK_BLOCK, 6, 3);
    	makeBlock(BRICK_BLOCK, 6, 4);
    	makeBlock(WATER_BLOCK, 6, 5);
    	makeBlock(WATER_BLOCK, 6, 7);
    	makeBlock(BRICK_BLOCK, 6, 8);
    	makeBlock(BRICK_BLOCK, 6, 9);
    	makeBlock(TREE_BLOCK, 6, 10);
    	makeBlock(TREE_BLOCK, 7, 2);
    	makeBlock(BRICK_BLOCK, 7, 3);
    	makeBlock(BRICK_BLOCK, 7, 4);
    	makeBlock(BRICK_BLOCK, 7, 5);
    	makeBlock(WATER_BLOCK, 7, 6);
    	makeBlock(BRICK_BLOCK, 7, 7);
    	makeBlock(BRICK_BLOCK, 7, 8);
    	makeBlock(BRICK_BLOCK, 7, 9);
    	makeBlock(TREE_BLOCK, 7, 10);
    	makeBlock(TREE_BLOCK, 8, 2);
    	makeBlock(TREE_BLOCK, 8, 3);
    	makeBlock(BRICK_BLOCK, 8, 5);
    	makeBlock(BRICK_BLOCK, 8, 6);
    	makeBlock(BRICK_BLOCK, 8, 7);
    	makeBlock(TREE_BLOCK, 8, 9);
    	makeBlock(TREE_BLOCK, 8, 10);
    	makeBlock(TREE_BLOCK, 9, 2);
    	makeBlock(TREE_BLOCK, 9, 3);
    	makeBlock(STEEL_BLOCK, 9, 6);
    	makeBlock(TREE_BLOCK, 9, 9);
    	makeBlock(TREE_BLOCK, 9, 10);
    	makeBlock(BRICK_BLOCK, 10, 1);
    	makeBlock(BRICK_BLOCK, 10, 2);
    	makeBlock(BRICK_BLOCK, 10, 3);
    	makeBlock(BRICK_BLOCK, 10, 9);
    	makeBlock(BRICK_BLOCK, 10, 10);
    	makeBlock(BRICK_BLOCK, 10, 11);
    	makeBlock(BRICK_BLOCK, 11, 1);
    	makeBlock(BRICK_BLOCK, 11, 2);
    	makeBlock(BRICK_BLOCK, 11, 3);
    	makeBlock(BRICK_BLOCK, 11, 9);
    	makeBlock(BRICK_BLOCK, 11, 10);
    	makeBlock(BRICK_BLOCK, 11, 11);
    }
    
    private void map3() {
    	makeBlock(STEEL_BLOCK, 1, 1);
    	makeBlock(STEEL_BLOCK, 1, 2);
    	makeBlock(BRICK_BLOCK, 1, 5);
    	makeBlock(BRICK_BLOCK, 1, 6);
    	makeBlock(BRICK_BLOCK, 1, 7);
    	makeBlock(STEEL_BLOCK, 1, 10);
    	makeBlock(STEEL_BLOCK, 1, 11);
    	makeBlock(STEEL_BLOCK, 2, 1);
    	makeBlock(TREE_BLOCK, 2, 4);
    	makeBlock(BRICK_BLOCK, 2, 5);
    	makeBlock(STEEL_BLOCK, 2, 6);
    	makeBlock(BRICK_BLOCK, 2, 7);
    	makeBlock(TREE_BLOCK, 2, 8);
    	makeBlock(STEEL_BLOCK, 2, 11);
    	
    	makeBlock(BRICK_BLOCK, 3, 3);
    	makeBlock(TREE_BLOCK, 3, 4);
    	makeBlock(STEEL_BLOCK, 3, 5);
    	makeBlock(STEEL_BLOCK, 3, 7);
    	makeBlock(TREE_BLOCK, 3, 8);
    	makeBlock(BRICK_BLOCK, 3, 9);
    	
    	makeBlock(BRICK_BLOCK, 4, 0);
    	makeBlock(TREE_BLOCK, 4, 1);
    	makeBlock(BRICK_BLOCK, 4, 3);
    	makeBlock(TREE_BLOCK, 4, 4);
    	makeBlock(TREE_BLOCK, 4, 8);
    	makeBlock(BRICK_BLOCK, 4, 9);
    	makeBlock(TREE_BLOCK, 4, 11);
    	makeBlock(BRICK_BLOCK, 4, 12);
    	
    	makeBlock(BRICK_BLOCK, 5, 0);
    	makeBlock(TREE_BLOCK, 5, 1);
    	makeBlock(TREE_BLOCK, 5, 3);
    	makeBlock(TREE_BLOCK, 5, 4);
    	makeBlock(TREE_BLOCK, 5, 8);
    	makeBlock(TREE_BLOCK, 5, 9);
    	makeBlock(TREE_BLOCK, 5, 11);
    	makeBlock(BRICK_BLOCK, 5, 12);
    	
    	makeBlock(BRICK_BLOCK, 6, 0);
    	makeBlock(BRICK_BLOCK, 6, 1);
    	makeBlock(BRICK_BLOCK, 6, 3);
    	makeBlock(BRICK_BLOCK, 6, 4);
    	makeBlock(BRICK_BLOCK, 6, 8);
    	makeBlock(BRICK_BLOCK, 6, 9);
    	makeBlock(BRICK_BLOCK, 6, 11);
    	makeBlock(BRICK_BLOCK, 6, 12);
    	
    	makeBlock(BRICK_BLOCK, 7, 3);
    	makeBlock(TREE_BLOCK, 7, 4);
    	makeBlock(STEEL_BLOCK, 7, 5);
    	makeBlock(STEEL_BLOCK, 7, 7);
    	makeBlock(TREE_BLOCK, 7, 8);
    	makeBlock(BRICK_BLOCK, 7, 9);
    	
    	makeBlock(TREE_BLOCK, 8, 4);
    	makeBlock(BRICK_BLOCK, 8, 5);
    	makeBlock(STEEL_BLOCK, 8, 6);
    	makeBlock(BRICK_BLOCK, 8, 7);
    	makeBlock(TREE_BLOCK, 8, 8);
    	
    	makeBlock(BRICK_BLOCK, 9, 5);
    	makeBlock(BRICK_BLOCK, 9, 6);
    	makeBlock(BRICK_BLOCK, 9, 7);
    	
    	makeBlock(WATER_BLOCK, 10, 1);
    	makeBlock(WATER_BLOCK, 10, 2);
    	makeBlock(WATER_BLOCK, 10, 10);
    	makeBlock(WATER_BLOCK, 10, 11);
    	
    	makeBlock(STEEL_BLOCK, 11, 1);
    	makeBlock(WATER_BLOCK, 11, 2);
    	makeBlock(WATER_BLOCK, 11, 10);
    	makeBlock(STEEL_BLOCK, 11, 11);
    }
    
    private void map4() {
    	makeBlock(BRICK_BLOCK, 1, 3);
    	makeBlock(TREE_BLOCK, 1, 4);
    	makeBlock(BRICK_BLOCK, 1, 5);
    	makeBlock(TREE_BLOCK, 1, 6);
    	makeBlock(BRICK_BLOCK, 1, 7);
    	makeBlock(BRICK_BLOCK, 1, 8);
    	makeBlock(BRICK_BLOCK, 1, 11);
    	
    	makeBlock(STEEL_BLOCK, 2, 2);
    	makeBlock(BRICK_BLOCK, 2, 9);
    	makeBlock(BRICK_BLOCK, 2, 11);
    	
    	makeBlock(STEEL_BLOCK, 3, 2);
    	makeBlock(BRICK_BLOCK, 3, 3);

    	makeBlock(BRICK_BLOCK, 4, 0);
    	makeBlock(BRICK_BLOCK, 4, 2);
    	makeBlock(BRICK_BLOCK, 4, 3);
    	makeBlock(BRICK_BLOCK, 4, 4);
    	makeBlock(BRICK_BLOCK, 4, 6);
    	makeBlock(STEEL_BLOCK, 4, 11);
    	
    	makeBlock(BRICK_BLOCK, 5, 0);
    	makeBlock(BRICK_BLOCK, 5, 3);
    	makeBlock(BRICK_BLOCK, 5, 4);
    	makeBlock(TREE_BLOCK, 5, 5);
    	makeBlock(BRICK_BLOCK, 5, 6);
    	makeBlock(TREE_BLOCK, 5, 7);
    	makeBlock(BRICK_BLOCK, 5, 8);
    	makeBlock(STEEL_BLOCK, 5, 11);
    	
    	makeBlock(BRICK_BLOCK, 6, 0);
    	makeBlock(BRICK_BLOCK, 6, 9);
    	makeBlock(STEEL_BLOCK, 6, 11);
    	
    	makeBlock(BRICK_BLOCK, 7, 7);
    	makeBlock(TREE_BLOCK, 7, 9);
    	
    	makeBlock(BRICK_BLOCK, 8, 2);
    	makeBlock(BRICK_BLOCK, 8, 5);
    	makeBlock(BRICK_BLOCK, 8, 7);
    	makeBlock(TREE_BLOCK, 8, 9);
    	
    	makeBlock(BRICK_BLOCK, 9, 3);
    	makeBlock(TREE_BLOCK, 9, 4);
    	makeBlock(BRICK_BLOCK, 9, 5);
    	makeBlock(TREE_BLOCK, 9, 6);
    	makeBlock(BRICK_BLOCK, 9, 7);
    	makeBlock(TREE_BLOCK, 9, 8);
    	
    	makeBlock(TREE_BLOCK, 10, 0);
    	
    	makeBlock(BRICK_BLOCK, 11, 0);
    	makeBlock(TREE_BLOCK, 11, 1);
    	makeBlock(TREE_BLOCK, 11, 11);
    	
    	makeBlock(BRICK_BLOCK, 12, 0);
    	makeBlock(BRICK_BLOCK, 12, 1);
    	makeBlock(BRICK_BLOCK, 12, 2);
    	makeBlock(BRICK_BLOCK, 12, 10);
    	makeBlock(BRICK_BLOCK, 12, 11);
    	makeBlock(BRICK_BLOCK, 12, 12);
    }
    
    
    private void map5() {
    	makeBlock(WATER_BLOCK, 1, 1);
    	makeBlock(WATER_BLOCK, 1, 2);
    	makeBlock(WATER_BLOCK, 1, 3);
    	makeBlock(WATER_BLOCK, 1, 4);
    	makeBlock(WATER_BLOCK, 1, 5);
    	makeBlock(WATER_BLOCK, 1, 6);
    	makeBlock(WATER_BLOCK, 1, 7);
    	makeBlock(WATER_BLOCK, 1, 8);
    	makeBlock(WATER_BLOCK, 1, 9);
    	makeBlock(WATER_BLOCK, 1, 10);
    	
    	makeBlock(WATER_BLOCK, 2, 0);
    	makeBlock(WATER_BLOCK, 2, 1);
    	makeBlock(WATER_BLOCK, 2, 10);
    	
    	makeBlock(TREE_BLOCK, 3, 3);
    	makeBlock(TREE_BLOCK, 3, 4);
    	makeBlock(TREE_BLOCK, 3, 5);
    	makeBlock(TREE_BLOCK, 3, 6);
    	makeBlock(WATER_BLOCK, 3, 9);
    	makeBlock(WATER_BLOCK, 3, 10);
    	
    	makeBlock(TREE_BLOCK, 4, 1);
    	makeBlock(TREE_BLOCK, 4, 2);
    	makeBlock(TREE_BLOCK, 4, 3);
    	makeBlock(TREE_BLOCK, 4, 4);
    	makeBlock(WATER_BLOCK, 4, 7);
    	makeBlock(WATER_BLOCK, 4, 8);
    	makeBlock(WATER_BLOCK, 4, 9);
    	
    	makeBlock(TREE_BLOCK, 5, 0);
    	makeBlock(TREE_BLOCK, 5, 1);
    	makeBlock(TREE_BLOCK, 5, 2);
    	makeBlock(TREE_BLOCK, 5, 3);
    	makeBlock(WATER_BLOCK, 5, 6);
    	makeBlock(WATER_BLOCK, 5, 7);
    	
    	makeBlock(TREE_BLOCK, 6, 1);
    	makeBlock(WATER_BLOCK, 6, 6);
    	makeBlock(BRICK_BLOCK, 6, 8);
    	makeBlock(BRICK_BLOCK, 6, 9);
    	
    	makeBlock(WATER_BLOCK, 7, 1);
    	makeBlock(WATER_BLOCK, 7, 2);
    	makeBlock(WATER_BLOCK, 7, 3);
    	makeBlock(WATER_BLOCK, 7, 5);
    	makeBlock(WATER_BLOCK, 7, 6);
    	makeBlock(BRICK_BLOCK, 7, 8);
    	makeBlock(TREE_BLOCK, 7, 9);
    	makeBlock(BRICK_BLOCK, 7, 10);
    	makeBlock(BRICK_BLOCK, 7, 11);
    	
    	makeBlock(WATER_BLOCK, 8, 0);
    	makeBlock(WATER_BLOCK, 8, 1);
    	makeBlock(WATER_BLOCK, 8, 3);
    	makeBlock(WATER_BLOCK, 8, 4);
    	makeBlock(WATER_BLOCK, 8, 5);
    	makeBlock(BRICK_BLOCK, 8, 9);
    	makeBlock(TREE_BLOCK, 8, 10);
    	makeBlock(BRICK_BLOCK, 8, 11);
    	
    	makeBlock(BRICK_BLOCK, 9, 3);
    	makeBlock(BRICK_BLOCK, 9, 9);
    	makeBlock(BRICK_BLOCK, 9, 10);
    	makeBlock(TREE_BLOCK, 9, 11);
    	makeBlock(BRICK_BLOCK, 9, 12);
    	
    	makeBlock(STEEL_BLOCK, 10, 0);
    	makeBlock(STEEL_BLOCK, 10, 1);
    	makeBlock(BRICK_BLOCK, 10, 2);
    	makeBlock(BRICK_BLOCK, 10, 3);
    	makeBlock(BRICK_BLOCK, 10, 11);
    	makeBlock(BRICK_BLOCK, 10, 12);
    	
    	makeBlock(STEEL_BLOCK, 11, 0);
    	makeBlock(STEEL_BLOCK, 11, 1);
    	makeBlock(BRICK_BLOCK, 11, 2);
    	
    	makeBlock(STEEL_BLOCK, 12, 0);
    	makeBlock(STEEL_BLOCK, 12, 1);
    	makeBlock(BRICK_BLOCK, 12, 10);
    	makeBlock(BRICK_BLOCK, 12, 11);
    	makeBlock(BRICK_BLOCK, 12, 12);
    }
    private void makeBlock(final int type, final int x, final int y) {
        Block block1 = new Block(type, x * BLOCK_SIZE, y * BLOCK_SIZE);
        Block block2 = new Block(type, x * BLOCK_SIZE + MINI_BLOCK_SIZE, y * BLOCK_SIZE);
        Block block3 = new Block(type, x * BLOCK_SIZE, y * BLOCK_SIZE + MINI_BLOCK_SIZE);
        Block block4 = new Block(type, x * BLOCK_SIZE + MINI_BLOCK_SIZE, y * BLOCK_SIZE + MINI_BLOCK_SIZE);
        blocks.add(block1);
        blocks.add(block2);
        blocks.add(block3);
        blocks.add(block4);
        switch(type) {
            case BRICK_BLOCK:
                bricks.add(block1);
                bricks.add(block2);
                bricks.add(block3);
                bricks.add(block4);
                break;
            case STEEL_BLOCK:
                steelBlocks.add(block1);
                steelBlocks.add(block2);
                steelBlocks.add(block3);
                steelBlocks.add(block4);
                break;
            default:
                break;
        }
    }

    private void removeBlock(final Block block) {
        blocks.remove(block);
        switch(block.getType()) {
            case BRICK_BLOCK:
                bricks.remove(block);
                break;
            case STEEL_BLOCK:
                steelBlocks.remove(block);
                break;
            default:
                break;
        }
    }

    public void addAllBlocks(Model model) {
        model.addBlocks(blocks);
    }

    public HashSet<Block> getBlocks() {
        return blocks;
    }
}
