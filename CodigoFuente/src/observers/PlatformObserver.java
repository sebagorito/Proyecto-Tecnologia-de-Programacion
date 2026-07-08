package observers;

import javax.swing.JPanel;

import platforms.Plataform;

public class PlatformObserver extends RenderObserver {
	
	private static final long serialVersionUID = 1L;
	private JPanel gamePanel;
	public PlatformObserver(Plataform p, JPanel gamePanel) {
		super(p);
		update();
		this.gamePanel = gamePanel;
	}
	
	public void paint() {
		gamePanel.add(this);
		gamePanel.revalidate();
		gamePanel.repaint();
	}

}
