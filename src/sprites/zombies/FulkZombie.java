package sprites.zombies;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class FulkZombie extends Zombie{

    public static final int FULL_HEALTH = 1000;
    public static final int DAMAGE = 10;

    private static final int HEIGHT = 110;
    private static final int WIDTH = 60;

    private static final int VERT_OFFSET = 3;
    private static final int HORIZ_OFFSET = 4;

    public static final int START_X = 900;
    public static final int START_Y = 0;

    private static final int WALK_RATE = 4;
    private static final int TILE_THRESHOLD = 60;
    private static final int OFFSET = -10;

    private static final int ACTION_RATE = 10;
    private static final int DEATH_RATE = 10;

    private static final Image walk1Img = new ImageIcon("resources/sprites/zombies/zombie/walk1.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
    private static final Image walk2Img = new ImageIcon("resources/sprites/zombies/zombie/walk3.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
    private static final Image walk3Img = new ImageIcon("resources/sprites/zombies/zombie/walk2.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);

    private static final Image action1Img = new ImageIcon("resources/sprites/zombies/zombie/eat1.png").getImage().getScaledInstance(WIDTH + 10, HEIGHT, Image.SCALE_SMOOTH);
    private static final Image action2Img = new ImageIcon("resources/sprites/zombies/zombie/eat2.png").getImage().getScaledInstance(WIDTH + 10, HEIGHT, Image.SCALE_SMOOTH);
    private static final Image action3Img = new ImageIcon("resources/sprites/zombies/zombie/eat3.png").getImage().getScaledInstance(WIDTH + 10, HEIGHT, Image.SCALE_SMOOTH);

    private static final Image death1Img = new ImageIcon("resources/sprites/zombies/zombie/die1.png").getImage().getScaledInstance(WIDTH + 10, HEIGHT, Image.SCALE_SMOOTH);
    private static final Image death2Img = new ImageIcon("resources/sprites/zombies/zombie/die2.png").getImage().getScaledInstance(WIDTH + 10, HEIGHT, Image.SCALE_SMOOTH);


    public FulkZombie(int gridX, int gridY){
        super(gridX, gridY, gridX * TILE_SIZE + HORIZ_OFFSET, gridY * TILE_SIZE + VERT_OFFSET, FULL_HEALTH, DAMAGE);
        setCurrentImg(walk1Img);
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
                setRealScreenX(getRealScreenX() + OFFSET + getIntersect());
                setIntersect(0);
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
                zeroActionCounter();
                setCurrentImg(action1Img);
            }

            tickActionCounter();
            if(getActionCounter() > ACTION_RATE){
                if(getCurrentImg() == action1Img){
                    setCurrentImg(action2Img);
                }
                else if(getCurrentImg() == action2Img){
                    setCurrentImg(action3Img);
                }
                else if(getCurrentImg() == action3Img){
                    setCurrentImg(action1Img);
                }
                zeroActionCounter();
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
                    setDoneDeath(false);
                }
                zeroDeathCounter();
            }
        }
    }

    public void draw(Graphics g){
        g.drawImage(getCurrentImg(), getRealScreenX(), getRealScreenY(), null);
    }
    
}