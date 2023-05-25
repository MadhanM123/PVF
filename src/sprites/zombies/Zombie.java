package sprites.zombies;

import java.awt.Graphics;
import sprites.Sprite;

public abstract class Zombie extends Sprite{

    private int walkCounter;
    private int tileCounter;
    private int actionCounter;
    private int deathCounter;

    private boolean nextTile;

    private int intersect;

    public Zombie(int gridX, int gridY, int screenX, int screenY, int health, int damage){
        super(gridX, gridY, screenX, screenY, health, damage);
        this.walkCounter = 0;
        this.actionCounter = 0;
        this.deathCounter = 0;
        this.tileCounter = 0;
        this.nextTile = false;
        this.intersect = 0;
    }

    public abstract void update(State state);

    public abstract void draw(Graphics g);

    public void tickWalkCounter(){
        walkCounter++;
    }

    public int getWalkCounter(){
        return walkCounter;
    }

    public void zeroWalkCounter(){
        walkCounter = 0;
    }

    public void tickTileCounter(){
        tileCounter++;
    }

    public int getTileCounter(){
        return tileCounter;
    }

    public void zeroTileCounter(){
        tileCounter = 0;
    }

    public void movedNextTile(boolean moved){
        nextTile = moved;
    }

    public boolean hasMovedNextTile(){
        return nextTile;
    }

    public void tickActionCounter(){
        actionCounter++;
    }

    public int getActionCounter(){
        return actionCounter;
    }

    public void zeroActionCounter(){
        actionCounter = 0;
    }

    public void tickDeathCounter(){
        deathCounter++;
    }

    public int getDeathCounter(){
        return deathCounter;
    }

    public void zeroDeathCounter(){
        deathCounter = 0;
    }

    public int getIntersect(){
        return intersect;
    }

    public void setIntersect(int intersect){
        this.intersect = intersect;
    }
}