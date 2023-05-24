package sprites.plants;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Repeater extends Plant{
    public static final int FULL_HEALTH = 0;
    public static final int DAMAGE = 0;

    public static final int COST = 10;

    private static final int HEIGHT = 90;
    private static final int WIDTH = 100;

    private static final int VERT_OFFSET = 30;
    private static final int HORIZ_OFFSET = -18;

    private static final int IDLE_RATE = 5;

    private static final int ACTION_RATE = 0;

    private static final Image idle1Img = new ImageIcon("resources/sprites/plants/repeater/rp.idle1.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
    private static final Image idle2Img = new ImageIcon("resources/sprites/plants/repeater/rp.idle2.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);

    private static final Image shootImg = new ImageIcon("resources/sprites/plants/repeater/rp.shoot.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);

    public Repeater(int gridX, int gridY){
        super(gridX, gridY, gridX * TILE_SIZE + HORIZ_OFFSET, gridY * TILE_SIZE + VERT_OFFSET, FULL_HEALTH, DAMAGE);
        setCurrentImg(idle1Img);
    }

    private void shoot(){

    }

    public void update(State state){
        tickIdleCounter();
        if(getIdleCounter() > IDLE_RATE){
            if(getCurrentImg() == idle1Img){
                setCurrentImg(idle2Img);
            }
            else{
                setCurrentImg(idle1Img);
            }

            zeroIdleCounter();
        }
    }

    public void draw(Graphics g){
        g.drawImage(getCurrentImg(), getRealScreenX(), getRealScreenY(), null);
    }

    @Override
    public boolean canDefend()
    {
        return true;
    }
    
}