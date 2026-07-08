package games;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import entities.FireBall;
import entities.Player;
import player.MarioFire;
import views.GameScreenPanel;

public class InputManager implements KeyListener {
    public boolean leftPressed = false;
    public boolean rightPressed = false;
    public boolean upPressed = false;
    private Player player;
    private GameScreenPanel panelJuego;
    private boolean isActive;

    
    public InputManager(Player player) {
    	this.player = player;
    	isActive = true;
    }
    
    public void setPanel(GameScreenPanel panel) {
    	panelJuego = panel;
    }
    
    public boolean isLeftPressed() {
        return leftPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    public boolean isUpPressed() {
        return upPressed;
    }

    public void keyPressed(KeyEvent e) {
    	 if (!isActive) return;
    	int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_W:
                upPressed = true;
                jump();
                break;
            case KeyEvent.VK_A:
                leftPressed = true;
                break;
            case KeyEvent.VK_D:
                rightPressed = true;
                break;
            case KeyEvent.VK_SPACE:
            	runOrShoot();
                break;
        }
    }
    
    public void runOrShoot() {
    	if(player.getStateMario().getStateName() == "MarioFire" && !player.isRunning()) {
    		MarioFire fire = (MarioFire) player.getStateMario();
    		FireBall aux = fire.shootFireball(player.getFactorySprite());
    		aux.setDirection(player.getDirection());
    		panelJuego.addFireBallGame(aux);
    	}
    	player.getStateMario().handleRunButton(true);
    }
    
    public void keyReleased(KeyEvent e) {
    	 if (!isActive) return;
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_W:
                upPressed = false;
                break;
            case KeyEvent.VK_A:
                leftPressed = false;
                break;
            case KeyEvent.VK_D:
                rightPressed = false;
                break;
            case KeyEvent.VK_SPACE:
                player.getStateMario().handleRunButton(false);
                break;
        }
    }
    
    public void update() {
    	if(isActive) {
	        if (leftPressed && (player.getCoordX() >= panelJuego.scrollRight((int)player.getCoordX()))) {
	            moveLeft();
	        }
	        if (rightPressed) {
	        	panelJuego.scrollRight((int)player.getCoordX());
	        	moveRight();
	        }
    	}
        if (player.isJumping() || player.isFalling()) {
        	player.setCoordY((int)player.getCoordY() + (int) player.getVelocityY());
        	player.setVelocityY(player.getVelocityY() + player.getGravity());
        }
    }
    
    public void moveRight() {
    	player.moveRight();
        if (!player.getDirection()) {
        	player.setDirection(true);
        	player.setSprite(player.getSpriteRight());
        }
        
    }

    public void moveLeft() {
    	player.moveLeft();
        if (player.getDirection()) {
        	player.setDirection(false);
        	player.setSprite(player.getSpriteLeft());
        }
    }
    
    public void jump() {
        if (!player.isJumping() && !player.isFalling()) {
            player.setIsJumping(true);
            player.setVelocityY(player.getVelocityY() - player.getJumpStrength());
            SoundManager.getInstance().playSound("jump");
        }
        
    }
    public void shutDown(){
    	isActive = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }


}

