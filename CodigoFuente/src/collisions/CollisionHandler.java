package collisions;

import java.util.List;
import entities.Enemy;
import entities.Entiti;
import entities.FireBall;
import entities.Player;
import entities.PowerUp;
import games.Map;
import platforms.Plataform;

public class CollisionHandler {
	private Map map;
	private Player player;
	
	
	public CollisionHandler(Map map, Player player) {
		this.map = map;
		this.player = player;
	}
	
	
    public static boolean checkCollision(Entiti entity1, Entiti entity2) {    	
        return entity1.getHitbox().intersects(entity2.getHitbox());
    }   
    
    public void checkEnemyPlataform() {
    	for(Enemy enemy : map.getEnemy()) {
    		for(Plataform plataform : map.getPlataforms()) {
    			plataform.accept(enemy);
    		}
    	}
    }
    
    public void checkAllEnemy() {
    	for (Enemy enemy : map.getEnemy()) {
            if (CollisionHandler.checkCollision(player, enemy)) {
                enemy.accept(player); 
            }
    	}
    }
    
    public void checkPlataform() {
    	for(Plataform plataform:map.getPlataforms()) {
    		if(CollisionHandler.checkCollision(player, plataform)) {
    			plataform.accept(player);
    		}
    	}
    }
    
	public boolean bottomIntersectsPlatform() {
	    for (Plataform block : map.getPlataforms()) {
	        if (player.getBottom().intersects(block.getHitbox())) {
	            return true;
	        }
	    }
	    return false;
	}
    
	public void checkAllPoweUps() {
	    for (PowerUp powerUp : map.getPowerUp()) {
	        if (powerUp.isActive() && CollisionHandler.checkCollision(player, powerUp)) {
	            powerUp.accept(player);
	        }
	    }
	}
	
	public void checkFireBallPlataform(List<? extends FireBall> fireBall) {
		for(Plataform plataform:map.getPlataforms()) {
			for(FireBall fire:fireBall) {
	    		if(fire != null && CollisionHandler.checkCollision(fire, plataform)) {
	    			plataform.accept(fire);
	    		}
			}
		}		
	}
	public void checkFireBallEnemy(List<? extends FireBall> fireBall) {
		for(Enemy enemy:map.getEnemy()) {
			for(FireBall fire:fireBall) {
	    		if(fire != null && CollisionHandler.checkCollision(fire, enemy)) {
	    			enemy.accept(fire);
	    		}
			}
		}
	}
	
	public void checkPowerUpPlataform() {
		for(PowerUp power : map.getPowerUp()) {
    		for(Plataform plataform : map.getPlataforms()) {
    			plataform.accept(power);
    		}
    	}
	}
	
}