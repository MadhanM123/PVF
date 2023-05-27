package sprites.zombies;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;


public class KingKwong extends Zombie{

    public static final int FULL_HEALTH = 2000;
    public static final int DAMAGE = 50;

    private static final int HEIGHT = 110;
    private static final int WIDTH = 60;

    private static final int VERT_OFFSET = 0;
    private static final int HORIZ_OFFSET = 10;

    public static final int START_X = 900;
    public static final int START_Y = 0;

    private static final int WALK_RATE = 5;
    private static final int TILE_THRESHOLD = 60;
    private static final int OFFSET = -13;  

    private static final int ATTACK_RATE = 90;
    private static final int ACTION_RATE = 8;
    private static final int DEATH_RATE = 10;
    
    private static final Image walk1Img = new ImageIcon("resources/sprites/zombies/kingKwong/walk1.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
    private static final Image walk2Img = new ImageIcon("resources/sprites/zombies/kingKwong/walk3.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
    private static final Image walk3Img = new ImageIcon("resources/sprites/zombies/kingKwong/walk2.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);

    private static final Image action1Img = new ImageIcon("resources/sprites/zombies/kingKwong/attack1.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
    private static final Image action2Img = new ImageIcon("resources/sprites/zombies/kingKwong/attack2.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
    private static final Image action3Img = new ImageIcon("resources/sprites/zombies/kingKwong/attack3.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
    private static final Image action4Img = new ImageIcon("resources/sprites/zombies/kingKwong/attack4.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);

    private static final Image death1Img = walk1Img;
    private static final Image death2Img = walk2Img;

    public KingKwong(int gridX, int gridY){
        super(gridX, gridY, gridX * TILE_SIZE + HORIZ_OFFSET, gridY * TILE_SIZE + VERT_OFFSET, FULL_HEALTH, DAMAGE, ATTACK_RATE);
        setCurrentImg(walk1Img);
    }

    private void dropImp(){
        
    }

    public void update(State state){
        if(state == State.IDLE){
            if(comparePrevState(state)){
                zeroWalkCounter();
                setCurrentImg(walk1Img);
            }
            
            tickWalkCounter();
            if(getWalkCounter() > WALK_RATE){
                if(getCurrentImg() == walk1Img){
                    setCurrentImg(walk2Img);
                }
                else if(getCurrentImg() == walk2Img){
                    setCurrentImg(walk3Img);
                }
                else if(getCurrentImg() == walk3Img){
                    setCurrentImg(walk1Img);
                }

                if(!getBattling()){
                    setRealScreenX(getRealScreenX() + OFFSET + getIntersect());
                    setIntersect(0);
                }
                zeroWalkCounter();
            }

            if(getRealScreenX() < getGridX() * TILE_SIZE - TILE_THRESHOLD){
                setGridX(getGridX() - 1);
                movedNextTile(true);
                zeroWalkCounter();
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
                    setCurrentImg(action2Img);
                }
                else if(getCurrentImg() == action2Img){
                    setCurrentImg(action3Img);
                }
                else if(getCurrentImg() == action3Img){
                    setCurrentImg(action4Img);
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
        g.drawImage(getCurrentImg(), getRealScreenX(), getRealScreenY(), null);
    }
    
}