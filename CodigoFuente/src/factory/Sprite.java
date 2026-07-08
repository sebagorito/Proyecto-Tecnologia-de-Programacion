package factory;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Sprite {
    public String path;
    protected Image image;
    public Sprite(Image image, String path) {
        this.image = image;
        this.path = path;
    }
    public Sprite(String path) {
        this.path = path;
        loadImage();
    }
    private void loadImage() {
        try {
            image = ImageIO.read(new File(path));
            if (image == null) {
                System.err.println("Failed to load image from path: " + path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Image getImage() {
        return image;
    }
    public String getPath() {
        return path;
    }
    
    public void setPath(String p) {
    	path = p;
    }
}


