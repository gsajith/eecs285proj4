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

public class PowerupsScreen extends JPanel{
    
    
    PowerupsScreen(JButton accelerator, JButton gemstone,
    JButton extralife, JButton  quit, JTextField curMoney)
    {
        

            //BufferedImage buttonIcon = ImageIO.read(new File("/Users/ChenyangDai/Desktop/tankimage/accelerator.png"));
            //accelerator = new JButton(new ImageIcon(buttonIcon));
            
            //BufferedImage buttonIcon2 = ImageIO.read(new File("/Users/ChenyangDai/Desktop/tankimage/gemstone.png"));
            //gemstone = new JButton(new ImageIcon(buttonIcon2));
            
            //BufferedImage buttonIcon3 = ImageIO.read(new File("/Users/ChenyangDai/Desktop/tankimage/extralife.png"));
            //extralife = new JButton(new ImageIcon(buttonIcon3));
            
            
            //BufferedImage buttonIcon5 = ImageIO.read(new File("/Users/ChenyangDai/Desktop/tankimage/quit.png"));
            //quit = new JButton(new ImageIcon(buttonIcon5));
            
            accelerator.setBorder(BorderFactory.createEmptyBorder());
            accelerator.setContentAreaFilled(false);
            
            gemstone.setBorder(BorderFactory.createEmptyBorder());
            gemstone.setContentAreaFilled(false);
            
            extralife.setBorder(BorderFactory.createEmptyBorder());
            extralife.setContentAreaFilled(false);

            
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
        center.add(accelerator);
        center.add(gemstone);
        center.add(extralife);

        center.setBackground(Color.black);
        
        
        this.add(center, BorderLayout.CENTER);
        this.add(top, BorderLayout.NORTH);

        this.setBackground(Color.black);
    }

}