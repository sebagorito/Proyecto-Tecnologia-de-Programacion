package observers;

import entities.Entiti;

public interface Observer {
	public void update();
	public void remove();
	public void paint();
	public void setEntity(Entiti e);
}
