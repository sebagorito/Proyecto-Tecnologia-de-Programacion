package platforms;

import entities.Enemy;
import factory.Sprite;
import visitors.VisitorEntityPlatform;

public class Pipe extends SolidBlock{
	Enemy pirahnaPlant;
	
	public Pipe(int x, int y, Sprite sprite, Enemy pirahnaPlant) {
		super(x, y, sprite);
		this.pirahnaPlant = pirahnaPlant;
	}
	
	public Enemy getPirahna() {
		return pirahnaPlant;
	}
	
	public void setPirahna(Enemy pirahnaPlant) {
		this.pirahnaPlant = pirahnaPlant;
	}
	
	public boolean hasPlant() {
		if(pirahnaPlant != null) {
			return true;
		}else {
			return false;
		}
	}
	
	public void accept(VisitorEntityPlatform visitor) {
		visitor.visit(this);
	}

}
