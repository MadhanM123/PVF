package sprites.plants;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public abstract class Plant extends Sprite{
    private BufferedImage action1;
    private BufferedImage action2;
    private BufferedImage tookDamage;
    private BufferedImage death;

    public Plant(int gridX, int gridY, int screenX, int screenY, double actionRate, int health, int damage, int hitBox){

    }

    public abstract void update();

    public abstract void draw(Graphics g);
}