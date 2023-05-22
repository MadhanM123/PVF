package sprites.plants;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class SunFlower extends Plant {

    public static final int ACTION_RATE = 30;
    public static final int FULL_HEALTH = 0;
    public static final int DAMAGE = 0;
    public static final int HITBOX = 0;
    public static final int IDLE_RATE = 5;


    public static final int COST = 10;

    private static final int HEIGHT = 140;
    private static final int WIDTH = 105;

    private static final Image idle1Img =  new ImageIcon("resources/sprites/plants/sunflower/sf.idle1.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
    private static final Image idle2Img =  new ImageIcon("resources/sprites/plants/sunflower/sf.idle2.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
    private static final Image idle3Img =  new ImageIcon("resources/sprites/plants/sunflower/sf.idle3.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
    private static final Image idle4Img =  new ImageIcon("resources/sprites/plants/sunflower/sf.idle2.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
    private static final Image sunProduce1Img =  new ImageIcon("resources/sprites/plants/sunflower/sf.produce1.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
    private static final Image sunProduce2Img =  new ImageIcon("resources/sprites/plants/sunflower/sf.produce2.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
    private static final Image sunProduce3Img =  new ImageIcon("resources/sprites/plants/sunflower/sf.produce1.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
    private static final Image sun =  new ImageIcon("resources/sprites/plants/sunflower/sf.sun.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);

    private Image currentImg;

    public SunFlower(int gridX, int gridY, int screenX, int screenY){
        super(gridX, gridY, screenX, screenY, FULL_HEALTH);
        currentImg = idle1Img;
    }

    public void addSun(){
        currentImg = sunProduce1Img;
        if(currentImg == sunProduce1Img){
            currentImg = sunProduce2Img;
        }
        else if(currentImg == sunProduce2Img){
            currentImg = sunProduce3Img;
        }
        else{
            currentImg = sunProduce1Img;
        }
        //add suns and add a sun image
    }

    public void update(){ 
        tickIdleCounter();
        tickActionCounter();

        if(getIdleCounter() > IDLE_RATE){
            if(currentImg == idle1Img){
                currentImg = idle2Img;
            }
            else if(currentImg == idle2Img){
                currentImg = idle3Img;
            }
            else if(currentImg == idle3Img){
                currentImg = idle4Img;
            }
            else{
                currentImg = idle1Img;
            }
            zeroIdleCounter();
        }

        if(getActionCounter() > ACTION_RATE){
            addSun();
            zeroActionCounter();
        }
    }

    public void draw(Graphics g){
        g.drawImage(currentImg, -10, 0, null);
    }
}
