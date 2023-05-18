import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import sprites.plants.Plant;
import sprites.zombies.Zombie;

public class GamePanel extends JPanel implements Runnable{

    private final int tileSize = 100;
    private final int scale = 3;

    private final int screenCol = 9;
    private final int screenRow = 5;

    private final int realScreenWidth = tileSize * screenCol;
    private final int realScreenLength = tileSize * screenRow;

    private final int FPS = 60;

    private Tile[][] grid;
    
    private CollisionManager collManager;
    private PlantPanel plantPanel;
    private int score;
    private JLabel scoreboard;

    private int currentSun;

    public GamePanel() throws IOException{
        this.setPreferredSize(new Dimension(realScreenWidth, realScreenLength));
        this.setDoubleBuffered(true);
        grid = new Tile[screenRow][screenCol];
        collManager = new CollisionManager();
        score = 0;

        scoreboard = new JLabel("60");
        scoreboard.setIcon(new ImageIcon(new ImageIcon("resources/sprites/scoreboard.png").getImage().getScaledInstance(60, 50, Image.SCALE_FAST)));
        scoreboard.setHorizontalTextPosition(JLabel.CENTER);
        scoreboard.setVerticalTextPosition(JLabel.CENTER);
        scoreboard.setForeground(Color.RED);
        this.add(scoreboard, BorderLayout.PAGE_START);
    }

    @Override
    public void run()
    {
        double drawInterval = 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(true){

            System.out.println(System.currentTimeMillis());

            update();

            repaint();

            try{
                double remainTime = nextDrawTime - System.nanoTime();
                remainTime /= 1000000;

                if(remainTime < 0){
                    remainTime = 0;
                }

                Thread.sleep((long) remainTime);
                nextDrawTime += drawInterval;
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }

    }

    public void update(){
        currentSun += 1;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }

    public Tile[][] getGrid(){
        return grid;

    }

    public void incrementSun(){
        currentSun++;
    }

    public void addPlantPanel(PlantPanel pp){
        this.plantPanel = pp;
    }

    public int getCurrentSun(){
        return currentSun;
    }

    
}