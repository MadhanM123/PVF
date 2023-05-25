package sprites;

import java.awt.Graphics;
import java.awt.Image;

public abstract class Sprite{
    private int gridX;
    private int gridY;
    
    private int realScreenX;
    private int realScreenY;

    private int health;
    private int damage;

    public enum State{
        IDLE,
        ACTION,
        DEATH
    };

    private boolean doneDeath;

    private State prev;

    private Image currentImg;

    public static final int TILE_SIZE = 110;

    public Sprite(int gridX, int gridY, int screenX, int screenY, int health, int damage){
        this.gridX = gridX;
        this.gridY = gridY;
        this.realScreenX = screenX;
        this.realScreenY = screenY;
        this.health = health;
        this.damage = damage;
        this.doneDeath = false;
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

    public int getHealth(){
        return health;
    }

    public void reduceHealth(int dec){
        this.health -= dec;
    }

    public int getDamage(){
        return damage;
    }

    public Image getCurrentImg(){
        return currentImg;
    }

    public void setCurrentImg(Image i){
        this.currentImg = i;
    }

    public boolean isDead(){
        return health <= 0;
    }

    public boolean getDoneDeath(){
        return doneDeath;
    }

    public void setDoneDeath(boolean d){
        doneDeath = d;
    }
}