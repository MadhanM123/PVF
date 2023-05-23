package sprites.zombies;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class ConeHead extends Zombie{

    public static final int FULL_HEALTH = 0;
    public static final int DAMAGE = 0;
    public static final int HITBOX = 0;

    public static final int START_X = 900;
    public static final int START_Y = 0;

    private static final int WALK_RATE = 4;
    private static final int TILE_THRESHOLD = 60;
    private static final int OFFSET = -10;

    private static final int ACTION_RATE = 10;
    private static final int ACTION_X = 50;
    private static final int ACTION_Y = 0;

    //61, 142, 247, 90

    private static final int HEIGHT = 110;
    private static final int WIDTH = 60;

    private static final Image walk1Img = new ImageIcon("resources/sprites/zombies/conehead/walk1.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
    private static final Image walk2Img = new ImageIcon("resources/sprites/zombies/conehead/walk3.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
    private static final Image walk3Img = new ImageIcon("resources/sprites/zombies/conehead/walk2.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);

    private static final Image action1Img = new ImageIcon("resources/sprites/zombies/conehead/eat1.png").getImage().getScaledInstance(WIDTH + 10, HEIGHT, Image.SCALE_SMOOTH);
    private static final Image action2Img = new ImageIcon("resources/sprites/zombies/conehead/eat2.png").getImage().getScaledInstance(WIDTH + 10, HEIGHT, Image.SCALE_SMOOTH);
    private static final Image action3Img = new ImageIcon("resources/sprites/zombies/conehead/eat3.png").getImage().getScaledInstance(WIDTH + 10, HEIGHT, Image.SCALE_SMOOTH);

    private Image currentImg;

    public ConeHead(int gridX, int gridY, int screenX, int screenY){
        super(gridX, gridY, screenX, screenY, FULL_HEALTH);
        this.currentImg = walk1Img;
    }
    
    public void update(State state){
        if(state == State.IDLE){
            if(comparePrevState(state)){
                zeroWalkCounter();
            }
            
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
                setRealScreenX(getRealScreenX() + OFFSET);
                zeroWalkCounter();
            }

            if(getRealScreenX() < getGridX() * TILE_SIZE - TILE_THRESHOLD){
                System.out.println(getRealScreenX() + ",," + getRealScreenY());
                setGridX(getGridX() - 1);
                movedNextTile(true);
                zeroWalkCounter();
            }
        }
        else if(state == State.ACTION){
            if(comparePrevState(state)){
                zeroActionCounter();
                this.currentImg = action1Img;
            }

            tickActionCounter();
            if(getActionCounter() > ACTION_RATE){
                System.out.println("yo");
                if(currentImg == action1Img){
                    currentImg = action2Img;
                }
                else if(currentImg == action2Img){
                    currentImg = action3Img;
                }
                else if(currentImg == action3Img){
                    currentImg = action1Img;
                }
                zeroActionCounter();
            }
        }
    }

    public void draw(Graphics g){
        g.drawImage(currentImg, getRealScreenX(), getRealScreenY(), null);
    }
}