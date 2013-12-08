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
    
    //Sets of tanks, bullets, and explosions
	private HashSet<Tank> tanks;
    private HashSet<Bullet> bullets;
    private HashSet<Boom> booms;
    
    //Mapmaker to determine level layout for each round
    private MapMaker makeMap;

    public View() {
        tanks = new HashSet<Tank>();
        bullets = new HashSet<Bullet>();
        booms = new HashSet<Boom>();
    }

    /*
     * Attach MapMaker to this view
     */
    public void attach(MapMaker m) {
        makeMap = m;
    }

    /*
     * Called when view.repaint() is called
     * Redraws map and all actors
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }
    
    /*
     * Adds a tank to view (to enable controlling it) 
     * and to the set of tanks
     */
    public void addTank(Tank tank) {
        tanks.add(tank);
        add(tank);
    }
    
    /*
     * Removes tank from view and set of tanks
     */
    public void removeTank(Tank tank) {
    	tanks.remove(tank);
    	remove(tank);
    }

    /*
     * Adds bullet to set of bullets
     */
    public void addBullet(Bullet bullet) {
        bullets.add(bullet);
    }

    /*
     * Removes bullet from set of bullets
     */
    public void removeBullet(Bullet bullet) {
        bullets.remove(bullet);
    }
    
    /*
     * Adds boom to set of booms
     */
    public void addBoom(Boom boom) {
    	booms.add(boom);
    }
    
    /*
     * Removes boom from set of booms
     */
    public void removeBoom(Boom boom) {
    	booms.remove(boom);
    }
    
    /*
     * Get the mapMaker for this view
     * Used by model to access sets of brick/steel/base blocks
     * for destruction checking
     */
    public MapMaker getMapMaker() {
        return makeMap;
    }
    
    /*
     * Get the set of bullets for this view
     * Used by model
     */
    public HashSet<Bullet> getBullets() {
        return bullets;
    }

    /*
     * Draw all bullets, tanks, blocks
     */
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

        // Draw all bullets
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

        // Draw / expire all booms
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
        try{
	        for(Block block : blocks) {
	            ((Graphics) g2d).drawImage(block.getImage(), block.gety() * PIXEL_SIZE, block.getx() * PIXEL_SIZE, 4*PIXEL_SIZE, 4*PIXEL_SIZE, null);
	        }
        } catch (ConcurrentModificationException e) {
        	System.out.println("Blocks modified");
        }
    }
}
