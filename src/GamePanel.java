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
import sprites.plants.Walnut;
import sprites.projectile.Projectile;
import sprites.zombies.ConeHead;
import sprites.zombies.FulkZombie;
import sprites.zombies.KingKwong;
import sprites.zombies.Zombie;

public class GamePanel extends JPanel implements Runnable{

    private final int screenCol = 9;
    private final int screenRow = 5;

    private final int realScreenWidth = Tile.TILE_SIZE * screenCol;
    private final int realScreenLength = Tile.TILE_SIZE * screenRow;

    private final int FPS = 30;

    private Tile[][] grid;

    private InfoPanel infoPanel;
    private PlantPanel plantPanel;

    private int health;
    private int sun;
    private int wave;

    public static final int ZOMBIE_RANGE = 10;
    public static final int PROJECTILE_HITBOX = 3;

    public GamePanel(PlantPanel pp, InfoPanel ip) throws IOException{
        this.setPreferredSize(new Dimension(realScreenWidth, realScreenLength));
        this.setDoubleBuffered(true);

        this.plantPanel = pp;
        this.infoPanel = ip;

        this.health = 5;
        this.sun = 50;
        this.wave = 0;
        
        grid = new Tile[screenRow][screenCol];
        this.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
        this.setLayout(new GridLayout(screenRow, screenCol, 0, 0));

        setupGrid();

        //grid[3][6].addPlant(new Walnut(6, 3));
        //grid[3][1].addProjectile(new Projectile(1, 3));
        //grid[2][8].addZombie(new ConeHead(8, 2));
        //grid[3][8].addZombie(new KingKwong(8, 3));
        // grid[3][8].addZombie(new KingKwong(8, 3));
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

        while(true){

            //System.out.println(System.currentTimeMillis());

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
                            grid[r][c + 1].addProjectile(p);
                            iter.remove();
                            p.movedNextTile(false);
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
        for(int c = startCol; c < screenCol; c++){
            if(grid[startRow][c].hasZombie()){
                return true;
            }
        }
        return false;
    }

    public Tile[][] getGrid(){
        return grid;

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