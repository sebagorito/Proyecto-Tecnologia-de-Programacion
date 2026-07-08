package platforms;

import factory.Sprite;
import visitors.VisitorEntityPlatform;

public class SolidBrick extends SolidBlock{

	public SolidBrick(int x, int y, Sprite sprite) {
		super(x, y, sprite);
	}
	
	public void removeBrick() {
		this.setSprite(null);
	}

	@Override
	public void accept(VisitorEntityPlatform visitor) {
		visitor.visit(this);
	}
	
	

}
