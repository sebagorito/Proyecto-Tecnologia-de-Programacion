package factory;
import java.util.HashMap;
import java.util.Map;

public class ScaleManager {

	private static Map<String, Double> scaleFactorsMode1 = new HashMap<>();
	private static Map<String, Double> scaleFactorsMode2 = new HashMap<>();

	static {
	scaleFactorsMode1.put("Mario", 2.0);
	scaleFactorsMode1.put("MarioRight", 2.0);
	scaleFactorsMode1.put("MarioLeft", 2.0);
	scaleFactorsMode1.put("MarioStarRight", 2.0);
	scaleFactorsMode1.put("MarioStarLeft", 2.0);
	scaleFactorsMode1.put("SuperMarioRight", 1.55);
	scaleFactorsMode1.put("SuperMarioLeft", 1.55);
	scaleFactorsMode1.put("MarioFireRight", 1.55);
	scaleFactorsMode1.put("MarioFireLeft", 1.55);
	scaleFactorsMode1.put("SuperMarioStarLeft", 1.55);
	scaleFactorsMode1.put("SuperMarioStarRight", 1.55);
	scaleFactorsMode1.put("MarioDead", 0.075);
	scaleFactorsMode1.put("Goomba", 1.8);
	scaleFactorsMode1.put("Lakitu", 2.0);
	scaleFactorsMode1.put("PiranhaPlant", 1.8);
	scaleFactorsMode1.put("Spiny", 2.5);
	scaleFactorsMode1.put("Shell", 1.6);
	scaleFactorsMode1.put("MovingShell", 2.5);
	scaleFactorsMode1.put("Koopa", 1.8);
	scaleFactorsMode1.put("StateKoopa", 2.5);
	scaleFactorsMode1.put("Coin", 2.0);
	scaleFactorsMode1.put("FireFlower", 1.8);
	scaleFactorsMode1.put("GreenMush", 1.8);
	scaleFactorsMode1.put("Star", 1.8);
	scaleFactorsMode1.put("SuperMush", 1.8);
	scaleFactorsMode1.put("FireBall", 1.8);
	scaleFactorsMode1.put("BuzzyBeetleLeft", 2.0);
	scaleFactorsMode1.put("BuzzyBeetleRight", 2.0);
	scaleFactorsMode1.put("BuzzyBeetleSmashed", 2.0);
	scaleFactorsMode1.put("LuckyBlock", 1.8);
	scaleFactorsMode1.put("Pipe", 1.8);
	scaleFactorsMode1.put("SolidBrick", 1.8);
	scaleFactorsMode1.put("Void", 2.5);
	scaleFactorsMode1.put("CoinBlock", 2.5);
	scaleFactorsMode1.put("SolidBlock", 1.8);
	scaleFactorsMode1.put("Flag", 0.157);
	scaleFactorsMode1.put("Floor", 1.8);
	scaleFactorsMode2.put("Mario", 2.0);
	scaleFactorsMode2.put("MarioRight", 2.0);
	scaleFactorsMode2.put("MarioLeft", 2.0);
	scaleFactorsMode2.put("MarioStarRight", 2.0);
	scaleFactorsMode2.put("MarioStarLeft", 2.0);
	scaleFactorsMode2.put("SuperMarioRight", 1.55);
	scaleFactorsMode2.put("SuperMarioLeft", 1.55);
	scaleFactorsMode2.put("MarioFireRight", 1.55);
	scaleFactorsMode2.put("MarioFireLeft", 1.55);
	scaleFactorsMode2.put("SuperMarioStarLeft", 1.55);
	scaleFactorsMode2.put("SuperMarioStarRight", 1.55);
	scaleFactorsMode2.put("MarioDead", 2.0);
	scaleFactorsMode2.put("Goomba", 1.8);
	scaleFactorsMode2.put("Lakitu", 2.0);
	scaleFactorsMode2.put("PiranhaPlant", 1.8);
	scaleFactorsMode2.put("Spiny", 2.5);
	scaleFactorsMode2.put("Shell", 1.6);
	scaleFactorsMode2.put("MovingShell", 2.5);
	scaleFactorsMode2.put("Koopa", 1.8);
	scaleFactorsMode2.put("StateKoopa", 2.5);
	scaleFactorsMode2.put("Coin", 2.0);
	scaleFactorsMode2.put("FireFlower", 1.8);
	scaleFactorsMode2.put("GreenMush", 1.8);
	scaleFactorsMode2.put("Star", 1.8);
	scaleFactorsMode2.put("SuperMush", 1.8);
	scaleFactorsMode2.put("FireBall", 1.8);
	scaleFactorsMode2.put("BuzzyBeetle", 2.5);
	scaleFactorsMode2.put("LuckyBlock", 1.8);
	scaleFactorsMode2.put("Pipe", 1.8);
	scaleFactorsMode2.put("SolidBrick", 1.8);
	scaleFactorsMode2.put("Void", 2.5);
	scaleFactorsMode2.put("CoinBlock", 2.5);
	scaleFactorsMode2.put("SolidBlock", 1.8);
	scaleFactorsMode2.put("Flag", 0.157);
	scaleFactorsMode2.put("Floor", 1.8);
	}
	
	  public static double getScaleFactor(String entityName) {
	        int modo = GameMode.getMode();
	        double scaleFactor;
	        if (modo == 1) {
	            scaleFactor = scaleFactorsMode1.getOrDefault(entityName, 1.0);
	        } else if (modo == 2) {
	            scaleFactor = scaleFactorsMode2.getOrDefault(entityName, 1.0);
	        } else {
	            scaleFactor = 1.0;
	        }

	        return scaleFactor;
	    }

}
