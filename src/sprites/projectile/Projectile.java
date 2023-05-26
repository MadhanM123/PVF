package sprites.projectile;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import sprites.Sprite;

public class Projectile extends Sprite {

    public static final int FULL_HEALTH = 1;
    public static final int DAMAGE = 10;

    private static final int HEIGHT = 20;
    private static final int WIDTH = 20;

    public static final int START_X = 900;
    public static final int START_Y = 0;

    private static final int VERT_OFFSET = 40;
    private static final int HORIZ_OFFSET = 60;

    private static final int OFFSET = 10;

    private static final int MOVE_RATE = 1;
    private static final int ACTION_RATE = 5;
    private static final int DEATH_RATE = 10;

    private static final Image idleImg = new ImageIcon("resources/sprites/projectile/projectile.png").getImage().getScaledInstance(WIDTH + 10, HEIGHT, Image.SCALE_SMOOTH);

    private boolean nextTile;

    public Projectile(int gridX, int gridY)
    {
        super(gridX, gridY, gridX * TILE_SIZE + HORIZ_OFFSET, gridY * TILE_SIZE + VERT_OFFSET, FULL_HEALTH, DAMAGE);
        setCurrentImg(idleImg);
    }

    public void movedNextTile(boolean moved){
        nextTile = moved;
    }

    public boolean hasMovedNextTile(){
        return nextTile;
    }

    public void update(State state){
        if(state == State.IDLE){
            tickWalkCounter();
            if(getWalkCounter() > MOVE_RATE){
                setRealScreenX(getRealScreenX() + OFFSET);
                zeroWalkCounter();
            }

            if(getRealScreenX() > getGridX() * TILE_SIZE){
                setGridX(getGridX() + 1);
                System.out.println(getGridX());
                movedNextTile(true);
                zeroWalkCounter();
            }
        }
        else if(state == State.DEATH){
            setCurrentImg(null);
            setDoneDeath(true);
        }
    }

    public void draw(Graphics g){
        g.drawImage(getCurrentImg(), getRealScreenX(), getRealScreenY(), null);
    }
    
}
