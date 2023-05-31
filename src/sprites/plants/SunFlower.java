package sprites.plants;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

import music.MusicPlayer;

/**
 * The SunFlower plant class
 * @author Madhan M., Andrew X.
 * @version 2023-05-28
 */
public class SunFlower extends Plant {
    private static final int FULL_HEALTH = 1000;
    private static final int DAMAGE = 0;

    /**
     * {@value #COST} Cost of Sunflower
     */
    public static final int COST = 10;

    private static final int HEIGHT = 130;
    private static final int WIDTH = 85;

    private static final int SUN_HEIGHT = 50;
    private static final int SUN_WIDTH = 50;

    private static final int VERT_OFFSET = -15;
    private static final int HORIZ_OFFSET = -8;

    private static final int IDLE_RATE = 5;

    private static final int ACTION_RATE = 4;
    private static final int DEATH_RATE = 10;
    private static final int ATTACK_RATE = 250;

    private static final int HOLD_TIME = 30;

    private static final Image idle1Img =  new ImageIcon("resources/sprites/plants/sunflower/sf.idle1.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
    private static final Image idle2Img =  new ImageIcon("resources/sprites/plants/sunflower/sf.idle2.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
    private static final Image idle3Img =  new ImageIcon("resources/sprites/plants/sunflower/sf.idle3.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
    private static final Image idle4Img =  new ImageIcon("resources/sprites/plants/sunflower/sf.idle2.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);

    private static final Image sunProduce1Img =  new ImageIcon("resources/sprites/plants/sunflower/sf.produce1.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
    private static final Image sunProduce2Img =  new ImageIcon("resources/sprites/plants/sunflower/sf.produce2.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);

    private static final Image sunProduce3Img = idle1Img;

    private static final Image deathImg = idle3Img;

    private static final Image sunImg =  new ImageIcon("resources/sprites/plants/sunflower/sf.sun.png").getImage().getScaledInstance(SUN_WIDTH, SUN_HEIGHT, Image.SCALE_SMOOTH);

    private boolean produced;

    private int holdingTime;

    /**
     * Initializes grid/screen coordinates, health, damage, and attack rate
     * @param gridX grid x-coordinate starting from left
     * @param gridY grid y-coordinate starting from top
     */
    public SunFlower(int gridX, int gridY){
        super(gridX, gridY, gridX * TILE_SIZE + HORIZ_OFFSET, gridY * TILE_SIZE + VERT_OFFSET, FULL_HEALTH, DAMAGE, ATTACK_RATE);
        setCurrentImg(idle1Img);
        this.produced = false;
        this.holdingTime = 0;
    }
    
    /**
     * returns if the sunflower has produced a sun
     * @return if the sunflower produced a sun
     */
    public boolean getProduced(){
        return produced;
    }

    /**
     * changes the condition if the sunflowers produced a sun
     * @param p new production condition
     */
    public void setProduced(boolean p){
        this.produced = p;
    }

    public void update(State state){ 
        MusicPlayer mp = new MusicPlayer();
        mp.setFile(5);


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
                else if(getCurrentImg() == idle2Img){
                    setCurrentImg(idle3Img);
                }
                else if(getCurrentImg() == idle3Img){
                    setCurrentImg(idle4Img);
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
                setCurrentImg(sunProduce2Img);
            }
            
            tickActionAniCounter();
            if(getActionAniCounter() > ACTION_RATE){
                if(getCurrentImg() == sunProduce2Img){
                    setCurrentImg(sunProduce1Img);
                }
                else if(getCurrentImg() == sunProduce1Img){
                    setCurrentImg(sunProduce3Img);
                }
                else{
                    setCurrentImg(sunProduce2Img);
                    zeroAttackCounter();
                    holdingTime++;
                    mp.play();
                    produced = true;
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
        
        if(holdingTime == 0){
            return;
        }
        else if(holdingTime < HOLD_TIME){
            g.drawImage(sunImg, getScreenX() + 30, getScreenY() + 30, null);
            holdingTime++;
        }
        else{
            holdingTime = 0;
        }
    }
}
