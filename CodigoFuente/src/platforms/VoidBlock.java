package platforms;

import factory.Sprite;
import visitors.VisitorEntityPlatform;

public class VoidBlock extends Plataform{

	public VoidBlock(int x, int y, Sprite sprite) {
		super(x, y, sprite);
	}

	@Override
	public void accept(VisitorEntityPlatform visitor) {
	
		
	}

}
