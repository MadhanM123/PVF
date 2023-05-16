package sprites.plants;
import sprites.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public abstract class Plant extends Sprite{
    private BufferedImage action1;
    private BufferedImage action2;
    private BufferedImage tookDamage;
    private BufferedImage death;

     /**
     * initializes a plant class and set default images to null
     * @param gridX grid x cord
     * @param gridY grid y cord
     * @param screenX pix; x cord
     * @param screenY pixl y cord
     * @param actionRate action rate of plant
     * @param health health of plant
     * @param damage damage of plant
     * @param hitBox max distance between sprites collisions 
     */
    public Plant(int gridX, int gridY, int screenX, int screenY, double actionRate, int health, int damage, int hitBox){
        super(gridX, gridY, screenX, screenY, actionRate, health, damage, hitBox);
        action1 = null;
        action2 = null;
        tookDamage = null;
        death = null;
    }


    public abstract void update();

    public abstract void draw(Graphics g);
}