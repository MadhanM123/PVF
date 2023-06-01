package sprites.plants;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

import music.MusicPlayer;

/**
 * The PeaShooter plant class
 * @author Madhan M., Andrew X.
 * @version 2023-05-28
 */
public class PeaShooter extends Plant{
    
    private static final int FULL_HEALTH = 1000;

    /**
     * {@value #COST} - Cost of PeaShooter
     */
    public static final int COST = 20;

    private static final int HEIGHT = 90;
    private static final int WIDTH = 100;

    private static final int VERT_OFFSET = 30;
    private static final int HORIZ_OFFSET = -15;

    private static final int IDLE_RATE = 5;
    private static final int ACTION_RATE = 8;
    private static final int DEATH_RATE = 10;
    private static final int ATTACK_RATE = 21;

    private static final Image idle1Img = new ImageIcon("resources/sprites/plants/peashooter/ps.idle1.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
    private static final Image idle2Img = new ImageIcon("resources/sprites/plants/peashooter/ps.idle2.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);

    private static final Image shootImg = new ImageIcon("resources/sprites/plants/peashooter/ps.shoot.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);

    private static final Image deathImg = idle1Img;

     /**
     * Initializes grid/screen coordinates, health, damage, attack rate, and sets up sound
     * @param gridX grid x-coordinate starting from left
     * @param gridY grid y-coordinate starting from top
     */
    public PeaShooter(int gridX, int gridY){
        super(gridX, gridY, gridX * TILE_SIZE + HORIZ_OFFSET, gridY * TILE_SIZE + VERT_OFFSET, FULL_HEALTH, 0, ATTACK_RATE);
        setCurrentImg(idle1Img);
        setClip(MusicPlayer.PEA_SHOOT);
    }

    public void update(State state){
        if(state == State.IDLE){
            if(comparePrevState(state)){
                zeroIdleCounter();
                setCurrentImg(idle1Img);
            }

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
        else if(state == State.ACTION){
            if(comparePrevState(state)){
                zeroActionAniCounter();
                setCurrentImg(shootImg);
            }

            tickActionAniCounter();
            if(getActionAniCounter() > ACTION_RATE){
                if(getCurrentImg() == shootImg){
                    setCurrentImg(idle2Img);
                }
                else{
                    setCurrentImg(shootImg);
                    playClip();
                    zeroAttackCounter();
                    setShot(true);
                }
                zeroActionAniCounter();
            }
        }
        else if(state == State.DEATH){
            if(comparePrevState(state)){
                zeroDeathCounter();
                setCurrentImg(deathImg);
            }

            tickDeathCounter();
            if(getDeathCounter() > DEATH_RATE){
                if(getCurrentImg() == deathImg){
                    setCurrentImg(null);
                    setDoneDeath(true);
                }
                zeroDeathCounter();
            }
        }
    }

    public void draw(Graphics g){
        g.drawImage(getCurrentImg(), getScreenX(), getScreenY(), null);
    }
}