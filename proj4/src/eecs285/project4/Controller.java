package eecs285.project4;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractAction;
import javax.swing.JPanel;
import javax.swing.KeyStroke;


public class Controller extends JPanel {
	Model mom;

	public Controller(Model model) {
		mom = model;
		setFocusable(true);
		this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT,0), "left");
		this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT,0), "right");
		this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_UP,0), "up");
		this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN,0), "down");
		this.getActionMap().put("up", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mom.move("up");
			}
		});
		this.getActionMap().put("down", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mom.move("down");
			}
		});
		this.getActionMap().put("left", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mom.move("left");
			}
		});
		this.getActionMap().put("right", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mom.move("right");
			}
		});
	}
}
