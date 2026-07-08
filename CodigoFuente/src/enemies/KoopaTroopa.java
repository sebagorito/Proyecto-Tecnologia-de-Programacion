package enemies;

import entities.Enemy;
import factory.FactorySprite;
import factory.Sprite;
import visitors.VisitorMario;

public class KoopaTroopa extends Enemy {
	public static final int WALKING = 0;
    public static final int SHELL = 1;
    private FactorySprite factory;

    private int state = WALKING;

    public KoopaTroopa(int x, int y, Sprite sprite, FactorySprite factory) {
        super(x, y, sprite);
        this.factory = factory;
    }
	public void movingShellLeft() {
		this.coordX -= speed;
	}
	
	public void movingShellRight() {
		this.coordX -= speed;
	}
	public void changeState() {
	    if (state == WALKING) {
	        state = SHELL;
	        this.setSprite(factory.getShell());
	        this.speed = 0;
	    } else {
	        state = WALKING;
	        this.setSprite(factory.getkoopa());
	    }
	}

    public int getState() {
        return state;
    }

	@Override
	public void accept(VisitorMario visitor) {
		visitor.visit(this);
	}

}
