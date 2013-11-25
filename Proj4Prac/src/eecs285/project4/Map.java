package eecs285.project4;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class Map extends JFrame {
	private JPanel middle;
	private JPanel right;
	private JPanel left;
	
	public Map(String inTitle) {
		super(inTitle);
		setLayout(new FlowLayout());
		this.setSize(500,500);
		middle = new JPanel(new GridLayout());
		right = new JPanel();
		
	}

}
