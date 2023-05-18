package sprites.plants;
import sprites.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import sprites.Sprite;

public abstract class Plant extends Sprite{
    
    public Plant(int gridX, int gridY, int screenX, int screenY, int health){
        super(gridX, gridY, screenX, screenY, health);
    }
    
    public abstract void update();

    public abstract void draw(Graphics g);
}