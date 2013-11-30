package eecs285.project4;

import static eecs285.project4.Constants.*;

import java.util.ArrayList;
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
     * Function to add blocks to the model and register them on the map.
     */
    public void addBlocks(ArrayList<Block> b) {
    	for (int a = 0; a < b.size(); a++) {
    		if (map[b.get(a).getx()][b.get(a).gety()] == 0 &&
    				b.get(a).getType() != BLANK_BLOCK) {
    			for (int i = 0; i < MINI_BLOCK_SIZE; i++) {
    				for (int j = 0; j < MINI_BLOCK_SIZE; j++) {  
    					map[b.get(a).getx() + i][b.get(a).gety() + j] = b.get(a).getType();
    				}
    			}
    		}
    	}
    }
    /**
     * Let each AI Tank update themselves.
     */
    public void go() {
        for(AITank tank : AITanks) {
           // tank.go();
        }
    }
    
    public boolean notifyLocation(BulletThread bThread) {
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
        System.out.println("Tank loc: " + row + ", " + column);
        int number = tank.getNumber();
        switch(direction) {
            case UP:
                // the tank can safely move up if its y-coordinate
                // is greater than 0
                if(row > 0 && clearPathUp(map, row - 1, column)) {
                    map[row][column] = 0;
                    map[row - 1][column] = number;
                    view.repaint();
                    return true;
                }
                break;
            case DOWN:
                if(row < (NUM_BLOCKS - 1) * BLOCK_SIZE && clearPathDown(map, row + 1, column)) {
                    map[row][column] = 0;
                    map[row + 1][column] = number;
                    view.repaint();
                    return true;
                }
                break;
            case LEFT:
                if(column > 0 && clearPathLeft(map, row, column - 1)) {
                    map[row][column] = 0;
                    map[row][column - 1] = number;
                    view.repaint();
                    return true;
                }
                break;
            case RIGHT:
                if(column < (NUM_BLOCKS - 1) * BLOCK_SIZE && clearPathRight(map, row, column + 1)) {
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
    private boolean clearPathUp(int map[][], int x, int y) {
    	for (int i = 0; i < BLOCK_SIZE; i++) {
    		if (!(map[x][y+i] >= BLANK_BLOCK && map[x][y+i] <= ICE_BLOCK)) {
    			return false;
    		}
    	}
    	return true;
    }
    
    private boolean clearPathDown(int map[][], int x, int y) {
    	for (int i = 0; i < BLOCK_SIZE; i++) {
    		if (!(map[x+BLOCK_SIZE-1][y+i] >= BLANK_BLOCK && map[x+BLOCK_SIZE-1][y+i] <= ICE_BLOCK)) {
    			return false;
    		}
    	}
    	return true;
    }
    
    private boolean clearPathLeft(int map[][], int x, int y) {
    	for (int i = 0; i < BLOCK_SIZE; i++) {
    		if (!(map[x+i][y] >= BLANK_BLOCK && map[x+i][y] <= ICE_BLOCK)) {
    			return false;
    		}
    	}
    	return true;
    }
    
    private boolean clearPathRight(int map[][], int x, int y) {
    	for (int i = 0; i < BLOCK_SIZE; i++) {
    		if (!(map[x+i][y+BLOCK_SIZE-1] >= BLANK_BLOCK && map[x+i][y+BLOCK_SIZE-1] <= ICE_BLOCK)) {
    			return false;
    		}
    	}
    	return true;
    }
    
}
