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

    private BufferedImage defaultImg = null;

    /**
     * initializes a sprite class
     * @param gridX grid x cord
     * @param gridY grid y cord
     * @param screenX pix; x cord
     * @param screenY pixl y cord
     * @param actionRate action rate of sprite
     * @param health health of sprite
     * @param damage damage of sprite
     * @param hitBox max distance between sprites collisions 
     */
    public Sprite(int gridX, int gridY, int screenX, int screenY, double actionRate, int health, int damage, int hitBox){
        this.gridX = gridX;
        this.gridY = gridY;
        realScreenX = screenX;
        realScreenY = screenY;
        this.actionRate = actionRate;
        this.health = health;
        this.damage = damage;
        this.hitBox = hitBox;
    }

    public abstract void update();
    
    public abstract void draw(Graphics g);
}