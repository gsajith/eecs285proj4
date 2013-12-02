package eecs285.project4;

import static eecs285.project4.Constants.*;

import java.util.ConcurrentModificationException;
import java.util.HashSet;

/**
 * Model houses the information of all tanks and bullets.
 * In addition, Model notifies the View how the map should be drawn.
 * It will keep the View updated when tanks move around, fire, "die", etc.
 */
public class Model {
    private int[][] map;
    private int[][] originalMap;
    private HashSet<AITank> AITanks;
    // the number of AI tanks currently created
    private int numAITanks;
    private PlayerTank playerTank;
    private View view;
    private int enemyCounter;
    private int respawnCounter;
    private boolean gameOver = false;

    /**
     * Create a bunch of AI tanks and one player tank.
     */
    public Model() {
    	respawnCounter = 0;
        enemyCounter = 0;
    	map = new int[MAP_SIZE][MAP_SIZE];
        originalMap = new int[MAP_SIZE][MAP_SIZE];
        AITanks = new HashSet<AITank>();
        for(int i = 0; i < 3; ++i) {
            AITanks.add(new AITank(WEAK_HEALTH, INITIAL_STRENGTH, TANK_SPEED, 0, i * (MAP_SIZE/2 - MINI_BLOCK_SIZE), this));
            enemyCounter++;
        }
        playerTank = new PlayerTank(ENHANCED_HEALTH, INITIAL_STRENGTH, TANK_SPEED, this);
    }

    /**
     * Attach the specified view to the model 
     * and notify the view about the locations of all tanks
     */
    public void attach(final View view) {
        this.view = view;
        for(Tank tank : AITanks) {
            this.view.addTank(tank);
        }
        this.view.addTank(playerTank);
        placeTank(playerTank.getRow(), playerTank.getColumn(), playerTank.getType());        

    }

    /**
     * Function to add blocks and register them on the map.
     */
    public void addBlocks(final HashSet<Block> blocks) {
        for(Block block : blocks) {
            if (map[block.getx()][block.gety()] == 0 && block.getType() != BLANK_BLOCK) {
                for (int i = 0; i < MINI_BLOCK_SIZE; i++) {
                    for (int j = 0; j < MINI_BLOCK_SIZE; j++) {  
                        map[block.getx() + i][block.gety() + j] = block.getType();
                    }
                }
            }
        }

        for(int i = 0; i < MAP_SIZE; i++) {
            for(int j = 0; j < MAP_SIZE; j++) {
                originalMap[i][j] = map[i][j];
            }
        }
    }

    /**
     * Let each AI Tank update themselves.
     */
    public void go() {
    	try{
	        for(AITank tank : AITanks) {
	           tank.go();
	        }
    	} catch (ConcurrentModificationException e) {
	    	System.out.println("Tanks modified");
	    }
        if (AITanks.size() <= MAX_AI_TANK_ON_MAP - 1 && enemyCounter < MAX_AI_TANK_NUM) {	
        	respawnCounter++;
        	if (respawnCounter > 60) {
        		AITank aiTank = new AITank(WEAK_HEALTH, INITIAL_STRENGTH, TANK_SPEED, 0, (enemyCounter % 3) * (MAP_SIZE/2 - MINI_BLOCK_SIZE), this);
        		enemyCounter++;
        		AITanks.add(aiTank);
        		view.addTank(aiTank);
            	placeTank(0, (enemyCounter % 3) * (MAP_SIZE/2 - MINI_BLOCK_SIZE), AI_REG_TANK);
            	respawnCounter = 0;
        	}
        }
        if(AITanks.size()==0) {
        	gameOver = true;
        }
        view.repaint();
    }    
    
    public boolean isGameOver() {
    	return gameOver;
    }

    /**
     * Determine whether a bullet can move to a specific location.
     * Return true if the move is valid, and update the map to reflect the new location.
     * Return false if the move is not valid.
     * If the move is valid, notify the view about the location change of a bullet.
     */
    public synchronized boolean notifyLocation(final BulletThread bThread) {
        int row = bThread.bullet.row;
        int column = bThread.bullet.column;
        int direction = bThread.bullet.bulletDirection;
        int speed = bThread.bullet.bulletSpeed;

        // Add bullet to view even if it already exists
        // Will just update this bullet instance in HashSet<Bullet> in View.
        view.addBullet(bThread.bullet); 
        

        switch(direction) {
            case UP:
                clearBullet(row+speed, column);
                if(row >= 0 && clearPath(row, column, UP, BULLET_SIZE, true)) {
                    return moveBullet(row, column);
                } else {
                    return endBullet(bThread);
                }
            case DOWN:
                clearBullet(row-speed, column);
                if(row < (NUM_BLOCKS * BLOCK_SIZE) - 1 && clearPath(row, column, DOWN, BULLET_SIZE, true)) {
                    return moveBullet(row, column);
                } else {
                    return endBullet(bThread);
                }
            case LEFT:
                clearBullet(row, column+speed);
                if(column >= 0 && clearPath(row, column, LEFT, BULLET_SIZE, true)) {
                    return moveBullet(row, column);
                } else {
                    return endBullet(bThread);
                }
            case RIGHT:
                clearBullet(row, column-speed);
                if(column < (NUM_BLOCKS * BLOCK_SIZE) - 1 && clearPath(row, column, RIGHT, BULLET_SIZE, true)) {
                    return moveBullet(row, column);
                } else {
                    return endBullet(bThread);
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
    public synchronized boolean notifyLocation(final Tank tank, final int direction) {
        
        int row = tank.getRow(), column = tank.getColumn();
        int type = tank.getType();
        switch(direction) {
            case UP:
                // the tank can safely move up if its y-coordinate
                // is greater than 0
                if(row > 0 && clearPath(row - 1, column, UP, BLOCK_SIZE, false)) {
                    clearTank(row, column);
                    placeTank(row - 1, column, type);
                    view.repaint();
                    return true;
                }
                break;
            case DOWN:
                if(row < (NUM_BLOCKS - 1) * BLOCK_SIZE && clearPath(row + 1, column, DOWN, BLOCK_SIZE, false)) {
                    clearTank(row, column);
                    placeTank(row + 1, column, type);
                    view.repaint();
                    return true;
                }
                break;
            case LEFT:
                if(column > 0 && clearPath(row, column - 1, LEFT, BLOCK_SIZE, false)) {
                    clearTank(row, column);
                    placeTank(row, column - 1, type);
                    view.repaint();
                    return true;
                }
                break;
            case RIGHT:
                if(column < (NUM_BLOCKS - 1) * BLOCK_SIZE && clearPath(row, column + 1, RIGHT, BLOCK_SIZE, false)) {
                    clearTank(row, column);
                    placeTank(row, column + 1, type);
                    view.repaint();
                    return true;
                }
                break;
            default:
                assert(false);
        }
        view.repaint();
        return false;
    }
    
    /*
     * Collision checking function which takes in coordinates you're moving to, direction
     * that you're moving (determines which adjacent row/col you need to check), and 
     * your size (e.g. 8 for tank, 2 for bullet -> determines how wide to check).
     */
    private boolean clearPath(final int row, final int column, final int direction, 
            final int checkSize, final boolean isBullet) {
        int rowMult = 0, colMult = 0;
        int rowOffset = 0, colOffset = 0;
        switch (direction) {
            case UP:
                colMult = 1;
                break;
            case DOWN:
                colMult = 1;
                rowOffset = checkSize - 1;
                break;
            case LEFT:
                rowMult = 1;
                break;
            case RIGHT:
                rowMult = 1;
                colOffset = checkSize - 1;
                break;
            default:
                assert(false);
        }
        
        for(int i = 0; i < checkSize; i++) {
            if(!(map[row+(i*rowMult) + rowOffset][column+(i*colMult) + colOffset] >= BLANK_BLOCK &&
                        map[row+(i*rowMult) + rowOffset][column+(i*colMult) + colOffset] <= (ICE_BLOCK + (isBullet?1:0)))) {
                return false;	
            }
        }

        
        return true;
    }

    /*
     * Places a tank block on map at (row,col).
     */
    private void placeTank(final int row, final int column, final int tankType) {
        for(int i = 0; i < BLOCK_SIZE; i++) {
            for(int j = 0; j < BLOCK_SIZE; j++) {
                map[row+i][column+j] = tankType;
            }
        }
    }

    /*
     * Removes a tank block from (row,col), sets this block on the map to 0.
     */
    private void clearTank(final int row, final int column) {
        for(int i = 0; i < BLOCK_SIZE; i++) {
            for(int j = 0; j < BLOCK_SIZE; j++) {    			
                map[row+i][column+j] = originalMap[row+i][column+j];
            }
        }
    }

    /*
     * Moves to bullet on map to row, col.
     * Assumes it has already been removed from it's previous location.
     */
    private boolean moveBullet(final int row, final int column) {
        for(int i = 0; i < BULLET_SIZE; i++) {
            for(int j = 0; j < BULLET_SIZE; j++) {
                map[row+i][column+j] = BULLET_BLOCK;
            }
        }
        view.repaint();
        return true;
    }

    /*
     * Clears bullet from map at (row,col).
     */
    private void clearBullet(final int row, final int column) {
        for(int i = 0; i < BULLET_SIZE; i++) {
            for(int j = 0; j < BULLET_SIZE; j++) {
                if(row >= 0 && column >= 0 && row+i < MAP_SIZE && column+j < MAP_SIZE)  
                    if(originalMap[row+i][column+j] >= BRICK_BLOCK && originalMap[row+i][column+j] <= BASE_BLOCK) {
                        //If bullet is traveling through a destructible block, it should be destroying it
                        map[row+i][column+j] = 0;
                    } else {
                        map[row+i][column+j] = originalMap[row+i][column+j];
                    }
            }
        }
    }
    
    private void clearBrick(final int row, final int column) {
        for(int i = 0; i < MINI_BLOCK_SIZE; i++) {
            for(int j = 0; j < MINI_BLOCK_SIZE; j++) {
                map[row+i][column+j] = 0;
                originalMap[row+i][column+j] = 0;
            }
        }
    }

    /*
     * Removes this BulletThread's bullet from view.
     */
	private synchronized boolean endBullet(final BulletThread bThread) {
		int row = bThread.bullet.row;
		int column = bThread.bullet.column;
		for (int i = 0; i < BULLET_SIZE; i++) {
			for (int j = 0; j < BULLET_SIZE; j++) {
				if (row + i >= 0 && row + i < (NUM_BLOCKS * BLOCK_SIZE) - 1
						&& column + j >= 0
						&& column + j < (NUM_BLOCKS * BLOCK_SIZE) - 1) {
					switch (map[row + i][column + j]) {
					case BASE_BLOCK:
						for (Block base : view.getMapMaker().getBase()) {
							if (row + i >= base.getx()
									&& row + i < base.getx() + MINI_BLOCK_SIZE
									&& column + j >= base.gety()
									&& column + j < base.gety()
											+ MINI_BLOCK_SIZE) {
								view.getMapMaker().removeBlock(base);
								view.removeBullet(bThread.bullet);
								clearBrick(base.getx(), base.gety());
								gameOver = true;
								return false;
							}
						}						
						break;
					case BULLET_BLOCK:
						for (Bullet bullet : view.getBullets()) {
							if (bullet != bThread.bullet
									&& bullet.getType() != bThread.bullet
											.getType() && row + i >= bullet.row
									&& row + i < bullet.row + BULLET_SIZE
									&& column + j >= bullet.column
									&& column + j < bullet.column + BULLET_SIZE) {
								view.removeBullet(bullet);
								view.removeBullet(bThread.bullet);
								return false;
							}
						}
						break;
					case BRICK_BLOCK:
						if (bThread.bullet.bulletStrength < INITIAL_STRENGTH) {
							view.removeBullet(bThread.bullet);
							return false;
						}
						for (Block brick : view.getMapMaker().getBricks()) {
							if (row + i >= brick.getx()
									&& row + i < brick.getx() + MINI_BLOCK_SIZE
									&& column + j >= brick.gety()
									&& column + j < brick.gety()
											+ MINI_BLOCK_SIZE) {
								view.getMapMaker().removeBlock(brick);
								view.removeBullet(bThread.bullet);
								clearBrick(brick.getx(), brick.gety());
								return false;
							}
						}
						break;
					case STEEL_BLOCK:
						if (bThread.bullet.bulletStrength < ENHANCED_STRENGTH) {
							view.removeBullet(bThread.bullet);
							return false;
						}
						for (Block steelBlock : view.getMapMaker()
								.getSteelBlocks()) {
							if (row + i >= steelBlock.getx()
									&& row + i < steelBlock.getx()
											+ MINI_BLOCK_SIZE
									&& column + j >= steelBlock.gety()
									&& column + j < steelBlock.gety()
											+ MINI_BLOCK_SIZE) {
								view.getMapMaker().removeBlock(steelBlock);
								view.removeBullet(bThread.bullet);
								clearBrick(steelBlock.getx(), steelBlock.gety());
								return false;
							}
						}
						break;
					case PLAYER1_TANK:
						if (bThread.bullet.getType() == PLAYER1_TANK) {
							view.removeBullet(bThread.bullet);
							return false;
						}
						if(row + i >= playerTank.getRow() 
								&& row + i < playerTank.getRow() + BLOCK_SIZE
								&& column + j >= playerTank.getColumn()
								&& column + j < playerTank.getColumn() + BLOCK_SIZE) {
							view.removeBullet(bThread.bullet);
							playerTank.decrementHealth();
							if(playerTank.getHealth() == 0) {
								clearTank(playerTank.getRow(), playerTank.getColumn());
								playerTank.resetLocation();
						        placeTank(playerTank.getRow(), playerTank.getColumn(), playerTank.getType());
						        playerTank.healthPoint = ENHANCED_HEALTH;
							}
						    return false;
						}
						break;
					case AI_REG_TANK:
						if (bThread.bullet.getType() == AI_REG_TANK) {
							view.removeBullet(bThread.bullet);
							return false;
						}
						for (Tank aiTank : AITanks) {
							if (row + i >= aiTank.getRow()
									&& row + i < aiTank.getRow() + BLOCK_SIZE
									&& column + j >= aiTank.getColumn()
									&& column + j < aiTank.getColumn() + BLOCK_SIZE) {
								AITanks.remove(aiTank);
								view.removeBullet(bThread.bullet);
								view.removeTank(aiTank);
								clearTank(aiTank.getRow(), aiTank.getColumn());
								return false;
							}
						}
						break;
					default:
						assert (false);
					}
				}
			}
		}
		view.removeBullet(bThread.bullet);
		view.repaint();
		return false;
    }
}
