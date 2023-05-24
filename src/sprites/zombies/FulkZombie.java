package sprites.zombies;

import java.awt.Graphics;

public class FulkZombie extends Zombie{

    public static final int FULL_HEALTH = 0;
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



    public FulkZombie(int gridX, int gridY){
        super(gridX, gridY, gridX * TILE_SIZE + HORIZ_OFFSET, gridY * TILE_SIZE + VERT_OFFSET, FULL_HEALTH, DAMAGE);
    }

    public void update(State state){

    }

    public void draw(Graphics g){
        
    }
    
}