package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import factory.GameMode;

public class MenuScreenPanel extends PanelBase{
	private static final long serialVersionUID = 1L;
	private JLabel mushOption;
	private JLabel mod1;
	private JLabel mod2;
	private JLabel ranking;
	private JLabel exit;
	private Font font;
	private int selectedIndex = 0;
	private JLabel[] menuOptions;
	private Image backgroundImage;

    public MenuScreenPanel(ViewsController frame, Font font) {
    	this.font = font;
        setLayout(null);
        this.setBackground(Color.BLACK);
        backgroundImage = new ImageIcon("src/Sprites/Mode1/menuImage.png").getImage();
        
        initMenuOptions();
        initMushIcon();
        keyListener(frame);
        
        setFocusable(true);  
        requestFocusInWindow();     
    }
    
    private void initMenuOptions() {
        mod1 = createMenuLabel("MODO 1",300);
        mod2 = createMenuLabel("MODO 2",350);
        ranking = createMenuLabel("RANKING",400);
        exit = createMenuLabel("SALIR",450);
       
        menuOptions = new JLabel[]{mod1, mod2, ranking, exit};
        
        for (JLabel option : menuOptions) {
            add(option);
        }
    }
    
    private JLabel createMenuLabel(String text, int yPosition) {
    	JLabel label = new JLabel(text);
    	label.setFont(font);
    	label.setBounds(350, yPosition, 150, 30);
    	label.setForeground(Color.WHITE);
    	return label;
    }
    
    public void initMushIcon() {
    	mushOption = new JLabel(new ImageIcon("src/Sprites/Mode1/superMush.png"));
    	mushOption.setBounds(290, 290, 50, 50);
    	add(mushOption);
    	updateMushPosition();
    }
    
    public void updateMushPosition() {
    	int yPosition = menuOptions[selectedIndex].getY();
    	mushOption.setBounds(290, yPosition-10, 50, 50);
    	revalidate();
    	repaint();
    }
    
    private void moveSelectionUp() {
        selectedIndex = (selectedIndex - 1 + menuOptions.length) % menuOptions.length;
        updateMushPosition();
    }

    private void moveSelectionDown() {
        selectedIndex = (selectedIndex + 1) % menuOptions.length;
        updateMushPosition();
    }
    
    
    public void keyListener(ViewsController frame) {
    	addKeyListener(new KeyAdapter() {      
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        moveSelectionUp();
                        break;
                    case KeyEvent.VK_DOWN:
                        moveSelectionDown();
                        break;
                    case KeyEvent.VK_ENTER:
					try {
						handleOptionSelection(frame);
					} catch (IOException e1) {						
						e1.printStackTrace();
					}
                        break;
                }
            }
        });
    }
    
    private void handleOptionSelection(ViewsController frame) throws IOException {
    	switch (selectedIndex) {
	        case 0:
	        	GameMode.setMode(1);
	        	frame.endGame();
	        	frame.showPanel("juego");
	        	frame.playDeathScreen(3, 1, 0, 0);
	            break;
	        case 1:
	        	GameMode.setMode(2);
	        	frame.endGame();
	        	frame.showPanel("juego");
	        	frame.playDeathScreen(3, 1, 0, 0);
	            break;
	        case 2:
	        	frame.showPanel("ranking");
	            break;
	        case 3:
	            System.exit(0);
	            break;
    	}
    }

    public String getPanelName() {
        return "menu";
    }
    
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}