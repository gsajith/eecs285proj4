package eecs285.project4;

import static eecs285.project4.Constants.*;

public class Bullet {
	protected int bulletStrength;
	protected int bulletSpeed;
	protected int bulletDirection;
	protected int row;
	protected int column;
	
	public Bullet(final int bulletStrength, final int bulletSpeed, final int bulletDirection, 
					final int row, final int column) {
		this.bulletStrength = bulletStrength;
		this.bulletSpeed = bulletSpeed;
		this.bulletDirection = bulletDirection;
		this.row = row;
		this.column = column;
		fixLocation(this.bulletDirection, this.row, this.column);
	}
	
	private void fixLocation(final int bulletDirection, int row,  int column) {
		switch(bulletDirection) {
		case UP:
			this.row = row - 2;
			this.column = column + 3;
			break;
		case DOWN:
			this.row = row + 8;
			this.column = column + 3;
			break;
		case LEFT:
			this.row = row + 3;
			this.column = column - 2;
			break;
		case RIGHT:
			this.row = row + 3;
			this.column = column + 8;
			break;
		}
	}
	
	public void move() {
		switch(bulletDirection) {
		case UP:
			row -= bulletSpeed;
			break;
		case DOWN:
			row += bulletSpeed;
			break;
		case LEFT:
			column -= bulletSpeed;
			break;
		case RIGHT:
			column += bulletSpeed;
			break;
		}
	}
}
