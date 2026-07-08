package factory;
import entities.*;
import player.*;
import enemies.*;
import pickUps.*;

public class FactoryEntities {
	protected FactorySprite factorySprite;
	
	public FactoryEntities(FactorySprite factorySprite) {
		this.factorySprite = factorySprite;
	}
	
	public Player getPlayer(int x, int y) {
	    Player player = new Player(x, y, factorySprite, 5, true, 0, 0); 
	    StateMario marioState = new Mario(player);
	    player.setStateMario(marioState);
	    return player;
	}
	
	public FireBall getFireBall(int x, int y) {
		Sprite spriteFireBall = factorySprite.getFireBall();
		FireBall fireBall = new FireBall(x,y,spriteFireBall);
		return fireBall;
	}
	
	public BuzzyBeetle getBuzzyBeetle(int x, int y) {
	    Sprite spriteBuzzy = factorySprite.getBuzzyBeetleLeft();
	    BuzzyBeetle buzzy = new BuzzyBeetle(x, y, spriteBuzzy, factorySprite);
	    return buzzy;
	}
	
	public Goomba getGoomba(int x, int y) {
		Sprite spriteGoomba = factorySprite.getGoomba();
		Goomba goomba = new Goomba(x,y,spriteGoomba);
		return goomba;
	}
	
	public Lakitu getLakitu(int x, int y) {
		Sprite spriteLakitu = factorySprite.getLakitu();
		Lakitu lakitu = new Lakitu(x,y,spriteLakitu);
		return lakitu;
	}
	
	public PiranhaPlant getPiranhaPlant(int x, int y) {
		Sprite spritePiranhaPlant = factorySprite.getPiranhaPlant();
		PiranhaPlant piranhaPlant = new PiranhaPlant(x,y,spritePiranhaPlant);
		return piranhaPlant;
	}
	
	public Spiny getSpiny(int x, int y) {
		Sprite spriteSpiny = factorySprite.getSpiny();
		Spiny spiny = new Spiny(x,y,spriteSpiny);
		return spiny;
	}
	
	public KoopaTroopa getKoopaTroopa(int x, int y) {
	    Sprite spriteKoopa = factorySprite.getkoopa();
	    KoopaTroopa koopa = new KoopaTroopa(x, y, spriteKoopa, factorySprite);
	    return koopa;
	}
	
	public Coin getCoin(int x, int y) {
		Sprite spriteCoin = factorySprite.getCoin();
		Coin coin = new Coin(x,y,spriteCoin);
		coin.setActive(true);
		return coin;
	}
	
	public FireFlower getFireFlower(int x, int y) {
		Sprite spriteFireFlower = factorySprite.getFireFlower();
		FireFlower fireFlower = new FireFlower(x,y,spriteFireFlower);
		return fireFlower;
	}
	
	public GreenMush getGreenMush(int x, int y) {
		Sprite spriteGreenMush = factorySprite.getGreenMush();
		GreenMush greenMush = new GreenMush(x,y,spriteGreenMush);
		return greenMush;
	}
	
	public Star getStar(int x, int y) {
		Sprite spriteStar = factorySprite.getStar();
		Star star = new Star(x,y,spriteStar);
		return star;
	}
	
	public SuperMush getSuperMush(int x, int y) {
		Sprite spriteSuperMush = factorySprite.getSuperMush();
		SuperMush superMush = new SuperMush(x,y,spriteSuperMush);
		return superMush;
	}
	
}
