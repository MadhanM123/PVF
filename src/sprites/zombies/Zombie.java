package sprites.zombies;
import sprites.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public abstract class Zombie extends Sprite{
    private BufferedImage action1;
    private BufferedImage action2;
    private BufferedImage tookDamage;
    private BufferedImage death;

    private int speed;

     /**
     * initializes a zombie class and set default images to null
     * @param gridX grid x cord
     * @param gridY grid y cord
     * @param screenX pix; x cord
     * @param screenY pixl y cord
     * @param actionRate action rate of zombie
     * @param health health of zombie
     * @param damage damage of zombie
     * @param hitBox max distance between sprites collisions 
     */
    public Zombie(int gridX, int gridY, int screenX, int screenY, double actionRate, int health, int damage, int hitBox){
        super(gridX, gridY, screenX, screenY, actionRate, health, damage, hitBox);
        action1 = null;
        action2 = null;
        tookDamage = null;
        death = null;
    }

    public abstract void update();

    public abstract void draw(Graphics g);
}