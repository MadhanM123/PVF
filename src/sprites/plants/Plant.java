package sprites.plants;

import java.awt.Graphics;
import sprites.Sprite;

public abstract class Plant extends Sprite{

    private int idleCounter;
    private int actionCounter;
    private int deathCounter;

    private boolean shot;
    
    public Plant(int gridX, int gridY, int screenX, int screenY, int health, int damage, int attackRate){
        super(gridX, gridY, screenX, screenY, health, damage, attackRate);
        this.idleCounter = 0;
        this.actionCounter = 0;
        this.deathCounter = 0;
        this.shot = true;
    }
    
    public abstract void update(State state);

    public abstract void draw(Graphics g);

    public boolean getShot(){
        return shot;
    }

    public void setShot(boolean s){
        this.shot = s;
    }
}