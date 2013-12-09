package eecs285.project4;

import static eecs285.project4.Constants.*;

import java.awt.Image;
import java.applet.Applet;
import java.applet.AudioClip;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ConcurrentModificationException;
import java.util.HashSet;

import javax.swing.ImageIcon;

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
    private PlayerTank playerTank1;
    private PlayerTank playerTank2;
    View view;
    private int enemyCounter;
    private int respawnCounter;
    private int gameOver = 0;
    private int livesLeft1;
    private int livesLeft2;
    private int numPlayers = 1;
    private static AudioClip explosion;
    private static AudioClip theme;

    /**
     * Sets number of lives for players 1 and 2.
     * Creates MAX_AI_TANK_ON_MAP number of AI tanks at three spawn locations
     * across the top of the map.
     */
    public Model(int livesLeft1, int livesLeft2) {
        try {
            explosion = Applet.newAudioClip(new URL("file:" + BASE_PATH + SOUND_PATH + "exploding.wav"));
            theme = Applet.newAudioClip(new URL("file: " + BASE_PATH + SOUND_PATH + "Victors.mp3"));
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        SoundThread themeThread = new SoundThread(theme);
        themeThread.start();
        
    	this.livesLeft1 = livesLeft1;
    	this.livesLeft2 = livesLeft2;
    	respawnCounter = 0;
        enemyCounter = 0;
    	map = new int[MAP_SIZE][MAP_SIZE];
        originalMap = new int[MAP_SIZE][MAP_SIZE];
        AITanks = new HashSet<AITank>();
        for(int i = 0; i < MAX_AI_TANK_ON_MAP; ++i) {
            AITanks.add(new AITank(WEAK_HEALTH, INITIAL_STRENGTH, TANK_SPEED, 0, i * (MAP_SIZE/2 - MINI_BLOCK_SIZE), this));
            enemyCounter++;
        }
    }
    
    /**
     * Sets the number of players (single or multiplayer mode)
     * @param numP - 1 if singleplayer, 2 if multiplayer
     */
    public void setNumPlayers(int numP) {
        numPlayers = numP;
        if(numPlayers < 1 || numPlayers > 2) {
            numPlayers = 1;
        }
        playerTank1 = new PlayerTank(ENHANCED_HEALTH, INITIAL_STRENGTH, TANK_SPEED, this, true);
        if(numPlayers==2) {
            playerTank2 = new PlayerTank(ENHANCED_HEALTH, INITIAL_STRENGTH, TANK_SPEED, this, false);
        }
        
    }

    /**
     * Adds armor upgrade to one or both player tanks
     * @param armor - Number of temporary health to gain
     */
    public void addArmor(int armor) {
        playerTank1.addArmor(armor);
        if(numPlayers==2) {
            playerTank2.addArmor(armor);
        }
    }
    
    /**
     * Add firepower upgrade to one or both player tanks
     * @param firePower - Permanent strength to gain
     */
    public void addFirepower(int firePower) {
        playerTank1.addFirepower(firePower);
        if(numPlayers==2) {
            playerTank2.addFirepower(firePower);
        }
    }
    
    /**
     * Attach the specified view to the model 
     * and notify the view about the locations of all tanks
     * Creates and places PlayerTanks at their start locations
     */
    public void attach(final View view) {
        this.view = view;
        for(Tank tank : AITanks) {
            this.view.addTank(tank);
        }
        if(livesLeft1 > 0) {
            this.view.addTank(playerTank1);
            placeTank(playerTank1.getRow(), playerTank1.getColumn(), playerTank1.getType());
        }           
        if(numPlayers==2 && livesLeft2 > 0) {
            this.view.addTank(playerTank2);
            placeTank(playerTank2.getRow(), playerTank2.getColumn(), playerTank2.getType());
        }

    }

    /**
     * Function to add blocks and register them on the map
     * @param blocks - Set of blocks to add to this map for this level
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
     * Update locations and shooting conditions of both player tanks.
     * Let each AI Tank update themselves.
     * Respawns new AI tanks after 3 seconds if less than MAX_AI_TANK_ON_MAP on screen.
     * Checks if all MAX_AI_TANK_NUM AI Tanks have been defeated, if so goes to next level.
     * 
     * When a new tank spawns, map is temporarily cleared of all AI tanks to prevent
     * minor bug that appeared during testing.
     */
    public void go() {
        playerTank1.moveAndShoot();
        if(numPlayers==2) {
            playerTank2.moveAndShoot();
        }
    	try{
    	    AITank[] AITankArray = new AITank[AITanks.size()];
    	    AITanks.toArray(AITankArray);
	        for(AITank tank : AITankArray) {
	           tank.go();
	        }
    	} catch (ConcurrentModificationException e) {
	    	System.out.println("AITanks modified");
	    }
    	
        if (AITanks.size() <= MAX_AI_TANK_ON_MAP - 1 && enemyCounter < MAX_AI_TANK_NUM) {	
        	respawnCounter++;
        	if (respawnCounter > 60) {
        		AITank aiTank = new AITank(WEAK_HEALTH, INITIAL_STRENGTH, TANK_SPEED, 0, (enemyCounter % 3) * (MAP_SIZE/2 - MINI_BLOCK_SIZE), this);
        		enemyCounter++;
        		AITanks.add(aiTank);
        		view.addTank(aiTank);
            	respawnCounter = 0;
            	/*
            	 * Whenever a new AI tank spawns, first clear
            	 * all AI tank spaces on the map to prevent a minor
            	 * bug which would leave AI tanks that were dead
            	 * on the map.
            	 */
            	for(int i = 0; i < MAP_SIZE; i++) {
                    for(int j = 0; j < MAP_SIZE; j++) {
                        if(map[i][j]==AI_REG_TANK) {
                            map[i][j] = 0;
                        }
                    }
                }
        	}
        }
        if(AITanks.size()==0 && enemyCounter==MAX_AI_TANK_NUM) {
        	gameOver = 1;
        }
        view.repaint();
    }    
    
    /**
     * Checks whether the game is over (regardless of win or loss)
     * @return gameOver boolean indicating game over status
     */
    public int isGameOver() {
    	return gameOver;
    }
    
    /**
     * Checks number of lives player1 has left
     * @return livesLeft1 player1 lives
     */
    public int livesLeft1() {
    	return livesLeft1;
    }
    
    /**
     * Checks number of lives player2 has left
     * @return livesLeft2 player2 lives
     */
    public int livesLeft2() {
        return livesLeft2;
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
                    return true;
                }
                break;
            case DOWN:
                if(row < (NUM_BLOCKS - 1) * BLOCK_SIZE && clearPath(row + 1, column, DOWN, BLOCK_SIZE, false)) {
                    clearTank(row, column);
                    placeTank(row + 1, column, type);
                    return true;
                }
                break;
            case LEFT:
                if(column > 0 && clearPath(row, column - 1, LEFT, BLOCK_SIZE, false)) {
                    clearTank(row, column);
                    placeTank(row, column - 1, type);
                    return true;
                }
                break;
            case RIGHT:
                if(column < (NUM_BLOCKS - 1) * BLOCK_SIZE && clearPath(row, column + 1, RIGHT, BLOCK_SIZE, false)) {
                    clearTank(row, column);
                    placeTank(row, column + 1, type);
                    return true;
                }
                break;
            default:
                assert(false);
        }
        return false;
    }
    
    /**
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

    /**
     * Places a tank block on map at (row,col).
     */
    private void placeTank(final int row, final int column, final int tankType) {
        for(int i = 0; i < BLOCK_SIZE; i++) {
            for(int j = 0; j < BLOCK_SIZE; j++) {
                map[row+i][column+j] = tankType;
            }
        }
    }

    /**
     * Removes a tank block from (row,col), sets this block on the map to what
     * it originally was before the tank stepped onto it.
     */
    private void clearTank(final int row, final int column) {
        for(int i = 0; i < BLOCK_SIZE; i++) {
            for(int j = 0; j < BLOCK_SIZE; j++) {    			
                map[row+i][column+j] = originalMap[row+i][column+j];
            }
        }
    }

    /**
     * Moves bullet on map to row, col and repaints map.
     * Assumes it has already been removed from it's previous location.
     */
    private boolean moveBullet(final int row, final int column) {
        for(int i = 0; i < BULLET_SIZE; i++) {
            for(int j = 0; j < BULLET_SIZE; j++) {
                map[row+i][column+j] = BULLET_BLOCK;
            }
        }
        //view.repaint();
        return true;
    }

    /**
     * Clears bullet from map at (row,col).
     * In its place, it puts either 0 (if it was originally a blank space
     * or if the bullet destructed at a destructible block) or whatever 
     * was on the map at that space originally.
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
    
    /**
     * Clears block from map at (row,col)
     * Updates originalMap as well so that this block
     * doesn't reappear in the future.
     */
    private void clearBlock(final int row, final int column) {
        for(int i = 0; i < MINI_BLOCK_SIZE; i++) {
            for(int j = 0; j < MINI_BLOCK_SIZE; j++) {
                map[row+i][column+j] = 0;
                originalMap[row+i][column+j] = 0;
            }
        }
    }

    /**
     * Removes this BulletThread's bullet from view.
     * Checks whether the any of the 2x2 bullet hit any destructible objects.
     * If it hit a base block:  
     *      For each base block, check if the bullet shares a space with 
     *      that base block, and if so, removes that base block, makes a boom,
     *      and removes the bullet.  If all base blocks have been removed,
     *      game is over (loss).
     * If it hit another bullet:
     *      Checks through all the bullets, and sees if any bullet other
     *      than this one shares a space with this bullet.  Removes both
     *      bullets if true.
     * If it hit brick/steel block:
     *      Checks through all brick/steel blocks, and sees if bullet shares
     *      a space with any of these.  If so, and if strength is sufficient,
     *      removes the block and the bullet.
     * If player tank:
     *      Checks that the bullet wasn't fired by another player tank, 
     *      then decrements the health of the tank that the bullet hit. If
     *      that tank's health reaches zero and it has lives left, it respawns
     *      at it's start location.  if it reaches zero and it has no lives left,
     *      it gets removed from View.  Once no player tank lives remain, game is over.
     * If AI tank:  
     *      Loops through all AI tanks and sees if this bullet
     *      shares a space with any of them.  If so, removes that AI tank 
     *      and this bullet.
     *      
     * Regardless of whether or not any destructions occurred, this bullet
     * is removed and the view is repainted because the bullet is confirmed
     * to have hit something at this point.
     */
	private synchronized boolean endBullet(final BulletThread bThread) {
		int row = bThread.bullet.row;
		int column = bThread.bullet.column;
		for (int i = 0; i < BULLET_SIZE; i++) {  //For each of the 4 spaces the 2x2 bullet occupies
			for (int j = 0; j < BULLET_SIZE; j++) {
				if (row + i >= 0 && row + i < (NUM_BLOCKS * BLOCK_SIZE) - 1  
						&& column + j >= 0
						&& column + j < (NUM_BLOCKS * BLOCK_SIZE) - 1) { //If the space is not off the map[][]
					switch (map[row + i][column + j]) {
					case BASE_BLOCK:
					    for (Block base : view.getMapMaker().getBase()) {   //Loop through all base blocks
							if (row + i >= base.getx()
									&& row + i < base.getx() + MINI_BLOCK_SIZE
									&& column + j >= base.gety()
									&& column + j < base.gety()
											+ MINI_BLOCK_SIZE) {  //If this bullet space is within this base block's space
								view.getMapMaker().removeBlock(base);  //Remove base block from view
								view.addBoom(new Boom(base.getx(), base.gety()));  //Add a boom at this location
								view.removeBullet(bThread.bullet);  //remove the bullet from view
								clearBlock(base.getx(), base.gety());  //Clear the block from map[][]
								view.getMapMaker().getBase().remove(base);  //Remove the base block from list of base blocks
								if(view.getMapMaker().getBase().size() == 0) gameOver = 2; //Check for gameover condition
								return false; //End this bulletThread
							}
						}						
						break;
						
					case BULLET_BLOCK:  //Similar logic to base block for all following cases
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
								view.addBoom(new Boom(brick.getx(), brick.gety()));
								view.removeBullet(bThread.bullet);
								clearBlock(brick.getx(), brick.gety());
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
								view.addBoom(new Boom(steelBlock.getx(), steelBlock.gety()));
								view.removeBullet(bThread.bullet);
								clearBlock(steelBlock.getx(), steelBlock.gety());
								return false;
							}
						}
						break;
						
					case PLAYER1_TANK:
                        if (bThread.bullet.getType() == PLAYER1_TANK || bThread.bullet.getType() == PLAYER2_TANK) {
                            view.removeBullet(bThread.bullet);
                            return false;
                        }
                        if(!hitTank(bThread, row, column, i, j, playerTank1)) return false;
                        break;
                        
					case PLAYER2_TANK:
						if (bThread.bullet.getType() == PLAYER1_TANK || bThread.bullet.getType() == PLAYER2_TANK) {
							view.removeBullet(bThread.bullet);
							return false;
						}
						if(!hitTank(bThread, row, column, i, j, playerTank2)) return false;
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
								view.addBoom(new Boom(aiTank.getRow(), aiTank.getColumn()));
								view.removeBullet(bThread.bullet);
								view.removeTank(aiTank);
								clearTank(aiTank.getRow(), aiTank.getColumn());
								AITanks.remove(aiTank);
								SoundThread sThread = new SoundThread(explosion);
				                sThread.start();  
				                try {
                                    sThread.join();
                                } catch (InterruptedException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
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
		//view.repaint();
		return false;
    }

	/**
	 * Function called when a playerTank is hit by a bullet
	 * Decrements this player tank's health, checks whether it has 
	 * died and respawns it if it has lives left.  Otherwise,
	 * checks for gameover condition when no lives left (loss).
	 */
    private boolean hitTank(final BulletThread bThread, int row, int column,
            int i, int j, Tank playerTank) {
        if(row + i >= playerTank.getRow() 
        		&& row + i < playerTank.getRow() + BLOCK_SIZE
        		&& column + j >= playerTank.getColumn()
        		&& column + j < playerTank.getColumn() + BLOCK_SIZE) {
        	view.addBoom(new Boom(playerTank.getRow(), playerTank.getColumn()));
        	view.removeBullet(bThread.bullet);
        	playerTank.decrementHealth();
        	if(playerTank.getHealth() == 0) {
        		if(playerTank == playerTank1) {
        		    livesLeft1--;
        		    if(livesLeft1 == 0) {
        		        view.removeTank(playerTank1);
        		    }
        		} else {
        		    livesLeft2--;
        		    if(livesLeft2 == 0) {
        		        view.removeTank(playerTank2);
        		    }
        		}
        		clearTank(playerTank.getRow(), playerTank.getColumn());
        		playerTank.resetLocation();
        		if((playerTank == playerTank1 && livesLeft1 > 0) || (playerTank == playerTank2 && livesLeft2 > 0)) {
        			placeTank(playerTank.getRow(), playerTank.getColumn(), playerTank.getType());                    
        		}
                playerTank.healthPoint = ENHANCED_HEALTH;						        
                if((livesLeft1 <= 0 && numPlayers == 1) || (livesLeft1 <= 0 && livesLeft2 <= 0)) gameOver = 2;                
        	}
            return false;
        }
        return true;
    }
}


