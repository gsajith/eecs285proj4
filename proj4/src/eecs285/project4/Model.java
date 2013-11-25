package eecs285.project4;

import javax.swing.JFrame;


public class Model {
/* class MODEL  stores the current game map state
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
 */
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
}
