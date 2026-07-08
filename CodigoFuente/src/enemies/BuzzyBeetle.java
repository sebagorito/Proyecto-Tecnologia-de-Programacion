package enemies;
import entities.Enemy;
import factory.FactorySprite;
import factory.Sprite;
import visitors.VisitorMario;

public class BuzzyBeetle extends Enemy {
	public static final int WALKING = 0;
	public static final int SHELL = 1;
	
	private int state = WALKING;
	private FactorySprite factory;
	
	public BuzzyBeetle(int x, int y, Sprite sprite, FactorySprite factory) {
        super(x, y, sprite);
        this.factory = factory;
    }
	@Override
	public void accept(VisitorMario visitor) {	
		visitor.visit(this);
	}	
	public void changeState() {
	    if (state == WALKING) {
	        state = SHELL;            
	        this.setSprite(factory.getBuzzyBeetleSmashed());
	        this.speed = 0;
	    } else
	    	die();
	}
	public int getState() {
		return state;
	}
}
