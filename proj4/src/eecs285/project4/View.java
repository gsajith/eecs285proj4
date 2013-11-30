package eecs285.project4;

import static eecs285.project4.Constants.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.HashSet;

import javax.swing.JPanel;

/**
 * View is responsible for drawing the map.
 */
public class View extends JPanel {
    private HashSet<Tank> tanks;
    
    public View() {
        tanks = new HashSet<Tank>();
    }

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	
    public void addTank(Tank tank) {
        tanks.add(tank);
        add(tank);
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


        
    }
}
