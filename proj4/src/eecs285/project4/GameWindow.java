package eecs285.project4;

import static eecs285.project4.Constants.*;

import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import eecs285.project4.*;

public class GameWindow extends JDialog{
    int money = 5000;

    
    boolean acc = false;
    boolean gem = false;
    boolean ext = false;
    public int tankArmor = 0;
    public int firePower = 0;
    
    //buttons for startscreen
    JButton newgame;
    JButton battleshop;
    JButton multiplayer;
    JButton exitgame;
    
    JPanel startScreen;
    
    //buttons used for upgrades
    JButton armor;
    JButton firepower;
    JButton doublebullet;
    JButton  quit;
    
    JPanel upgradeScreen;
    //buttons used for shopscreen
    JButton upgrades;
    JButton utilities;
    JButton powerups;
    JButton quit4;
    
    JPanel shopScreen;
    
    
    //buttons used in power up screen;
    JButton accelerator;
    JButton gemstone;
    JButton extralife;
    JButton quit2;
    JPanel powerupsScreen;
    
    
    //buttons used in utilities
    JButton landmine;
    JButton missle;
    JButton laser;
    JButton shield;
    JButton  quit3;
    
    JPanel utilitiesScreen;
    
    
    //money counter
    JTextField curMoney;
    JTextField curMoney2;
    JTextField curMoney3;
    JTextField curMoney4;
    //lisenters
    
    
    private class quitListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            shopScreen.setVisible(false);
            startScreen.setVisible(true);
            setContentPane(startScreen);
        }
    }
    
    private class quit2Listener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent arg0) {

            powerupsScreen.setVisible(false);
            utilitiesScreen.setVisible(false);
            upgradeScreen.setVisible(false);
            //getContentPane().removeAll();
            shopScreen.setVisible(true);
            setContentPane(shopScreen);
        }
    }
    
    private void startGame()
    {
        setVisible(false);
    }

    //start screen buttons
    private class newgameL implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            //startScreen.setVisible(false);
            startGame();
            //start the game here!
        }
    }
    
    private class battleshopL implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            startScreen.setVisible(false);
            shopScreen.setVisible(true);
            setContentPane(shopScreen);
        }
    }
    
    private class multiplayerL implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            
            //not implemented
        }
    }
    
    private class exitgameL implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            System.exit(0);
        }
    }
    
    
    
    //different sub shop
    private class upgradesListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            
            shopScreen.setVisible(false);
            upgradeScreen.setVisible(true);
            setContentPane(upgradeScreen);
            
        }
    }
    
    private class utilitiesListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            
            shopScreen.setVisible(false);
            utilitiesScreen.setVisible(true);
            setContentPane(utilitiesScreen);
        }
    }
    private class powerupsListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            shopScreen.setVisible(false);
            powerupsScreen.setVisible(true);
            setContentPane(powerupsScreen);
        }
    }
    
    private boolean checkMoney(int amount)
    {
        
        if (amount > this.money)
        {
            //JDialog message = new JDialog();
            JOptionPane.showMessageDialog(this, "Not Enough Money!");
            
            
            return false;
            
        }
        else{
            this.money -= amount;
            curMoney.setText("You Have: " + money);
            curMoney2.setText("You Have: " + money);
            curMoney3.setText("You Have: " + money);
            curMoney4.setText("You Have: " + money);
            
            JOptionPane.showMessageDialog(this, "Perchase Successfully!");
            return true;
        }
    }
    
    private void printError()
    {
        JOptionPane.showMessageDialog(this, "You already bought this power up!");
    }
    
    
    //upgrade listener
    private class armorL implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            if (checkMoney(5000))
            {
              tankArmor += 5;
            }
            
        }
    }
    
    private class firepowerL implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            if (checkMoney(5000))
            {
              firePower = 2;
            }
            
        }
    }
    
    private class doublebulletL implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            if (checkMoney(5000))
            {
               //upgrade tank here
            }
            
        }
    }
    
    
    
    
    
    
    //buying each items listener
    private class landmineL implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            if (checkMoney(500))
            {
               //add the item here! 
                Items.landmine ++;
            }
            
        }
    }
    
    private class laserL implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            if (checkMoney(2000))
            {
               //add the item here! 
                Items.laser ++;
            }
            
        }
    }
    
    private class missleL implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            if (checkMoney(1000))
            {
               //add the item here! 
            }
            
        }
    }
    
    
    private class shieldL implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            if (checkMoney(2000))
            {
               //add the item here! 
            }
            
        }
    }
    
    //power up lisenter
 
    private class acceleratorL implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            if (acc)
            {
                printError();
                return;
            }
            if (checkMoney(5000))
            {
               acc = true;
            }       
        }
    }
    
    private class gemstoneL implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            if (gem)
            {
                printError();
                return;
            }
            if (checkMoney(20000))
            {
               gem = true;
            }       
        }
    }  
    
    private class extralifeL implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            if (ext)
            {
                printError();
                return;
            }
            if (checkMoney(20000))
            {
               ext = true;
            }       
        }
    }  
    
    
    
    
    GameWindow (JFrame mainFrame,String title)
    {
        super(mainFrame, title, true);
        
        try
        {
        BufferedImage buttonIcon = ImageIO.read(new File("eecs285/project4/shopImage/TankUpgrade.png"));
        upgrades = new JButton(new ImageIcon(buttonIcon));
        
        BufferedImage buttonIcon2 = ImageIO.read(new File("eecs285/project4/shopImage/Utilities.png"));
        utilities = new JButton(new ImageIcon(buttonIcon2));
        
        BufferedImage buttonIcon3 = ImageIO.read(new File("eecs285/project4/shopImage/PowerUp.png"));
        powerups = new JButton(new ImageIcon(buttonIcon3));
        
        BufferedImage buttonIcon4 = ImageIO.read(new File("eecs285/project4/shopImage/quit.png"));
        quit = new JButton(new ImageIcon(buttonIcon4));
        
        BufferedImage buttonIcon5 = ImageIO.read(new File("eecs285/project4/shopImage/landmine.png"));
        landmine = new JButton(new ImageIcon(buttonIcon5));
        
        BufferedImage buttonIcon6 = ImageIO.read(new File("eecs285/project4/shopImage/missle.png"));
        missle = new JButton(new ImageIcon(buttonIcon6));
        
        BufferedImage buttonIcon7 = ImageIO.read(new File("eecs285/project4/shopImage/laser.png"));
        laser = new JButton(new ImageIcon(buttonIcon7));
        
        BufferedImage buttonIcon8 = ImageIO.read(new File("eecs285/project4/shopImage/shield.png"));
        shield = new JButton(new ImageIcon(buttonIcon8));
        
        BufferedImage buttonIcon9 = ImageIO.read(new File("eecs285/project4/shopImage/quit.png"));
        quit3 = new JButton(new ImageIcon(buttonIcon9));
        
        BufferedImage buttonIcon10 = ImageIO.read(new File("eecs285/project4/shopImage/accelerator.png"));
        accelerator = new JButton(new ImageIcon(buttonIcon10));
        
        BufferedImage buttonIcon11 = ImageIO.read(new File("eecs285/project4/shopImage/gemstone.png"));
        gemstone = new JButton(new ImageIcon(buttonIcon11));
        
        BufferedImage buttonIcon12 = ImageIO.read(new File("eecs285/project4/shopImage/extralife.png"));
        extralife = new JButton(new ImageIcon(buttonIcon12));
        
        BufferedImage buttonIcon13 = ImageIO.read(new File("eecs285/project4/shopImage/quit.png"));
        quit2 = new JButton(new ImageIcon(buttonIcon13));
        
        BufferedImage buttonIcon14 = ImageIO.read(new File("eecs285/project4/shopImage/newgame.png"));
        newgame = new JButton(new ImageIcon(buttonIcon14));
        
        BufferedImage buttonIcon15 = ImageIO.read(new File("eecs285/project4/shopImage/battleshop.png"));
        battleshop = new JButton(new ImageIcon(buttonIcon15));
        
        BufferedImage buttonIcon16 = ImageIO.read(new File("eecs285/project4/shopImage/multiplayer.png"));
        multiplayer = new JButton(new ImageIcon(buttonIcon16));
        
        BufferedImage buttonIcon17 = ImageIO.read(new File("eecs285/project4/shopImage/exitgame.png"));
        exitgame = new JButton(new ImageIcon(buttonIcon17));
        
        BufferedImage buttonIcon18 = ImageIO.read(new File("eecs285/project4/shopImage/armor.png"));
        armor = new JButton(new ImageIcon(buttonIcon18));
        
        BufferedImage buttonIcon19 = ImageIO.read(new File("eecs285/project4/shopImage/firepower.png"));
        firepower = new JButton(new ImageIcon(buttonIcon19));
        
        BufferedImage buttonIcon20 = ImageIO.read(new File("eecs285/project4/shopImage/doublebullet.png"));
        doublebullet = new JButton(new ImageIcon(buttonIcon20));
        
        BufferedImage buttonIcon21 = ImageIO.read(new File("eecs285/project4/shopImage/quit.png"));
        quit4 = new JButton(new ImageIcon(buttonIcon21));
        
        
        
        
        
        
        
        /*JButton upgrades;
        JButton utilities;
        JButton powerups;
        JButton quit4;*/
        }
        catch (IOException a)
        {
            
        }
        curMoney = new JTextField();
        Font font = new Font ("Myriad Pro", Font.BOLD, 30);
        curMoney.setFont(font);
        curMoney.setForeground(Color.WHITE);
        curMoney.setBackground(Color.BLACK);
        
        curMoney2 = new JTextField();
        curMoney2.setFont(font);
        curMoney2.setForeground(Color.WHITE);
        curMoney2.setBackground(Color.BLACK);
        
        curMoney3 = new JTextField();
        curMoney3.setFont(font);
        curMoney3.setForeground(Color.WHITE);
        curMoney3.setBackground(Color.BLACK);
        
        curMoney4 = new JTextField();
        curMoney4.setFont(font);
        curMoney4.setForeground(Color.WHITE);
        curMoney4.setBackground(Color.BLACK);
        
        //buttons in start screen
        newgame.addActionListener(new newgameL());
        battleshop.addActionListener(new battleshopL());
        exitgame.addActionListener(new exitgameL());
        
        //buttons in upgrade screen
        armor.addActionListener(new armorL());
        firepower.addActionListener(new firepowerL());
        doublebullet.addActionListener(new doublebulletL());
        
        
        
        //buttons in shop screen
        upgrades.addActionListener(new upgradesListener());
        powerups.addActionListener(new powerupsListener());
        utilities.addActionListener(new utilitiesListener());
        
        quit.addActionListener(new quitListener());
        quit2.addActionListener(new quit2Listener());
        quit3.addActionListener(new quit2Listener());
        quit4.addActionListener(new quit2Listener());
        
        
        
        //buttons in utilities screen
        landmine.addActionListener(new landmineL());
        missle.addActionListener(new missleL());
        laser.addActionListener(new laserL());
        shield.addActionListener(new shieldL());
        
        //buttons in powerups screen
        accelerator.addActionListener(new acceleratorL());
        gemstone.addActionListener(new gemstoneL());
        extralife.addActionListener(new extralifeL());
        
        curMoney.setText("You Have: " + money);
        curMoney2.setText("You Have: " + money);
        curMoney3.setText("You Have: " + money);
        curMoney4.setText("You Have: " + money);
        
        
        
        
        shopScreen = new ShopScreen(upgrades, utilities, powerups, quit, curMoney);
        powerupsScreen  = new PowerupsScreen(accelerator, gemstone, extralife, quit2, curMoney2);
        utilitiesScreen = new UtilitiesScreen(landmine, missle, laser, shield, quit3, curMoney3);
        startScreen = new StartScreen(newgame, battleshop, multiplayer, exitgame);
        upgradeScreen = new UpgradeScreen(armor, firepower, doublebullet, quit4, curMoney4);
        
        
        setContentPane(startScreen);
        setSize(900, 700);
        setVisible(true);
    }
    

}







