package eecs285.project4;

import static eecs285.project4.Constants.*;

import java.util.HashSet;

/**
 * Model houses the information of all tanks and bullets.
 * In addition, Model notifies the View how the map should be drawn.
 * It will keep the View updated when tanks move around, fire, "die", etc.
 */

public class Model {

    private HashSet<Tank> AITanks;
    private Tank playerTank;
    private int[][] map;
    private View view;

    /**
     * Create a bunch of AI tanks and one player tank.
     */
    public Model() {
        AITanks = new HashSet<Tank>();
        playerTank = new PlayerTank(5, 5, 5, this);
        map = new int[MAP_SIZE][MAP_SIZE];
        map[0][0] = PLAYER1_TANK;
    }

    /**
     * Attach the specified view to the model 
     * and notify the view about the locations of all tanks
     */
    public void attach(View view) {
        this.view = view;
        for(Tank tank : AITanks) {
            this.view.addTank(tank);
        }
        this.view.addTank(playerTank);
    }

    /**
     * Tentative function to add blocks to the map.
     */
    public void addBlock(Block b) {
    	if (map[b.getx()][b.gety()] == 0 &&
    		b.getType() != BLANK_BLOCK &&
    		b.getType() != BASE_BLOCK) {    		
    		map[b.getx()][b.gety()] = b.getType();
    	}
    }
    
    /**
     * Determine whether a tank can move to a specific location.
     * Return true if the move is valid, and update the map to reflect the new locatio.
     * Return false if the move is not valid.
     * If the move is valid notify the view about the location change of a tank.
     */
    public boolean notifyLocation(Tank tank, final int direction) {
        int row = tank.getRow();
        int column = tank.getColumn();
        switch(direction) {
            case UP:
                // the tank can safely move up if its y-coordinate
                // is greater than 0
                if(row > 0 && clearPath(map[row - 1][column])) {
                    map[row][column] = 0;
                    map[row - 1][column] = 1;
                    view.repaint();
                    return true;
                }
                break;
            case DOWN:
                if(row < (NUM_BLOCKS - 1) * BLOCK_SIZE && clearPath(map[row + 1][column])) {
                    map[row][column] = 0;
                    map[row + 1][column] = 1;
                    view.repaint();
                    return true;
                }
                break;
            case LEFT:
                if(column > 0 && clearPath(map[row][column - 1])) {
                    map[row][column] = 0;
                    map[row][column - 1] = 1;
                    view.repaint();
                    return true;
                }
                break;
            case RIGHT:
                if(column < (NUM_BLOCKS - 1) * BLOCK_SIZE && clearPath(map[row][column + 1])) {
                    map[row][column] = 0;
                    map[row][column + 1] = 1;
                    view.repaint();
                    return true;
                }
                break;
            default:
                assert(false);
        }
        return false;
    }

    // private function that takes in the number for the
    // block at a map coordinate and says if there is a
    // block that a tank could move through
    private boolean clearPath(int x) {
    	return x >= BLANK_BLOCK && x <= ICE_BLOCK;
    }
}