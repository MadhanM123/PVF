package sprites.zombies;

import java.awt.Graphics;
import sprites.Sprite;

public abstract class Zombie extends Sprite{

    private boolean nextTile;

    private int intersect;

    private boolean battling;

    private boolean doneDamage;

    public Zombie(int gridX, int gridY, int screenX, int screenY, int health, int damage, int attackRate){
        super(gridX, gridY, screenX, screenY, health, damage, attackRate);
        this.nextTile = false;
        this.intersect = 0;
        this.battling = false;
        this.doneDamage = false;
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

    public boolean getBattling(){
        return battling;
    }

    public void setBattling(boolean b){
        this.battling = b;
    }

    public boolean getDoneDamage(){
        return doneDamage;
    }

    public void setDoneDamage(boolean doneDamage){
        this.doneDamage = doneDamage;
    }
}