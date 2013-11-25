package eecs285.project4;

import java.io.File;
import java.net.URISyntaxException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class ImageControl
{		
	private ImageIcon blackBox;
	private ImageIcon blueBox;
	private ImageIcon brickBox;
	private ImageIcon baseBox;
	private ImageIcon greenBox;
	private ImageIcon[] holder; // #howtofail
	File myDir;
	
	public ImageControl(String blackB, String blueB,
					String brickB, String baseB, String greenB) {
		try {	
			myDir = new File(getClass().getClassLoader().getResource(
                "P4images").toURI());
		} 
		catch (URISyntaxException uriExcep) {
			System.out.println("URI syntax exception");
			System.exit(4);
		}
		//holder = myDir.listFiles();
	}

}
