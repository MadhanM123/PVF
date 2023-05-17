package sprites.zombies;
import sprites.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import sprites.Sprite;

public abstract class Zombie extends Sprite{
    private BufferedImage action1;
    private BufferedImage action2;
    private BufferedImage tookDamage;
    private BufferedImage death;

    public Zombie(int gridX, int gridY, int screenX, int screenY){
        super(gridX, gridY, screenX, screenY);
    }

    public abstract void update();

    public abstract void draw(Graphics g);
}