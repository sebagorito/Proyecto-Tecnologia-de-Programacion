package observers;
import entities.Player;
import views.GameScreenPanel;


public class ObserverPlayer extends RenderObserver{
	
	private static final long serialVersionUID = 1L;
	private GameScreenPanel gamePanel;
	private Player player;
	public ObserverPlayer(GameScreenPanel gamePanel, Player player) {
		super(player);
		this.gamePanel = gamePanel;
		this.player = player;

		update();
		
        gamePanel.setFocusable(true);
        gamePanel.requestFocusInWindow();
        gamePanel.addKeyListener(player.input);
	}
	
	public void update() {
		super.update();
	}

}
