package sprites.plants;

import java.awt.Graphics;

public class PeaShooter extends Plant{

    public static final int ACTION_RATE = 0;
    public static final int FULL_HEALTH = 0;
    public static final int DAMAGE = 0;
    public static final int HITBOX = 0;

    public static final int COST = 10;

    public PeaShooter(int gridX, int gridY, int screenX, int screenY){
        super(gridX, gridY, screenX, screenY, FULL_HEALTH);
    }

    private void shoot(){

    }

    public void update(){

    }

    public void draw(Graphics g){
        
    }
    
}