package eecs285.project4;

import java.awt.Image;

import javax.swing.ImageIcon;

import static eecs285.project4.Constants.*;

public class Boom {
	private int row;
	private int col;
	private Image image;
	private int duration;
	
	public Boom(int r, int c) {
		row = r;
		col = c;
		image = new ImageIcon(BASE_PATH + BLOCK_PATH + "Boom.png").getImage();
		duration = 20;
	}
	
	public void decrement() {
		duration--;
	}
	
	public int getRow() { return row; }
	public int getCol() { return col; }
	public Image getImage() { return image; }
	public int getDuration() { return duration; }
}
