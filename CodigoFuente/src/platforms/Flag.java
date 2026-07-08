package platforms;

import factory.Sprite;
import visitors.VisitorEntityPlatform;

public class Flag extends Plataform{
	private boolean slidingDownFlag;
	private float velocitY;
	private int width;
	private int height;
	
	public Flag(int x, int y, Sprite sprite) {
		super(x,y, sprite);
		slidingDownFlag = false;
		velocitY =1f;
		width = sprite.getImage().getWidth(null);
		height = sprite.getImage().getHeight(null);
	}
	
	public void setDownFlag(boolean down) {
		slidingDownFlag = down;
	}
	public void slideDownFlag() {
        if (slidingDownFlag) {
            coordY += this.velocitY; 
            if (coordY >= 40) { 
                slidingDownFlag = false;
            }
        }
    }
	
	public void update() {
		if (slidingDownFlag) {
            slideDownFlag(); 
            return; 
        }
	}
	
	public void accept(VisitorEntityPlatform visitor) {
		visitor.visit(this);
	}

	public void updateHitbox() {
    	hitbox.setBounds((int)getCoordX() + width/2, (int)getCoordY(), 1, height);
	}
}
