package eecs285.project4;

import static eecs285.project4.Constants.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.HashSet;
import javax.swing.JPanel;

/**
 * View is responsible for drawing the map.
 */
public class View extends JPanel {
    private HashSet<Tank> tanks;
<<<<<<< HEAD
    private HashSet<Bullet> bullets;
	private int[][] map;

=======
    
>>>>>>> 1a3b08e6f0d7b064d613e5b4f0c48d95e5bd52dd
    public View() {
        tanks = new HashSet<Tank>();
        bullets = new HashSet<Bullet>();
    }

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	
    public void addTank(Tank tank) {
        tanks.add(tank);
        add(tank);
    }
<<<<<<< HEAD

    public void addBullet(Bullet bullet) {
    	bullets.add(bullet);
    }
    
    public void removeBullet(Bullet bullet) {
    	bullets.remove(bullet);
    }
    
	public void update(int[][] map) {
		this.map = map;
	}
=======
>>>>>>> 1a3b08e6f0d7b064d613e5b4f0c48d95e5bd52dd
	
	private void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;

		// draw map background
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, MAP_SIZE * PIXEL_SIZE, MAP_SIZE * PIXEL_SIZE);
        
		// then draw each tank
        for(Tank tank : tanks) {
            g2d.drawImage(tank.getImage(), tank.getColumn() * PIXEL_SIZE, tank.getRow() * PIXEL_SIZE, null);
        }
        g2d.setColor(Color.WHITE);
        for(Bullet bullet : bullets) {
        	g2d.fillRect(bullet.column * PIXEL_SIZE, bullet.row * PIXEL_SIZE, 2 * PIXEL_SIZE, 2 * PIXEL_SIZE);
        }
    }
}
