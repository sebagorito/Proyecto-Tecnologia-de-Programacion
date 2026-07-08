package platforms;

import entities.PowerUp;
import factory.Sprite;
import visitors.VisitorEntityPlatform;
import factory.*;

public class LuckyBlock extends SolidBlock {
	
	protected PowerUp powerUp;
	private boolean active;
	
	public LuckyBlock(int x, int y, Sprite sprite, PowerUp powerUp) {
		super(x, y, sprite);
		this.powerUp = powerUp;
		active = true;
	}
	
	public PowerUp getPowerUp() {
		return powerUp;
	}
	
	public void setPowerUp(PowerUp power) {
		this.powerUp = power;
	}
	
	public boolean isActive() {
		return active;
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
