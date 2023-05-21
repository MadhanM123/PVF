package sprites.zombies;
import sprites.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import sprites.Sprite;

public abstract class Zombie extends Sprite{

    private int walkCounter;
    private int actionCounter;
    private int deathCounter;

    public Zombie(int gridX, int gridY, int screenX, int screenY, int health){
        super(gridX, gridY, screenX, screenY, health);
        this.walkCounter = 0;
        this.actionCounter = 0;
        this.deathCounter = 0;
    }

    public abstract void update();

    public abstract void draw(Graphics g);

    public void tickWalkCounter(){
        walkCounter++;
    }

    public int getWalkCounter(){
        return walkCounter;
    }

    public void zeroWalkCounter(){
        walkCounter = 0;
    }
}