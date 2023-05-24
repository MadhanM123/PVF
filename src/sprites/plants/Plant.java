package sprites.plants;
import sprites.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import sprites.Sprite;

public abstract class Plant extends Sprite{

    private int idleCounter;
    private int actionCounter;
    private int deathCounter;

    boolean canDefend;
    
    public Plant(int gridX, int gridY, int screenX, int screenY, int health, int damage){
        super(gridX, gridY, screenX, screenY, health, damage);
        this.idleCounter = 0;
        this.actionCounter = 0;
        this.deathCounter = 0;
        this.canDefend = true;
    }
    
    public abstract void update(State state);

    public abstract void draw(Graphics g);

    public void tickIdleCounter(){
        idleCounter++;
    }

    public int getIdleCounter(){
        return idleCounter;
    }

    public void zeroIdleCounter(){
        idleCounter = 0;
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
    
    public abstract boolean canDefend();
}