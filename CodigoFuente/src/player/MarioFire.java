package player;

import platforms.Plataform;
import entities.Enemy;
import entities.FireBall;
import entities.Player;
import factory.FactoryEntities;
import factory.FactorySprite;
import factory.Sprite;
public class MarioFire extends StateMario{
	private FireBall fireball;
	FactoryEntities fabrica;
	
	public MarioFire(Player player) {
		super(player);
		fireball = null;
	}
	
	@Override
	public void collectSuperMush() {
		player.addPoints(50);
	}

	@Override
	public void collectGreenMush() {
		player.addLife();
		player.addPoints(100);
		
	}
	

	@Override
	public void collectFireFlower() {
		player.addPoints(50);
	}

	@Override
	public void collectStar() {
		player.setStateMario(new MarioInvincible(player, this));
		player.addPoints(35);
	}


	@Override
	public void collectCoin() {
		player.addPoints(5);
	}

	@Override
	public void receiveDamage() {
		player.setStateMario(new Mario(player));
	}
	
	public FireBall getFireBall() {
		return fireball;
	}

	@Override
	public void timeExpired() {	
	}


	@Override
	public void handleEnemyCollision(Enemy enemy) {
		 if (!isInvincible) {
		        receiveDamage();
		    }
		
	}

	@Override
	public void handlePlatformCollision(Plataform platform) {
	}

	@Override
	public String getStateName() {
		return "MarioFire";
	}

	@Override
	public void onExit() {
	}
	public FireBall shootFireball(FactorySprite factory) {
		fabrica = new FactoryEntities(factory);
		fireball = fabrica.getFireBall((int)player.getCoordX(), (int)player.getCoordY()+10);
		return fireball;
	}

	@Override
	public void handleRunButton(boolean pressed) {
		if (pressed) {
			player.startRunning();
		} else {
			player.stopRunning();
		}
	}

	@Override
	public Sprite getSpriteRight() {
	    return player.getFactorySprite().getMarioFireRight();
	}

	@Override
	public Sprite getSpriteLeft() {
	    return player.getFactorySprite().getMarioFireLeft();
	}



	
	
	
}

