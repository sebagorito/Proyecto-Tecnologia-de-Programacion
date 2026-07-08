package platforms;

import factory.Sprite;
import visitors.VisitorEntityPlatform;

public class Floor extends SolidBlock{

	public Floor(int x, int y, Sprite sprite) {
		super(x, y, sprite);
	}

	public void accept(VisitorEntityPlatform visitor) {
		visitor.visit(this);
	}
	
	

}