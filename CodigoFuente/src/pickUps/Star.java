package pickUps;
import entities.Player;
import entities.PowerUp;
import factory.Sprite;
import visitors.VisitorMario;
public class Star extends PowerUp {

	public Star(int x, int y, Sprite sprite) {
		super(x, y, sprite);
	}
	
	public void applyEffect(Player player) {
		player.getStateMario().collectStar();
	}

	@Override
	public void accept(VisitorMario visitor) {
		visitor.visit(this);
		System.out.println("Agarre una estrella");
	
	}
}
