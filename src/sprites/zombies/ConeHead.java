package sprites.zombies;

import java.awt.Graphics;

public class ConeHead extends Zombie{

    public static final int ACTION_RATE = 0;
    public static final int FULL_HEALTH = 0;
    public static final int DAMAGE = 0;
    public static final int HITBOX = 0;
    public static final int VELOCITY = 0;

    public ConeHead(int gridX, int gridY, int screenX, int screenY){
        super(gridX, gridY, screenX, screenY, FULL_HEALTH, VELOCITY);
    }
    
    public void update(){

    }

    public void draw(Graphics g){
        
    }
}