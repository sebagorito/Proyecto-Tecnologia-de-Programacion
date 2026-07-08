package player;
import factory.Sprite;
import platforms.Plataform;
import entities.Enemy;
import entities.Player;
public class Mario extends StateMario{
	
	public Mario(Player player) {
		super(player);
	}
	
	public void collectSuperMush() {
		player.setStateMario(new SuperMario (player));
		player.addPoints(10);
	}
	public void collectFireFlower() {
		player.setStateMario(new MarioFire (player));
		player.addPoints(5);
	}
	public void collectStar() {
		player.setStateMario(new MarioInvincible(player, this));
		player.addPoints(20);
	}
	public void collectGreenMush() {
		player.addLife();
		player.addPoints(100);
	}
	public void collectCoin() {
		player.addPoints(5);
	}
	public void receiveDamage() {
		player.die();
	}
	
	public void timeExpired() {
	}

	@Override
	public void handleEnemyCollision(Enemy enemy) {
		if (!isInvincible) {
            player.die();
        } else {
        }
    }

	@Override
	public void handlePlatformCollision(Plataform platform) {		
	}

	@Override
	public String getStateName() {
		return "Mario";
	}

	@Override
	public void onExit() {
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
	    return player.getFactorySprite().getMarioRight();
	}

	@Override
	public Sprite getSpriteLeft() {
	    return player.getFactorySprite().getMarioLeft();
	}
}
