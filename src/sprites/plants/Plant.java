package sprites.plants;
import sprites.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import sprites.Sprite;

public abstract class Plant extends Sprite{

    boolean canDefend;
    
    public Plant(int gridX, int gridY, int screenX, int screenY, int health){
        super(gridX, gridY, screenX, screenY, health);
        this.canDefend = true;
    }
    
    public abstract void update(State state);

    public abstract void draw(Graphics g);

    public abstract boolean canDefend();
}