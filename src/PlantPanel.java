import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import sprites.plants.SunFlower;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlantPanel extends JPanel{
    private JButton sunflowerButton;
    private JButton peashooterButton;
    private JButton walnutButton;
    private JButton repeaterButton;

    private final int sunflowerCost = 50;
    private final int peashooterCost = 100;
    private final int walnutCost = 50;
    private final int repeaterCost = 150;

    private JLabel sunflowerCostLabel;
    private JLabel peashooterCostLabel;
    private JLabel walnutCostLabel;
    private JLabel repeaterCostLabel;

    private final int buttonWidth = 100;
    private final int buttonHeight = 100;
    private final int realScreenWidth = buttonWidth * 5;
    private final int realScreenLength = buttonHeight * 3;

    private GamePanel gp;

    private PlantSelector plantSelector;

    public PlantPanel(GamePanel gp) throws IOException{
        this.setPreferredSize(new Dimension(realScreenWidth, realScreenLength));

        this.gp = gp;

        sunflowerButton = new JButton();
        peashooterButton = new JButton();
        walnutButton = new JButton();

        sunflowerButton.setBackground(Color.WHITE);
        peashooterButton.setBackground(Color.WHITE);
        walnutButton.setBackground(Color.WHITE);

        sunflowerButton.setIcon(new ImageIcon(new ImageIcon("resources/sprites/plants/sunflower/sf.idle2.png").getImage().getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_DEFAULT)));
        peashooterButton.setIcon(new ImageIcon(new ImageIcon("resources/sprites/plants/peashooter/ps.idle1.png").getImage().getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_DEFAULT)));
        walnutButton.setIcon(new ImageIcon(new ImageIcon("resources/sprites/plants/walnut/wn.idle.png").getImage().getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_DEFAULT)));

        sunflowerCostLabel = new JLabel("" + sunflowerCost, SwingConstants.CENTER);
        peashooterCostLabel = new JLabel("" + peashooterCost, SwingConstants.CENTER);
        walnutCostLabel = new JLabel("" + walnutCost, SwingConstants.CENTER);

        sunflowerCostLabel.setFont(new Font("Serif", Font.BOLD, 50));
        peashooterCostLabel.setFont(new Font("Serif", Font.BOLD, 50));
        walnutCostLabel.setFont(new Font("Serif", Font.BOLD, 50));

        sunflowerCostLabel.setForeground(Color.GREEN);
        peashooterCostLabel.setForeground(Color.GREEN);
        walnutCostLabel.setForeground(Color.GREEN);

        sunflowerButton.setLayout(new BorderLayout());
        peashooterButton.setLayout(new BorderLayout());
        walnutButton.setLayout(new BorderLayout());

        sunflowerButton.add(sunflowerCostLabel, BorderLayout.PAGE_END);
        peashooterButton.add(peashooterCostLabel, BorderLayout.PAGE_END);
        walnutButton.add(walnutCostLabel, BorderLayout.PAGE_END);

        this.setLayout(new GridLayout(2, 2));

        this.add(sunflowerButton);
        this.add(walnutButton);
        this.add(peashooterButton);

        plantSelector = new PlantSelector();

        sunflowerButton.addActionListener(plantSelector);
        walnutButton.addActionListener(plantSelector);
        peashooterButton.addActionListener(plantSelector);

        this.setVisible(true);
    }

    public void update(){

    }

    private void clearButton(JButton button){
        button.setBackground(Color.WHITE);
        button.setForeground(Color.GREEN);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }

    private class PlantSelector implements ActionListener{
        private JButton prevPlantClicked;
        private int prevCost;

        public PlantSelector(){
            prevPlantClicked = null;
            prevCost = 0;
        }

        public boolean checkTile(){
            return false;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource() == sunflowerButton){
                if(prevPlantClicked != null){
                    clearButton(prevPlantClicked);
                    prevPlantClicked = null;
                    prevCost = 0;
                }
                else{
                    prevPlantClicked = sunflowerButton;
                    if(gp.getCurrentSun() >= sunflowerCost){
                        sunflowerButton.setBackground(Color.BLUE);
                        sunflowerButton.setForeground(Color.BLACK);
                        prevCost = sunflowerCost;
                    }
                    else{
                        sunflowerButton.setBackground(Color.RED);
                        sunflowerButton.setForeground(Color.BLACK);
                    }
                }
            }
            else if(e.getSource() == walnutButton){
                if(prevPlantClicked != null){
                    clearButton(prevPlantClicked);
                    prevPlantClicked = null;
                    prevCost = 0;
                }
                else{
                    prevPlantClicked = walnutButton;
                    if(gp.getCurrentSun() >= walnutCost){
                        walnutButton.setBackground(Color.BLUE);
                        walnutButton.setForeground(Color.BLACK);
                        prevCost = walnutCost;
                    }
                    else{
                        walnutButton.setBackground(Color.RED);
                        walnutButton.setForeground(Color.BLACK);
                    }
                }
            }
            else if(e.getSource() == peashooterButton){
                if(prevPlantClicked != null){
                    clearButton(prevPlantClicked);
                    prevPlantClicked = null;
                    prevCost = 0;
                }
                else{
                    prevPlantClicked = peashooterButton;
                    if(gp.getCurrentSun() >= peashooterCost){
                        peashooterButton.setBackground(Color.BLUE);
                        peashooterButton.setForeground(Color.BLACK);
                        prevCost = peashooterCost;
                    }
                    else{
                        peashooterButton.setBackground(Color.RED);
                        peashooterButton.setForeground(Color.BLACK);
                    }
                }
            }
            else if(e.getSource() == repeaterButton){
                if(prevPlantClicked != null){
                    clearButton(prevPlantClicked);
                    prevPlantClicked = null;
                    prevCost = 0;
                }
                else{
                    prevPlantClicked = repeaterButton;
                    if(gp.getCurrentSun() >= repeaterCost){
                        repeaterButton.setBackground(Color.BLUE);
                        repeaterButton.setForeground(Color.BLACK);
                        prevCost = repeaterCost;
                    }
                    else{
                        repeaterButton.setBackground(Color.RED);
                        repeaterButton.setForeground(Color.BLACK);
                    }
                }
            }
            else if(e.getSource() instanceof Tile){
                if(prevPlantClicked != null){
                    Tile tile = (Tile) e.getSource();
                    if(tile.isEmpty() && prevCost > 0){
                        if(prevPlantClicked == sunflowerButton){
                            tile.addPlant(new SunFlower(tile.getX, ABORT, realScreenWidth, realScreenLength));
                        }
                    }
                }
            }
        }
    }
}