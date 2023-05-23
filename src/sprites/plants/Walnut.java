package sprites.plants;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Walnut extends Plant{


    public static final int ACTION_RATE = 0;
    public static final int FULL_HEALTH = 0;
    private static final int DAMAGE = 0;
    private static final int HITBOX = 0;

    public static final int COST = 10;

    private static final int HEIGHT = 130;
    private static final int WIDTH = 105;

    private static final Image idleImg = new ImageIcon("resources/sprites/plants/walnut/wn.idle.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
    

    public Walnut(int gridX, int gridY, int screenX, int screenY){
        super(gridX, gridY, screenX, screenY, FULL_HEALTH);
    }

    public void update(State state){

    }

    public void draw(Graphics g){
        g.drawImage(idleImg, -20, 0, null);
    }

    @Override
    public boolean canDefend()
    {
        return false;
    }
}