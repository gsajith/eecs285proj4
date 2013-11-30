package eecs285.project4;

import static eecs285.project4.Constants.*;

import java.util.HashSet;

/**
 * Model houses the information of all tanks and bullets.
 * In addition, Model notifies the View how the map should be drawn.
 * It will keep the View updated when tanks move around, fire, "die", etc.
 */

public class Model {
    private HashSet<AITank> AITanks;
    private Tank playerTank;
    private int[][] map;
    private View view;

    /**
     * Create a bunch of AI tanks and one player tank.
     */
    public Model() {
        AITanks = new HashSet<AITank>();
        for(int i = 0; i < 3; ++i) {
            AITanks.add(new AITank(5, 5, 5, this));
        }
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
     * Let each AI Tank update themselves.
     */
    public void go() {
        for(AITank tank : AITanks) {
            tank.go();
        }
    }
    
    public synchronized boolean notifyLocation(BulletThread bThread) {
    	int row = bThread.bullet.row;
    	int column = bThread.bullet.column;
    	int direction = bThread.bullet.bulletDirection;
    	int speed = bThread.bullet.bulletSpeed;
    	view.addBullet(bThread.bullet);
    	switch(direction) {
    	case UP:
    		 // the tank can safely move up if its y-coordinate
            // is greater than 0
            if(row >= 0) {
                map[row + speed][column] = 0;
                map[row][column] = BULLET_BLOCK;
                view.repaint();
                return true;
	        } else {
	        	bThread.tank.canShoot = true;
	        	map[row + speed][column] = 0;
            	view.removeBullet(bThread.bullet);
	        	view.repaint();
	        	bThread.stop();
	        	return false;
	        }
        case DOWN:
            if(row < (NUM_BLOCKS * BLOCK_SIZE) - 1) {
                map[row - speed][column] = 0;
                map[row][column] = BULLET_BLOCK;
                view.repaint();
                return true;
	        } else {
	        	bThread.tank.canShoot = true;
	        	map[row - speed][column] = 0;
            	view.removeBullet(bThread.bullet);
	        	view.repaint();
	        	bThread.stop();
	        	return false;
	        }
        case LEFT:
            if(column >= 0) {
                map[row][column + speed] = 0;
                map[row][column] = BULLET_BLOCK;
                view.repaint();
                return true;
            } else {
	        	bThread.tank.canShoot = true;
            	map[row][column + speed] = 0;
            	view.removeBullet(bThread.bullet);
            	view.repaint();
	        	bThread.stop();
            	return false;
            }
        case RIGHT:
            if(column < (NUM_BLOCKS * BLOCK_SIZE) - 1) {
                map[row][column - speed] = 0;
                map[row][column] = BULLET_BLOCK;
                view.repaint();
                return true;
            } else {
	        	bThread.tank.canShoot = true;
            	map[row][column - speed] = 0;
            	view.removeBullet(bThread.bullet);
            	view.repaint();
	        	bThread.stop();
            	return false;
            }
        default:
            assert(false);
    }
    	return false;
    }

    /**
     * Determine whether a tank can move to a specific location.
     * Return true if the move is valid, and update the map to reflect the new location.
     * Return false if the move is not valid.
     * If the move is valid, notify the view about the location change of a tank.
     */
    public synchronized boolean notifyLocation(Tank tank, final int direction) {
        int row = tank.getRow(), column = tank.getColumn();
        int number = tank.getNumber();
        switch(direction) {
            case UP:
                // the tank can safely move up if its y-coordinate
                // is greater than 0
                if(row > 0 && clearPath(map[row - 1][column])) {
                    map[row][column] = 0;
                    map[row - 1][column] = number;
                    view.repaint();
                    return true;
                }
                break;
            case DOWN:
                if(row < (NUM_BLOCKS - 1) * BLOCK_SIZE && clearPath(map[row + 1][column])) {
                    map[row][column] = 0;
                    map[row + 1][column] = number;
                    view.repaint();
                    return true;
                }
                break;
            case LEFT:
                if(column > 0 && clearPath(map[row][column - 1])) {
                    map[row][column] = 0;
                    map[row][column - 1] = number;
                    view.repaint();
                    return true;
                }
                break;
            case RIGHT:
                if(column < (NUM_BLOCKS - 1) * BLOCK_SIZE && clearPath(map[row][column + 1])) {
                    map[row][column] = 0;
                    map[row][column + 1] = number;
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
