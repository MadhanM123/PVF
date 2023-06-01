package sprites.zombies;

import java.awt.Graphics;

import sprites.Sprite;

/**
 * The zombie abstract class that is template for other zombie classes
 * @author Madhan M., Andrew X.
 * @version 2023-05-28
 */
public abstract class Zombie extends Sprite{

    private boolean nextTile;

    private int intersect;

    private boolean doneDamage;

    private final String name;

    /**
     * generic zombie constructor
     * @param gridX grid x-coordinate starting from left
     * @param gridY grid y-coordinate starting from top
     * @param screenX x position on screen
     * @param screenY y position on screen
     * @param health health of zombie
     * @param damage damage of zombie
     * @param attackRate attack rate of zombie
     * @param name name of zombie
     */
    public Zombie(int gridX, int gridY, int screenX, int screenY, int health, int damage, int attackRate, String name){
        super(gridX, gridY, screenX, screenY, health, damage, attackRate);
        this.nextTile = false;
        this.intersect = 0;
        this.doneDamage = false;
        this.name = name;
    }

    public abstract void update(State state);

    public abstract void draw(Graphics g);

    /**
     * sets if the zombie has move to next tile
     * @param moved the condition if the moved or not
     */
    public void setMovedNextTile(boolean moved){
        nextTile = moved;
    }

    /**
     * returns if the zombie has moved to next tile
     * @return if the zombie has moved to next tile
     */
    public boolean hasMovedNextTile(){
        return nextTile;
    }
    
    /**
     * returns the intersect between zombies
     * @return the intersect between zombies
     */
    public int getIntersect(){
        return intersect;
    }

    /**
     * sets the intersect between zombies
     * @param intersect the intersect value
     */
    public void setIntersect(int intersect){
        this.intersect = intersect;
    }

    /**
     * returns if the zombies has done damage
     * @return if the zombie has done damage
     */
    public boolean getDoneDamage(){
        return doneDamage;
    }

    /**
     * changes the condition if the zombie has done damage
     * @param doneDamage the condiition if the zombie has done damage
     */
    public void setDoneDamage(boolean doneDamage){
        this.doneDamage = doneDamage;
    }

    /**
     * retruns the name of the type of zombie
     * @return name of the type of zombie
     */
    public String getName(){
        return name;
    }
}