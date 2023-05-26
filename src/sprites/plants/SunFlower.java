package sprites.plants;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class SunFlower extends Plant {
    public static final int FULL_HEALTH = 1000;
    public static final int DAMAGE = 0;

    public static final int COST = 10;

    private static final int HEIGHT = 130;
    private static final int WIDTH = 85;

    private static final int VERT_OFFSET = -15;
    private static final int HORIZ_OFFSET = 0;

    private static final int IDLE_RATE = 5;

    private static final int ACTION_RATE = 3;
    private static final int DEATH_RATE = 10;

    private static final Image idle1Img =  new ImageIcon("resources/sprites/plants/sunflower/sf.idle1.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
    private static final Image idle2Img =  new ImageIcon("resources/sprites/plants/sunflower/sf.idle2.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
    private static final Image idle3Img =  new ImageIcon("resources/sprites/plants/sunflower/sf.idle3.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
    private static final Image idle4Img =  new ImageIcon("resources/sprites/plants/sunflower/sf.idle2.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);

    private static final Image sunProduce1Img =  new ImageIcon("resources/sprites/plants/sunflower/sf.produce1.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
    private static final Image sunProduce2Img =  new ImageIcon("resources/sprites/plants/sunflower/sf.produce2.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
    private static final Image sunProduce3Img = idle1Img;

    private static final Image deathImg = idle3Img;

    private static final Image sunImg =  new ImageIcon("resources/sprites/plants/sunflower/sf.sun.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);

    public SunFlower(int gridX, int gridY){
        super(gridX, gridY, gridX * TILE_SIZE + HORIZ_OFFSET, gridY * TILE_SIZE + VERT_OFFSET, FULL_HEALTH, DAMAGE);
        setCurrentImg(idle1Img);
    }

    public void addSun(){
        //add suns and add a sun image
    }

    public void update(State state){ 
        
        if(state == State.IDLE){
            if(comparePrevState(state)){
                zeroIdleCounter();
                setCurrentImg(idle1Img);
            }

            tickIdleCounter();
            if(getIdleCounter() > IDLE_RATE){
                if(getCurrentImg() == idle1Img){
                    setCurrentImg(idle2Img);
                }
                else if(getCurrentImg() == idle2Img){
                    setCurrentImg(idle3Img);
                }
                else if(getCurrentImg() == idle3Img){
                    setCurrentImg(idle4Img);
                }
                else{
                    setCurrentImg(idle1Img);
                }
                zeroIdleCounter();
            }
        }
        else if(state == State.ACTION){
            if(comparePrevState(state)){
                zeroActionCounter();
                setCurrentImg(sunProduce2Img);
            }
            
            tickActionCounter();
            if(getActionCounter() > ACTION_RATE){
                if(getCurrentImg() == sunProduce2Img){
                    setCurrentImg(sunProduce1Img);
                }
                else if(getCurrentImg() == sunProduce1Img){
                    setCurrentImg(sunProduce3Img);
                }
                else{
                    setCurrentImg(sunProduce2Img);
                }
                zeroActionCounter();
            }
        }
        else if(state == State.DEATH){
            if(comparePrevState(state)){
                zeroDeathCounter();
                setCurrentImg(deathImg);
            }

            tickDeathCounter();
            if(getDeathCounter() > DEATH_RATE){
                if(getCurrentImg() == deathImg){
                    setCurrentImg(null);
                    setDoneDeath(true);
                }
                zeroDeathCounter();
            }
        }
    }

    public void draw(Graphics g){
        g.drawImage(getCurrentImg(), getRealScreenX(), getRealScreenY(), null);
    }

    @Override
    public boolean canDefend()
    {
        return false;
    }    
}
