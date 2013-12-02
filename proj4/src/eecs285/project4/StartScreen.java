package eecs285.project4;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class StartScreen extends JPanel{
    
    
    JLabel title;   //used to fill in the blank

    
    StartScreen(JButton newgame, JButton battleshop, JButton multiplayers, JButton exitgame)
    {
    
        BufferedImage labelIcon2;
        try {
            labelIcon2 = ImageIO.read(new File("C:\\Users\\Chermine\\workspace\\eecs285proj4\\proj4\\src\\eecs285\\project4\\shopImage\\title.png"));
            title = new JLabel(new ImageIcon(labelIcon2));
            
            newgame.setBorder(BorderFactory.createEmptyBorder());
            newgame.setContentAreaFilled(false);
            
            battleshop.setBorder(BorderFactory.createEmptyBorder());
            battleshop.setContentAreaFilled(false);
            
            multiplayers.setBorder(BorderFactory.createEmptyBorder());
            multiplayers.setContentAreaFilled(false);
            
            exitgame.setBorder(BorderFactory.createEmptyBorder());
            exitgame.setContentAreaFilled(false);
            
            
            
            
            
        } catch (IOException e) {

        }
        
        this.setLayout(new BorderLayout());
        this.setBackground(Color.black);
        JPanel top = new JPanel ();
        top.add(title);
        top.setBackground(Color.black);
        
        JPanel middle = new JPanel (new GridLayout(2,2));
        middle.add(newgame);
        middle.add(battleshop);
        middle.add(multiplayers);
        middle.add(exitgame);
        middle.setBackground(Color.black);
        
        this.add(top, BorderLayout.NORTH);
        this.add(middle, BorderLayout.CENTER);
    }
    
    
    
    
    
    
}








