package platforms;
import factory.FactoryPlataforms;
import factory.FactorySprite;
import factory.Sprite;
import visitors.VisitorEntityPlatform;

public class CoinBlock extends SolidBlock {
	public int totalCoin;
	
	public CoinBlock(int x, int y, Sprite sprite) {
		super(x, y, sprite);
		totalCoin = 10;
	}
	
	public int getTotalCoin() {
		return totalCoin;
	}
	
	public void setTotalCoin(int coin) {
		totalCoin += coin;
	}
	
	public void transform(FactorySprite factory) {
		FactoryPlataforms fabrica = new FactoryPlataforms(factory);
		active = false;
		observer.setEntity(fabrica.getSolidBlock((int)this.getCoordX(), (int)this.getCoordY()));
		observer.update();
	}

	@Override
	public void accept(VisitorEntityPlatform visitor) {
		visitor.visit(this);
	}

}
