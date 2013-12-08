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
        
        //Start at level 1 (map1)
    	int mapNum = 1;
    	
    	//Player 1 lives
    	int livesLeft1 = 3;
    	//Player 2 lives    	
    	int livesLeft2 = 3;
    	
    	//Temporary dummy model to get while loop started
        Model model = new Model(livesLeft1, livesLeft2);
        
    	while(mapNum < 6 && model.isGameOver() != 2 && livesLeft2 > 0) {
	        JFrame frame = new JFrame("Battle City");
	        JPanel panel1 = new JPanel(new BorderLayout());
	        JPanel lives1 = new JPanel(new GridLayout(1, 3));
	        JPanel lives2 = new JPanel(new GridLayout(1, 3));
	        View view = new View();
	        model = new Model(livesLeft1, livesLeft2);
	
	        //Make map for this level
	        MapMaker mapMaker = new MapMaker();
	        mapMaker.makeMap(mapNum);
	        mapNum++;
	
	        //Add this map's blocks to the map[][]
	        view.attach(mapMaker);
	        mapMaker.addAllBlocks(model);

	        //Open up game menu window
            GameWindow window = new GameWindow (frame, "Battle City");
	        
	        // set up lives panel
            JPanel livesMain = new JPanel(new GridLayout(10, 1));
	        JPanel livesMain1 = new JPanel(new GridLayout(2, 1));
	        JPanel livesMain2 = new JPanel(new GridLayout(2, 1));
	        JLabel life = new JLabel();
	        life.setIcon(new ImageIcon(BASE_PATH + TANK_PATH + "PlayerTankUp.png"));
	        JLabel life2 = new JLabel();
	        life2.setIcon(new ImageIcon(BASE_PATH + TANK_PATH + "PlayerTankUp.png"));
	        JLabel life3 = new JLabel();
	        life3.setIcon(new ImageIcon( BASE_PATH + TANK_PATH + "PlayerTankUp.png"));

	        if(livesLeft1 > 0) lives1.add(life);
	        if(livesLeft1 > 1) lives1.add(life2);
	        if(livesLeft1 > 2) lives1.add(life3);
	        livesMain1.add(new JLabel("Player 1 Lives"));
	        livesMain1.add(lives1);
	        livesMain1.setSize(new Dimension(100, 100));     

            livesMain.add(new JPanel());
            livesMain.add(new JPanel());
            livesMain.add(new JPanel());
            livesMain.add(new JPanel());            
            livesMain.add(livesMain1, BorderLayout.CENTER);

            JLabel lifeTwo = new JLabel();
            lifeTwo.setIcon(new ImageIcon(BASE_PATH + TANK_PATH + "PlayerTankUp.png"));
            JLabel life2Two = new JLabel();
            life2Two.setIcon(new ImageIcon(BASE_PATH + TANK_PATH + "PlayerTankUp.png"));
            JLabel life3Two = new JLabel();
            life3Two.setIcon(new ImageIcon( BASE_PATH + TANK_PATH + "PlayerTankUp.png"));
            
            //Only show player2 lives if multiplayer mode is activated
            if(window.numPlayers == 2) {                
                if(livesLeft2 > 0) lives2.add(lifeTwo);
                if(livesLeft2 > 1) lives2.add(life2Two);
                if(livesLeft2 > 2) lives2.add(life3Two);
                livesMain2.add(new JLabel("Player 2 Lives"));
                livesMain2.add(lives2);
                livesMain2.setSize(new Dimension(100, 100));  
                livesMain.add(livesMain2, BorderLayout.CENTER);
            }
	        
	        // set up enemyTanks panel
	        //JPanel enemyTanks = new JPanel(new GridLayout())       
	        // not yet implemented
            
            
	        //Set up window sizes and main panels
	        panel1.setSize(MAP_SIZE * PIXEL_SIZE + SIDE_BORDER, MAP_SIZE * PIXEL_SIZE + TOP_BORDER);
	        panel1.add(view, BorderLayout.CENTER);
	        panel1.add(livesMain, BorderLayout.EAST);
	        frame.setSize(MAP_SIZE * PIXEL_SIZE + SIDE_BORDER + 100, MAP_SIZE * PIXEL_SIZE + TOP_BORDER + 100);
	        frame.getContentPane().setLayout(new BorderLayout());
	        frame.getContentPane().add(panel1, BorderLayout.CENTER);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setResizable(false);
	
	        frame.setVisible(true);
	        
	        //Set number of players based on mode chosen in GameWindow
            model.setNumPlayers(window.numPlayers);
            
            //Update upgrade states if any upgrades were purchased
	        if(window.tankArmor > 0) {
	            model.addArmor(window.tankArmor);
	            window.tankArmor = 0;
	        }	        
	        if(window.firePower > 0) {
	            model.addFirepower(window.firePower);
	        }

	        //Set up view, spawn player tanks, etc
            model.attach(view);
	
            //While this round is not over
	        while(model.isGameOver() == 0) {
	            try {
	                //Update every 50 milliseconds
	                Thread.sleep(50);
	            } catch(InterruptedException e) {}
	            
	            //Do all move actions for player and AI tanks
	            model.go(); 
	            
	            //Removes lives from panel if lives were lost
	            if(model.livesLeft1()<=2) lives1.remove(life3);
	            if(model.livesLeft1()<=1) lives1.remove(life2);
	            if(model.livesLeft1()<=0) lives1.remove(life);
                if(window.numPlayers == 2) {
                    if(model.livesLeft2()<=2) lives2.remove(life3Two);
                    if(model.livesLeft2()<=1) lives2.remove(life2Two);
                    if(model.livesLeft2()<=0) lives2.remove(lifeTwo);
                }
                
                //Update lives panels
	            livesMain.repaint();
	            livesMain1.repaint();
	            livesMain2.repaint();
	        }
	        
	        //Update number of lives for next round
	        livesLeft1 = model.livesLeft1();
	        livesLeft2 = model.livesLeft2();
	        
	        //Round ended, close this window and return to menu
	        frame.dispose(); 
    	}
    	return;
    }
}
