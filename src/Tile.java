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
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import sprites.Sprite;
import sprites.Sprite.State;
import sprites.plants.*;
import sprites.zombies.*;

public class Tile extends JComponent implements MouseListener{
    private Queue<Zombie> zombies;
    private Plant plant;
    private Set<Sprite> deadSet;

    private int gridX;
    private int gridY;

    private int screenX;
    private int screenY;

    private boolean moved;

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
        this.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        this.addMouseListener(this);
        this.plantSelector = ps;
        this.moved = false;
        this.screenX = gridX * TILE_SIZE;
        this.screenY = gridY * TILE_SIZE;
    }

    /**
     * checks if the tile has anything on it
     * @return true if tile is empty and false if there is anything on the tile
     */
    public boolean isEmpty(){
        return (zombies.isEmpty() && plant == null);
    }

    public boolean hasNoPlant(){
        return plant == null;
    }

    public boolean hasZombie(){
        return !zombies.isEmpty();
    }

    /**
     * gets the first zombie in the tile
     * @return the first zombie in the tile
     */
    public Queue<Zombie> getZombies(){
        return zombies;
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

    public void update(){
        if(plant != null && !zombies.isEmpty() && !plant.isDead()){
            for(Zombie z: zombies){
                if(!z.isDead()){
                    plant.update(State.ACTION);
                    plant.reduceHealth(z.getDamage());

                    z.update(State.ACTION);
                    z.reduceHealth(plant.getDamage());
                }
                else{
                    z.update(State.DEATH);
                    deadSet.add(z);
                    zombies.poll();
                }
            }
        }
        else if(plant != null && !zombies.isEmpty()){
            plant.update(State.DEATH);
            deadSet.add(plant);
            plant = null;
            
            int intersect = 0;
            for(Zombie z : zombies){
                if(z.isDead()){
                    z.update(State.DEATH);
                    deadSet.add(z);
                    zombies.poll();
                }
                z.setIntersect(intersect);
                z.update(State.IDLE);
                intersect += 20;
            }
        }
        else if(plant != null){
            plant.update(State.IDLE);
        }
        else if(!zombies.isEmpty()){
            for(Zombie z : zombies){
                z.update(State.IDLE);
                setZombieMoved(z.hasMovedNextTile());
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
        this.moved = moved;
    }

    public boolean zombieMoved(){
        return moved;
    }

    public boolean colliding(){
        return plant != null && zombies.peek() != null;
    }
}