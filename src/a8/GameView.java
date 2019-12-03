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
	private JButton resetButton;
	private JButton advanceButton;

	private JSlider xSlider;
	private JSlider ySlider;
	private int sBoardWidth = 10;
	private int sBoardHeight = 10;
	private JLabel dimensionLabel;
	List<ChangeListener> change_listeners;
	
	// buttons for presets
	
	private JButton
	private JButton
	private JButton
	
	
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
				
				resetButton = new JButton("Restart");
				resetButton.addActionListener(this);
				advanceButton = new JButton("Advance");
				advanceButton.addActionListener(this);
				resetMessagePanel.add(advanceButton, BorderLayout.EAST);
				resetMessagePanel.add(resetButton, BorderLayout.SOUTH);
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
				xSlider.addChangeListener(this);
				ySlider.addChangeListener(this);
				
				// message to display what the current dimensions are
				dimensionChooserPanel.add(dimensionLabel, BorderLayout.SOUTH);
				
				
				add(dimensionChooserPanel,BorderLayout.EAST);
				
				//create panel to house preset board layouts
				
				JPanel presetButtonPanel = new JPanel();
				
				
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
		message.setText("Instructions:");
		dimensionLabel.setText("Current X Dimension: " + sBoardWidth + " Current Y Dimension: " + sBoardHeight);
		this.revalidate();
	}

	@Override
	public void spotClicked(Spot spot) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void spotEntered(Spot spot) {
		spot.highlightSpot();
		
	}

	@Override
	public void spotExited(Spot spot) {
		spot.unhighlightSpot();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == resetButton) { 	
			resetGame();
		}
		
		
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
		
		
	
		resetGame();
	
		
	}
		
	
		// important method for the auto clicker resetButton.doClick();
}
