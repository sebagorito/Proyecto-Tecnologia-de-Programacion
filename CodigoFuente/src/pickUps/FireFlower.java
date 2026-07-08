package pickUps;
import entities.Player;
import entities.PowerUp;
import factory.Sprite;
import visitors.VisitorMario;
public class FireFlower extends PowerUp{

	public FireFlower(int x, int y, Sprite sprite) {
		super(x, y, sprite);
	}

	
	public void applyEffect(Player player) {
		player.getStateMario().collectFireFlower();
	}
	
	@Override
	public void update() {};

	@Override
	public void accept(VisitorMario visitor) {
		visitor.visit(this);
		System.out.println("Agarre un flor de fuego");
	
	}
}
