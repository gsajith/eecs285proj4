package eecs285.project4;

import static eecs285.project4.Constants.*;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class BattleCity {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Battle City");
        View view = new View();
		Model model = new Model();
        model.attach(view);
		
        // set up the main frame
		frame.setContentPane(view);
		frame.setLayout(new BorderLayout());
		frame.setSize(MAP_SIZE * PIXEL_SIZE + SIDE_BORDER, MAP_SIZE * PIXEL_SIZE + TOP_BORDER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
	}
}
