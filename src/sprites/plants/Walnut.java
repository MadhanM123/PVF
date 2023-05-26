package sprites.plants;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Walnut extends Plant{
    public static final int FULL_HEALTH = 2000;
    public static final int DAMAGE = 0;

    public static final int COST = 10;

    private static final int HEIGHT = 80;
    private static final int WIDTH = 55;

    private static final int VERT_OFFSET = 25;
    private static final int HORIZ_OFFSET = 4;

    private static final int ACTION_RATE = 30;

    private static final Image idleImg = new ImageIcon("resources/sprites/plants/walnut/wn.idle.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);

    private static final Image hurt1Img = new ImageIcon("resources/sprites/plants/walnut/wn.hurt1.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
    private static final Image hurt2Img = new ImageIcon("resources/sprites/plants/walnut/wn.hurt2.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
    
    public Walnut(int gridX, int gridY){
        super(gridX, gridY, gridX * TILE_SIZE + HORIZ_OFFSET, gridY * TILE_SIZE + VERT_OFFSET, FULL_HEALTH, DAMAGE);
        setCurrentImg(idleImg);
    }

    public void update(State state){
        if(state == State.IDLE){
            if(comparePrevState(state)){
                setCurrentImg(idleImg);
            }
        }
        else if(state == State.ACTION){
            if(comparePrevState(state)){
                zeroActionAniCounter();
                setCurrentImg(hurt1Img);
            }

            tickActionAniCounter();
            if(getActionAniCounter() > ACTION_RATE){
                if(getCurrentImg() == hurt1Img){
                    setCurrentImg(hurt2Img);
                }
                else{
                    setCurrentImg(hurt1Img);
                }
                zeroActionAniCounter();
            }
        }
        else if(state == State.DEATH){
            if(comparePrevState(state)){
                setCurrentImg(null);
                setDoneDeath(true);
            }
        }
    }

    public void draw(Graphics g){
        g.drawImage(getCurrentImg(), getRealScreenX(), getRealScreenY(), null);
    }

    @Override
    public boolean canDefend()
    {
        return false;
    }
}