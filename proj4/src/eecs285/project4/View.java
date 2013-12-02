package eecs285.project4;

import static eecs285.project4.Constants.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.ConcurrentModificationException;
import java.util.HashSet;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * View is responsible for drawing the map.
 */
public class View extends JPanel {
	private HashSet<Tank> tanks;
    private HashSet<Bullet> bullets;
    private HashSet<Boom> booms;
    private MapMaker makeMap;

    public View() {
        tanks = new HashSet<Tank>();
        bullets = new HashSet<Bullet>();
        booms = new HashSet<Boom>();
    }

    public void attach(MapMaker m) {
        makeMap = m;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }
    
    public void addTank(Tank tank) {
        tanks.add(tank);
        add(tank);
    }
    
    public void removeTank(Tank tank) {
    	tanks.remove(tank);
    	remove(tank);
    }

    public void addBullet(Bullet bullet) {
        bullets.add(bullet);
    }

    public void removeBullet(Bullet bullet) {
        bullets.remove(bullet);
    }
    
    public void addBoom(Boom boom) {
    	booms.add(boom);
    }
    
    public void removeBoom(Boom boom) {
    	booms.remove(boom);
    }
    
    //These methods shouldn't return references but I'm not so concerned about not modifying them at this point
    public MapMaker getMapMaker() {
        return makeMap;
    }
    
    public HashSet<Bullet> getBullets() {
        return bullets;
    }

    private void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;

        // draw map background
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, MAP_SIZE * PIXEL_SIZE, MAP_SIZE * PIXEL_SIZE);

        // then draw each tank
        try{
        	for(Tank tank : tanks) {
        		g2d.drawImage(tank.getImage(), tank.getColumn() * PIXEL_SIZE,  tank.getRow() * PIXEL_SIZE, 8*PIXEL_SIZE, 8*PIXEL_SIZE, null);
        	}
        } catch (ConcurrentModificationException e) {
        	System.out.println("Tanks modified");
        }

        // set up blocks
        drawMap(g2d);

        g2d.setColor(Color.WHITE);
        try{
            for(Bullet bullet : bullets) {
                g2d.fillOval(bullet.column * PIXEL_SIZE, bullet.row * PIXEL_SIZE, BULLET_SIZE * PIXEL_SIZE, BULLET_SIZE * PIXEL_SIZE);
            } 
        } catch (ConcurrentModificationException e) {
            // Something tried to modify bullets while it was being iterated over
            // This happens rarely and shouldn't actually be a problem since we're just drawing here
            System.out.println("Bullets modified");
        }

        try {
        	for (Boom boom : booms) {
        		g2d.drawImage(boom.getImage(), boom.getCol() * PIXEL_SIZE, boom.getRow() *PIXEL_SIZE, null);
        		boom.decrement();
        	}
        	for (Boom boom : booms) {
        		if (boom.getDuration() <= 0) {
        			booms.remove(boom);
        			break;
        		}
        	}
        } catch (ConcurrentModificationException e) {
        	System.out.println("Booms modified");
        }
    }

    // Draws the blocks
    private void drawMap(Graphics2D g2d) {
        HashSet<Block> blocks = makeMap.getBlocks();
	        for(Block block : blocks) {
	            ((Graphics) g2d).drawImage(block.getImage(), block.gety() * PIXEL_SIZE, block.getx() * PIXEL_SIZE, 4*PIXEL_SIZE, 4*PIXEL_SIZE, null);
	        }
    }
}
