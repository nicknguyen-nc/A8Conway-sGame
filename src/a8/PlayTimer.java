package a8;

public class PlayTimer extends Thread {

	private GameView view;
	private boolean play;

	private int playDelayValue;
	
	public PlayTimer(GameView view, int playDelayValue) {
		this.view = view;
		this.playDelayValue = playDelayValue;
		play = true;
}
	public void halt() {
		play = false;
	}

	public void run() {

		while (play) {

			try {

				view.getAdvanceButton().doClick();;
				Thread.sleep(playDelayValue);


			}
			catch (InterruptedException e) {

			}
		}
	}
}