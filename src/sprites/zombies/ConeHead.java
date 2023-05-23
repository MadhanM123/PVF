package sprites.zombies;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class ConeHead extends Zombie{

    public static final int ACTION_RATE = 0;
    public static final int FULL_HEALTH = 0;
    public static final int DAMAGE = 0;
    public static final int HITBOX = 0;
    public static final int WALK_RATE = 5;
    public static final int TILE_RATE = 25;
    public static final int OFFSET = -25;

    private static final int HEIGHT = 280;
    private static final int WIDTH = 350;

    private static final Image walk1Img = new ImageIcon("resources/sprites/zombies/conehead/walk1.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
    private static final Image walk2Img = new ImageIcon("resources/sprites/zombies/conehead/walk3.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
    private static final Image walk3Img = new ImageIcon("resources/sprites/zombies/conehead/walk2.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);

    private Image currentImg;

    public static final int IDLE_STATE = 0;
    public static final int ATTACK_STATE = 0;
    public static final int DEATH_STATE = 0;

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

            tickTileCounter();
            if(getTileCounter() > TILE_RATE){
                setGridX(getGridX() - 1);
                movedNextTile(true);
                setRealScreenX(-70);
                zeroWalkCounter();
                zeroTileCounter();
            }
        }
    }

    public void draw(Graphics g){
        g.drawImage(currentImg, getRealScreenX(), getRealScreenY(), null);
    }
}