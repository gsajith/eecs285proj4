package eecs285.project4;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class BattleCity {
	
	//Frame size we're adding to 416x416 to account for size of border
	public static final int SIDE_BORDER = 2;
	public static final int TOP_BORDER = 24;

	public static void main(String[] args) {
		JFrame frame = new JFrame("Battle City");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel main = new JPanel(new BorderLayout());
		View view = new View();
		Model model = new Model(view);
		Controller controller = new Controller(model);
		
		view.update(model.getMap());
		
		main.add(controller);
		main.add(view);
		
		frame.setSize(View.MAP_SIZE * View.PIXEL_SIZE + SIDE_BORDER, View.MAP_SIZE * View.PIXEL_SIZE + TOP_BORDER);
		frame.setContentPane(main);
		frame.setResizable(false);
		frame.setVisible(true);

		while(true) {
			try {
			Thread.sleep(50);
			} catch (InterruptedException e) {}
			view.update(model.getMap());
			view.repaint();
		}
	}
}
