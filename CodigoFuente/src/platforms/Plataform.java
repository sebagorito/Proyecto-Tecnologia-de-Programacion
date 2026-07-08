package platforms;
import visitors.VisitedPlatform;
import visitors.VisitorEntityPlatform;
import entities.Entiti;
import factory.Sprite;

public abstract class  Plataform extends Entiti implements VisitedPlatform{
	protected int coordX;
	protected int coordY;
	protected Sprite sprite;
	
	public Plataform (int x, int y, Sprite sprite) {
		super(x,y,sprite);
	}
	
	@Override
	public abstract void accept(VisitorEntityPlatform visitor);

	

}
