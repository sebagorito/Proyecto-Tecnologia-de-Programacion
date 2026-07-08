package entities;
import factory.Sprite;
import visitors.ElementEntity;
import visitors.VisitorEntityPlatform;

public abstract class PowerUp extends Entiti implements ElementEntity, VisitorEntityPlatform{
	public PowerUp(int x, int y, Sprite sprite) {
		super(x,y, sprite);
        this.speed = 1.0f;
        this.direction = true;
        this.isMoving  = false;
        this.isFalling = true;
        this.gravity =0.5f;
        this.velocityY = 0;
        this.divideSubHitboxes();
	}

}
