package pickUps;
import entities.Player;
import entities.PowerUp;
import factory.Sprite;
import visitors.VisitorMario;


public class Coin extends PowerUp {

	public Coin(int x, int y, Sprite sprite) {
		super(x, y, sprite);
	}

	
	public void applyEffect(Player player) {
		player.getStateMario().collectCoin();
	}
	
	@Override
	public void update() {
		active = true;
		observer.paint();
	};
	
	@Override
	public void accept(VisitorMario visitor) {
		visitor.visit(this);
		System.out.println("Agarre una monedita");
	
	}
}
