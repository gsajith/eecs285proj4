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
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UpgradeScreen extends JPanel{
    
    
    UpgradeScreen(JButton armor, JButton firepower,
    JButton doublebullet, JButton  quit, JTextField curMoney)
    {

            
            armor.setBorder(BorderFactory.createEmptyBorder());
            armor.setContentAreaFilled(false);
            
            firepower.setBorder(BorderFactory.createEmptyBorder());
            firepower.setContentAreaFilled(false);
            
            doublebullet.setBorder(BorderFactory.createEmptyBorder());
            doublebullet.setContentAreaFilled(false);

            
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
        center.add(armor);
        center.add(firepower);
        center.add(doublebullet);

        center.setBackground(Color.black);
        
        
        this.add(center, BorderLayout.CENTER);
        this.add(top, BorderLayout.NORTH);

        this.setBackground(Color.black);
    }

}