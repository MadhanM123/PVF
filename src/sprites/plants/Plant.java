package sprites.plants;

import java.awt.Graphics;
import sprites.Sprite;

public abstract class Plant extends Sprite{
    private boolean shot;
    
    public Plant(int gridX, int gridY, int screenX, int screenY, int health, int damage, int attackRate){
        super(gridX, gridY, screenX, screenY, health, damage, attackRate);
        this.shot = false;
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