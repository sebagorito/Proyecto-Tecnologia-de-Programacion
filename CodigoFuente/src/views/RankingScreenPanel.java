package views;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map.Entry;

import games.Ranking;

public class RankingScreenPanel extends PanelBase {
	private static final long serialVersionUID = 1L;
	private Ranking ranking;
    private Font font;
    private Image backgroundImage;

    public RankingScreenPanel(ViewsController frame, Font font) {
        this.font = font;
        setLayout(new GridBagLayout());
        
        ranking = new Ranking();

        try {
            backgroundImage = ImageIO.read(new File("Sprites/Mode1/highscoreScreen.png"));} catch (IOException e) {
            e.printStackTrace();
        }

        loadLabels();

        setFocusable(true);  
        requestFocusInWindow();
        
        createLabelMenu();
        initMushIcon();

        keyListener(frame);
    }

    public void addNewScore(String name, int score) {
        ranking.addScore(name, score);
        loadLabels();
    }

    public void loadLabels() {
        removeAll();
        List<Entry<String, Integer>> list = ranking.sort(ranking.loadScores());

        for (int i = 0; i < Math.min(5, list.size()); i++) {
            String name = list.get(i).getKey();
            int score = list.get(i).getValue();
            add(createLabel(name, score), createGrid(0, i, 2, GridBagConstraints.CENTER));
        }

        revalidate();
        repaint();
    }

    private JLabel createLabel(String name, int score) {
        JLabel rankingLabel = new JLabel(name + ": " + score);
        rankingLabel.setFont(font);
        rankingLabel.setForeground(Color.WHITE);
        rankingLabel.setHorizontalAlignment(JLabel.CENTER); 
        return rankingLabel;
    }

    public void promptAndAddScore() {
        String name = JOptionPane.showInputDialog(this, "Ingresa tu nombre:");
        if (name != null && !name.trim().isEmpty()) {
            String scoreStr = JOptionPane.showInputDialog(this, "Ingresa tu puntaje:");
            try {
                int score = Integer.parseInt(scoreStr);
                addNewScore(name, score);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Puntaje inválido. Intenta nuevamente.");
            }
        }
    }

    public void initMushIcon() {
        JLabel mushOption = new JLabel(new ImageIcon("Sprites/Mode1/superMush.png"));
        add(mushOption, createGrid(0, 6, 1, GridBagConstraints.EAST));
    }

    private void createLabelMenu() {
        JLabel menuLabel = new JLabel("MENU");
        menuLabel.setFont(font);
        menuLabel.setForeground(Color.WHITE);
        add(menuLabel, createGrid(1, 6, 1, GridBagConstraints.WEST));
    }
    
    private GridBagConstraints createGrid(int gridx, int gridy, int gridwidth, int anchor) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridx;                    
        gbc.gridy = gridy;                   
        gbc.gridwidth = gridwidth;            
        gbc.fill = GridBagConstraints.HORIZONTAL; 
        gbc.insets = new Insets(10, 0, 10, 0);    
        gbc.anchor = anchor;                  
        gbc.weightx = 1.0;                   
        return gbc;
    }
    
    public void keyListener(ViewsController frame) {
    	addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    frame.getGamePanel().returnToMenu();
                }
            }
        });
    }

    @Override
    public String getPanelName() {
    	requestFocusInWindow();
        return "ranking";
    }
    
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
