package entities;

import java.awt.Rectangle;

import enemies.BuzzyBeetle;
import enemies.Goomba;
import enemies.KoopaTroopa;
import enemies.Lakitu;
import enemies.PiranhaPlant;
import enemies.Spiny;
import factory.Sprite;
import pickUps.Coin;
import pickUps.FireFlower;
import pickUps.GreenMush;
import pickUps.Star;
import pickUps.SuperMush;
import platforms.Flag;
import platforms.LuckyBlock;
import platforms.Plataform;
import platforms.SolidBlock;
import platforms.SolidBrick;
import visitors.VisitorEntityPlatform;
import visitors.VisitorMario;

public class FireBall extends Entiti implements VisitorEntityPlatform, VisitorMario{
	 private boolean direction; 
	 private float gravity = 0.2f; 
	 private float velocityY = 3f; 
	 protected Rectangle top;
	 	 
	public FireBall(int x, int y,  Sprite spriteFireBall) {
		super(x,y, spriteFireBall);
		this.direction = false;
		this.active = true;
		this.speed = 6f;
		this.isFalling = true;
	}
	
	@Override
	public void update() {
       
        if (!direction) {
            coordX -= speed;
        } else {
        	coordX += speed;
        }
        
   
        if (isFalling) {
        	velocityY += gravity;
        }
        coordY += velocityY;
        notifyObserver();
        
        if (coordY > 600) {
            active = false;
            observer.remove();
        }
	 }
	

	public void setDirection(boolean direction) {
		this.direction = direction;
	}
	
	@Override
	public void visit(SolidBlock b) {
		handlePlatformCollision(b);
	}
	@Override
	public void visit(LuckyBlock l) {
		handlePlatformCollision(l);
	}
	@Override
	public void visit(SolidBrick b) {
		handlePlatformCollision(b);
	}
	@Override
	public void visit(Flag flag) {}
	@Override
	public void visit(FireFlower f) {}
	@Override
	public void visit(GreenMush g) {}
	@Override
	public void visit(SuperMush s) {}
	@Override
	public void visit(Star s) {}
	@Override
	public void visit(Coin c) {}
	
	@Override
	public void visit(Lakitu l) {
		 l.die();
	     this.setActive(false);
	     System.out.println("La bola de fuego ha eliminado a un enemigo.");
	}
	@Override
	public void visit(Goomba goomba) {
		 goomba.die();
	     active = false;
	     observer.remove();
	     System.out.println("La bola de fuego ha eliminado a un enemigo.");
	}
	@Override
	public void visit(PiranhaPlant p) {
		 p.die();
		 this.setActive(false);
		 observer.remove();
	     System.out.println("La bola de fuego ha eliminado a un enemigo.");
	}
	@Override
	public void visit(BuzzyBeetle b) {
		 b.die();
         this.setActive(false);
         observer.remove();
	     System.out.println("La bola de fuego ha eliminado a un enemigo.");
	}
	@Override
	public void visit(KoopaTroopa k) {
	 	k.die();
        this.setActive(false);
        observer.remove();
        System.out.println("La bola de fuego ha eliminado a un enemigo.");
	}

	@Override
	public void visit(Spiny s) {
	 	s.die();
        this.setActive(false);
        observer.remove();
        System.out.println("La bola de fuego ha eliminado a un enemigo.");
	}
	
	 private void handlePlatformCollision(Plataform p) {
	        if (bottom.intersects(p.getHitbox())) {
	            coordY = p.getHitbox().y - hitbox.height;
	            velocityY = -3f; 
	        } else if (left.intersects(p.getHitbox()) || right.intersects(p.getHitbox())) {

	            active = false;
	            observer.remove();
	        }
	    }
}
