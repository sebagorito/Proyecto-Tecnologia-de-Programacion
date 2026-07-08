package enemies;
import entities.Enemy;
import factory.Sprite;
import visitors.VisitorMario;
public class Spiny extends Enemy {

	public Spiny(int x, int y, Sprite sprite) {
		super(x, y, sprite);
	}

	@Override
	public void accept(VisitorMario visitor) {
		visitor.visit(this);
	}

}
