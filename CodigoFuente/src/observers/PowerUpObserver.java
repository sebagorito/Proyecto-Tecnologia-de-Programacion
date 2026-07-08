package observers;
import javax.swing.JPanel;
import entities.PowerUp;

public class PowerUpObserver extends RenderObserver {
	
	private static final long serialVersionUID = 1L;
	private JPanel gamePanel;
	public PowerUpObserver(PowerUp power, JPanel gamePanel) {
		super(power);
		update();
		this.gamePanel = gamePanel;
	}
	
	public void paint() {
		gamePanel.add(this);
		gamePanel.revalidate();
		gamePanel.repaint();
	}
	

}
