package eecs285.project4;

import static eecs285.project4.Constants.*;

import java.awt.BorderLayout;
import javax.swing.JFrame;

public class BattleCity {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Battle City");
        View view = new View();
		Model model = new Model();
        model.attach(view);
		Block b1 = new Block(BRICK_BLOCK, 6 * BLOCK_SIZE, 6 * BLOCK_SIZE);
		Block b2 = new Block(BRICK_BLOCK, 6 * BLOCK_SIZE + MINI_BLOCK_SIZE, 6 * BLOCK_SIZE);
		Block b3 = new Block(BRICK_BLOCK, 6 * BLOCK_SIZE, 6 * BLOCK_SIZE + MINI_BLOCK_SIZE);
		Block b4 = new Block(BRICK_BLOCK, 6 * BLOCK_SIZE + MINI_BLOCK_SIZE, 6 * BLOCK_SIZE + MINI_BLOCK_SIZE);
		model.addBlock(b1);
		model.addBlock(b2);
		model.addBlock(b3);
		model.addBlock(b4);
		
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
