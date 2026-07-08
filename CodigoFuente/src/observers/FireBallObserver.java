package observers;

import java.awt.Graphics;
import javax.swing.JPanel;
import entities.FireBall;


public class FireBallObserver extends RenderObserver{
	private static final long serialVersionUID = 1L;
	private JPanel gamePanel;
	
	public FireBallObserver(FireBall fireBall, JPanel gamePanel) {
		super(fireBall);
		update();
		this.gamePanel = gamePanel;
	}
	
	public void paint() {
		gamePanel.add(this);
		gamePanel.revalidate();
		gamePanel.repaint();
	}
	
	public void paintComponent(Graphics g) {
        if (((FireBall) entity).isActive()) {
            super.paintComponent(g);
        }
    }


}
