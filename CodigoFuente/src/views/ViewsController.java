package views;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.Map.Entry;
import java.io.File;
import javax.swing.JPanel;
import javax.swing.Timer;
import entities.Enemy;
import entities.FireBall;
import entities.Player;
import entities.PowerUp;
import games.*;
import observers.Observer;
import platforms.Plataform;
import player.*;


public class ViewsController extends JFrame implements ControllerViews,ControllerViewsGame{
	private static final long serialVersionUID = 1L;
	private CardLayout cardLayout;
    private JPanel panelConteiner;
    private Font marioFont;
    private Timer timerDeathScreen;
    private GameScreenPanel gamePanel;
    boolean primerInicio;
    private Game game;
    
    public ViewsController(Game game) throws IOException {
    	configurar_ventana();	
    }
    
    
    
    protected void configurar_ventana() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HIGH);
		setLocationRelativeTo(null);
		panelConteiner = new JPanel();
        cardLayout = new CardLayout();
        panelConteiner.setLayout(cardLayout);

        loadFont();
        try {
			gamePanel = new GameScreenPanel(marioFont);
			gamePanel.setFrame(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
        addPanel(new MenuScreenPanel(this, marioFont));
        addPanel(new RankingScreenPanel(this, marioFont));
        addPanel(gamePanel);
			
	    add(panelConteiner);
		setVisible(true);
	}
    
    public void createNewGame() throws IOException {
        game = new Game();
        game.setViewController(this);
        game.setLoadEntities();
    }

    public void startGame(int lives, int levelToLoad, int points, int coins) {
        try {
            if (!game.getCorriendo()) {
                game.start(lives, levelToLoad, points, coins);
                gamePanel.updateLevel(levelToLoad);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void endGame() {
        try {
        	if(game != null)
        		game.stop();
            createNewGame();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void playDeathScreen(int remainingLives, int levelToReload, int points, int coins) {
        if (levelToReload > 3) {
                endGame();
                showRankingScreen(points);
            } else {
                primerInicio = false;
                if (!game.getCorriendo()) {
                    startGame(remainingLives, levelToReload, points, coins);
                    primerInicio = true;
                }
                showMiddleScreen(levelToReload);
	            timerDeathScreen = new Timer(5000, new ActionListener() {
	                @Override
	                public void actionPerformed(ActionEvent e) {
	                    if (remainingLives == 0) {
	                        gamePanel.returnToMenu();
	                    } else {
	                        if (game.getCorriendo() && !primerInicio) {
	                        	String stateName = "Mario";
                                if(!game.getPlayer().isDead()) {
                                    stateName = game.getPlayer().getStateMario().getStateName();
                                }
	                            endGame();
	                            gamePanel.reloadGameComponents();
	                            startGame(remainingLives, levelToReload, points, coins);
	                            setMarioState(stateName);
	                        }
	                        showPanelMap();
	                        game.startLevelTimer();
	                    }
	                    if (timerDeathScreen != null) {
	                        timerDeathScreen.stop();
	                    }
	                }
	            });
	            timerDeathScreen.setRepeats(false);
	            timerDeathScreen.start();
            } 
        }
    
    private void showMiddleScreen(int levelToReload) {
    	BeetwenLevelsPanel panelMedio = new BeetwenLevelsPanel(marioFont, game, levelToReload);
        addPanel(panelMedio);
        showPanel("levels"); 
    }
    
    private void setMarioState(String stateName) {
    	
    	if (stateName.equals("SuperMario")) {
            game.getPlayer().setStateMario(new SuperMario(game.getPlayer()));
        } else if (stateName.equals("MarioFire")) {
            game.getPlayer().setStateMario(new MarioFire(game.getPlayer()));
        } else {
            game.getPlayer().setStateMario(new Mario(game.getPlayer()));
        }
    }
    
    public void showRankingScreen(int points) {
    	RankingScreenPanel rankingPanel = new RankingScreenPanel(this, marioFont);
    	addPanel(rankingPanel);
    	showPanel("ranking");         
    	Ranking ranking = new Ranking();
    	List<Entry<String, Integer>> topScores = ranking.sort(ranking.loadScores());
    	boolean isTopScore = false;
    	if (topScores.size() < 5) {
    		isTopScore = true;
    		} else {
    			int lowestTopScore = topScores.get(topScores.size() - 1).getValue();
    			if (points > lowestTopScore) {
    				isTopScore = true;
                }
            }
    	if (isTopScore) {       
    		String name = JOptionPane.showInputDialog(this, "¡Has obtenido un puntaje alto!\nIngresa tu nombre:");            if (name != null && !name.trim().isEmpty()) {
            ranking.addScore(name, points);
            rankingPanel.loadLabels(); 
            } else {
            	ranking.addScore("Anónimo", points);
            	rankingPanel.loadLabels();
            }
    	}
    }

    
    public void showPanel(String panelName) {
    	cardLayout.show(panelConteiner, panelName);
    	JPanel currentPanel = getVisiblePanel();
    	currentPanel.requestFocusInWindow();
    }
    
    public JPanel getVisiblePanel() {
    	for (Component comp : panelConteiner.getComponents()) {
            if (comp.isVisible()) {
                return (JPanel) comp;
            }
        }
        return null;
    }
    
    public void loadFont() {
    	try {
            marioFont = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/PressStart2P-Regular.ttf")).deriveFont(14f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(marioFont);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

    }

    public void refresh() {
    	revalidate();
		repaint();
    }

    public void addPanel(PanelBase panel) {
        panelConteiner.add(panel,panel.getPanelName());
        refresh();
    }

	@Override
	public Observer registerEnemy(Enemy enemy) {
		Observer observerEntity = gamePanel.addEnemy(enemy);
    	refresh();
		return observerEntity;
	}

	public Observer registerPlayer(Player player) {
		Observer observerPlayer = gamePanel.addEntityPlayer(player);
    	refresh();
    	return observerPlayer;
	}
	
	public Observer registerPlataform(Plataform plataform) {
		Observer observerPlataform = gamePanel.addPlataform(plataform);
		refresh();
		return observerPlataform;
	}
	
	public Observer registerPowerUp(PowerUp powerUp) {
	     Observer observer = gamePanel.addPowerUp(powerUp);
	     refresh();
	     return observer; 
	}
	 
	public Observer registerFireBall(FireBall fireBall) {
		Observer observer = gamePanel.addFireBall(fireBall);
		refresh();
		return observer;
	}
	
	
	public boolean isInScreen(Enemy e) {
        if(isGameShowing()) {
            return gamePanel.isInViewport(e);
        }
        return false;
    }

    public boolean isGameShowing() {
        return getVisiblePanel().equals(gamePanel);
    }
	
	public Game getGame() {
		return game;
	}
	
	public GameScreenPanel getGamePanel() {
		return gamePanel;
	}
	
	@Override
	public void showPanelMap() {
		showPanel("juego");
		SoundManager.getInstance().playLoop("general");
	}

	@Override
	public void showPanelEndGame() {
		showPanel("menu");
	}

}