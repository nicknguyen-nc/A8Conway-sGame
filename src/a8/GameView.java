package a8;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import java.awt.BorderLayout;      
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class GameView extends JPanel implements ChangeListener, ActionListener, SpotListener{
	
	private JSpotBoard board; //Spotboard play area
	private JLabel message; // Label for messages
	private JPanel button_panel;
	

	private JSlider xSlider;
	private JSlider ySlider;
	private int sBoardWidth = 10;
	private int sBoardHeight = 10;
	private JLabel dimensionLabel;
	List<ChangeListener> change_listeners;
	
	public GameView() {
		// create spotboard and message label
				board = new JSpotBoard(10,10);
				message = new JLabel();
				dimensionLabel = new JLabel();

				// set layout and place spotboard at center
				setLayout(new BorderLayout());
				add(board, BorderLayout.CENTER);
				
				// create subpanel for message area and reset button
				JPanel resetMessagePanel = new JPanel();
				resetMessagePanel.setLayout(new BorderLayout());
				
				// reset button and ourselves as action listener
				
				JButton resetButton = new JButton("Restart");
				resetButton.addActionListener(this);
				resetMessagePanel.add(resetButton, BorderLayout.EAST);
				resetMessagePanel.add(message, BorderLayout.CENTER);
				
				// Add subpanel in south area of layout
				
				add(resetMessagePanel, BorderLayout.SOUTH);
				
				// add spot listener for clicks and stuff
				board.addSpotListener(this);
				
				// create panel for board size changers
				
				JPanel dimensionChooserPanel = new JPanel();
				dimensionChooserPanel.setLayout(new BorderLayout());
				xSlider = new JSlider(10, 500, 10);
				ySlider = new JSlider(10, 500, 10);
				dimensionChooserPanel.add(xSlider, BorderLayout.WEST);
				dimensionChooserPanel.add(ySlider,BorderLayout.EAST);
				
				
				// message to display what the current dimensions are
				dimensionChooserPanel.add(dimensionLabel, BorderLayout.SOUTH);
				
				
				add(dimensionChooserPanel,BorderLayout.EAST);
				xSlider.addChangeListener(this);
				ySlider.addChangeListener(this);
				
				change_listeners = new ArrayList<ChangeListener>();
				resetGame();			
	}
	
	public void addChangeListener(ChangeListener l) {
		change_listeners.add(l);
	}

	public void removeChangeListener(ChangeListener l) {
		change_listeners.remove(l);
	}

	public void resetGame() {
		this.remove(board);
		board = new JSpotBoard(sBoardWidth, sBoardHeight);
		board.addSpotListener(this);
		this.add(board,BorderLayout.CENTER);
		this.revalidate();;
	}

	@Override
	public void spotClicked(Spot spot) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void spotEntered(Spot spot) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void spotExited(Spot spot) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		resetGame();
		
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		sBoardWidth = xSlider.getValue();
		sBoardHeight = ySlider.getValue();
		
		if (!((JSlider) e.getSource()).getValueIsAdjusting()) {
			for (ChangeListener l : change_listeners) {
				l.stateChanged(new ChangeEvent(this));
			}
		}
	
		
	}
		
	
		
}
