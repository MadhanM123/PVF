package sprites.zombies;
import sprites.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import sprites.Sprite;

public abstract class Zombie extends Sprite{

    private int walkCounter;
    private int tileCounter;
    private int actionCounter;
    private int deathCounter;

    private boolean nextTile;

    public Zombie(int gridX, int gridY, int screenX, int screenY, int health){
        super(gridX, gridY, screenX, screenY, health);
        this.walkCounter = 0;
        this.actionCounter = 0;
        this.deathCounter = 0;
        this.tileCounter = 0;
        this.nextTile = false;
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
}