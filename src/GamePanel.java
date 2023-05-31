import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.Iterator;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import sprites.plants.PeaShooter;
import sprites.plants.Repeater;
import sprites.plants.SunFlower;
import sprites.plants.Walnut;
import sprites.projectile.Projectile;
import sprites.zombies.ConeHead;
import sprites.zombies.FulkZombie;
import sprites.zombies.KingKwong;
import sprites.zombies.Zombie;

/**
 * The GamePanel class holds the playing grid, main game loop, and the logic to move sprites between tiles.
 * @author Madhan M., Andrew X.
 * @version 2023-05-28
 */
public class GamePanel extends JPanel implements Runnable{

    private static final int SCREEN_COL = 9;
    private static final int SCREEN_ROW = 5;

    private static final int FPS = 30;

    private static final int SCREEN_WIDTH = Tile.TILE_SIZE * SCREEN_COL;
    private static final int SCREEN_LENGTH = Tile.TILE_SIZE * SCREEN_ROW;

    private static final int SUN_INCREMENT = 10;

    /**
     * {@value #ZOMBIE_RANGE} Minimum distance between zombies
     */
    public static final int ZOMBIE_RANGE = 10;

    /**
     * {@value #PROJECTILE_HITBOX} Hitbox between projectiles and zombies
     */
    public static final int PROJECTILE_HITBOX = 3;

    /**
     * {@value #LAST_TILE_RANGE} Minimum distance from end of grid for zombies to player-side of grid and projectiles to zombie-side of grid
     */
    public static final int LAST_TILE_RANGE = 5;

    private Tile[][] grid;

    private InfoPanel infoPanel;
    private int health;
    private int sun;
    private int wave;

    private long frame;

    /**
     * Initializes stats, grid, and sets panels
     * @param plantPanel Instance of PlantPanel
     * @param infoPanel Instance of InfoPanel
     */
    public GamePanel(PlantPanel plantPanel, InfoPanel infoPanel){
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_LENGTH));
        this.setDoubleBuffered(true);

        this.infoPanel = infoPanel;

        this.health = 5;
        this.sun = 50;
        this.wave = 0;

        this.frame = 0;
        
        grid = new Tile[SCREEN_ROW][SCREEN_COL];
        this.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
        this.setLayout(new GridLayout(SCREEN_ROW, SCREEN_COL, 0, 0));

        for(int r = 0; r < grid.length; r++){
            for(int c = 0; c < grid[0].length; c++){
                grid[r][c] = new Tile(c, r, plantPanel);
                this.add(grid[r][c]);
            }
        }

        grid[3][8].addZombie(new KingKwong(8,3,0));
    }

    /**
     * Runs the game loop of updating and repainting 30 times per second.
     */
    @Override
    public void run()
    {
        double drawInterval = 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(health > 0){
            
            update();

            repaint();

            try{
                double remainTime = nextDrawTime - System.nanoTime();
                remainTime /= 1000000;
                frame++;

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
        return;
    }

    private void update(){
        addZombieWave();
        addSun();

        infoPanel.setHealth(health);
        infoPanel.setSun(sun);

        for(int r = 0; r < grid.length; r++){
            for(int c = 0; c < grid[r].length; c++){
                if(grid[r][c].getPlant() instanceof PeaShooter || grid[r][c].getPlant() instanceof Repeater){
                    grid[r][c].setShouldShoot(checkRow(r, c));
                }

                grid[r][c].update();
                
                if(grid[r][c].getZombieMoved()){
                    Iterator<Zombie> iter = grid[r][c].getZombies().iterator();

                    while(iter.hasNext()){
                        Zombie z = iter.next();

                        if(z.hasMovedNextTile()){
                            grid[r][c - 1].addZombie(z);
                            iter.remove();
                            z.setMovedNextTile(false);
                        }
                    }
                }

                if(grid[r][c].getProjectileMoved()){
                    Iterator<Projectile> iter = grid[r][c].getProjectiles().iterator();

                    while(iter.hasNext()){
                        Projectile p = iter.next();

                        if(p.hasMovedNextTile()){
                            if(c + 1 == SCREEN_COL){
                                if(SCREEN_WIDTH - p.getScreenX() <= LAST_TILE_RANGE && (grid[r][c].getZombies().isEmpty() 
                                || (!grid[r][c].getZombies().isEmpty() && p.getScreenX() > grid[r][c].getZombies().peek().getScreenX()))){
                                    iter.remove();
                                }
                            }
                            else{
                                grid[r][c + 1].addProjectile(p);
                                iter.remove();
                                p.movedNextTile(false);
                            }
                        }
                    }
                }

                if(grid[r][c].getPlant() instanceof SunFlower){
                    SunFlower sf = (SunFlower) grid[r][c].getPlant();
                    if(sf.getProduced()){
                        sun += SUN_INCREMENT;
                        sf.setProduced(false);
                    }
                }

                if(c == 0 && !grid[r][c].getZombies().isEmpty() && grid[r][c].checkZombieDistance()){
                    health--;
                    clearRow(r);
                }
            }
        }
    }

    private void addZombieWave(){
        int col = SCREEN_COL - 1, row = 0;
   
        if(frame % 300 == 0 ){
            infoPanel.setWave(wave++);
            for(int i = 0; i < frame/300; i++){
                row = (int) (Math.random()*5);
                if(wave <= 3){
                    grid[row][col].addZombie(new FulkZombie(col, row, grid[row][col].countZombie(FulkZombie.NAME) * ZOMBIE_RANGE));
                }
                else if(wave > 3 && wave <= 6){
                    int prob = (int) (Math.random() * 3);
                    if(prob < 2){
                        grid[row][col].addZombie(new FulkZombie(col, row, grid[row][col].countZombie(FulkZombie.NAME) * ZOMBIE_RANGE));
                    }
                    else{
                        grid[row][col].addZombie(new ConeHead(col, row, grid[row][col].countZombie(ConeHead.NAME) * ZOMBIE_RANGE));
                    }
                }
                else{
                    int prob = (int) (Math.random() * 6);
                    if(prob < 2){
                        grid[row][col].addZombie(new FulkZombie(col, row, grid[row][col].countZombie(FulkZombie.NAME) * ZOMBIE_RANGE));
                    }
                    else if(prob < 5){
                        grid[row][col].addZombie(new ConeHead(col, row, grid[row][col].countZombie(ConeHead.NAME) * ZOMBIE_RANGE));
                    }
                    else{
                        grid[row][col].addZombie(new KingKwong(col, row, grid[row][col].countZombie(KingKwong.NAME) * ZOMBIE_RANGE));
                    }
                }
            }
        }
    }   

    private boolean checkRow(int startRow, int startCol){
        for(int c = startCol; c < SCREEN_COL; c++){
            if(!grid[startRow][c].getZombies().isEmpty()){
                return true;
            }
        }
        return false;
    }

    private void clearRow(int startRow){
        for(int c = 0; c < SCREEN_COL; c++){
            grid[startRow][c].clearPlant();
            grid[startRow][c].clearProjectile();
            grid[startRow][c].clearZombies();
        }
    }

    private void addSun(){
        if(frame != 0 && frame % 300 == 0){
            sun += SUN_INCREMENT;
        }
    }

    /**
     * Decrements current sun
     * @param dec Amount to decrement by
     */
    public void decrementSun(int dec){
        sun -= dec;
    }

    /**
     * Getter for current sun
     * @return Current amount of sun
     */
    public int getSun(){
        return sun;
    } 

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for(int r = 0; r < grid.length; r++){
            for(int c = 0; c < grid[r].length; c++){
                grid[r][c].draw(g);
            }
        }
    }
}