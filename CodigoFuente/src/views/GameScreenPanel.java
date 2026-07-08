package views;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.ScrollPaneConstants;
import entities.Enemy;
import entities.FireBall;
import entities.Player;
import entities.PowerUp;
import games.SoundManager;
import observers.EntityObserver;
import observers.Observer;
import observers.ObserverPlayer;
import observers.PowerUpObserver;
import observers.FireBallObserver;
import platforms.Plataform;

public class GameScreenPanel extends PanelBase  {
	private static final long serialVersionUID = 1L;

	protected Observer observer;
	
	private ViewsController frame;
    private JPanel panelMap;
    private JPanel panelInfo;
    private JLayeredPane panelCapas;
	private JScrollPane panelScrollMap;
	
	private JLabel pointLabel;
	private JLabel coinsLabel;
	private JLabel worldLabel;
	private JLabel timeLabel;
	
	private Font font;


    public GameScreenPanel(Font font) throws IOException {
    	this.font = font;
    	setPreferredSize(new Dimension(Constants.PANEL_WIDTH, Constants.PANEL_HIGH));
    	createPanelCapas();
        addPanelMap();
        addPanelInfo();
    }

    public void updateInfPlayer(Player player) {
    	updateLabelInformation(player);
    }
    
    public void updateLabelInformation(Player player) {

    }
    
    public void setFrame(ViewsController frame) {
    	this.frame = frame;
    	keyListener(frame);
    }
    
    protected String textDigits(int numero, int digitos) {
		String texto_autocompletado = "";
		if (range(numero, 0, 9)) {
			texto_autocompletado = "0000" + numero;
		}else {
			if (range(numero, 10, 99)) {
				texto_autocompletado = "000" + numero;
			}else {
				if (range(numero, 100, 999)) {
					texto_autocompletado = "00" + numero;
				}else {
					if(range(numero, 1000, 9999)) {
						texto_autocompletado = "0" + numero;
					}else {
						texto_autocompletado += numero;
					}
				}
			}
		}
		return texto_autocompletado;
	}
    
    protected boolean range(int num, int min, int max) {
		boolean aux;
		aux = num >= min;
		aux = aux && (num <= max);
		return aux;
	}


    protected void createPanelCapas() {
    	panelCapas = new JLayeredPane();
    	panelCapas.setPreferredSize(new Dimension(Constants.PANEL_WIDTH, Constants.PANEL_HIGH));
    	
        add(panelCapas, BorderLayout.CENTER);
    }
    protected void addPanelMap() throws IOException {
        String imagePath = "Sprites/Fondos/background.jpg";
        Image image = ImageIO.read(new File(imagePath));

        panelMap = new BackGroundPanel(image);
        
        panelMap.setPreferredSize(new Dimension(Constants.PANEL_MAP_WIDTH, Constants.PANEL_HIGH));

        panelScrollMap = new JScrollPane(panelMap);
        panelScrollMap.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        panelScrollMap.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        panelScrollMap.setBounds(0, 0, Constants.PANEL_WIDTH, Constants.PANEL_HIGH);
        panelScrollMap.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        panelCapas.add(panelScrollMap, Integer.valueOf(0));
	}
    
    protected void addPanelInfo() {
        panelInfo = new JPanel(null);
        panelInfo.setOpaque(false);
        panelInfo.setBounds(0, 0, Constants.PANEL_WIDTH, Constants.PANEL_HIGH);
        initLabels();
        panelCapas.add(panelInfo, Integer.valueOf(1));
    }
    
    public void updateLabels(int points, int coins, int time) {
    	pointLabel.setText("Puntaje: " + points);
    	coinsLabel.setText("Monedas: " + coins);
    	timeLabel.setText("Tiempo: " + time);
    	
    }
    
    public void updateLevel(int level) {
    	worldLabel.setText("World: " + level);
    }
    
    private void initLabels() {
        
    	pointLabel = new JLabel("Puntaje: " + 0);
    	pointLabel.setFont(font);
    	pointLabel.setBounds(60, 10, 200, 30);
    	pointLabel.setForeground(Color.WHITE);
        panelInfo.add(pointLabel);

        coinsLabel = new JLabel("Monedas: " + 0);
        coinsLabel.setFont(font);
        coinsLabel.setBounds(255, 10, 200, 30);
        coinsLabel.setForeground(Color.WHITE);
        panelInfo.add(coinsLabel);
        
        worldLabel = new JLabel("World: " + 0);
        worldLabel.setFont(font);
        worldLabel.setBounds(450, 10, 200, 30);
        worldLabel.setForeground(Color.WHITE);
        panelInfo.add(worldLabel);
        
        timeLabel = new JLabel("Tiempo: " + 0);
        timeLabel.setFont(font);
        timeLabel.setBounds(610, 10, 200, 30);
        timeLabel.setForeground(Color.WHITE);
        panelInfo.add(timeLabel);
    }
    
    public boolean isInViewport(Enemy e) {
	   boolean retorno = false;
	   if(e.getHitbox() != null)
		   if(panelMap.getVisibleRect().intersects(e.getHitbox())) {
			   retorno = true;
		   }
	   return retorno;   
    }
    
    public Observer addPlataform(Plataform plataform) {
    	EntityObserver plataformObserver = new EntityObserver(plataform);
    	panelMap.add(plataformObserver);
    	panelMap.revalidate();
    	panelMap.repaint();
    	return plataformObserver;
    }
    
    public Observer addEnemy(Enemy entity) {
    	EntityObserver entityObserver = new EntityObserver(entity);
    	panelMap.add(entityObserver);
    	panelMap.revalidate();
    	panelMap.repaint();
    	return entityObserver;
    }
    
    public Observer addPowerUp(PowerUp power) {
    	PowerUpObserver powerObserver = new PowerUpObserver(power, panelMap);
    	if(power.isActive())
    		powerObserver.paint();
    	return powerObserver;
    }
    
    public void addFireBallGame(FireBall fireBall) {
    	frame.getGame().addFireBalls(fireBall);
    }
    
    public Observer addFireBall(FireBall fireball) {
    	FireBallObserver fireObserver = new FireBallObserver(fireball, panelMap);
    	fireObserver.paint();
    	return fireObserver;
    }
    
    public Observer addEntityPlayer(Player player) {
    	ObserverPlayer observerPlayer = new ObserverPlayer(this, player);
    	panelMap.add(observerPlayer);
    	panelMap.revalidate();
    	panelMap.repaint();
    	frame.getGame().getPlayer().getInput().setPanel(this);
		return observerPlayer;
    }
    
    public int scrollRight(int playerX) {
    	JViewport viewport = panelScrollMap.getViewport();
        Point viewPosition = viewport.getViewPosition();
        int margin = 170;
        if (playerX > viewPosition.x + margin && playerX < viewPosition.x + viewport.getWidth() - margin) {
            viewPosition.x = playerX - margin;
        }
        viewPosition.x = Math.max(0, Math.min(viewPosition.x, panelMap.getWidth() - viewport.getWidth()));
        viewport.setViewPosition(viewPosition);
        return viewPosition.x;
    }
    
    public void reloadGameComponents() {
    	removeAll(); 
        try {
            createPanelCapas();  
            addPanelMap();       
            addPanelInfo();     
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        revalidate();  
        repaint();    
    }
    
    public void returnToMenu() {
    	frame.showPanel("menu");
    	frame.endGame();
    	reloadGameComponents();
    	SoundManager.getInstance().stopAllSounds();
    }
    
    public void keyListener(ViewsController frame) {
    	addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                	returnToMenu();
                }
            }
        });
    }
  
    @Override
	public String getPanelName() {
		return "juego";
	}

	
}

