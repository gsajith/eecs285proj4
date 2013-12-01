package eecs285.project4;

import static eecs285.project4.Constants.*;

import java.awt.BorderLayout;
import javax.swing.JFrame;

public class BattleCity {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Battle City");
        View view = new View();
		Model model = new Model();
        
		// mapMaker calls
		MapMaker mapMaker = new MapMaker();
        mapMaker.makeMap(0);
		view.attach(mapMaker);
		mapMaker.addAllBlocks(model);
		
		model.attach(view);
		
        // set up the main frame
		frame.setContentPane(view);
		frame.setLayout(new BorderLayout());
		frame.setSize(MAP_SIZE * PIXEL_SIZE + SIDE_BORDER, MAP_SIZE * PIXEL_SIZE + TOP_BORDER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);

        while(true) {
            try {
                Thread.sleep(50);
            } catch(InterruptedException e) {}
            model.go();
        }
	}
}
