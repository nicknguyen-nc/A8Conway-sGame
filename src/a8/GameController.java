package a8;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JButton;

public class GameController {

	
	private GameView theView;
	private GameModel theModel;
	private PlayTimer playTimer;
	
	public GameController(GameView theView, GameModel theModel) {
		this.theModel = theModel;
		this.theView = theView;
		// this.theView.addTaurusListener(new TaurusCheckListener());
		this.theView.addSpotListener(new SpotBoardListener());
		this.theView.addSliderListener(new SliderListener());
		this.theView.addTorusListener(new TorusCheckListener());
		this.theView.addButtonListener(new ButtonListener());
	}
	
	class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
		
			if(((JButton)e.getSource()).getText() == "Advance") {
				theModel.calculateNextBoard(theView.getBoard());
				theView.resetGame(theModel.getNextBoard());
				theView.addSpotListener(new SpotBoardListener());
			}
			if(((JButton)e.getSource()).getText() == "Randomize Board") {
				theModel.generateRandomBoard(theView.getBoard());
				theView.resetGame(theModel.getNextBoard());
				theView.addSpotListener(new SpotBoardListener());
			}
			if(((JButton)e.getSource()).getText() == "Clear Board") {
				theView.resetGame(new JSpotBoard(theView.getBoardWidth(),theView.getBoardHeight()));
				theView.addSpotListener(new SpotBoardListener());
			}
			// theView.getDelay();
			if(((JButton)e.getSource()).getText() == "Start") {
				playTimer = new PlayTimer(theView, theView.getDelay());
				playTimer.start();
				((JButton)e.getSource()).setText("Stop");
				((JButton)e.getSource()).revalidate();
				return;
			}
			if(((JButton)e.getSource()).getText() == "Stop") {
				playTimer.halt();
				((JButton)e.getSource()).setText("Start");
				((JButton)e.getSource()).revalidate();
				return;
			}
			

		}
		
	}
	
	
	
	class TorusCheckListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(((JCheckBox)e.getSource()).getText() == "Torus Mode") {
				theModel.setTorusMode();
			
			}
			
		}
		
	}
	
	class SpotBoardListener implements SpotListener {

		@Override
		public void spotClicked(Spot spot) {
			if (spot.isEmpty()) {
				theView.getBoard().getSpotAt(spot.getSpotX(), spot.getSpotY()).toggleSpot();
				spot.setSpotColor(Color.RED);
				theView.getBoard().getSpotAt(spot.getSpotX(), spot.getSpotY()).setBackground(Color.RED);
			} else {
				theView.getBoard().getSpotAt(spot.getSpotX(), spot.getSpotY()).toggleSpot();
				theView.getBoard().getSpotAt(spot.getSpotX(), spot.getSpotY()).setBackground(Color.DARK_GRAY);
			}
			
		}

		@Override
		public void spotEntered(Spot spot) {
			spot.highlightSpot();
			
		}

		@Override
		public void spotExited(Spot spot) {
			spot.unhighlightSpot();
			
		}
		
	}
	
	class SliderListener implements ChangeListener {
	
		@Override
		public void stateChanged(ChangeEvent e) {
			if(((JSlider)e.getSource()).getToolTipText() == "xSlider" || ((JSlider)e.getSource()).getToolTipText() == "ySlider") {
				theView.setBoardWidth();
				theView.setBoardHeight();
				theView.resetGame(new JSpotBoard(theView.getBoardWidth(),theView.getBoardHeight()));
				theView.addSpotListener(new SpotBoardListener());

			}
			if(((JSlider)e.getSource()).getToolTipText() == "Minimimum is 10 milliseconds, Maximum is 1 second") {
				theView.getDelayLabel().setText("Your Current Delay is " + theView.getDelay() + " Milliseconds"); 
			}
			
			if(((JSlider)e.getSource()).getToolTipText() == "birthSlider") {
				theModel.setBirthThreshold(theView.getBirth());
				theModel.setSurviveThreshold(theView.getSurvive());
				theView.thresholdLabel.setText("Survival Threshold: " + theView.getSurvive() + " Birth Threshold: "+ theView.getBirth());
			}
			if(((JSlider)e.getSource()).getToolTipText() == "surviveSlider") {
				theModel.setBirthThreshold(theView.getBirth());
				theModel.setSurviveThreshold(theView.getSurvive());
				theView.thresholdLabel.setText("Survival Threshold: " + theView.getSurvive() + " Birth Threshold: "+ theView.getBirth());
			}
			if (!((JSlider) e.getSource()).getValueIsAdjusting()) {
				for (ChangeListener l : theView.getChangeListenerList()) {
					l.stateChanged(new ChangeEvent(this));
				}
			}
			
			
		}
		public void addChangeListener(ChangeListener l) {
			theView.getChangeListenerList().add(l);
		}

		public void removeChangeListener(ChangeListener l) {
			theView.getChangeListenerList().remove(l);
		}
		
	}
	
	
}
