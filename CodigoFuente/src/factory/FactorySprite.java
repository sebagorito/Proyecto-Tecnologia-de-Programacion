package factory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public abstract class FactorySprite {
	
	
	protected int modo;  
    public String basePath;
    private static final String MARIO_RIGHT_SPRITE = "marioRight.png";
    private static final String MARIO_LEFT_SPRITE = "marioLeft.png";
    private static final String MARIO_DEAD_SPRITE = "marioDead.png";
    private static final String SUPER_MARIO_LEFT_SPRITE = "superMarioLeft.png";
    private static final String SUPER_MARIO_RIGHT_SPRITE = "superMarioRight.png";
    private static final String FIRE_MARIO_LEFT_SPRITE = "marioFireLeft.png";
    private static final String FIRE_MARIO_RIGHT_SPRITE = "marioFireRight.png";
    private static final String MARIO_STAR_RIGHT_SPRITE = "marioStarRight.png";
    private static final String MARIO_STAR_LEFT_SPRITE = "marioStarLeft.png";
    private static final String SUPER_MARIO_STAR_RIGHT_SPRITE = "superMarioStarRight.png";
    private static final String SUPER_MARIO_STAR_LEFT_SPRITE = "superMarioStarLeft.png";
    private static final String GOOMBA_SPRITE = "goomba2.png";
    private static final String LAKITU_SPRITE = "lakitu.png";
    private static final String PIRANHA_PLANT_SPRITE = "piranhaClosedMouth.png";	
    private static final String SPINY_SPRITE = "spiny1Left.png";
    private static final String SHELL_SPRITE = "koopaShell.png";
    private static final String MOVING_SHELL_SPRITE = "koopaShell.png";
    private static final String KOOPA_SPRITE = "koopaTroopaLeft.png";
    private static final String STATE_KOOPA_SPRITE = "koopaTroopa2Left.png";
    private static final String COIN_SPRITE = "coin.png";
    private static final String FIRE_FLOWER_SPRITE = "fireFlower.png";
    private static final String GREEN_MUSH_SPRITE = "greenMush.png";
    private static final String STAR_SPRITE = "Star.png";
    private static final String SUPER_MUSH_SPRITE = "superMush.png";
    private static final String FIRE_BALL_SPRITE = "fireball1.png";
    private static final String BUZZY_BEETLE_LEFT_SPRITE = "buzzyBeetle1Left.png";
    private static final String BUZZY_BEETLE_RIGHT_SPRITE = "buzzyBeetleSmashed.png";
    private static final String BUZZY_BEETLE_SMASHED_SPRITE = "buzzyBeetleSmashed.png";
    private static final String LUCKY_BLOCK_SPRITE = "luckyBlock.png";
    private static final String PIPE_SPRITE = "pipe.png";
    private static final String SOLID_BRICK_SPRITE = "solidBrick.png";
    private static final String VOID_SPRITE = "void.png";
    private static final String SOLID_BLOCK_SPRITE = "solidBlock.png";
    private static final String FLAG_SPRITE = "Flag.png";
    private static final String FLOOR_SPRITE = "floor.png";
    
    public FactorySprite() {
    	this.modo = GameMode.getMode();
        if (modo == 1) {
            basePath = "src/Sprites/Mode1/";
        } else if (modo == 2) {
            basePath = "src/Sprites/Mode2/";
        } else {
            throw new IllegalArgumentException("Modo inválido: " + modo);
        }
    }
    
    protected int getModo() {
        return this.modo;
    }
    private Sprite createSprite(String entityName, String fileName) {
        String fullPath = basePath + fileName;

        BufferedImage originalImage = cargarImagen(fullPath);
        if (originalImage == null) {
            return null;
        }

        double scaleFactor = ScaleManager.getScaleFactor(entityName);

        BufferedImage scaledImage = rescaleImage(originalImage, scaleFactor);
        if (scaledImage == null) {
            return null;
        }

        return new Sprite(scaledImage, fullPath);
    }

    private BufferedImage cargarImagen(String imagePath) {
        try {
            return ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    private BufferedImage rescaleImage(BufferedImage originalImage, double scaleFactor) {
        int targetWidth = (int) (originalImage.getWidth() * scaleFactor);
        int targetHeight = (int) (originalImage.getHeight() * scaleFactor);

        BufferedImage scaledImage = new BufferedImage(targetWidth, targetHeight, originalImage.getType());
        Graphics2D g2d = scaledImage.createGraphics();
        
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        g2d.dispose();

        return scaledImage;
    }
    
    
    
    public Sprite getMarioRight() {
        return createSprite("MarioRight", MARIO_RIGHT_SPRITE);
    }

    public Sprite getMarioLeft() {
        return createSprite("MarioLeft", MARIO_LEFT_SPRITE);
    }
    
    public Sprite getMarioDead() {
        return createSprite("MarioDead", MARIO_DEAD_SPRITE);
    }

    public Sprite getSuperMarioLeft() {
        return createSprite("SuperMarioLeft", SUPER_MARIO_LEFT_SPRITE);
    }

    public Sprite getSuperMarioRight() {
        return createSprite("SuperMarioRight", SUPER_MARIO_RIGHT_SPRITE);
    }
    
    public Sprite getMarioFireLeft() {
        return createSprite("MarioFireLeft", FIRE_MARIO_LEFT_SPRITE);
    }
    
    public Sprite getMarioFireRight() {
        return createSprite("MarioFireRight", FIRE_MARIO_RIGHT_SPRITE);
    }
    
    public Sprite getMarioStarRight() {
        return createSprite("MarioStarRight", MARIO_STAR_RIGHT_SPRITE);
    }
    
    public Sprite getMarioStarLeft() {
        return createSprite("MarioStarLeft", MARIO_STAR_LEFT_SPRITE);
    }
    
    public Sprite getSuperMarioStarRight() {
        return createSprite("SuperMarioStarRight", SUPER_MARIO_STAR_RIGHT_SPRITE);
    }
    
    public Sprite getSuperMarioStarLeft() {
        return createSprite("SuperMarioStarLeft", SUPER_MARIO_STAR_LEFT_SPRITE);
    }

    public Sprite getGoomba() {
        return createSprite("Goomba", GOOMBA_SPRITE);
    }

    public Sprite getLakitu() {
        return createSprite("Lakitu", LAKITU_SPRITE);
    }

    public Sprite getPiranhaPlant() {
        return createSprite("PiranhaPlant", PIRANHA_PLANT_SPRITE);
    }

    public Sprite getSpiny() {
        return createSprite("Spiny", SPINY_SPRITE);
    }
    
    public Sprite getShell() {
        return createSprite("Shell", SHELL_SPRITE);
    }

    public Sprite getMovingShell() {
        return createSprite("MovingShell", MOVING_SHELL_SPRITE);
    }

    public Sprite getkoopa() {
        return createSprite("Koopa", KOOPA_SPRITE);
    }

    public Sprite getStateKoopa() {
        return createSprite("StateKoopa", STATE_KOOPA_SPRITE);
    }

    public Sprite getCoin() {
        return createSprite("Coin", COIN_SPRITE);
    }

    public Sprite getFireFlower() {
        return createSprite("FireFlower", FIRE_FLOWER_SPRITE);
    }

    public Sprite getGreenMush() {
        return createSprite("GreenMush", GREEN_MUSH_SPRITE);
    }

    public Sprite getStar() {
        return createSprite("Star", STAR_SPRITE);
    }

    public Sprite getSuperMush() {
        return createSprite("SuperMush", SUPER_MUSH_SPRITE);
    }

    public Sprite getFireBall() {
        return createSprite("FireBall", FIRE_BALL_SPRITE);
    }
	
    public Sprite getBuzzyBeetleLeft() {
        return createSprite("BuzzyBeetleLeft", BUZZY_BEETLE_LEFT_SPRITE);
    }
    
    public Sprite getBuzzyBeetleRight() {
        return createSprite("BuzzyBeetleRight", BUZZY_BEETLE_RIGHT_SPRITE);
    }
    
    public Sprite getBuzzyBeetleSmashed() {
        return createSprite("BuzzyBeetleSmashed", BUZZY_BEETLE_SMASHED_SPRITE);
    }
    
    public Sprite getLuckyBlock() {
        return createSprite("LuckyBlock", LUCKY_BLOCK_SPRITE);
    }

    public Sprite getPipe() {
         return createSprite("Pipe", PIPE_SPRITE);
    }

    public Sprite getSolidBrick() {
        return createSprite("SolidBrick", SOLID_BRICK_SPRITE);
    }

    public Sprite getVoid() {
        return createSprite("Void", VOID_SPRITE);
    }
     
    public Sprite getSolidBlock() {
        return createSprite("SolidBlock", SOLID_BLOCK_SPRITE);
    }
	
    public Sprite getFlag() {
    	return createSprite("Flag", FLAG_SPRITE);
    }
    
    public Sprite getFloor() {
    	return createSprite("Floor", FLOOR_SPRITE);
    }
}