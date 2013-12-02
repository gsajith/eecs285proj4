package eecs285.project4;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
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

public class UtilitiesScreen extends JPanel{
    
    
    UtilitiesScreen(JButton landmine,
    JButton missle, JButton laser,
    JButton shield, JButton  quit, JTextField curMoney)
    {
        

            //BufferedImage buttonIcon = ImageIO.read(new File("/Users/ChenyangDai/Desktop/tankimage/landmine.png"));
            //landmine = new JButton(new ImageIcon(buttonIcon));
            
            //BufferedImage buttonIcon2 = ImageIO.read(new File("/Users/ChenyangDai/Desktop/tankimage/missle.png"));
            //missle = new JButton(new ImageIcon(buttonIcon2));
            
            //BufferedImage buttonIcon3 = ImageIO.read(new File("/Users/ChenyangDai/Desktop/tankimage/laser.png"));
            //laser = new JButton(new ImageIcon(buttonIcon3));
            
            //BufferedImage buttonIcon4 = ImageIO.read(new File("/Users/ChenyangDai/Desktop/tankimage/shield.png"));
            //shield = new JButton(new ImageIcon(buttonIcon4));
            
            //BufferedImage buttonIcon5 = ImageIO.read(new File("/Users/ChenyangDai/Desktop/tankimage/quit.png"));
            //quit = new JButton(new ImageIcon(buttonIcon5));
            
            landmine.setBorder(BorderFactory.createEmptyBorder());
            landmine.setContentAreaFilled(false);
            
            missle.setBorder(BorderFactory.createEmptyBorder());
            missle.setContentAreaFilled(false);
            
            laser.setBorder(BorderFactory.createEmptyBorder());
            laser.setContentAreaFilled(false);
            
            shield.setBorder(BorderFactory.createEmptyBorder());
            shield.setContentAreaFilled(false);
            
            quit.setBorder(BorderFactory.createEmptyBorder());
            quit.setContentAreaFilled(false);
            

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
        center.add(landmine);
        center.add(missle);
        center.add(laser);
        center.add(shield);
        center.setBackground(Color.black);
        
        
        this.add(center, BorderLayout.CENTER);
        this.add(top, BorderLayout.NORTH);

        this.setBackground(Color.black);
    }

}
