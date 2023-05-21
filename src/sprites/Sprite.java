package sprites;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public abstract class Sprite{
    private int gridX;
    private int gridY;
    
    private int realScreenX;
    private int realScreenY;

    private int health;

    public Sprite(int gridX, int gridY, int screenX, int screenY, int health){
        this.gridX = gridX;
        this.gridY = gridY;
        this.realScreenX = screenX;
        this.realScreenY = screenY;
        this.health = health;
    }

    public abstract void update();
    
    public abstract void draw(Graphics g);

    public int getRealScreenX(){
        return realScreenX;
    }

    public int getRealScreenY(){
        return realScreenY;
    }

    public void changeRealScreenX(int offset){
        realScreenX += offset;
    }

    public void changeRealScreenY(int offset){
        realScreenY += offset;
    }
}