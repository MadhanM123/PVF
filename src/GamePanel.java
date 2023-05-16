import java.awt.Graphics;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{

    private int tileSize;
    private int screenCol;
    private int screenRow;
    private int realScreenWidth;
    private int realScreenLength;
    private int FPS;
    private Tile[][] grid;
    private CollisionManager collManager;
    private JLabel score;
    private int currentSun;

    public GamePanel(){

    }

    public void startGame(){

    }

    @Override
    public void run()
    {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'run'");
    }

    public void update(){

    }

    public void paintComponent(Graphics g){
        
    }

    public Tile[][] getGrid(){
        return grid;

    }

    public void incrementSun(){
        
    }

    
}