package eecs285.project4;

import java.util.HashSet;

/**
 * Model houses the information of all tanks and bullets.
 * In addition, Model notifies the View how the map should be drawn.
 * It will keep the View updated when tanks move around, fire, "die", etc.
 */
public class Model {

    private HastSet<Tank> AITanks;
    private Tank playerTank;

    /**
     * Create a bunch of AI tanks and one player tank.
     */
    public Model(final int playerHealth, final int playerStrength, final int playerSpeed) {
        playerTank = new PlayerTank(playerHealth, playerStrength, playerSpeed);
    }

    /**
     * Notify the view about the location change of a tank.
     */
    public void notifyLocation(Tank tank) {
        
    }

/* class MODEL stores the current game map state
 *
 * Syntax:
 *     Model obj = new Model();
 *
 * Description:
 * 
 * 
 * Input:
 * 
 * 
 * Output:
 * 
 * 
 *
 *
	private int[][] map;
	private View window;
	
	public Model(View win) {
		map = new int[View.MAP_SIZE][View.MAP_SIZE];
		map[0][0] = 1;
		window = win;
	}
	
	public int[][] getMap() {
		return map;
	}

	public void move(String a) {
		if (a.equals("left")) {
            window.setTankDirection(View.tankDirection.LEFT);
			for(int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[i].length; j++) {
					if(map[i][j] != 0 && i != 0) {
						map[i][j] = 0;
						map[i-1][j] = 1;
						return;
					}
				}
			}
		} else if (a.equals("right")) {
            window.setTankDirection(View.tankDirection.RIGHT);
			for(int i = 0; i < map.length; i++) {
				for(int j = 0; j < map[i].length; j++) {
					if(map[i][j] != 0 && i !=  map[i].length - 1 - View.BLOCK_SIZE) {
						map[i][j] = 0;
						map[i+1][j] = 1;
						return;
					}
				}
			}
		} else if (a.equals("up")) {
            window.setTankDirection(View.tankDirection.UP);
			for(int i = 0; i < map.length; i++) {
				for(int j = 0; j < map[i].length; j++) {
					if(map[i][j] != 0 && j != 0) {
						map[i][j] = 0;
						map[i][j-1] = 1;
						return;
					}
				}
			}
		} else if (a.equals("down")) {
            window.setTankDirection(View.tankDirection.DOWN);
			for(int i = 0; i < map.length; i++) {
				for(int j = 0; j < map[i].length; j++) {
					if(map[i][j] != 0 && j !=  map[i].length - 1 - View.BLOCK_SIZE) {
						map[i][j] = 0;
						map[i][j+1] = 1;
						return;
					}
				}
			}
		}
	}
*/
}
