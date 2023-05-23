package sprites;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public abstract class Sprite{
    private int gridX;
    private int gridY;
    
    private int realScreenX;
    private int realScreenY;

    private int health;

    public enum State{
        IDLE,
        ACTION,
        DEATH
    };

    private State prev;

    public Sprite(int gridX, int gridY, int screenX, int screenY, int health){
        this.gridX = gridX;
        this.gridY = gridY;
        this.realScreenX = screenX;
        this.realScreenY = screenY;
        this.health = health;
    }

    public abstract void update(State state);
    
    public abstract void draw(Graphics g);

    public int getRealScreenX(){
        return realScreenX;
    }

    public int getRealScreenY(){
        return realScreenY;
    }

    public int getGridX(){
        return gridX;
    }

    public int getGridY(){
        return gridY;
    }

    public void setRealScreenX(int newX){
        realScreenX = newX;
    }

    public void setRealScreenY(int newY){
        realScreenY = newY;
    }

    public void setGridX(int newX){
        gridX = newX;
    }

    public void setGridY(int newY){
        gridY = newY;
    }

    public boolean comparePrevState(State s){
        if(s == prev){
            return false;
        }
        else{
            prev = s;
            return true;
        }
    }
}