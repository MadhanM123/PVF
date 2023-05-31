package sprites.plants;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * The Wallnut plant class
 * @author Madhan M., Andrew X.
 * @version 2023-05-28
 */
public class Walnut extends Plant{
    /**
     * {@value #FULL_HEALTH} Full health of Wallnut
     */
    public static final int FULL_HEALTH = 1000;
    private static final int DAMAGE = 0;

    /**
     * {@value #COST} Cost of Wallnut
     */
    public static final int COST = 10;

    private static final int HEIGHT = 80;
    private static final int WIDTH = 55;

    private static final int VERT_OFFSET = 25;
    private static final int HORIZ_OFFSET = 4;

    private static final int ACTION_RATE = 30;
    private static final int ATTACK_RATE = 0;

    private static final Image idleImg = new ImageIcon("resources/sprites/plants/walnut/wn.idle.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);

    private static final Image hurt1Img = new ImageIcon("resources/sprites/plants/walnut/wn.hurt1.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
    private static final Image hurt2Img = new ImageIcon("resources/sprites/plants/walnut/wn.hurt2.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
    
    /**
     * Initializes grid/screen coordinates, health, damage, and attack rate
     * @param gridX grid x-coordinate starting from left
     * @param gridY grid y-coordinate starting from top
     */
    public Walnut(int gridX, int gridY){
        super(gridX, gridY, gridX * TILE_SIZE + HORIZ_OFFSET, gridY * TILE_SIZE + VERT_OFFSET, FULL_HEALTH, DAMAGE, ATTACK_RATE);
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
        g.drawImage(getCurrentImg(), getScreenX(), getScreenY(), null);
    }

    @Override
    public boolean attackReady()
    {
        return false;
    } 
}