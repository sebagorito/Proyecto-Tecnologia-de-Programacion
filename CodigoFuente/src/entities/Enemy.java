package entities;
import factory.Sprite;
import games.SoundManager;
import visitors.ElementEntity;
import visitors.VisitorEntityPlatform;


public abstract class Enemy extends Entiti implements ElementEntity, VisitorEntityPlatform{
	protected int points;
	protected int deadPoints;
	
	 public Enemy(int x, int y, Sprite sprite) {
	        super(x, y, sprite);
	        this.points = 100; 
	        this.deadPoints = 200; 
	        this.speed = 1.0f;
	        this.direction = false;
	        this.isMoving  = false;
	        this.isFalling = true;
	        this.gravity =0.5f;
	        this.velocityY = 0;
	        this.active = true;
	    }
	
	public int getPoints() {
		return points;
	}
	
	public int getDeadPoints() {
		return deadPoints;
	}

	public int die() {
		SoundManager.getInstance().playSound("goomba");
        observer.remove();
        registerObserver(null);
        return deadPoints;
	}

}
