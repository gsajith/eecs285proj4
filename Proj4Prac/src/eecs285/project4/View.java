package eecs285.project4;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.net.URISyntaxException;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class View extends JPanel{
	private int[][] map;
	private int tankDirection=1;
	private Image tankUp;
	private Image tankRight;
	private Image tankDown;
	private Image tankLeft;
	public static final int BLOCK_SIZE = 8;
	public static final int MAP_SIZE = 13*BLOCK_SIZE;
	public static final int PIXEL_SIZE = 4;
	
	
	public View() {
		tankUp = new ImageIcon("C:\\Users\\Chermine\\workspace\\eecs285proj4\\Proj4Prac\\bin\\eecs285\\project4\\tankImage\\tankDraft1.png").getImage();

		tankRight = new ImageIcon("C:\\Users\\Chermine\\workspace\\eecs285proj4\\Proj4Prac\\bin\\eecs285\\project4\\tankImage\\tankDraft2.png").getImage();

		tankDown = new ImageIcon("C:\\Users\\Chermine\\workspace\\eecs285proj4\\Proj4Prac\\bin\\eecs285\\project4\\tankImage\\tankDraft3.png").getImage();

		tankLeft = new ImageIcon("C:\\Users\\Chermine\\workspace\\eecs285proj4\\Proj4Prac\\bin\\eecs285\\project4\\tankImage\\tankDraft4.png").getImage();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	
	public void update(int[][] Map) {
		map = Map;
	}
	
	public void setTankDirection(int dir) {
		tankDirection = dir;
	}
	
	private void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		int x = 0;
		int y = 0;
		
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[i].length; j++) {
				if(map[j][i] != 0) {
					x = j;
					y = i;
				}
			}
		}
		g2d.setColor(new Color(136,0,21));
		g2d.fillRect(0, 0, 416,416);
		//turn into enum for direction
		switch(tankDirection) {
		case 1:
			g2d.drawImage(tankUp, x*(PIXEL_SIZE), y*(PIXEL_SIZE), null);
			break;
		case 2:
			g2d.drawImage(tankRight, x*(PIXEL_SIZE), y*(PIXEL_SIZE), null);
			break;
		case 3:
			g2d.drawImage(tankDown, x*(PIXEL_SIZE), y*(PIXEL_SIZE), null);
			break;
		case 4:
			g2d.drawImage(tankLeft, x*(PIXEL_SIZE), y*(PIXEL_SIZE), null);
			break;
			
		}
				
	}

}
