import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import sprites.plants.PeaShooter;
import sprites.plants.Repeater;
import sprites.plants.SunFlower;
import sprites.plants.Walnut;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlantPanel extends JPanel{
    private JButton sunflowerButton;
    private JButton peashooterButton;
    private JButton walnutButton;
    private JButton repeaterButton;

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

    public PlantPanel() throws IOException{
        this.setPreferredSize(new Dimension(realScreenWidth, realScreenLength));

        sunflowerButton = new JButton();
        peashooterButton = new JButton();
        walnutButton = new JButton();

        sunflowerButton.setBackground(Color.WHITE);
        peashooterButton.setBackground(Color.WHITE);
        walnutButton.setBackground(Color.WHITE);

        sunflowerButton.setIcon(new ImageIcon(new ImageIcon("resources/sprites/plants/sunflower/sf.idle2.png").getImage().getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_DEFAULT)));
        peashooterButton.setIcon(new ImageIcon(new ImageIcon("resources/sprites/plants/peashooter/ps.idle1.png").getImage().getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_DEFAULT)));
        walnutButton.setIcon(new ImageIcon(new ImageIcon("resources/sprites/plants/walnut/wn.idle.png").getImage().getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_DEFAULT)));

        sunflowerCostLabel = new JLabel("" + SunFlower.COST, SwingConstants.CENTER);
        peashooterCostLabel = new JLabel("" + PeaShooter.COST, SwingConstants.CENTER);
        walnutCostLabel = new JLabel("" + Walnut.COST, SwingConstants.CENTER);

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

        sunflowerButton.setBorder(BorderFactory.createLineBorder(Color.PINK));
        peashooterButton.setBorder(BorderFactory.createLineBorder(Color.PINK));
        walnutButton.setBorder(BorderFactory.createLineBorder(Color.PINK));

        this.setLayout(new GridLayout(2, 2));

        this.add(sunflowerButton);
        this.add(walnutButton);
        this.add(peashooterButton);

        plantSelector = new PlantSelector();

        sunflowerButton.addActionListener(plantSelector);
        walnutButton.addActionListener(plantSelector);
        peashooterButton.addActionListener(plantSelector);

        this.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        this.setVisible(true);
    }

    public void update(){

    }

    private void clearButton(JButton button){
        button.setBackground(Color.WHITE);
        button.setForeground(Color.GREEN);
    }

    public void addGamePanel(GamePanel gp){
        this.gp = gp;
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
                    if(gp.getSun() >= SunFlower.COST){
                        sunflowerButton.setBackground(Color.BLUE);
                        sunflowerButton.setForeground(Color.BLACK);
                        prevCost = SunFlower.COST;
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
                    if(gp.getSun() >= Walnut.COST){
                        walnutButton.setBackground(Color.BLUE);
                        walnutButton.setForeground(Color.BLACK);
                        prevCost = Walnut.COST;
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
                    if(gp.getSun() >= PeaShooter.COST){
                        peashooterButton.setBackground(Color.BLUE);
                        peashooterButton.setForeground(Color.BLACK);
                        prevCost = PeaShooter.COST;
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
                    if(gp.getSun() >= Repeater.COST){
                        repeaterButton.setBackground(Color.BLUE);
                        repeaterButton.setForeground(Color.BLACK);
                        prevCost = Repeater.COST;
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
                            tile.addPlant(new SunFlower(tile.getGridX(), tile.getGridY(), tile.getX(), tile.getY()));
                        }
                        else if(prevPlantClicked == walnutButton){
                            tile.addPlant(new Walnut(tile.getGridX(), tile.getGridY(), tile.getX(), tile.getY()));
                        }
                        else if(prevPlantClicked == peashooterButton){
                            tile.addPlant(new PeaShooter(tile.getGridX(), tile.getGridY(), tile.getX(), tile.getY()));
                        }
                        else if(prevPlantClicked == repeaterButton){
                            tile.addPlant(new Repeater(tile.getGridX(), tile.getGridY(), tile.getX(), tile.getY()));
                        }
                    }
                    else if(!tile.isEmpty()){
                        tile.setBackground(Color.RED);
                    }
                }
            }
        }
    }
}