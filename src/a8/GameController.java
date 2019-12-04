package a8;

public class GameController {

	
	private GameView theView;
	private GameModel theModel;
	
	public GameController(GameView theView, GameModel theModel) {
		this.theModel = theModel;
		this.theView = theView;
	}
}
