package sprites.plants;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class SunFlower extends Plant {

    public static final int ACTION_RATE = 0;
    public static final int FULL_HEALTH = 0;
    public static final int DAMAGE = 0;
    public static final int HITBOX = 0;

    public static final int COST = 10;

    private static final int HEIGHT = 140;
    private static final int WIDTH = 105;

    private static final Image idleImg = new ImageIcon("resources/sprites/plants/sunflower/sf.idle1.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
    
    
    public SunFlower(int gridX, int gridY, int screenX, int screenY){
        super(gridX, gridY, screenX, screenY, FULL_HEALTH);
    }

    public void addSun(){

    }

    public void update(State state){

    }

    public void draw(Graphics g){
        g.drawImage(idleImg, -10, 0, null);
    }

    @Override
    public boolean canDefend()
    {
        return false;
    }

    
}
