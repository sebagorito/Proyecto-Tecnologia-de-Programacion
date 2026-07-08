package games;


import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException; 
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import collisions.CollisionHandler;
import factory.FactorySprite;
import entities.Enemy;
import entities.Entiti;
import entities.FireBall;
import entities.Player;
import entities.PowerUp;
import observers.Observer;
import parser.LoadEntities;
import platforms.Plataform;
import player.Mario;
import player.StateMario;
import views.ViewsController;

public class Game {

    private Player player;
    private Map map;
    private LoadEntities loadEntities;
    private FactorySprite spriteJugador;
    private ViewsController viewsController;
    private Timer gameTimer, timerAux;
    private CollisionHandler collision;
    private float timerLevel = 10;
    private boolean timerLevelRunning = false;
   
    private int level;
    
    private List<FireBall> listFireBall;
    private boolean animationStarted = false;
    private boolean running;

    public Game() throws IOException {
        running = false;
        listFireBall = new ArrayList<FireBall>();
    }
    public void setViewController(ViewsController viewsController) {
    	this.viewsController = viewsController;
    }
    
    public void setLoadEntities() {
    	loadEntities = new LoadEntities();
    	spriteJugador = loadEntities.getFactory();
    	
    }
    public Player getPlayer() {
    	return player;
    }
    
    public boolean getCorriendo() {
    	return running;

    }
    
    public List<FireBall> getFireBalls(){
    	return listFireBall;
    }
    public void registerObserver() {
    	registerPlayer(player);
    	registerPlataform(map.getPlataforms());
    	registerEntities(map.getEnemy());
    	registerPowerUp(map.getPowerUp());
    }
    
    public void registerPlayer(Player player) {
    	Observer playerObserver = viewsController.registerPlayer(player);
    	player.registerObserver(playerObserver);
    }
    
    public void registerEntities(List<? extends Enemy> enemies) {
    	for(Enemy enemy:enemies) {
    		Observer observer = viewsController.registerEnemy(enemy);
    		enemy.registerObserver(observer);
    	}
    }
    
    public void registerPlataform(List<? extends Plataform> plataforms) {
    	for(Plataform plataform:plataforms) {
    		Observer observer = viewsController.registerPlataform(plataform);
    		plataform.registerObserver(observer);
    	}
    }
    
    public void registerPowerUp(List<? extends PowerUp> powerUp) {
    	for(PowerUp power:powerUp) {
    		Observer observer = viewsController.registerPowerUp(power);
    		power.registerObserver(observer);
    	}
    }
   
    public void registerFireBall(FireBall fire) {
		Observer observer = viewsController.registerFireBall(fire);
		fire.registerObserver(observer);
    }
    
    public void start(int lives, int levelToLoad, int points, int coins) throws IOException {
    	level = levelToLoad;
    	map = loadEntities.loadLevel(levelToLoad);
    	Entiti marioEntity =   map.getMario();
	    int initialX = (int) marioEntity.getCoordX();
	    int initialY = (int) marioEntity.getCoordY();
	    player = new Player(initialX, initialY, spriteJugador, lives, true, points, coins);
	    StateMario marioState = new Mario(player);
	    player.setStateMario(marioState);
    	registerObserver();
    	running = true;
    	collision = new CollisionHandler (map,player);
    	gameTimer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(running)
            		update();
            }
        });
    	gameTimer.start();
    }
    
    public void startLevelTimer() {
        switch(level){
        case(1):
            timerLevel = 300;
            break;
        case(2):
            timerLevel = 250;
            break;
        case(3):
            timerLevel = 200;
            break;
        }
        timerLevelRunning = true;
    }
    
    public void stop() throws IOException {
    	if (gameTimer != null) {
    		gameTimer.stop();
        }
    	if (timerAux != null) {
    		timerAux.stop();
        }
    }
    public void checkCollision() {
    	collision.checkAllEnemy();
    	collision.checkPlataform();
    	collision.checkAllPoweUps();
    	collision.checkEnemyPlataform();
    	collision.checkFireBallEnemy(listFireBall);
    	collision.checkFireBallPlataform(listFireBall);
    	collision.checkPowerUpPlataform();
    }
    
    public void addFireBalls(FireBall fireball) {
    	listFireBall.add(fireball);
    	registerFireBall(fireball);
    }
    

    public void update() {
        player.update();
        if(timerLevelRunning)
            timerLevel -= 0.015;
        viewsController.getGamePanel().updateLabels(player.getPoints(), player.getCoins(), (int)timerLevel);
        if(timerLevel <= 0 && !player.isDead()) {
            player.die();
            System.out.println("Mario muere por tiempo");
        }
        if (!player.isFalling() && !player.isJumping() && !collision.bottomIntersectsPlatform()) {
        	player.setIsFalling(true);
            player.setVelocityY(player.getVelocityY() + player.getGravity());
        }
        if (player.isFalling()) {
            player.setCoordY((int)player.getCoordY() +(int)player.getVelocityY());
        }
        if((player.isDead() || player.isWinning() )&& !animationStarted) {
        	animationStarted = true;
        	timerLevelRunning = false;
        	timerAux = new Timer(4000, new ActionListener() { 
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	if(player.isWinning())
	            		level += 1;
	            	viewsController.playDeathScreen(player.getLife(), level, player.getPoints(), player.getCoins());
	            	if(player.getLife() == 0)
	            		SoundManager.getInstance().playSound("gameOver");
	            	System.out.println("Aparece la pantalla del gameover");
	            	if (timerAux != null) {
	            		timerAux.stop();
	            		
	    	        }
	            }
	        });
        	timerAux.setRepeats(false);
        	timerAux.start();
        }
        
        for (Enemy enemi:map.getEnemy()) {
        	if(viewsController.isInScreen(enemi)) {
        		enemi.setMoving(true);
        	}
        	
    		enemi.update();
    	}
        for (PowerUp powerUp : map.getPowerUp()) {
            if (powerUp.isActive()) {
            	powerUp.setMoving(true);
                powerUp.update(); 
            }
        }
        
        Iterator<FireBall> iterator = listFireBall.iterator();
        while (iterator.hasNext()) {
            FireBall fire = iterator.next();
            if (!fire.isActive()) {
                iterator.remove(); 
            } else {
                fire.update();
            }
        }
        
        checkCollision();
        viewsController.repaint();  
    }
    
}
