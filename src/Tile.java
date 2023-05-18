import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.JComponent;

import sprites.plants.*;
import sprites.zombies.*;

public class Tile extends JComponent{
    private Queue<Zombie> zombies;
    private Plant plant;

    private int row;
    private int col;

    private BufferedImage img;

    /**
     * initilizes a tile with no sprites and default background
     */
    public Tile(int r, int c){
        plant = null;
        zombies = new LinkedList<Zombie>();
        this.row = r;
        this.col = c;
        img = //path of tile image
    }

    /**
     * checks if the tile has anything on it
     * @return true if tile is empty and false if there is anything on the tile
     */
    public boolean isEmpty(){
        return (zombies.isEmpty() && plant == null);
    }

    /**
     * gets the first zombie in the tile
     * @return the first zombie in the tile
     */
    public Zombie getZombie(){
        return zombies.peek();
    }

    /**
     * gets the plant on the tile
     * @return the plant on the tile
     */
    public Plant getPlant(){
        return plant;
    }

    /**
     * adds a new zombie into the tile
     * @param z the zombie to be added into the tile
     */
    public void addZombie(Zombie z){
        zombies.add(z);
    }

    /**
     * adds a new plant onto the tile
     * @param p the plants to be added into the tile
     */
    public void addPlant(Plant p){
        plant = p;
    }

    /**
     * removes the first zombie in the tile
     */
    public void removeZombie(){
        zombies.remove();
    }

    public int getGridY(){
        return this.row;
    }

    public int getGridX(){
        return this.col;
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