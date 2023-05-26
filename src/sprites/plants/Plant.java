package sprites.plants;

import java.awt.Graphics;
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
    
    public abstract boolean canDefend();
}