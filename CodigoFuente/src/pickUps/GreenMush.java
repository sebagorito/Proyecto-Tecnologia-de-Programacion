package pickUps;
import entities.Player;
import entities.PowerUp;
import factory.Sprite;
import visitors.VisitorMario;
public class GreenMush extends PowerUp {

	public GreenMush(int x, int y, Sprite sprite) {
		super(x, y, sprite);
	}

	
	public void applyEffect(Player player) {
		player.getStateMario().collectGreenMush();
	}

	@Override
	public void accept(VisitorMario visitor) {
		visitor.visit(this);
		System.out.println("Agarre un honguito verde");
	
	}
}
