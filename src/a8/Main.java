package a8;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

// READ THIS
// Run this class to play the game
// NOTE: Large dimensions for the spotboard cause the program to run slowly, but it still will eventually work
// NOTE 2: TO Set custom birth and survive thresholds, first slide the slider as desired, then click the set threshold button
public class Main {
	public static void main(String[] args) {
		GameView view = new GameView();
		GameModel model = new GameModel();
		GameController controller = new GameController(view, model);
		
		JFrame main_frame = new JFrame();
		main_frame.setTitle("Conway's Game of Life");
		main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel top_panel = new JPanel();
		top_panel.setLayout(new BorderLayout());
		main_frame.setContentPane(top_panel);
		
		top_panel.add(view,BorderLayout.CENTER);

		main_frame.pack();
		main_frame.setVisible(true);
	}
}
