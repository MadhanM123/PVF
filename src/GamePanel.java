import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.Iterator;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import sprites.plants.PeaShooter;
import sprites.plants.Repeater;
import sprites.plants.SunFlower;
import sprites.projectile.Projectile;
import sprites.zombies.ConeHead;
import sprites.zombies.FulkZombie;
import sprites.zombies.KingKwong;
import sprites.zombies.Zombie;

public class GamePanel extends JPanel implements Runnable{

    private static final int SCREEN_COL = 9;
    private static final int SCREEN_ROW = 5;

    public static final int SCREEN_WIDTH = Tile.TILE_SIZE * SCREEN_COL;
    public static final int SCREEN_LENGTH = Tile.TILE_SIZE * SCREEN_ROW;

    private static final int FPS = 30;

    private Tile[][] grid;

    private InfoPanel infoPanel;
    private PlantPanel plantPanel;

    private int health;
    private int sun;
    private int wave;

    public static final int ZOMBIE_RANGE = 10;
    public static final int PROJECTILE_HITBOX = 3;
    public static final int LAST_TILE_RANGE = 5;

    public GamePanel(PlantPanel pp, InfoPanel ip) throws IOException{
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_LENGTH));
        this.setDoubleBuffered(true);

        this.plantPanel = pp;
        this.infoPanel = ip;

        this.health = 5;
        this.sun = 50;
        this.wave = 0;
        
        grid = new Tile[SCREEN_ROW][SCREEN_COL];
        this.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
        this.setLayout(new GridLayout(SCREEN_ROW, SCREEN_COL, 0, 0));

        setupGrid();
    }

    public void setupGrid(){
        for(int r = 0; r < grid.length; r++){
            for(int c = 0; c < grid[0].length; c++){
                grid[r][c] = new Tile(c, r, plantPanel.getPlantSelector());
                this.add(grid[r][c]);
            }
        }
    }

    @Override
    public void run()
    {
        double drawInterval = 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;
        int frames = 0;

        while(health > 0){
            
            update(frames);

            repaint();

            try{
                double remainTime = nextDrawTime - System.nanoTime();
                remainTime /= 1000000;
                frames++;

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

    public void update(int frame){
        addZombieWave(frame);
        addSun(frame);

        infoPanel.setHealth(health);
        infoPanel.setSun(sun);

        for(int r = 0; r < grid.length; r++){
            for(int c = 0; c < grid[r].length; c++){
                if(grid[r][c].getPlant() instanceof PeaShooter || grid[r][c].getPlant() instanceof Repeater){
                    grid[r][c].setShouldShoot(checkRow(r, c));
                }

                grid[r][c].update();
                
                if(grid[r][c].zombieMoved()){
                    Iterator<Zombie> iter = grid[r][c].getZombies().iterator();

                    while(iter.hasNext()){
                        Zombie z = iter.next();

                        if(z.hasMovedNextTile()){
                            grid[r][c - 1].addZombie(z);
                            iter.remove();
                            z.movedNextTile(false);
                        }
                    }
                }

                if(grid[r][c].projectileMoved()){
                    Iterator<Projectile> iter = grid[r][c].getProjectiles().iterator();

                    while(iter.hasNext()){
                        Projectile p = iter.next();

                        if(p.hasMovedNextTile()){
                            if(c + 1 == SCREEN_COL){
                                System.out.println("here");
                                if(grid[r][c].checkProjectile(p)){
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
                        incrementSun();
                        sf.setProduced(false);
                    }
                }

                if(c == 0 && grid[r][c].hasZombie() && grid[r][c].checkZombieDistance()){
                    health--;
                    clearRow(r);
                }
            }
        }
    }

    void addZombieWave(int frame){
        int col = 8, row = 0;   
        if(frame % 300 == 0 ){
            infoPanel.setWave(wave++);
            for(int i = 0; i < frame/200; i++){
                row = (int) (Math.random()*5);
                if(wave <= 3){
                    grid[row][col].addZombie(new FulkZombie(col, row));
                }
                else if(wave > 3 && wave <= 6){
                    int prob = (int) (Math.random() * 3);
                    if(prob < 2){
                        grid[row][col].addZombie(new FulkZombie(col, row));
                    }
                    else{
                        grid[row][col].addZombie(new ConeHead(col, row));
                    }
                }
                else{
                    int prob = (int) (Math.random() * 6);
                    if(prob < 2){
                        grid[row][col].addZombie(new FulkZombie(col, row));
                    }
                    else if(prob < 5){
                        grid[row][col].addZombie(new ConeHead(col, row));
                    }
                    else{
                        grid[row][col].addZombie(new KingKwong(col, row));
                    }
                }
            }
        }
    }   

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for(int r = 0; r < grid.length; r++){
            for(int c = 0; c < grid[r].length; c++){
                grid[r][c].draw(g);
            }
        }
    }

    public boolean checkRow(int startRow, int startCol){
        for(int c = startCol; c < SCREEN_COL; c++){
            if(grid[startRow][c].hasZombie()){
                return true;
            }
        }
        return false;
    }

    public void clearRow(int startRow){
        for(int c = 0; c < SCREEN_COL; c++){
            grid[startRow][c].clearPlant();
            grid[startRow][c].clearProjectile();
            grid[startRow][c].clearZombies();
        }
    }

    public Tile[][] getGrid(){
        return grid;

    }

    private void addSun(int frame){
        if(frame != 0 && frame % 300 == 0){
            incrementSun();
        }
    }

    public void incrementSun(){
        sun += 10;
    }

    public void decrementSun(int dec){
        sun -= dec;
    }

    public int getSun(){
        return sun;
    } 
}