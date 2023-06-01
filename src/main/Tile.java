package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Iterator;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;
import javax.swing.JComponent;

import sprites.Sprite;
import sprites.Sprite.State;
import sprites.plants.*;
import sprites.projectile.Projectile;
import sprites.zombies.*;

/**
 * The Tile class represents a tile on the playing grid. Holds and draws all sprites and the collision code.
 * @author Madhan M., Andrew X.
 * @version 2023-05-28
 */
public class Tile extends JComponent implements MouseListener{
    private Queue<Zombie> zombies;
    private Plant plant;
    private Queue<Projectile> projectiles;
    private List<Sprite> deadSet;

    private int gridX;
    private int gridY;

    private int screenX;
    private int screenY;

    private boolean zombieMoved;
    private boolean projectileMoved;
    private boolean shouldShoot;

    private PlantPanel plantPanel;

    /**
     * {@value #TILE_SIZE} - Length of Tile
     */
    public static final int TILE_SIZE = 110;

    private static Image TILE_IMAGE = new ImageIcon("resources/sprites/tile/grasstile.png").getImage().getScaledInstance(TILE_SIZE, TILE_SIZE, Image.SCALE_DEFAULT);

    /**
     * Creates a Tile with coordinates and adds a plantpanel. Initializes sprite structures and adds a listener.
     * @param gridX grid x-coordinate from left
     * @param gridY grid y-coordinate from top
     * @param pp plantpanel to add
     */
    public Tile(int gridX, int gridY, PlantPanel pp){
        this.gridX = gridX;
        this.gridY = gridY;
        this.screenX = gridX * TILE_SIZE;
        this.screenY = gridY * TILE_SIZE;
        this.plantPanel = pp;

        this.zombieMoved = false;
        this.projectileMoved = false;
        this.shouldShoot = false;

        this.plant = null;
        this.zombies = new LinkedList<>();
        this.deadSet = new ArrayList<>();
        this.projectiles = new LinkedList<>();

        this.setPreferredSize(new Dimension(TILE_SIZE, TILE_SIZE));
        this.setBorder(BorderFactory.createLineBorder(new Color(0, 190, 0)));
        this.addMouseListener(this);
    }

    /**
     * Gets the zombies on the tile
     * @return Queue of current zombies
     */
    public Queue<Zombie> getZombies(){
        return zombies;
    }

    /**
     * Gets the projectiles on the tile
     * @return Queue of current projectiles
     */
    public Queue<Projectile> getProjectiles(){
        return projectiles;
    }

    /**
     * Gets the plant on the tile
     * @return Current plant
     */
    public Plant getPlant(){
        return plant;
    }

    /**
     * Adds a new zombie onto tile
     * @param z Zombie to be added 
     */
    public void addZombie(Zombie z){
        zombies.add(z);
    }

    /**
     * Adds a new plant onto tile
     * @param p Plant to be added
     */
    public void addPlant(Plant p){
        plant = p;
    }

    /**
     * Adds a new projectile onto tile
     * @param p Projectile to be added
     */
    public void addProjectile(Projectile p){
        projectiles.add(p);
    }

    /**
     * Gets the grid-relative y-coordinate
     * @return y-coordinate
     */
    public int getGridY(){
        return this.gridY;
    }

    /**
     * Gets the grid-relative x-coordinate
     * @return x-coordinate
     */
    public int getGridX(){
        return this.gridX;
    }

    /**
     * Counts the number of a specific zombie on tile
     * @param className Name of zombie to count
     * @return Amount of specific zombie on tile
     */
    public int countZombie(String className){
        int count = 0;
        for(Zombie zomb: zombies){
            if(zomb.getName().equals(className)){
                count++;
            }
        }
        return count;
    }

    /**
     * Kills current zombies and disables any KingKwong's death spawn
     */
    public void clearZombies(){
        while(!zombies.isEmpty()){
            Zombie z = zombies.poll();
            if(z instanceof KingKwong){
                ((KingKwong) z).setShouldSpawn(false);
            }
            deadSet.add(z);
        }
    }

    /**
     * Kills current plant
     */
    public void clearPlant(){
        if(plant != null){
            deadSet.add(plant);
            plant = null;
        }
    }

    /**
     * Kills current projectiles
     */
    public void clearProjectile(){
        while(!projectiles.isEmpty()){
            Projectile p = projectiles.poll();
            deadSet.add(p);
        }
    }

    /**
     * Checks if any zombie has reached player end of grid
     * @return True if any zombies has reached grid end
     */
    public boolean checkZombieDistance(){
        for(Zombie z: zombies){
            if(z.getScreenX() <= GamePanel.LAST_TILE_RANGE){
                return true;
            }
        }
        return false;
    }

    /**
     * Returns preferred dimension
     * @return preferred dimension
     */
    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(TILE_SIZE, TILE_SIZE);
    }

    /**
     * Returns maximum size allowed
     * @return maximum size
     */
    @Override
    public Dimension getMaximumSize()
    {
        return new Dimension(TILE_SIZE, TILE_SIZE);
    }

    /**
     * Returns minimum size
     * @return minimum size
     */
    @Override
    public Dimension getMinimumSize()
    {
        return new Dimension(TILE_SIZE, TILE_SIZE);
    }

    /**
     * Calls tileClicked from PlantPanel
     * @param e Mouse click
     */
    @Override
    public void mouseClicked(MouseEvent e)
    {
        plantPanel.tileClicked(this);
    }

    /**
     * Does nothing when pressed
     * @param e Mouse press
     */
    @Override
    public void mousePressed(MouseEvent e)
    {
        return;
    }

    /**
     * Does nothing when released
     * @param e Mouse release
     */
    @Override
    public void mouseReleased(MouseEvent e)
    {
        return;
    }

    /**
     * Does nothing when entered
     * @param e Mouse entry
     */
    @Override
    public void mouseEntered(MouseEvent e)
    {
        return;
    }

    /**
     * Does nothing when exited
     * @param e Mouse exit
     */
    @Override
    public void mouseExited(MouseEvent e)
    {
        return;
    }

    /**
     * Gets whether zombie moved to next tile
     * @return True if zombie moved, else false
     */
    public boolean getZombieMoved(){
        return zombieMoved;
    }

    /**
     * Gets whether projectile moved to next tile
     * @return True if projectile moved, else flase
     */
    public boolean getProjectileMoved(){
        return projectileMoved;
    }

    /**
     * Sets a shooter to shoot. Called when there are zombies in the same row
     * @param s Whether to shoot or not
     */
    public void setShouldShoot(boolean s){
        shouldShoot = s;
    }

    /**
     * Handles all collisions on a tile. Updates plants, zombies, projectiles, and dead sprites
     */
    public void update(){
        if(plant != null && !zombies.isEmpty()){
            if(plant.getHealth() <= 0){
                plant.update(State.DEATH);
                deadSet.add(plant);
                plant = null;

                int i = 0;
                int prevX = zombies.peek().getScreenX();
                boolean first = true;
                Iterator<Zombie> zombieIter = zombies.iterator();

                while(zombieIter.hasNext()){
                    Zombie z = zombieIter.next();
                    if(z.getHealth() > 0){
                        if(z.getScreenX() - prevX < GamePanel.ZOMBIE_RANGE && !first){
                            i += 20;
                            z.setIntersect(i);
                            prevX = z.getScreenX();
                        }

                        if(!projectiles.isEmpty() && z.getScreenX() - projectiles.peek().getScreenX() < GamePanel.PROJECTILE_HITBOX){
                            Projectile p = projectiles.poll();
                            z.reduceHealth(p.getDamage());
                            p.update(State.DEATH);
                            deadSet.add(p);
                        }

                        z.zeroAttackCounter();
                        z.update(State.IDLE);
                        first = false;
                    }
                    else{
                        deadSet.add(z);
                        zombieIter.remove();
                    }
                }
            }
            else{
                Iterator<Zombie> zombieIter = zombies.iterator();
                boolean zombieAttacking = false;

                while(zombieIter.hasNext()){
                    Zombie z = zombieIter.next();
                    if(z.getHealth() > 0){
                        if(z.attackReady()){
                            if(z.getDoneDamage()){
                                plant.reduceHealth(z.getDamage());
                                z.setDoneDamage(false);
                            }
                            
                            z.update(State.ACTION);
                            zombieAttacking = true;
                        }
                        else{
                            z.update(State.REST);
                        }

                        if(!projectiles.isEmpty() && z.getScreenX() - projectiles.peek().getScreenX() < GamePanel.PROJECTILE_HITBOX){
                            Projectile p = projectiles.poll();
                            z.reduceHealth(p.getDamage());
                            p.update(State.DEATH);
                            deadSet.add(p);
                        }                     
                    }
                    else{
                        deadSet.add(z);
                        zombieIter.remove();
                    }
                }

                //Plant logic
                if(zombieAttacking){
                    if(plant.attackReady() || plant instanceof Walnut){
                        plant.update(State.ACTION);
                        if(plant.getShot()){ 
                            this.addProjectile(new Projectile(gridX, gridY)); 
                            plant.setShot(false);
                        }
                    }
                    else{
                        plant.update(State.IDLE);
                    }
                }
                else{
                    boolean atkReady = plant.attackReady();
                    if((atkReady && (shouldShoot || plant instanceof SunFlower))){
                        plant.update(State.ACTION);
                        if(plant.getShot()){ 
                            this.addProjectile(new Projectile(gridX, gridY)); 
                            plant.setShot(false);
                        }
                    }
                    else if(plant instanceof Walnut && plant.getHealth() < Walnut.FULL_HEALTH){
                        plant.update(State.REST);
                    }
                    else{
                        plant.update(State.IDLE);
                    }
                }
            }
        }
        else if(plant != null){
            boolean atkReady = plant.attackReady();
            if(atkReady && shouldShoot){
                plant.update(State.ACTION);
                if(plant.getShot()){ 
                    this.addProjectile(new Projectile(gridX, gridY)); 
                    plant.setShot(false);
                }
            }
            else if(atkReady && plant instanceof SunFlower){
                plant.update(State.ACTION);
            }
            else{
                plant.update(State.IDLE);
            }
        }
        else if(!zombies.isEmpty()){
            Iterator<Zombie> zombieIter = zombies.iterator();
            while(zombieIter.hasNext()){
                Zombie z = zombieIter.next();
                if(z.getHealth() > 0){
                    z.update(State.IDLE);

                    if(this.zombieMoved == false){
                        this.zombieMoved = z.hasMovedNextTile();
                    }

                    if(!projectiles.isEmpty() && z.getScreenX() - projectiles.peek().getScreenX() < GamePanel.PROJECTILE_HITBOX){
                        Projectile p = projectiles.poll();
                        z.reduceHealth(p.getDamage());
                        p.update(State.DEATH);
                        deadSet.add(p);
                    }
                }
                else{
                    deadSet.add(z);
                    zombieIter.remove();
                }
            }
        }

        if(!projectiles.isEmpty()){
            for(Projectile p: projectiles){
                p.update(State.IDLE);

                if(this.projectileMoved == false){
                    this.projectileMoved = p.hasMovedNextTile();
                }
            }
        }

        if(!deadSet.isEmpty()){
            Iterator<Sprite> iter = deadSet.iterator();
            while(iter.hasNext()){
                Sprite s = iter.next();
                if(s instanceof KingKwong && ((KingKwong) s).getShouldSpawn()){
                    this.addZombie(new FulkZombie(gridX, gridY, 0));
                    ((KingKwong) s).setShouldSpawn(false);
                }

                if(!s.getDoneDeath()){
                    s.update(State.DEATH);
                }
                else{
                    iter.remove();
                }

            }
        }
    }

    /**
     * Draws all sprites onto given graphics
     * @param g Graphics context to draw onto
     */
    public void draw(Graphics g){
        g.drawImage(TILE_IMAGE, screenX, screenY, null);
        if(plant != null) plant.draw(g);
        if(!zombies.isEmpty()){
            for(Zombie z : zombies){
                z.draw(g);
            }
        }
        if(!deadSet.isEmpty()){
            for(Sprite s: deadSet){
                s.draw(g);
            }
        }
        if(!projectiles.isEmpty()){
            for(Projectile p : projectiles){
                p.draw(g);
            }
        }
    }
}