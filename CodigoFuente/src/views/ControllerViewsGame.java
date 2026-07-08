package views;
import entities.Enemy;
import entities.Player;
import entities.PowerUp;
import observers.Observer;
import platforms.Plataform;
import entities.FireBall;

public interface ControllerViewsGame {
	public Observer registerEnemy(Enemy enemy);
	public Observer registerPlayer(Player player);
	public Observer registerPowerUp(PowerUp powerUp);
	public Observer registerPlataform(Plataform plataform);
	public Observer registerFireBall(FireBall fireBall);
	public void showPanelMap();
	public void showPanelEndGame();
}
