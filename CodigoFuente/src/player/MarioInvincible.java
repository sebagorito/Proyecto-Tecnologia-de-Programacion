package player;

import entities.Enemy;
import entities.Player;
import factory.Sprite;
import games.SoundManager;
import platforms.Plataform;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
public class MarioInvincible extends StateMario{
	private Timer timer;
	private StateMario previousState;
	
	public MarioInvincible(Player player, StateMario prev) {
		super(player);
		this.previousState= prev;
		startInvincibilityTimer();
		SoundManager.getInstance().stopAudio("general");
		SoundManager.getInstance().playLoop("star");
	}
	private void startInvincibilityTimer() {
	        timer = new Timer(10000, new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                timeExpired();
	            }
	        });
	        timer.setRepeats(false);
	        timer.start();
	    }
	  public void stopTimer() {
	        if (timer != null) {
	        	SoundManager.getInstance().stopAudio("star");
	        	if(!(player.isWinning() || player.isDead()))
	        		SoundManager.getInstance().playLoop("general");
	            timer.stop();
	        }
	    }

	    public void onExit() {
	        stopTimer();
	    }

	    @Override
	    public void timeExpired() {
	        stopTimer();
	        player.setStateMario(previousState);
	    }
	@Override
	public void collectSuperMush() {
		this.previousState = new SuperMario(player);
		player.addPoints(10);
	}

	@Override
	public void collectFireFlower() {
		player.addPoints(50);

	}

	@Override
	public void collectStar() {
		player.addLife();
		player.addPoints(350);

	}

	@Override
	public void collectGreenMush() {
		player.addPoints(100);

	}

	@Override
	public void collectCoin() {
		player.addPoints(5);

	}

	@Override
	public void receiveDamage() {	
	}
	@Override
	public boolean isInvincible() {
		return false;
	}
	@Override
	public void handleEnemyCollision(Enemy enemy) {
		enemy.die();
	}
	@Override
	public void handlePlatformCollision(Plataform platform) {		
	}
	@Override
	public String getStateName() {
		return "MarioInvincible";
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
	    String prevStateName = previousState.getStateName();
	    if (prevStateName.equals("Mario")) {
	        return player.getFactorySprite().getMarioStarRight(); 
	    } else if (prevStateName.equals("SuperMario")) {
	        return player.getFactorySprite().getSuperMarioStarRight();
	    } else if (prevStateName.equals("MarioFire")) {
	        return player.getFactorySprite().getSuperMarioStarRight(); 
	    } else {
	        return player.getFactorySprite().getMarioStarRight(); 
	    }
	}

	@Override
	public Sprite getSpriteLeft() {
	    String prevStateName = previousState.getStateName();
	    if (prevStateName.equals("Mario")) {
	        return player.getFactorySprite().getMarioStarLeft();
	    } else if (prevStateName.equals("SuperMario")) {
	        return player.getFactorySprite().getSuperMarioStarLeft(); 
	    } else if (prevStateName.equals("MarioFire")) {
	        return player.getFactorySprite().getSuperMarioStarLeft(); 
	    } else {
	        return player.getFactorySprite().getMarioStarLeft(); 
	    }
	}
	


	

}
