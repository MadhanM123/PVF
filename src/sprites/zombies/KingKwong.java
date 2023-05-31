package sprites.zombies;
import music.*;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * The KingKwong zomibe class
 * @author Madhan M., Andrew X., Nate M.
 * @version 2023-05-28
 */
public class KingKwong extends Zombie{

    private static final int FULL_HEALTH = 400;
    private static final int DAMAGE = 200;

    private static final int HEIGHT = 180;
    private static final int WIDTH = 80;

    private static final int VERT_OFFSET = -70;
    private static final int HORIZ_OFFSET = 10;

    private static final int WALK_RATE = 10;
    private static final int TILE_THRESHOLD = 60;
    private static final int OFFSET = -13;  

    private static final int ATTACK_RATE = 90;
    private static final int ACTION_RATE = 4;
    private static final int DEATH_RATE = 5;
    
    private static final Image walk1Img = new ImageIcon("resources/sprites/zombies/kingKwong/walk1.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
    private static final Image walk2Img = new ImageIcon("resources/sprites/zombies/kingKwong/walk3.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
    private static final Image walk3Img = new ImageIcon("resources/sprites/zombies/kingKwong/walk2.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);

    private static final Image action1Img = new ImageIcon("resources/sprites/zombies/kingKwong/attack1.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
    private static final Image action2Img = new ImageIcon("resources/sprites/zombies/kingKwong/attack2.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
    private static final Image action3Img = new ImageIcon("resources/sprites/zombies/kingKwong/attack3.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
    private static final Image action4Img = new ImageIcon("resources/sprites/zombies/kingKwong/attack4.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);

    private static final Image death1Img = walk1Img;
    private static final Image death2Img = walk2Img;
    
     /**
     * {@value #NAME} Name of KingKwong
     */
    public static final String NAME = "KingKwong";

    private boolean shouldSpawn;

    /**
     * Initializes grid/screen coordinates, health, damage, and attack rate
     * @param gridX grid x-coordinate starting from left
     * @param gridY grid y-coordinate starting from top
     * @param x_offset amount to offset initial screen x-coordinate by
     */
    public KingKwong(int gridX, int gridY, int x_offset){
        super(gridX, gridY, gridX * TILE_SIZE + HORIZ_OFFSET, gridY * TILE_SIZE + VERT_OFFSET, FULL_HEALTH, DAMAGE, ATTACK_RATE, NAME);
        setCurrentImg(walk1Img);
        this.shouldSpawn = true;
    }

    /**
     * returns if the KingKwong should spawn
     * @return if the KingKwong should spawn
     */
    public boolean getShouldSpawn(){
        return shouldSpawn;
    }

    /**
     * sets if the KingKwong should spawn
     * @param shouldSpawn spawn condition
     */
    public void setShouldSpawn(boolean shouldSpawn){
        this.shouldSpawn = shouldSpawn;
    }

    public void update(State state){
        MusicPlayer mp = new MusicPlayer();

        if(state == State.REST){
            if(comparePrevState(state)){
                setCurrentImg(walk1Img);
            }
        }
        else if(state == State.IDLE){
            if(comparePrevState(state)){
                zeroIdleCounter();
                setCurrentImg(walk1Img);
            }
            
            tickIdleCounter();
            if(getIdleCounter() > WALK_RATE){
                if(getCurrentImg() == walk1Img){
                    mp.setFile(3);
                    mp.play();
                    setCurrentImg(walk2Img);
                }
                else if(getCurrentImg() == walk2Img){
                    setCurrentImg(walk3Img);
                }
                else if(getCurrentImg() == walk3Img){
                    setCurrentImg(walk1Img);
                }

                setScreenX(getScreenX() + OFFSET + getIntersect());
                setIntersect(0);
                zeroIdleCounter();
            }

            if(getScreenX() < getGridX() * TILE_SIZE - TILE_THRESHOLD){
                setGridX(getGridX() - 1);
                setMovedNextTile(true);
                zeroIdleCounter();
            }
        }
        else if(state == State.ACTION){
            if(comparePrevState(state)){
                zeroActionAniCounter();
                setCurrentImg(action1Img);
            }
            
            tickActionAniCounter();
            if(getActionAniCounter() > ACTION_RATE){
                if(getCurrentImg() == action1Img){
                    mp.setFile(3);
                    mp.play();
                    setCurrentImg(action2Img);
                }
                else if(getCurrentImg() == action2Img){
                    setCurrentImg(action3Img);
                }
                else if(getCurrentImg() == action3Img){
                    setCurrentImg(action4Img);
                    setDoneDamage(true);
                }
                else if(getCurrentImg() == action4Img){
                    setCurrentImg(action1Img);
                    zeroAttackCounter();
                }
                zeroActionAniCounter();
            }
        }
        else if(state == State.DEATH){
            if(comparePrevState(state)){
                zeroDeathCounter();
                setCurrentImg(death1Img);
                mp.setFile(4);
                mp.play();
            }

            tickDeathCounter();
            if(getDeathCounter() > DEATH_RATE){
                if(getCurrentImg() == death1Img){
                    setCurrentImg(death2Img);
                }
                else if(getCurrentImg() == death2Img){
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