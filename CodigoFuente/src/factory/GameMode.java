package factory;

public class GameMode {
    private static int currentMode = 1;

    public static void setMode(int mode) {
        if (mode != 1 && mode != 2) {
            throw new IllegalArgumentException("Modo inválido: " + mode);
        }
        
        currentMode = mode;
    }

    public static int getMode() {
    	
        return currentMode;
    }
}