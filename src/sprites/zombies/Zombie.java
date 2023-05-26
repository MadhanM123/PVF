package sprites.zombies;

import java.awt.Graphics;
import sprites.Sprite;

public abstract class Zombie extends Sprite{

    private boolean nextTile;

    private int intersect;

    public Zombie(int gridX, int gridY, int screenX, int screenY, int health, int damage){
        super(gridX, gridY, screenX, screenY, health, damage);
        this.nextTile = false;
        this.intersect = 0;
    }

    public abstract void update(State state);

    public abstract void draw(Graphics g);

    public void movedNextTile(boolean moved){
        nextTile = moved;
    }

    public boolean hasMovedNextTile(){
        return nextTile;
    }
    
    public int getIntersect(){
        return intersect;
    }

    public void setIntersect(int intersect){
        this.intersect = intersect;
    }
}