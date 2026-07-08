package observers;

import entities.Entiti;

public class EntityObserver extends RenderObserver{
	private static final long serialVersionUID = 1L;

	public EntityObserver(Entiti entity) {
		super(entity);
		update();
	}
}
