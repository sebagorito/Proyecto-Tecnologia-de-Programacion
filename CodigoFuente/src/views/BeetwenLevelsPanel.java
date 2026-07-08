package views;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import games.Game;

public class BeetwenLevelsPanel extends PanelBase {
	private static final long serialVersionUID = 1L;
	private Font font;
	private Game game;
	private int level;
	
	public BeetwenLevelsPanel(Font font, Game game, int level) {
    	this.font = font;
    	this.game = game;
    	this.level = level;
    	setLayout(null);
        this.setBackground(Color.BLACK);
        print();
        setFocusable(true);  
        requestFocusInWindow();
    }
	
	public void print() {
		if(game.getPlayer().getLife() > 0) {
			printLifes(game.getPlayer().getLife());
		}
		if(game.getPlayer().getLife() == 0) {
			gameOver();
		}
	}
	
	public void printLifes(int lives) {
		ImageIcon marioSprite = new ImageIcon(game.getPlayer().getInitialSprite(game.getPlayer().getFactorySprite()).getImage());
        JLabel marioLabel = new JLabel(marioSprite);
        JLabel lifesLabel = new JLabel("x " + lives);
        lifesLabel.setFont(font);
        lifesLabel.setForeground(Color.WHITE);
        lifesLabel.setBounds(Constants.WINDOW_WIDTH/2 - 25, Constants.WINDOW_HIGH/2 -30, 70, 30); 
        marioLabel.setBounds(Constants.WINDOW_WIDTH/2 - 75, Constants.WINDOW_HIGH/2 -30, marioSprite.getIconWidth(), marioSprite.getIconHeight());
        JLabel world = new JLabel("World " + level);
        world.setFont(font);
        world.setForeground(Color.WHITE);
        world.setBounds(Constants.WINDOW_WIDTH/2 - 80, Constants.WINDOW_HIGH/2 -70, 150, 30);
        add(world);
        this.add(marioLabel);
        this.add(lifesLabel);
	}
	
	public void gameOver() {
        JLabel gameOv = new JLabel("Game Over");
        gameOv.setFont(font);
        gameOv.setForeground(Color.WHITE);
        gameOv.setBounds(Constants.WINDOW_WIDTH/2 - 80, Constants.WINDOW_HIGH/2 -30, 150, 30);
        add(gameOv);
	}
	
	@Override
	public String getPanelName() {
		return "levels";
	}

}
