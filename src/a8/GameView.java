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
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class GameView extends JPanel  /*implements ChangeListener , ActionListener, SpotListener */{
	
	private JSpotBoard board; //Spotboard play area
	private JLabel message; // Label for messages
	private JPanel button_panel;

	private JButton advanceButton;

	private JSlider xSlider;
	private JSlider ySlider;
	
	private JSlider birthSlider;
	private JSlider surviveSlider;

	JLabel thresholdLabel;
	
	private int sBoardWidth = 10;
	private int sBoardHeight = 10;
	private JLabel dimensionLabel;
	ArrayList<ChangeListener> change_listeners;
	
	// buttons for presets
	
	private JButton clearButton;
	private JButton randomizeButton;
	private JCheckBox torusCheckBox;
	
	// Text fields for birth/death
	
	private JTextField birthNumber;
	private JTextField surviveNumber;
	private JButton setThresholdsButton;
	
	
	// stuff for auto
	private JButton autoButton;
	private JLabel delayLabel;
	private JSlider delaySlider;

	
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
				

		//		resetButton.addActionListener(this);
			
				advanceButton = new JButton("Advance");
		//		advanceButton.addActionListener(this);
				torusCheckBox = new JCheckBox("Torus Mode");
				// maybe add action listner here
				resetMessagePanel.add(advanceButton, BorderLayout.EAST);

				resetMessagePanel.add(message, BorderLayout.NORTH);
				resetMessagePanel.add(torusCheckBox, BorderLayout.WEST);
				// Add subpanel in south area of layout
				
				add(resetMessagePanel, BorderLayout.SOUTH);
				
				// add spot listener for clicks and stuff
		//		board.addSpotListener(this);
				
				// create panel for board size changers
				JPanel dimensionThresholdPanel = new JPanel();
				dimensionThresholdPanel.setLayout(new BorderLayout());
				JPanel birthSurvivePanel = new JPanel();
				birthSurvivePanel.setLayout(new BorderLayout());
				JPanel dimensionChooserPanel = new JPanel();
				dimensionChooserPanel.setLayout(new BorderLayout());
				birthSlider = new JSlider (1, 8, 3);
				birthSlider.setToolTipText("birthSlider");
				surviveSlider = new JSlider (1, 8, 2);
				surviveSlider.setToolTipText("surviveSlider");

				thresholdLabel = new JLabel();
				xSlider = new JSlider(10, 500, 10);
				xSlider.setToolTipText("xSlider");
				ySlider = new JSlider(10, 500, 10);
				ySlider.setToolTipText("ySlider");
				dimensionChooserPanel.add(xSlider, BorderLayout.WEST);
				dimensionChooserPanel.add(ySlider,BorderLayout.EAST);
				
				birthSurvivePanel.add(surviveSlider, BorderLayout.WEST);
				birthSurvivePanel.add(birthSlider, BorderLayout.EAST);

				birthSurvivePanel.add(thresholdLabel, BorderLayout.SOUTH);
		//		xSlider.addChangeListener(this);
		//		ySlider.addChangeListener(this);
				
				// message to display what the current dimensions are
				dimensionChooserPanel.add(dimensionLabel, BorderLayout.SOUTH);
				
				
				dimensionThresholdPanel.add(dimensionChooserPanel,BorderLayout.WEST);
				dimensionThresholdPanel.add(birthSurvivePanel,BorderLayout.EAST);
				add(dimensionThresholdPanel,BorderLayout.EAST);
				//create panel to house preset board layouts
				JPanel presetTimerPanel = new JPanel();
				presetTimerPanel.setLayout(new BorderLayout());
				JPanel presetButtonPanel = new JPanel();
				presetButtonPanel.setLayout(new BorderLayout());
				randomizeButton = new JButton("Randomize Board");
				clearButton = new JButton("Clear Board");
		//		clearButton.addActionListener(this);
		//		randomizeButton.addActionListener(this);
				presetButtonPanel.add(clearButton,BorderLayout.EAST);
				presetButtonPanel.add(randomizeButton, BorderLayout.WEST);
				
				JPanel timerPanel = new JPanel();
				timerPanel.setLayout(new BorderLayout());
				
				
				delaySlider = new JSlider(10, 1000, 10);
				delayLabel = new JLabel("Your Current Delay is " + getDelay() + " Milliseconds");
				delaySlider.setToolTipText("Minimimum is 10 milliseconds, Maximum is 1 second");
				autoButton = new JButton("Start");
			
				
				timerPanel.add(delaySlider, BorderLayout.CENTER);
				timerPanel.add(delayLabel, BorderLayout.NORTH);
				timerPanel.add(autoButton, BorderLayout.SOUTH);
				
				
				presetTimerPanel.add(presetButtonPanel, BorderLayout.WEST);
				presetTimerPanel.add(timerPanel, BorderLayout.EAST);
				add(presetTimerPanel,BorderLayout.WEST);
		
				/*
				setLayout(this.getLayout());
				JPanel thresholdPanel = new JPanel();
				thresholdPanel.setLayout(new BorderLayout());
				birthNumber = new JTextField(3);
				surviveNumber = new JTextField(2);
				thresholdPanel.add(birthNumber, BorderLayout.EAST);
				thresholdPanel.add(surviveNumber, BorderLayout.WEST);
				add(thresholdPanel, BorderLayout.EAST);
				*/
				change_listeners = new ArrayList<ChangeListener>();
				
				
				
				
				
				resetGame(board);			
	}
	/*
	public void addChangeListener(ChangeListener l) {
		change_listeners.add(l);
	}

	public void removeChangeListener(ChangeListener l) {
		change_listeners.remove(l);
	}
	*/

	public JButton getAdvanceButton() {
		return advanceButton;
	}
	public JLabel getDelayLabel() {
		return delayLabel;
	}
	public void resetGame(JSpotBoard board) {
		remove(this.board);
		this.board = board;
		add(this.board);
		
		
		for(Spot s: this.board) {
			if (s.isEmpty()) {
				s.setBackground(Color.DARK_GRAY);
			}
		}
		
		this.board.revalidate();
		
		message.setText("Instructions: Click on square to set/clear");
		dimensionLabel.setText("Current X Dimension: " + sBoardWidth + " Current Y Dimension: " + sBoardHeight);
		thresholdLabel.setText("Survival Threshold: " + getSurvive() + " Birth Threshold: "+ getBirth());
	}
/*
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
	*/
	/*
	@Override
	public void stateChanged(ChangeEvent e) {
		sBoardWidth = xSlider.getValue();
		sBoardHeight = ySlider.getValue();
		
		if (!((JSlider) e.getSource()).getValueIsAdjusting()) {
			for (ChangeListener l : change_listeners) {
				l.stateChanged(new ChangeEvent(this));
			}
		}
		
		
	
		resetGame(new JSpotBoard(sBoardWidth, sBoardHeight));
		
		
	}
	*/
	public ArrayList<ChangeListener> getChangeListenerList() {
		return change_listeners;
	}
	
	public int getBirth() {
		return birthSlider.getValue();
	}
	
	public int getSurvive() {
		return surviveSlider.getValue();
	}
	
	public int getDelay() {
		return delaySlider.getValue();
	}
	

	public JSpotBoard getBoard() {
		return board;
	}
	
	public int getBoardWidth() {
		return sBoardWidth;
	}
	public void setBoardWidth() {
		sBoardWidth = xSlider.getValue();
	}
	
	
	public int getBoardHeight() {
		return sBoardHeight;
	}
	public void setBoardHeight() {
		sBoardHeight = ySlider.getValue();
	}
	
	void addSpotListener(SpotListener listenerForSpotBoard) {
		board.addSpotListener(listenerForSpotBoard);
		
	}
	
	void addButtonListener(ActionListener listenerForButton) {
		advanceButton.addActionListener(listenerForButton);

		randomizeButton.addActionListener(listenerForButton);
		clearButton.addActionListener(listenerForButton);
		
		autoButton.addActionListener(listenerForButton);
		
	}
	
	void addTorusListener(ActionListener listenerForTorus) {
		torusCheckBox.addActionListener(listenerForTorus);
	}
	
	void addSliderListener(ChangeListener listenerForSliders) {
		xSlider.addChangeListener(listenerForSliders);
		ySlider.addChangeListener(listenerForSliders);
		delaySlider.addChangeListener(listenerForSliders);
		birthSlider.addChangeListener(listenerForSliders);
		surviveSlider.addChangeListener(listenerForSliders);
		
	}
	

}
		
	
		// important method for the auto clicker resetButton.doClick();

