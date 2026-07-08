package parser;
import java.io.IOException;

public class Level extends ReadText{
	
    public Level(String text) {
    	super(text);
    }
    
    public String readLevel() throws IOException {
    	String aux = super.readFile();
    	return aux;
    }
    
    
    
}