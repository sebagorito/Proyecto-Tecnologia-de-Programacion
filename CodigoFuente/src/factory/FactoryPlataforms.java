package factory;
import entities.Enemy;
import entities.PowerUp;
import platforms.*;


public class FactoryPlataforms {
	protected FactorySprite factorySprite;
		
		public FactoryPlataforms(FactorySprite factorySprite) {
			this.factorySprite = factorySprite;
			if (this.factorySprite == null) {
		        System.err.println("FactorySprite es null en FactoryPlataforms");
		    } else {
		        System.out.println("FactoryPlataforms inicializado correctamente con FactorySprite");
		    }
		}
		
		public CoinBlock getCoinBlock(int x, int y) {
			Sprite spriteCoinBlock = factorySprite.getSolidBrick();
			CoinBlock coinBlock = new CoinBlock(x,y,spriteCoinBlock);
			return coinBlock;
		}
		
		public LuckyBlock getLuckyBlock(int x, int y, PowerUp powerUp) {
			Sprite spriteLuckyBlock = factorySprite.getLuckyBlock();
			LuckyBlock luckyBlocks = new LuckyBlock(x,y,spriteLuckyBlock, powerUp);
			return luckyBlocks;
		}
		
		public SolidBrick getSolidBrick(int x, int y) {
			Sprite spriteSolidBrick = factorySprite.getSolidBrick();
			SolidBrick solidBrick = new SolidBrick(x,y,spriteSolidBrick);
			return solidBrick;
		}
		
		public VoidBlock getVoid(int x, int y) {
			Sprite spriteVoid = factorySprite.getVoid();
			VoidBlock voidBlock = new VoidBlock(x,y,spriteVoid);
			return voidBlock;
		}
		
		public Pipe getPipe(int x, int y, Enemy pirahna) {
			Sprite spritePipe = factorySprite.getPipe();
			Pipe pipe = new Pipe(x,y,spritePipe,pirahna);
			return pipe;
		}
		
		public SolidBlock getSolidBlock(int x, int y) {
			Sprite spriteSolidBlock = factorySprite.getSolidBlock();
			SolidBlock solidBlock = new SolidBlock(x,y,spriteSolidBlock);
			return solidBlock;
		}
		
		public Flag getFlag(int x, int y) {
			Sprite spriteFlag = factorySprite.getFlag();
			Flag flag = new Flag(x, y, spriteFlag);
			return flag;
		}
		
		public Floor getFloor(int x, int y) {
			Sprite spriteFloor = factorySprite.getFloor();
			Floor floor = new Floor(x, y, spriteFloor);
			return floor;
		}
}
