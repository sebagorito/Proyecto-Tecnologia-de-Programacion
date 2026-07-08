package enemies;

import entities.Enemy;
import factory.Sprite;
import platforms.SolidBlock;
import visitors.VisitorMario;

public class PiranhaPlant extends Enemy {
    
    private boolean directionVertical; 
    private static final int MOVEMENT_RANGE = 40;
    private int initialY;

    public PiranhaPlant(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        this.initialY = y; 
        this.directionVertical = false; 
        this.velocityY = 1f;
    }

    @Override
    public void update() {
        int minY = initialY - MOVEMENT_RANGE; 
        int maxY = initialY + MOVEMENT_RANGE; 

        if (directionVertical) {
           
            coordY -= velocityY;
            if (coordY <= minY) {
                directionVertical = false;
            }
        } else {
            coordY += velocityY;
            if (coordY >= maxY) {
                directionVertical = true;
            }
        }
        notifyObserver();
    }

    @Override
    public void accept(VisitorMario visitor) {
        visitor.visit(this);
    }
    public void visit(SolidBlock b) {
    	
    }
    public int die() {
        
    	return getDeadPoints();
    }
}
