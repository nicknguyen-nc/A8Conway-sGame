package a8;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;



public class GamePlayer {
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
