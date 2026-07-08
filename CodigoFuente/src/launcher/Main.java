package launcher;
import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.SwingUtilities;

import games.Game;
import views.ViewsController;

public class Main {

	public static void main(String[] args) {		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Game juego = new Game();
					ViewsController controlador_vistas = new ViewsController(juego);
					juego.setViewController(controlador_vistas);
					controlador_vistas.showPanel("menu");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}


