package sprites;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public abstract class Sprite{
    private int gridX;
    private int gridY;
    
    private int realScreenX;
    private int realScreenY;

    private double actionRate;

    private int health;
    private int damage;
    private int hitBox;

    private BufferedImage defaultImg;

    public Sprite(int gridX, int gridY, int screenX, int screenY, double actionRate, int health, int damage, int hitBox){

    }

    public abstract void update();

    public abstract void draw(Graphics g);
}