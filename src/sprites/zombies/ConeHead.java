package sprites.zombies;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class ConeHead extends Zombie{

    public static final int FULL_HEALTH = 1000;
    public static final int DAMAGE = 500;

    private static final int HEIGHT = 110;
    private static final int WIDTH = 60;

    private static final int VERT_OFFSET = 3;
    private static final int HORIZ_OFFSET = 70;

    public static final int START_X = 900;
    public static final int START_Y = 0;

    private static final int WALK_RATE = 3;
    private static final int TILE_THRESHOLD = 60;
    private static final int OFFSET = -10;

    private static final int ATTACK_RATE = 15;
    private static final int ACTION_RATE = 5;
    private static final int DEATH_RATE = 5;

    private static final Image walk1Img = new ImageIcon("resources/sprites/zombies/conehead/walk1.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
    private static final Image walk2Img = new ImageIcon("resources/sprites/zombies/conehead/walk3.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
    private static final Image walk3Img = new ImageIcon("resources/sprites/zombies/conehead/walk2.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);

    private static final Image action1Img = new ImageIcon("resources/sprites/zombies/conehead/eat1.png").getImage().getScaledInstance(WIDTH + 10, HEIGHT, Image.SCALE_SMOOTH);
    private static final Image action2Img = new ImageIcon("resources/sprites/zombies/conehead/eat2.png").getImage().getScaledInstance(WIDTH + 10, HEIGHT, Image.SCALE_SMOOTH);
    private static final Image action3Img = new ImageIcon("resources/sprites/zombies/conehead/eat3.png").getImage().getScaledInstance(WIDTH + 10, HEIGHT, Image.SCALE_SMOOTH);

    private static final Image death1Img = new ImageIcon("resources/sprites/zombies/conehead/die1.png").getImage().getScaledInstance(WIDTH + 10, HEIGHT, Image.SCALE_SMOOTH);
    private static final Image death2Img = new ImageIcon("resources/sprites/zombies/conehead/die2.png").getImage().getScaledInstance(WIDTH + 10, HEIGHT, Image.SCALE_SMOOTH);

    public static final String NAME = "ConeHead";

    public ConeHead(int gridX, int gridY, int x_offset){
        super(gridX, gridY, gridX * TILE_SIZE + HORIZ_OFFSET + x_offset, gridY * TILE_SIZE + VERT_OFFSET, FULL_HEALTH, DAMAGE, ATTACK_RATE, NAME);
        setCurrentImg(walk1Img);
    }
    
    public void update(State state){
        if(getGridY() == 0) System.out.println(getGridX());
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
                movedNextTile(true);
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
                    setCurrentImg(action2Img);
                }
                else if(getCurrentImg() == action2Img){
                    setCurrentImg(action3Img);
                    setDoneDamage(true);
                }
                else if(getCurrentImg() == action3Img){
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
        g.drawImage(getCurrentImg(), getScreenX(), getScreenY(), null);
    }
}