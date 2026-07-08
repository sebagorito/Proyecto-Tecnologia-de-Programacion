package platforms;

import factory.Sprite;
import visitors.VisitorEntityPlatform;

public class SolidBlock extends Plataform {

	public SolidBlock(int x, int y, Sprite sprite) {
		super(x, y, sprite);
	}

	@Override
	public void accept(VisitorEntityPlatform visitor) {
		visitor.visit(this);
		
	}

}
