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

public class ShopScreen extends JPanel{
    
    JLabel  message;
    
    JLabel blank;   //used to fill in the blank
    JLabel blank2;
    
    //JTextField
    
    ShopScreen(JButton upgrades, JButton utilities, JButton powerups, JButton quit, JTextField curMoney)
    {
        
        try
        {
            BufferedImage labelIcon = ImageIO.read(new File("C:\\Users\\Chermine\\workspace\\eecs285proj4\\proj4\\bin\\eecs285\\project4\\shopImage\\message.png"));
            message = new JLabel(new ImageIcon(labelIcon));
            
            BufferedImage labelIcon2 = ImageIO.read(new File("C:\\Users\\Chermine\\workspace\\eecs285proj4\\proj4\\bin\\eecs285\\project4\\shopImage\\blank.png"));
            blank = new JLabel(new ImageIcon(labelIcon2));
            
            blank2 = new JLabel(new ImageIcon(labelIcon2));
            
            upgrades.setBorder(BorderFactory.createEmptyBorder());
            upgrades.setContentAreaFilled(false);
            
            utilities.setBorder(BorderFactory.createEmptyBorder());
            utilities.setContentAreaFilled(false);
            
            powerups.setBorder(BorderFactory.createEmptyBorder());
            powerups.setContentAreaFilled(false);
            
            quit.setBorder(BorderFactory.createEmptyBorder());
            quit.setContentAreaFilled(false);
            
        }
        catch (IOException a)
        {
            
        }
        this.setLayout(new BorderLayout());
        JPanel top = new JPanel ();
        
        top.setLayout(new BorderLayout());
        top.add(quit, BorderLayout.WEST);
        top.add(curMoney, BorderLayout.EAST);
        
        
        top.setBackground(Color.black);
        
        
        
        JPanel center = new JPanel ();
        JPanel empty = new JPanel ();
        empty.setBackground(Color.black);
        
        center.setLayout(new FlowLayout());
        
        //center.add(blank);
        center.add(upgrades);
        center.add(blank);
        center.add(utilities);
        center.add(blank2);
        center.add(powerups);
        
        center.setBackground(Color.black);
        
        JPanel bottom = new JPanel ();
        bottom.setLayout(new FlowLayout());
        bottom.add(message);
        bottom.setBackground(Color.black);
        
        this.add(center, BorderLayout.CENTER);
        this.add(top, BorderLayout.NORTH);
        this.add(bottom, BorderLayout.SOUTH);
        this.setBackground(Color.black);
    }

}
