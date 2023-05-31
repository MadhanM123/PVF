package sprites.plants;

import java.awt.Graphics;
import sprites.Sprite;

/**
 * The plant absract class that is template for other plant classes
 * @author Madhan M., Andrew X.
 * @version 2023-05-28
 */
public abstract class Plant extends Sprite{

    private boolean shot;

    /**
     * generic plant constructor
     * @param gridX grid x-coordinate starting from left
     * @param gridY grid y-coordinate starting from top
     * @param screenX x position on screen
     * @param screenY y position on screen
     * @param health health of plant
     * @param damage damage of plant
     * @param attackRate attack rate of plant
     */
    public Plant(int gridX, int gridY, int screenX, int screenY, int health, int damage, int attackRate){
        super(gridX, gridY, screenX, screenY, health, damage, attackRate);
        this.shot = false;
    }
    
    public abstract void update(State state);

    public abstract void draw(Graphics g);

    /**
     * checks if the plant has shot
     * @return shot condition
     */
    public boolean getShot(){
        return shot;
    }

    /**
     * modifies if the plant has shot
     * @param s the shot condition
     */
    public void setShot(boolean s){
        this.shot = s;
    }
}