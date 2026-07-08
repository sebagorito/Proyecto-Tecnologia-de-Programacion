package enemies;
import entities.Enemy;
import factory.Sprite;
import visitors.VisitorMario;
public class Lakitu extends Enemy{

	public Lakitu(int x, int y, Sprite sprite) {
		super(x, y, sprite);
	}

	@Override
	public void accept(VisitorMario visitor) {
		visitor.visit(this);
	}

}
