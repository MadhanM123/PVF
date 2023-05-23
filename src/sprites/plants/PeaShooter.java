package sprites.plants;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class PeaShooter extends Plant{

    public static final int ACTION_RATE = 0;
    public static final int FULL_HEALTH = 0;
    public static final int DAMAGE = 0;
    public static final int HITBOX = 0;
    public static final int IDLE_RATE = 5;

    public static final int COST = 10;

    private static final int HEIGHT = 90;
    private static final int WIDTH = 100;

    private static final Image idle1Img = new ImageIcon("resources/sprites/plants/peashooter/ps.idle1.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
    private static final Image idle2Img = new ImageIcon("resources/sprites/plants/peashooter/ps.idle2.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
    private static final Image shootImg = new ImageIcon("resources/sprites/plants/peashooter/ps.shoot.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);

    private Image currentImg;

    public PeaShooter(int gridX, int gridY, int screenX, int screenY){
        super(gridX, gridY, screenX, screenY, FULL_HEALTH);
        currentImg = idle1Img;
    }

    private void shoot(){

    }

    public void update(State state){
        tickIdleCounter();
        if(getIdleCounter() > IDLE_RATE){
            if(currentImg == idle1Img){
                currentImg = idle2Img;
            }
            else{
                currentImg = idle1Img;
            }

            zeroIdleCounter();
        }
    }

    public void draw(Graphics g){
        g.drawImage(currentImg, -15, 30, null);
    }

    @Override
    public boolean canDefend()
    {
        return true;
    }
    
}