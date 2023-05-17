import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class PlantSelector implements ActionListener{

    private JButton prevClicked;
    private JButton sunflowerButton;
    private JButton peashooterButton;
    private JButton walnutButton;
    private JButton repeaterButton;

    public PlantSelector(JButton sunflowerButton, JButton peashooterButton, JButton walnutButton, JButton repeaterButton){
        prevClicked = null;
        this.sunflowerButton = sunflowerButton;
        this.peashooterButton = peashooterButton;
        this.walnutButton = walnutButton;
        this.repeaterButton = repeaterButton;
    }

    public boolean checkTile(){
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == sunflowerButton){
            if(prevClicked != null){
                
            }
        }
        else if(e.getSource() == walnutButton){

        }
        else if(e.getSource() == peashooterButton){

        }
        else if(e.getSource() == repeaterButton){

        }
        else if(e.getSource() instanceof Tile){

        }
    }
}