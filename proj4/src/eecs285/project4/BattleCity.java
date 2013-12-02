package eecs285.project4;

import static eecs285.project4.Constants.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BattleCity {
    public static void main(String[] args)  {
    	int mapNum = 1;
    	int livesLeft = 3;
        Model model = new Model(livesLeft);
    	while(mapNum < 6 && model.isGameOver() != 2 && livesLeft > 0) {
	        JFrame frame = new JFrame("Battle City");
	        JPanel panel1 = new JPanel(new BorderLayout());
	        JPanel lives = new JPanel(new GridLayout(1, 3));
	        View view = new View();
	        model = new Model(livesLeft);
	
	        // mapMaker calls
	        MapMaker mapMaker = new MapMaker();
	        mapMaker.makeMap(mapNum);
	        mapNum++;
	
	        view.attach(mapMaker);
	        mapMaker.addAllBlocks(model);
	
	        model.attach(view);
	
	        // set up the main frame
	        
	        // set up lives panel
	        JPanel livesMain = new JPanel(new GridLayout(2, 1));
	        JPanel livesMain2 = new JPanel(new GridLayout(5, 1));
	        JLabel life = new JLabel();
	        life.setIcon(new ImageIcon("C:\\Users\\Chermine\\workspace\\eecs285proj4\\proj4\\bin\\eecs285\\project4\\tankImage\\PlayerTankUp.png"));
	        JLabel life2 = new JLabel();
	        life2.setIcon(new ImageIcon("C:\\Users\\Chermine\\workspace\\eecs285proj4\\proj4\\bin\\eecs285\\project4\\tankImage\\PlayerTankUp.png"));
	        JLabel life3 = new JLabel();
	        life3.setIcon(new ImageIcon( "C:\\Users\\Chermine\\workspace\\eecs285proj4\\proj4\\bin\\eecs285\\project4\\tankImage\\PlayerTankUp.png"));
	
	        if(livesLeft > 0) lives.add(life);
	        if(livesLeft > 1) lives.add(life2);
	        if(livesLeft > 2) lives.add(life3);
	        livesMain.add(new JLabel("Lives"));
	        livesMain.add(lives);
	        livesMain.setSize(new Dimension(100, 100));
	        
	        livesMain2.add(new JPanel());
	        livesMain2.add(new JPanel());
	        livesMain2.add(livesMain, BorderLayout.CENTER);
	        
	        // set up enemyTanks panel
	        //JPanel enemyTanks = new JPanel(new GridLayout())
	        
	        panel1.setSize(MAP_SIZE * PIXEL_SIZE + SIDE_BORDER, MAP_SIZE * PIXEL_SIZE + TOP_BORDER);
	        panel1.add(view, BorderLayout.CENTER);
	        panel1.add(livesMain2, BorderLayout.EAST);
	        frame.setSize(MAP_SIZE * PIXEL_SIZE + SIDE_BORDER + 100, MAP_SIZE * PIXEL_SIZE + TOP_BORDER + 100);
	        frame.getContentPane().setLayout(new BorderLayout());
	        frame.getContentPane().add(panel1, BorderLayout.CENTER);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setResizable(false);
	
	        GameWindow window = new GameWindow (frame, "battleCity");
	        frame.setVisible(true);
	
	        while(model.isGameOver() == 0) {
	            try {
	                Thread.sleep(50);
	            } catch(InterruptedException e) {}
	            model.go();
	            if(model.livesLeft()<=2) lives.remove(life3);
	            if(model.livesLeft()<=1) lives.remove(life2);
	            if(model.livesLeft()<=0) lives.remove(life);
	            livesMain.repaint();
	            livesMain2.repaint();
	        }
	        livesLeft = model.livesLeft();
	        frame.dispose(); 
    	}
    	return;
    }
}
