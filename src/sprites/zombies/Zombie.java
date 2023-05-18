package sprites.zombies;
import sprites.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import sprites.Sprite;

public abstract class Zombie extends Sprite{

    int velocity;

    public Zombie(int gridX, int gridY, int screenX, int screenY, int health, int velocity){
        super(gridX, gridY, screenX, screenY, health);
        this.velocity = velocity;
    }

    public abstract void update();

    public abstract void draw(Graphics g);
}