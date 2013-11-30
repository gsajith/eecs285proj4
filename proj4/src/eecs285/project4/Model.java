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
        map[0][0] = 1;
    }

    /**
     * Attach the specified view to the model 
     * and notify the view about the locations of all tanks
     */
    public void attach(View view) {
        this.view = view;
        for(Tank tank : AITanks) {
            view.addTank(tank);
        }
        view.addTank(playerTank);
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
                map[row][column] = 2;
                view.update(map);
                view.repaint();
                return true;
	        } else {
	        	bThread.tank.canShoot = true;
	        	map[row + speed][column] = 0;
            	view.removeBullet(bThread.bullet);
	        	view.update(map);
	        	view.repaint();
	        	bThread.stop();
	        	return false;
	        }
        case DOWN:
            if(row < (NUM_BLOCKS * BLOCK_SIZE) - 1) {
                map[row - speed][column] = 0;
                map[row][column] = 2;
                view.update(map);
                view.repaint();
                return true;
	        } else {
	        	bThread.tank.canShoot = true;
	        	map[row - speed][column] = 0;
            	view.removeBullet(bThread.bullet);
	        	view.update(map);
	        	view.repaint();
	        	bThread.stop();
	        	return false;
	        }
        case LEFT:
            if(column >= 0) {
                map[row][column + speed] = 0;
                map[row][column] = 2;
                view.update(map);
                view.repaint();
                return true;
            } else {
	        	bThread.tank.canShoot = true;
            	map[row][column + speed] = 0;
            	view.removeBullet(bThread.bullet);
            	view.update(map);
            	view.repaint();
	        	bThread.stop();
            	return false;
            }
        case RIGHT:
            if(column < (NUM_BLOCKS * BLOCK_SIZE) - 1) {
                map[row][column - speed] = 0;
                map[row][column] = 2;
                view.update(map);
                view.repaint();
                return true;
            } else {
	        	bThread.tank.canShoot = true;
            	map[row][column - speed] = 0;
            	view.removeBullet(bThread.bullet);
            	view.update(map);
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
     * If the move is valid notify the view about the location change of a tank.
     */
    public boolean notifyLocation(Tank tank, final int direction) {
        int row = tank.getRow();
        int column = tank.getColumn();
        switch(direction) {
            case UP:
                // the tank can safely move up if its y-coordinate
                // is greater than 0
                if(row > 0) {
                    map[row][column] = 0;
                    map[row - 1][column] = 1;
                    view.update(map);
                    view.repaint();
                    return true;
                }
                break;
            case DOWN:
                if(row < (NUM_BLOCKS - 1) * BLOCK_SIZE) {
                    map[row][column] = 0;
                    map[row + 1][column] = 1;
                    view.update(map);
                    view.repaint();
                    return true;
                }
                break;
            case LEFT:
                if(column > 0) {
                    map[row][column] = 0;
                    map[row][column - 1] = 1;
                    view.update(map);
                    view.repaint();
                    return true;
                }
                break;
            case RIGHT:
                if(column < (NUM_BLOCKS - 1) * BLOCK_SIZE) {
                    map[row][column] = 0;
                    map[row][column + 1] = 1;
                    view.update(map);
                    view.repaint();
                    return true;
                }
                break;
            default:
                assert(false);
        }
        return false;
    }
}
