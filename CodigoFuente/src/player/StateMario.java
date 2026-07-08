package player;
import entities.Enemy;
import factory.Sprite;
import platforms.Plataform;
import entities.Player;
public abstract class StateMario{

	protected Player player;
	protected boolean isInvincible = false;
	protected int invincibilityTimer = 0; 
	public StateMario( Player player) {
		this.player = player;
	}
	
	public Sprite getSprite() {
		return player.sprite;
	}
	 public  boolean isInvincible() {
		 return isInvincible;
	 }
	 public void setInvincible(boolean invincible, int duration) {
	        isInvincible = invincible;
	        invincibilityTimer = duration; 
	    }
	 public void update() {
	        if (isInvincible) {
	            invincibilityTimer -= 10; 
	            if (invincibilityTimer <= 0) {
	                isInvincible = false;
	                invincibilityTimer = 0;
	            }
	        }
	    }
	  public abstract String getStateName();
	  public abstract void collectSuperMush();
	  public abstract void collectFireFlower();
	  public abstract void collectStar();
	  public abstract void collectGreenMush();
	  public abstract void collectCoin();
	  public abstract void timeExpired();
	  public abstract void handleRunButton (boolean pressed);  
	  public abstract Sprite getSpriteRight();
	  public abstract Sprite getSpriteLeft();
	  public abstract void receiveDamage();
	  public abstract void handleEnemyCollision(Enemy enemy);
	  public abstract void handlePlatformCollision(Plataform platform);

	  public void onExit() {
	  
	  }
	  
	  
}
