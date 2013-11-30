package eecs285.project4;

import static eecs285.project4.Constants.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ConcurrentModificationException;
import java.util.HashSet;

import javax.swing.ImageIcon;
import java.awt.Rectangle;
import java.awt.Shape;

import javax.swing.JPanel;

/**
 * View is responsible for drawing the map.
 */
public class View extends JPanel {
    private HashSet<Tank> tanks;
    private HashSet<Bullet> bullets;
    
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

    public void addBullet(Bullet bullet) {
    	bullets.add(bullet);
    }
    
    public void removeBullet(Bullet bullet) {
        	bullets.remove(bullet);
    }
	
	private void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;

		// draw map background
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, MAP_SIZE * PIXEL_SIZE, MAP_SIZE * PIXEL_SIZE);
        
		// then draw each tank
        for(Tank tank : tanks) {
            g2d.drawImage(tank.getImage(), tank.getColumn() * PIXEL_SIZE, tank.getRow() * PIXEL_SIZE, null);
        }
        
        // set up blocks
        g2d.setColor(Color.RED);
        g2d.fillRect(6 * PIXEL_SIZE * BLOCK_SIZE, 6 * PIXEL_SIZE * BLOCK_SIZE,
        						MINI_BLOCK_SIZE * PIXEL_SIZE, MINI_BLOCK_SIZE * PIXEL_SIZE);

        g2d.setColor(Color.BLUE);
        g2d.fillRect(6 * PIXEL_SIZE * BLOCK_SIZE + MINI_BLOCK_SIZE * PIXEL_SIZE, 6 * PIXEL_SIZE * BLOCK_SIZE,
        						MINI_BLOCK_SIZE * PIXEL_SIZE, MINI_BLOCK_SIZE * PIXEL_SIZE);

        g2d.setColor(Color.GREEN);
        g2d.fillRect(6 * PIXEL_SIZE * BLOCK_SIZE, 6 * PIXEL_SIZE * BLOCK_SIZE + MINI_BLOCK_SIZE * PIXEL_SIZE,
        						MINI_BLOCK_SIZE * PIXEL_SIZE, MINI_BLOCK_SIZE * PIXEL_SIZE);

        g2d.setColor(Color.YELLOW);
        g2d.fillRect(6 * PIXEL_SIZE * BLOCK_SIZE + MINI_BLOCK_SIZE * PIXEL_SIZE, 6 * PIXEL_SIZE * BLOCK_SIZE + MINI_BLOCK_SIZE * PIXEL_SIZE,
        						MINI_BLOCK_SIZE * PIXEL_SIZE, MINI_BLOCK_SIZE * PIXEL_SIZE);


        g2d.setColor(Color.WHITE);
    	try{
    		for(Bullet bullet : bullets) {
    			g2d.fillOval(bullet.column * PIXEL_SIZE, bullet.row * PIXEL_SIZE, BULLET_SIZE * PIXEL_SIZE, BULLET_SIZE * PIXEL_SIZE);
    		} 
    	} catch (ConcurrentModificationException e) {
    		//Something tried to modify bullets while it was being iterated over
    		//This happens rarely and shouldn't actually be a problem since we're just drawing here
    		System.out.println("Bullets modified");
        }
        
        
    }
}
