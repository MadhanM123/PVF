import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import sprites.plants.Walnut;
import sprites.zombies.ConeHead;

public class GamePanel extends JPanel implements Runnable{
    private final int screenCol = 9;
    private final int screenRow = 5;

    private final int realScreenWidth = Tile.TILE_SIZE * screenCol;
    private final int realScreenLength = Tile.TILE_SIZE * screenRow;

    private final int FPS = 40;

    private Tile[][] grid;
    
    private CollisionManager collManager;

    private InfoPanel infoPanel;
    private PlantPanel plantPanel;

    private int health;
    private int sun;
    private int wave;

    public GamePanel(PlantPanel pp, InfoPanel ip) throws IOException{
        this.setPreferredSize(new Dimension(realScreenWidth, realScreenLength));
        this.setDoubleBuffered(true);

        this.plantPanel = pp;
        this.infoPanel = ip;

        this.health = 5;
        this.sun = 0;
        this.wave = 0;
        
        grid = new Tile[screenRow][screenCol];
        collManager = new CollisionManager();
        this.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
        this.setLayout(new GridLayout(screenRow, screenCol, 0, 0));

        setupGrid();

        grid[3][7].addPlant(new Walnut(3, 7, -20, 0));
        grid[3][8].addZombie(new ConeHead(3, 8, -100, -70));

        collManager = new CollisionManager();
    }

    public void setupGrid(){
        for(int r = 0; r < grid.length; r++){
            for(int c = 0; c < grid[0].length; c++){
                grid[r][c] = new Tile(r, c, plantPanel.getPlantSelector());
                this.add(grid[r][c]);
            }
        }
    }

    @Override
    public void run()
    {
        double drawInterval = 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(true){

            // System.out.println(System.currentTimeMillis());

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
        infoPanel.setWave(wave++);
        infoPanel.setSun(sun++);
        for(int r = 0; r < grid.length; r++){
            for(int c = 0; c < grid[r].length; c++){
                grid[r][c].update();
                if(grid[r][c].zombieMoved()){
                    grid[r][c - 1].addZombie(grid[r][c].removeZombie());
                }
            }
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        // tileManager.draw(g);
    }

    public Tile[][] getGrid(){
        return grid;

    }

    public void incrementSun(){
        sun++;
    }

    public int getSun(){
        return sun;
    } 
}