package entities;

import factory.Sprite;
import games.InputManager;
import games.SoundManager;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import enemies.BuzzyBeetle;
import enemies.Goomba;
import enemies.KoopaTroopa;
import enemies.Lakitu;
import enemies.PiranhaPlant;
import enemies.Spiny;
import observers.Observer;
import pickUps.*;
import platforms.*;
import player.Mario;
import player.StateMario;
import visitors.VisitorEntityPlatform;
import visitors.VisitorMario;
import factory.FactorySprite;
import views.Constants;

public class Player extends Entiti implements VisitorMario, VisitorEntityPlatform {
    protected int points = 0;
    protected int coins = 0;
    protected int life;
    protected observers.Observer observerPlayer;

    private StateMario mario;
    private boolean isJumping = false;
    private boolean isFalling = false;
    private boolean isRunning = false;
    private int jumpStrength = 13; 
    private float gravity =0.5f; 
    private FactorySprite factorySprite;
    private Sprite spriteLeft;
    private Sprite spriteRight;
    private Sprite spriteDead;
    private float velocityY = 0; 
    private boolean isDead;
    private boolean isWinning;
    public InputManager input;
    private Timer timerFlag;
    private Rectangle top;

    public Player(int x, int y, FactorySprite factorySprite, int lives, boolean direction, int points, int coins) {
        super(x, y, getInitialSprite(factorySprite));
        this.life = lives; 
        this.speed = 3;
        this.factorySprite = factorySprite; 
        this.spriteLeft = factorySprite.getMarioLeft();
        this.spriteRight = factorySprite.getMarioRight();
        this.spriteDead = factorySprite.getMarioDead();
        this.direction = direction;
        this.points = points;
        this.coins = coins;
        
        isDead = false;
        isWinning = false;
        input = new InputManager(this);
        divideSubHitboxes();

    }
    
    public void registerObserver(observers.Observer observer) {
        observerPlayer = observer;
    }
    
    public void setIsJumping(boolean jump) {
    	isJumping = jump;
    }
    
    public void setIsFalling(boolean falling) {
    	isFalling = falling;
    }
    
    public void setVelocityY(float velocity) {
    	velocityY = velocity;
    }

    public void setStateMario(StateMario newMario) {
    	if (this.mario != null) {
            mario.onExit();
        }
    	this.mario = newMario;
    	updateSpriteBasedOnState();
        if (observerPlayer != null) {
            observerPlayer.update();
        }
    }
   
    public int getPoints() {
        return points;
    }
    
    public int getCoins() {
    	return coins;
    }

    public int getLife() {
        return life;
    }

    public void addPoints(int cant) {
        points += cant;
    }
    
    public void addCoins() {
        coins++;
    }
    
    public void addLife() {
    	life++;
    }

    public float getSpeed() {
        return speed;
    }

    public StateMario getStateMario() {	
        return mario;
    }
    
    public boolean isJumping() {
    	return isJumping;
    }
    
    public boolean isFalling() {
    	return isFalling;
    }
    
    public float getVelocityY() {
    	return velocityY;
    }

    public float getJumpStrength() {
    	return jumpStrength;
    }
    
    public float getGravity() {
    	return gravity;
    }
    
    public Sprite getSpriteRight() {
    	return spriteRight;
    }
    
    public Sprite getSpriteLeft() {
    	return spriteLeft;
    }
    public FactorySprite getFactorySprite() {
        return this.factorySprite;
    }

    public static Sprite getInitialSprite(FactorySprite factorySprite) {
        return factorySprite.getMarioRight();
    }

    public boolean isDead() {
        return isDead;
    }
    
    public boolean isWinning() {
    	return isWinning;
    }
    
    public boolean isRunning() {
    	return isRunning;
    }
    
    public Observer getObserver() {
    	return observerPlayer;
    }
    
    public Rectangle getBottom() {
    	return bottom;
    }
    
    public InputManager getInput() {
    	return input;
    }
    @Override
    public void divideSubHitboxes() {
        top = new Rectangle(getHitbox().x + hitboxSideThickness, getHitbox().y, (int)hitbox.getWidth() - (2 * hitboxSideThickness), hitboxSideThickness);
        bottom = new Rectangle(getHitbox().x + hitboxSideThickness, getHitbox().y + (int)hitbox.getHeight(), (int)hitbox.getWidth() - (2 * hitboxSideThickness), hitboxSideThickness);
        left = new Rectangle(getHitbox().x, getHitbox().y + hitboxSideThickness, hitboxSideThickness, (int)hitbox.getHeight() - (2 * hitboxSideThickness));
        right = new Rectangle(getHitbox().x + (int)hitbox.getWidth() - hitboxSideThickness, getHitbox().y + hitboxSideThickness, hitboxSideThickness, (int)hitbox.getHeight() - (2 * hitboxSideThickness));
    }
    
    public void turnHitboxesOff() {
    	super.turnHitboxesOff();
    	top.setBounds(0, 0, 0, 0);
    }
    
    @Override
    public void updateHitboxes() {
    	super.updateHitboxes();
    	top.setLocation(getHitbox().x + hitboxSideThickness, getHitbox().y);   	
    }
    
    
    public void update() {
        input.update();
        mario.update();
        if(velocityY == 0) {
        	isJumping = false;
    		isFalling = false;
        }
        if(velocityY < 0) {
        	isJumping = true;
    		isFalling = false;
        }
        if(velocityY > 0) {
        	isFalling = true;
        	isJumping = false;
        }
        if(coordY > Constants.WINDOW_HIGH) {
        	if(!isDead) {
        		die(); 
        		this.setStateMario(new Mario(this));
        	}
        }
        if(isWinning && velocityY == 0) {
        	input.moveRight();
        	gravity = 0;
        }
        if(coins == 100) {
        	coins = 0;
        	life++;
        }
        getObserver().update();
    }
    private void updateSpriteBasedOnState() {
        spriteRight = mario.getSpriteRight();
        spriteLeft = mario.getSpriteLeft();
        this.sprite = direction ? spriteRight : spriteLeft;
    }
    public void die() {
        if (!isDead) {
            isDead = true;
            this.setSprite(spriteDead);
            changeSpeed(0);
            velocityY = 0; 
            life -= 1;
            bounce();
            turnHitboxesOff();
            input.shutDown();
            SoundManager.getInstance().stopAllSounds();
            SoundManager.getInstance().playSound("dead");
        }
    }
    private void bounce() {
   		velocityY = -5;
    }
       
    public void startRunning() {
    	if(!isRunning) {
    		speed += 2;
    		isRunning = true;
    		}
    }
    public void stopRunning() {
    	if(isRunning) {
    		speed -= 2;
    		isRunning = false;
    		}
    }
    private void playFlagAnimation(Flag f) {
		velocityY = 0;
		this.setCoordX(f.getHitbox().x + 30);
		setDirection(false);
		sprite = spriteLeft;
		gravity = 0.05f;

    }

   @Override
    public void visit(GreenMush g) {
    	mario.collectGreenMush();
        g.setActive(false);
        g.getObserver().remove();
        SoundManager.getInstance().playSound("life");
    }
    
    @Override
    public void visit(SuperMush s) {
        mario.collectSuperMush();
        s.setActive(false);
        s.getObserver().remove();
        SoundManager.getInstance().playSound("superMush");
        
    }

    @Override
    public void visit(Star s) {
    	 mario.collectStar();
         s.setActive(false);
         s.getObserver().remove();
    }

    @Override
    public void visit(Coin c) {
    	mario.collectCoin();
        c.setActive(false);
        c.getObserver().remove();
    	SoundManager.getInstance().playSound("coin");
    	addCoins();
    }

    @Override
    public void visit(Lakitu l) {
        if((left.intersects(l.getHitbox()) || right.intersects(l.getHitbox())) && !isFalling) {
               mario.handleEnemyCollision(l);
              }
              if(bottom.intersects(l.getHitbox())) {
                  points += l.die();
                  bounce();
              }
    }

  @Override
   public void visit(PiranhaPlant p) {
	  mario.handleEnemyCollision(p);
   }

   @Override
   public void visit(BuzzyBeetle b) {
       if(bottom.intersects(b.getHitbox()) && isFalling) {
           b.changeState();
           bounce();
       }

}

   
   @Override
   public void visit(Goomba goomba) {
	    if (bottom.intersects(goomba.getHitbox()) && isFalling ) {
       		bounce();
            points += goomba.die(); 
            SoundManager.getInstance().playSound("goomba");
        }else{
	   		mario.handleEnemyCollision(goomba);
        }
   	}

    @Override
    public void visit(KoopaTroopa k) {
    	if (k.getState() == KoopaTroopa.WALKING) {
           if(left.intersects(k.getHitbox()) || right.intersects(k.getHitbox())) {
    			mario.handleEnemyCollision(k);
    	   	}
    	   	if(bottom.intersects(k.getHitbox()) && isFalling) {
    	   		k.changeState();
    	   		bounce();
    	   	}
        } else if (k.getState() == KoopaTroopa.SHELL) {
        	if(left.intersects(k.getHitbox()) || right.intersects(k.getHitbox())) {
           	}
           	if(bottom.intersects(k.getHitbox()) && isFalling) {
           		bounce();
           		k.die();
           	}
        }
    }

    @Override
    public void visit(FireFlower f) { 
    	 mario.collectFireFlower();
         f.setActive(false);
         f.getObserver().remove();
    }
    
     
    
    
public void visit(SolidBlock b) {
    	
        if (bottom.intersects(b.getHitbox()) && !isJumping) {
            velocityY = 0;
            isJumping = false; 
            isFalling = false;  
        	coordY = (int)b.getCoordY() - (int)hitbox.getHeight();
        }else
	    if (top.intersects(b.getHitbox())) {
	    		SoundManager.getInstance().playSound("bump");
	            velocityY = 0;
	            isJumping = false;
	            isFalling = true; 
	            coordY = (int)(b.getHitbox().getY() + b.getHitbox().getHeight());	            
        }else
	    if (left.intersects(b.getHitbox())) {
	        	coordX = (int)(b.getCoordX() + b.getHitbox().getWidth());
        }else
	    if (right.intersects(b.getHitbox())) {
	        	coordX = (int)(b.getCoordX() - hitbox.getWidth());
        }
    }
    
    public void visit(LuckyBlock l) {
        if (bottom.intersects(l.getHitbox()) && !isJumping) {
            velocityY = 0;
            isJumping = false; 
            isFalling = false;  
        	coordY = (int)l.getCoordY() - (int)hitbox.getHeight();
        }else
	    if (top.intersects(l.getHitbox()) && isJumping) {
	    	SoundManager.getInstance().playSound("bump");
	        velocityY = 0;
	        isJumping = false;  
	        isFalling = true; 
	        coordY = (int)(l.getHitbox().getY() + l.getHitbox().getHeight()); 
		        if(l.isActive()) {
		           	if(l.getPowerUp() == null) {
		            	SoundManager.getInstance().playSound("coin");
		            	addCoins();
			        }else {
			           	SoundManager.getInstance().playSound("item");
			           	l.getPowerUp().getObserver().paint();
			            l.getPowerUp().setActive(true);
			        }
		            l.transform(factorySprite);
	            }
        }else
	    if (left.intersects(l.getHitbox())) {
	        	coordX = (int)(l.getCoordX() + l.getHitbox().getWidth());
        }else
	    if (right.intersects(l.getHitbox())) {
	        	coordX = (int)(l.getCoordX() - hitbox.getWidth());
        }
    }
	
	public void visit(SolidBrick b) {
		
	    if (bottom.intersects(b.getHitbox()) && !isJumping) {
	        velocityY = 0;
	        isJumping = false; 
	        isFalling = false;  
	    	coordY = (int)b.getCoordY() - (int)hitbox.getHeight();
	    }else
	    if (top.intersects(b.getHitbox()) && isJumping) {
	    		SoundManager.getInstance().playSound("bump");
	            velocityY = 0;
	            isJumping = false;  
	            isFalling = true; 
	            coordY = (int)(b.getHitbox().getY() + b.getHitbox().getHeight()); 
	            if(this.getStateMario().getStateName() == "SuperMario" || this.getStateMario().getStateName() == "MarioFire") {
	            	SoundManager.getInstance().playSound("break");
		            b.getObserver().remove();
	            }
	    }else
	    if (left.intersects(b.getHitbox())) {
	        	coordX = (int)(b.getCoordX() + b.getHitbox().getWidth());
	    }else
	    if (right.intersects(b.getHitbox())) {
	        	coordX = (int)(b.getCoordX() - hitbox.getWidth());
	    }
	    
	    
	}
	
	public void visit(CoinBlock c) {
		if (bottom.intersects(c.getHitbox()) && !isJumping) {
	        velocityY = 0;
	        isJumping = false; 
	        isFalling = false;  
	    	coordY = (int)c.getCoordY() - (int)hitbox.getHeight();
	    }else
	    if (top.intersects(c.getHitbox()) && isJumping) {
	    		SoundManager.getInstance().playSound("bump");
	            velocityY = 0;
	            isJumping = false;  
	            isFalling = true; 
	            coordY = (int)(c.getHitbox().getY() + c.getHitbox().getHeight());
	            if(c.getTotalCoin() > 0) {
	            	SoundManager.getInstance().playSound("coin");
		            c.setTotalCoin(-1);
		            addCoins();
	            } else {
	            	c.transform(factorySprite);
	            }
	    }else
	    if (left.intersects(c.getHitbox())) {
	        	coordX = (int)(c.getCoordX() + c.getHitbox().getWidth());
	    }else
	    if (right.intersects(c.getHitbox())) {
	        	coordX = (int)(c.getCoordX() - hitbox.getWidth());
	    }
	}
	
	public void visit(Flag f) {
		if(!isWinning) {
			isWinning = true;
			playFlagAnimation(f);
	        input.shutDown();
	        SoundManager.getInstance().stopAllSounds();
	        SoundManager.getInstance().playSound("flag");
	        f.setActive(false);
	        timerFlag = new Timer(3000, new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	if (timerFlag != null) {
	            		timerFlag.stop();
	            		
	    	        }
	            }
	        });
	        timerFlag.setRepeats(false);
	        timerFlag.start();
		}
	}

	@Override
	public void visit(Spiny spiny) {
	}
}