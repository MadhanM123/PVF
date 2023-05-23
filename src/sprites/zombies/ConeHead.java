package sprites.zombies;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class ConeHead extends Zombie{

    public static final int ACTION_RATE = 0;
    public static final int FULL_HEALTH = 0;
    public static final int DAMAGE = 0;
    public static final int HITBOX = 0;

    public static final int START_X = 80;
    public static final int START_Y = 0;

    public static final int WALK_RATE = 4;
    public static final int TILE_RATE = 30;
    public static final int TILE_THRESHOLD = -40;
    public static final int OFFSET = -10;

    //61, 142, 247, 90

    private static final int HEIGHT = 110;
    private static final int WIDTH = 60;

    private static final Image walk1Img = new ImageIcon("resources/sprites/zombies/conehead/walk1.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
    private static final Image walk2Img = new ImageIcon("resources/sprites/zombies/conehead/walk3.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
    private static final Image walk3Img = new ImageIcon("resources/sprites/zombies/conehead/walk2.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);

    private Image currentImg;

    public static final int IDLE_STATE = 0;
    public static final int ATTACK_STATE = 1;
    public static final int DEATH_STATE = 2;

    public ConeHead(int gridX, int gridY, int screenX, int screenY){
        super(gridX, gridY, screenX, screenY, FULL_HEALTH);
        this.currentImg = walk1Img;
    }
    
    public void update(State state){
        if(state == State.IDLE){
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

            if(getRealScreenX() < TILE_THRESHOLD){
                setGridX(getGridX() - 1);
                movedNextTile(true);
                setRealScreenX(START_X);
                zeroWalkCounter();
            }
        }
        else if(state == State.ACTION){
            tickActionCounter();
        }
    }

    public void draw(Graphics g){
        g.drawImage(currentImg, getRealScreenX(), getRealScreenY(), null);
    }
}