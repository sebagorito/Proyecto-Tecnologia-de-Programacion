package parser;

import java.io.IOException;

import entities.Enemy;
import entities.PowerUp;
import factory.FactoryEntities;
import factory.FactoryPlataforms;
import factory.FactorySprite;
import factory.FactorySpriteOriginal;
import games.Map;
import player.StateMario;
public class LoadEntities {
	protected Level level;
	protected final String PATH = "Text/Levels/";
	protected StateMario mario;
	protected Map map;
	private FactorySprite factory;
	
	public LoadEntities() {
		map = new Map();
		factory = new FactorySpriteOriginal();
		if (factory == null) {
	        System.err.println("FactorySprite es null en LoadEntities");
	    } else {
	        System.out.println("FactorySprite inicializado correctamente en LoadEntities");
	    }
	}
	public FactorySprite getFactory() {
		return factory;
	}
	
	public Map loadLevel(int numberLevel) throws IOException {
		
		if(numberLevel > 0 && numberLevel <= 3) {
            level = new Level(PATH + "level" + numberLevel + ".txt");
        }else {
            level = new Level(PATH + "level1.txt");
        }
	    String readLevel = level.readLevel().replace("\r", "");
	    String[] lines = readLevel.split("\n");
	    
	    FactoryEntities factoryEntities = new FactoryEntities(factory);
	    FactoryPlataforms factoryPlataform = new FactoryPlataforms(factory);
	    
	    int fila = -1;
	    int blockSize = 27;
	    
	    for (String line : lines) {
	        if (line.isEmpty()) {
	            continue; 
	        }
	       fila++;

	        for (int i = 0; i < line.length(); i++) {
	            char elemento = line.charAt(i);

	            int x = i * blockSize; 
	            
	            int y = (fila) * blockSize;

	            switch (elemento) {
		            case '#':
	                	map.addPlataforms(factoryPlataform.getFloor(x, y));
	                    break;
	                case 'M':
	                    map.setMario(factoryEntities.getPlayer(x, y));
	                    break;
	                case '?':
	                	PowerUp aux = null;
	                    map.addPlataforms(factoryPlataform.getLuckyBlock(x, y, aux));
	                    break;
	                case 'P':
	                	Enemy temp = null;
	                    map.addPlataforms(factoryPlataform.getPipe(x, y,temp));
	                    break;
	                case '$': 
	                	map.addPlataforms(factoryPlataform.getCoinBlock(x, y));
	                    break;
	                case 'f': 
	                	map.addPlataforms(factoryPlataform.getFlag(x, y));
	                	break;
	                case 'C': 
	                	map.addPlataforms(factoryPlataform.getSolidBlock(x, y));
	                    break;
	                
	                case 'B':
	                	map.addPlataforms(factoryPlataform.getSolidBrick(x, y));
	                    break;
	                case 'G':
	                	map.addEnemy(factoryEntities.getGoomba(x, y));
	                    break;
	                case 'S': 
	                	map.addEnemy(factoryEntities.getSpiny(x, y));
	                    break;
	                case 'A': 
	                	Enemy piranhaPlant = factoryEntities.getPiranhaPlant(x+15, y);
	                	map.addPlataforms(factoryPlataform.getPipe(x, y, piranhaPlant));
	                	map.addEnemy(piranhaPlant);
	                    break;
	                case 'Z': 
	                	map.addEnemy(factoryEntities.getBuzzyBeetle(x, y));
	                    break;
	                case 'K': 
	                	map.addEnemy(factoryEntities.getKoopaTroopa(x, y));
	                    break;
	                case '%': 
	                	map.addPowerUp(factoryEntities.getCoin(x, y));
	                    break;
	                case 'F': 
	                	PowerUp fireFlower = factoryEntities.getFireFlower(x, y-30);
	                    map.addPlataforms(factoryPlataform.getLuckyBlock(x, y, fireFlower));
	                    map.addPowerUp(fireFlower);
	                    break;
	                case 'E': 
	                	PowerUp gMush = factoryEntities.getGreenMush(x, y-30);
	                    map.addPlataforms(factoryPlataform.getLuckyBlock(x, y, gMush));
	                    map.addPowerUp(gMush);
	                    break;
	                case '=': 
	                	PowerUp superMush = factoryEntities.getSuperMush(x, y-30);
	                    map.addPlataforms(factoryPlataform.getLuckyBlock(x, y, superMush));
	                    map.addPowerUp(superMush);
	                    break;
	                case '&': 
	                	PowerUp star = factoryEntities.getStar(x, y-30);
	                    map.addPlataforms(factoryPlataform.getLuckyBlock(x, y, star));
	                    map.addPowerUp(star);
	                    break;
	                default:
	                    break;
	            }
	        }

	    }
	    return map;
	    
	}
}
