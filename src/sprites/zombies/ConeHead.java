package sprites.zombies;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class ConeHead extends Zombie{

    public static final int ACTION_RATE = 0;
    public static final int FULL_HEALTH = 0;
    public static final int DAMAGE = 0;
    public static final int HITBOX = 0;
    public static final int WALK_RATE = 5;
    public static final int OFFSET = -5;

    private static final int HEIGHT = 280;
    private static final int WIDTH = 280;

    private static final Image walk1Img = new ImageIcon("resources/sprites/zombies/conehead/walk1.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
    private static final Image walk2Img = new ImageIcon("resources/sprites/zombies/conehead/walk3.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
    private static final Image walk3Img = new ImageIcon("resources/sprites/zombies/conehead/walk2.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);

    private Image currentImg;

    public ConeHead(int gridX, int gridY, int screenX, int screenY){
        super(gridX, gridY, screenX, screenY, FULL_HEALTH);
        this.currentImg = walk1Img;
    }
    
    public void update(){
        tickWalkCounter();
        if(getWalkCounter() > WALK_RATE){
            if(currentImg == walk1Img){
                currentImg = walk2Img;
            }
            else if(currentImg == walk2Img){
                currentImg = walk3Img;
            }
            else if(currentImg == walk3Img){
                currentImg = walk1Img;
            }
            changeRealScreenX(OFFSET);
            zeroWalkCounter();
        }
    }

    public void draw(Graphics g){
        g.drawImage(currentImg, getRealScreenX(), getRealScreenY(), null);
    }
}