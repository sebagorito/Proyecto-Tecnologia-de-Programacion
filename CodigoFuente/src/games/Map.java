package games;
import entities.*;
import java.util.LinkedList;
import java.util.List;
import platforms.*;

public class Map {
	protected List<Enemy> enemies;
	protected List<Plataform> plataforms;
	protected List<PowerUp> powerUps;
	protected Player mario;
	
	public Map() {
		enemies = new LinkedList<Enemy>();
		plataforms = new LinkedList<Plataform>();
		powerUps = new LinkedList<PowerUp>();
	}
	
	public void addPowerUp(PowerUp pick) {
		powerUps.add(pick);
	}
	
	public void addEnemy(Enemy enemy) {
		enemies.add(enemy);
	}
	
	public void addPlataforms(Plataform plataform) {
		plataforms.add(plataform);
	}
	
	public void setMario(Player mario) {
		this.mario = mario;
	}
	
	public List<Enemy> getEnemy(){
		return enemies;
	}
	
	public List<PowerUp> getPowerUp(){
		return powerUps;
	}
	
	public List<Plataform> getPlataforms(){
		return plataforms;
	}
	
	public Player getMario() {
		return mario;
	}
}
