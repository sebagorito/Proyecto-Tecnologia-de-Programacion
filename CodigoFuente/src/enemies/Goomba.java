package enemies;
import entities.Enemy;
import factory.Sprite;
import visitors.VisitorMario;

public class Goomba extends Enemy{
	int deadPoints;
	
	public Goomba(int x, int y, Sprite sprite) {
		super(x, y, sprite);
	}

	public void accept(VisitorMario visitor) {
		visitor.visit(this);
		
	}
}
