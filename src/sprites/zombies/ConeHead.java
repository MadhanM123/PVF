package sprites.zombies;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class ConeHead extends Zombie{

    public static final int ACTION_RATE = 0;
    public static final int FULL_HEALTH = 0;
    public static final int DAMAGE = 0;
    public static final int HITBOX = 0;
    public static final int VELOCITY = 0;

    private static final int HEIGHT = 280;
    private static final int WIDTH = 280;

    private static final Image idleImg = new ImageIcon("resources/sprites/zombies/conehead/walk1.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);

    public ConeHead(int gridX, int gridY, int screenX, int screenY){
        super(gridX, gridY, screenX, screenY, FULL_HEALTH, VELOCITY);
    }
    
    public void update(){

    }

    public void draw(Graphics g){
        g.drawImage(idleImg, getRealScreenX(), getRealScreenY(), null);
    }
}