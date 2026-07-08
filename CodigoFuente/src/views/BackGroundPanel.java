package views;
import javax.swing.JPanel;
import java.awt.Image;
import java.awt.Graphics;
public class BackGroundPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private Image backgroundImage;
	
	public BackGroundPanel(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
        setLayout(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, 13000, getHeight(), this);
        }
    }
}
