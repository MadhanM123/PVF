import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JComponent;

import sprites.plants.*;
import sprites.zombies.*;

public class Tile extends JComponent{
    private Queue<Zombie> zombies;
    private Plant plant;

    private int row;
    private int col;

    public static Image TILE_IMAGE;
    public static int TILE_SIZE;

    static{
        try
        {
            TILE_SIZE = 110;
            TILE_IMAGE = ImageIO.read(new File("resources/sprites/tile/grasstile.png")).getScaledInstance(TILE_SIZE, TILE_SIZE, Image.SCALE_DEFAULT);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * initilizes a tile with no sprites and default background
     */
    public Tile(int r, int c){
        plant = null;
        zombies = new LinkedList<Zombie>();
        this.row = r;
        this.col = c;
        this.setPreferredSize(new Dimension(TILE_SIZE, TILE_SIZE));
        this.setBorder(BorderFactory.createLineBorder(Color.CYAN));
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
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(TILE_IMAGE, 0, 0, null);
    }

    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(TILE_SIZE, TILE_SIZE);
    }

    @Override
    public Dimension getMaximumSize()
    {
        return new Dimension(TILE_SIZE, TILE_SIZE);
    }

    @Override
    public Dimension getMinimumSize()
    {
        return new Dimension(TILE_SIZE, TILE_SIZE);
    }
}