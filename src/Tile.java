import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Queue;
import javax.swing.JComponent;

public class Tile extends JComponent{
    private Queue<Zombie> zombies;
    private Plant plant;

    private BufferedImage img;

    public Tile(BufferedImage img){

    }

    public boolean isEmpty(){

    }

    public Zombie getZombie(){

    }

    public Plant getPlant(){

    }

    public void addZombie(){

    }

    public void addPlant(){

    }

    public void removeZombie(){

    }

    @Override
    public void paintComponent(Graphics g)
    {
        // TODO Auto-generated method stub
        super.paintComponent(g);
    }

    @Override
    public Dimension getPreferredSize()
    {
        // TODO Auto-generated method stub
        return super.getPreferredSize();
    }

    @Override
    public Dimension getMaximumSize()
    {
        // TODO Auto-generated method stub
        return super.getMaximumSize();
    }

    @Override
    public Dimension getMinimumSize()
    {
        // TODO Auto-generated method stub
        return super.getMinimumSize();
    }
}