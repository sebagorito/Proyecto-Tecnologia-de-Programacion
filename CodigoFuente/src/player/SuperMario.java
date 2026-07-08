package player;

import factory.Sprite;
import platforms.Plataform;
import entities.Enemy;
import entities.Player;
public class SuperMario extends StateMario{
	
	public SuperMario(Player player) {
		super(player);
	}
	public void collectSuperMush() {
		player.addPoints(50);
	}
	public void collectFireFlower() {
		player.setStateMario(new MarioFire(player));
		player.addPoints(30);
	}
	public void collectStar() {
		player.setStateMario(new MarioInvincible(player, this));
		player.addPoints(30);
	}
	public void collectGreenMush() {
		player.addLife();
		player.addPoints(100);
	}
	public void collectCoin() {
		player.addPoints(5);
	}
	public void receiveDamage() {
		player.setStateMario(new Mario(player));
	}
	
	@Override
	public void timeExpired() {}
	
	@Override
	public void handleEnemyCollision(Enemy enemy) {
		if (!isInvincible) {
            player.setStateMario(new Mario(player));
            player.setCoordY((int)player.getCoordY()+20);
            System.out.println("Se convierte en Mario");
            player.getStateMario().setInvincible(true, 1000);
        } else {
        }
	}
	@Override
	public void handlePlatformCollision(Plataform platform) {}
	
	@Override
	public String getStateName() {
		return "SuperMario";
	}
	@Override
	public void onExit() {}
	
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
	    return player.getFactorySprite().getSuperMarioRight();
	}

	@Override
	public Sprite getSpriteLeft() {
	    return player.getFactorySprite().getSuperMarioLeft();
	}
}
