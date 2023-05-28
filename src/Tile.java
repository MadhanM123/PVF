import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Iterator;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import sprites.Sprite;
import sprites.Sprite.State;
import sprites.plants.*;
import sprites.projectile.Projectile;
import sprites.zombies.*;

public class Tile extends JComponent implements MouseListener{
    private Queue<Zombie> zombies;
    private Plant plant;
    private Queue<Projectile> projectiles;
    private Set<Sprite> deadSet;

    private int gridX;
    private int gridY;

    private int screenX;
    private int screenY;

    private boolean zombieMoved;
    private boolean projectileMoved;
    private boolean shouldShoot;

    private PlantPanel.PlantSelector plantSelector;

    public static Image TILE_IMAGE;
    public static int TILE_SIZE;

    static{
        TILE_SIZE = 110;
        TILE_IMAGE = new ImageIcon("resources/sprites/tile/grasstile.png").getImage().getScaledInstance(TILE_SIZE, TILE_SIZE, Image.SCALE_DEFAULT);
    }

    /**
     * initializes a tile with no sprites and default background
     */
    public Tile(int gridX, int gridY, PlantPanel.PlantSelector ps){
        this.plant = null;
        this.zombies = new LinkedList<Zombie>();
        this.deadSet = new HashSet<>();
        this.gridX = gridX;
        this.gridY = gridY;
        this.setPreferredSize(new Dimension(TILE_SIZE, TILE_SIZE));
        this.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        this.addMouseListener(this);
        this.plantSelector = ps;
        this.zombieMoved = false;
        this.projectileMoved = false;
        this.shouldShoot = false;
        this.screenX = gridX * TILE_SIZE;
        this.screenY = gridY * TILE_SIZE;
        this.projectiles = new LinkedList<>();
    }

    /**
     * checks if the tile has anything on it
     * @return true if tile is empty and false if there is anything on the tile
     */
    public boolean isEmpty(){
        return (zombies.isEmpty() && plant == null);
    }

    public boolean hasPlant(){
        return plant != null;
    }

    public boolean hasZombie(){
        return !zombies.isEmpty();
    }

    public boolean hasProjectile(){
        return !projectiles.isEmpty();
    }

    /**
     * gets the first zombie in the tile
     * @return the first zombie in the tile
     */
    public Queue<Zombie> getZombies(){
        return zombies;
    }

    public Queue<Projectile> getProjectiles(){
        return projectiles;
    }

    /**
     * gets the plant on the tile
     * @return the plant on the tile
     */
    public Plant getPlant(){
        return plant;
    }

    /**
     * adds a new zombie into the tile
     * @param z the zombie to be added into the tile
     */
    public void addZombie(Zombie z){
        zombies.add(z);
    }

    /**
     * adds a new plant onto the tile
     * @param p the plants to be added into the tile
     */
    public void addPlant(Plant p){
        plant = p;
    }

    public void addProjectile(Projectile p){
        projectiles.add(p);
    }

    /**
     * removes the first zombie in the tile
     */
    public Zombie removeZombie(){
        return zombies.remove();
    }

    public int getGridY(){
        return this.gridY;
    }

    public int getGridX(){
        return this.gridX;
    }

    public void clearZombies(){
        while(!zombies.isEmpty()){
            Zombie z = zombies.poll();
            deadSet.add(z);
        }
    }

    public void clearPlant(){
        if(plant != null){
            deadSet.add(plant);
            plant = null;
        }
    }

    public void clearProjectile(){
        while(!projectiles.isEmpty()){
            Projectile p = projectiles.poll();
            deadSet.add(p);
        }
    }

    public boolean checkProjectile(Projectile p){
        if(checkProjectileDistance(p) && (zombies.isEmpty() || (!zombies.isEmpty() && p.getRealScreenX() > zombies.peek().getRealScreenX()))){
            return true;
        }
        return false;
    }

    public boolean checkZombieDistance(){
        for(Zombie z: zombies){
            if(z.getRealScreenX() <= GamePanel.LAST_TILE_RANGE){
                return true;
            }
        }
        return false;
    }

    private boolean checkProjectileDistance(Projectile p){
        if(GamePanel.SCREEN_WIDTH - p.getRealScreenX() <= GamePanel.LAST_TILE_RANGE){
            return true;
        }
        return false;
    }

    public void update(){
        if(plant != null && !zombies.isEmpty()){
            if(plant.isDead()){
                plant.update(State.DEATH);
                deadSet.add(plant);
                plant = null;

                int i = 0;
                int prevX = zombies.peek().getRealScreenX();
                boolean first = true;
                Iterator<Zombie> zombieIter = zombies.iterator();

                while(zombieIter.hasNext()){
                    Zombie z = zombieIter.next();
                    if(!z.isDead()){
                        if(z.getRealScreenX() - prevX < GamePanel.ZOMBIE_RANGE && !first){
                            i += 20;
                            z.setIntersect(i);
                            prevX = z.getRealScreenX();
                        }

                        if(!projectiles.isEmpty() && z.getRealScreenX() - projectiles.peek().getRealScreenX() < GamePanel.PROJECTILE_HITBOX){
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
                    if(!z.isDead()){
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

                        if(!projectiles.isEmpty() && z.getRealScreenX() - projectiles.peek().getRealScreenX() < GamePanel.PROJECTILE_HITBOX){
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
                if(!z.isDead()){
                    z.update(State.IDLE);
                    setZombieMoved(z.hasMovedNextTile());

                    if(!projectiles.isEmpty() && z.getRealScreenX() - projectiles.peek().getRealScreenX() < GamePanel.PROJECTILE_HITBOX){
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
                setProjectileMoved(p.hasMovedNextTile());
            }
        }

        if(!deadSet.isEmpty()){
            Iterator<Sprite> iter = deadSet.iterator();
            while(iter.hasNext()){
                Sprite s = iter.next();
                if(s instanceof KingKwong && s.getPrev() != State.DEATH){
                    this.addZombie(new FulkZombie(gridX, gridY));
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

    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(TILE_SIZE, TILE_SIZE);
    }

    @Override
    public Dimension getMaximumSize()
    {
        return new Dimension(TILE_SIZE, TILE_SIZE);
    }

    @Override
    public Dimension getMinimumSize()
    {
        return new Dimension(TILE_SIZE, TILE_SIZE);
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        plantSelector.attemptAddPlant(this);
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        return;
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        return;
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {
        return;
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
        return;
    }

    public void setZombieMoved(boolean moved){
        if(this.zombieMoved == false) this.zombieMoved = moved;
    }

    public boolean zombieMoved(){
        return zombieMoved;
    }

    public boolean colliding(){
        return plant != null && zombies.peek() != null;
    }

    public boolean projectileMoved(){
        return projectileMoved;
    }

    public void setProjectileMoved(boolean moved){
        if(this.projectileMoved == false) this.projectileMoved = moved;
    }

    public boolean shouldShoot(){
        return shouldShoot;
    }

    public void setShouldShoot(boolean s){
        shouldShoot = s;
    }
}