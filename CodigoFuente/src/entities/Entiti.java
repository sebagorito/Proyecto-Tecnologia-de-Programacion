package entities;
import factory.Sprite;
import observers.Observer;
import platforms.CoinBlock;
import platforms.Flag;
import platforms.LuckyBlock;
import platforms.Plataform;
import platforms.SolidBlock;
import platforms.SolidBrick;

import java.awt.Rectangle;

public abstract class  Entiti {
	protected int coordX;
	protected int coordY;
	public Sprite sprite;
	protected float speed;
	protected boolean direction;
	protected Observer observer;
	protected Rectangle hitbox;
	protected boolean active;
	protected boolean isMoving;
	protected boolean isFalling;
	protected float gravity;
	protected float velocityY;
	protected Rectangle left;
	protected Rectangle right;
	protected Rectangle bottom;
	protected int hitboxSideThickness = 5;
	
	public Entiti(int x, int y, Sprite sprite) {
		coordX = x;
		coordY = y;
		this.sprite = sprite;
		this.direction = true;
		hitbox = new Rectangle(coordX, coordY, sprite.getImage().getWidth(null), sprite.getImage().getHeight(null));
		this.speed = 1.0f;
        this.isMoving  = false;
        this.isFalling = true;
        this.gravity =0.5f;
        this.velocityY = 0;
        divideSubHitboxes();
	}
	
	public float getCoordX() {
		return coordX;
	}
	
	public void setCoordX(int x) {
		coordX = x;
	}
	
	public float getCoordY() {
		return coordY;
	}
	
	public void setCoordY(int y) {
		coordY = y;
	}
	
	public boolean getDirection() {
		return direction;
	}
	
	public void setDirection(boolean direction) {
		this.direction = direction;
	}
	
	public void setMoving(boolean moving) {
		this.isMoving = moving;
	}
	
	public float getSpeed() {
		return speed;
	}
	
	public Sprite getSprite(){
		return sprite;
	}
	public void setActive(boolean isActive) {
        this.active = isActive;
    }

    public boolean isActive() {
        return active;
    }
	
	public void changeSpeed(float speed) {
		this.speed = speed;
	}
	
	public void changeDirection(boolean direction) {
		this.direction = direction;
	}
	
	public void registerObserver(Observer observer) {
		this.observer = observer;
	}
	
	public Observer getObserver() {
		return observer;
	}
	
	public Rectangle getHitbox() {
		return hitbox;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	public void moveLeft() {
		coordX -= speed;
	}
	public void moveRight() {
		coordX += speed;
	}
	
	public void update() {
		if (isMoving && active) {
			 if (!direction) {
				 moveLeft();
			 } else {
				 moveRight();
			 }
		 }
	       
        if (isFalling) {
	        velocityY += gravity;
	        velocityY = Math.min(velocityY, 5);
	        coordY += (int) velocityY;
	    } else {
	        velocityY = 0; 
	    }
	    notifyObserver();
	}
	
	public void notifyObserver() {
        if (observer != null) {
            observer.update();
        }
    }
	public void divideSubHitboxes() {
	    left = new Rectangle(getHitbox().x, getHitbox().y + hitboxSideThickness, hitboxSideThickness, (int) hitbox.getHeight() - (2 * hitboxSideThickness));
	    right = new Rectangle(getHitbox().x + (int) hitbox.getWidth() - hitboxSideThickness, getHitbox().y + hitboxSideThickness, hitboxSideThickness, (int) hitbox.getHeight() - (2 * hitboxSideThickness));
	    bottom = new Rectangle(getHitbox().x + hitboxSideThickness, getHitbox().y + (int) hitbox.getHeight() - hitboxSideThickness, (int) hitbox.getWidth() - (2 * hitboxSideThickness), hitboxSideThickness);
	}
	
    public void updateHitboxes() {
    	hitbox.setBounds(coordX, coordY, sprite.getImage().getWidth(null), sprite.getImage().getHeight(null));
    	bottom.setLocation(getHitbox().x + hitboxSideThickness, getHitbox().y + (int)hitbox.getHeight());
    	left.setLocation(getHitbox().x, getHitbox().y + hitboxSideThickness);
    	right.setLocation(getHitbox().x + (int)hitbox.getWidth() - hitboxSideThickness, getHitbox().y + hitboxSideThickness);
    }
	
    public void turnHitboxesOff() {
    	hitbox.setBounds(0, 0, 0, 0);
    	bottom.setBounds(0, 0, 0, 0);
    	left.setBounds(0, 0, 0, 0);
    	right.setBounds(0, 0, 0, 0);
    }
   
	public void visit(SolidBlock b) {
		visitBlock(b);
	}
	public void visit(LuckyBlock l) {
		visitBlock(l);
	}
	public void visit(SolidBrick b) {
		visitBlock(b);
	}
	
	public void visitBlock(Plataform p) {
        if (left.intersects(p.getHitbox())) {
            direction = true;
        } else if (right.intersects(p.getHitbox())) {
            direction = false;
        }
	        
	    if (bottom.intersects(p.getHitbox())) {
	    	velocityY = 0;
            isFalling = true; 
            coordY = p.getHitbox().y - getHitbox().height;
	    }
	}
	
	public void visit(Flag f) {
		visitBlock(f);
	}
	
	public void visit(CoinBlock c) {
		visitBlock(c);
	}
}
