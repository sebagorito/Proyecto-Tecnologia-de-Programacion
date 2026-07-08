package observers;
import javax.swing.ImageIcon;

import javax.swing.JLabel;
import entities.Entiti;


public class RenderObserver extends JLabel implements Observer{
	
	private static final long serialVersionUID = 1L;
	protected Entiti entity;
	
	public RenderObserver(Entiti entity) {
		super();
        this.entity = entity;
    }
	
	@Override
	public void update() {
		updateImage();
		updateSizePixel();
		entity.updateHitboxes();
		repaint();
	}
	

	public void updateImage() {
		ImageIcon icono = new ImageIcon(entity.getSprite().getImage());
	    setIcon(icono);
    }
	
	public void updateSizePixel() {
		int width = this.getIcon().getIconWidth();
		int high = this.getIcon().getIconHeight();
		setBounds((int) entity.getCoordX(),(int) entity.getCoordY(), width, high);
	}

    public void remove() {
        setIcon(null);
        entity.getHitbox().setBounds(0, 0, 0, 0);
    }
    
    public void setEntity(Entiti e) {
    	this.entity = e;
    }

	@Override
	public void paint() {
		ImageIcon icono = new ImageIcon(entity.getSprite().getImage());
	    setIcon(icono);
		
	}
    
    
	
}
