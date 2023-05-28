import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
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

/**
 * The PlantPanel class holds the buttons for selecting plants and an inner class ActionListener that handles plant placement.
 * @author Madhan M.
 * @version 2023-05-31
 */
public class PlantPanel extends JPanel{
    private final JButton sunflowerButton;
    private final JButton peashooterButton;
    private final JButton walnutButton;
    private final JButton repeaterButton;

    private final JLabel sunflowerCostLabel;
    private final JLabel peashooterCostLabel;
    private final JLabel walnutCostLabel;
    private final JLabel repeaterCostLabel;

    private static final int buttonWidth = 80;
    private static final int buttonHeight = 120;
    private static final int realScreenWidth = buttonWidth * 5;
    private static final int realScreenLength = buttonHeight * 3;

    private GamePanel gamePanel;
    private PlantSelector plantSelector;

    /**
     * Initalizes all buttons and sets up their icons and costs. Adds action listeners to them and adds them into the panel.
     */
    public PlantPanel(){
        this.setPreferredSize(new Dimension(realScreenWidth, realScreenLength));

        sunflowerButton = new JButton();
        peashooterButton = new JButton();
        walnutButton = new JButton();
        repeaterButton = new JButton();

        sunflowerButton.setBackground(Color.WHITE);
        peashooterButton.setBackground(Color.WHITE);
        walnutButton.setBackground(Color.WHITE);
        repeaterButton.setBackground(Color.WHITE);

        sunflowerButton.setIcon(new ImageIcon(new ImageIcon("resources/sprites/plants/sunflower/sf.idle2.png").getImage().getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_SMOOTH)));
        peashooterButton.setIcon(new ImageIcon(new ImageIcon("resources/sprites/plants/peashooter/ps.idle1.png").getImage().getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_SMOOTH)));
        walnutButton.setIcon(new ImageIcon(new ImageIcon("resources/sprites/plants/walnut/wn.idle.png").getImage().getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_SMOOTH)));
        repeaterButton.setIcon(new ImageIcon(new ImageIcon("resources/sprites/plants/repeater/rp.idle1.png").getImage().getScaledInstance(buttonWidth + 20, buttonHeight + 20, Image.SCALE_SMOOTH)));

        sunflowerCostLabel = new JLabel("" + SunFlower.COST, SwingConstants.CENTER);
        peashooterCostLabel = new JLabel("" + PeaShooter.COST, SwingConstants.CENTER);
        walnutCostLabel = new JLabel("" + Walnut.COST, SwingConstants.CENTER);
        repeaterCostLabel = new JLabel("" + Repeater.COST, SwingConstants.CENTER);

        sunflowerCostLabel.setFont(new Font("Serif", Font.BOLD, 50));
        peashooterCostLabel.setFont(new Font("Serif", Font.BOLD, 50));
        walnutCostLabel.setFont(new Font("Serif", Font.BOLD, 50));
        repeaterCostLabel.setFont(new Font("Serif", Font.BOLD, 50));

        sunflowerCostLabel.setForeground(Color.GREEN);
        peashooterCostLabel.setForeground(Color.GREEN);
        walnutCostLabel.setForeground(Color.GREEN);
        repeaterCostLabel.setForeground(Color.GREEN);

        sunflowerButton.setLayout(new BorderLayout());
        peashooterButton.setLayout(new BorderLayout());
        walnutButton.setLayout(new BorderLayout());
        repeaterButton.setLayout(new BorderLayout());

        sunflowerButton.add(sunflowerCostLabel, BorderLayout.PAGE_END);
        peashooterButton.add(peashooterCostLabel, BorderLayout.PAGE_END);
        walnutButton.add(walnutCostLabel, BorderLayout.PAGE_END);
        repeaterButton.add(repeaterCostLabel, BorderLayout.PAGE_END);

        sunflowerButton.setBorder(BorderFactory.createLineBorder(Color.PINK));
        peashooterButton.setBorder(BorderFactory.createLineBorder(Color.PINK));
        walnutButton.setBorder(BorderFactory.createLineBorder(Color.PINK));
        repeaterButton.setBorder(BorderFactory.createLineBorder(Color.PINK));

        this.setLayout(new GridLayout(2, 2));

        this.add(sunflowerButton);
        this.add(walnutButton);
        this.add(peashooterButton);
        this.add(repeaterButton);

        plantSelector = new PlantSelector();

        sunflowerButton.addActionListener(plantSelector);
        walnutButton.addActionListener(plantSelector);
        peashooterButton.addActionListener(plantSelector);
        repeaterButton.addActionListener(plantSelector);

        this.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        this.setVisible(true);
    }

    /**
     * Clears the button color by making the background white and foreground green
     * @param button Button to clear
     */
    private void clearButton(JButton button){
        button.setBackground(Color.WHITE);
        button.setForeground(Color.GREEN);
    }

    /**
     * Adds the current gamepanel
     * @param gp GamePanel to add
     */
    public void addGamePanel(GamePanel gp){
        this.gamePanel = gp;
    }

    /**
     * Calls the action listener when a tile is clicked which handles plant selection
     * @param tile Tile that was clicked
     */
    public void tileClicked(Tile tile){
        plantSelector.attemptAddPlant(tile);
    }

    private class PlantSelector implements ActionListener{
        private JButton prevPlantClicked;
        private int prevCost;

        private PlantSelector(){
            prevPlantClicked = null;
            prevCost = 0;
        }

        private void attemptAddPlant(Tile tile){
            if(tile.getPlant() == null && tile.getZombies().isEmpty() && prevPlantClicked != null && prevCost > 0){
                if(prevPlantClicked == sunflowerButton){
                    tile.addPlant(new SunFlower(tile.getGridX(), tile.getGridY()));
                }
                else if(prevPlantClicked == walnutButton){
                    tile.addPlant(new Walnut(tile.getGridX(), tile.getGridY()));
                }
                else if(prevPlantClicked == peashooterButton){
                    tile.addPlant(new PeaShooter(tile.getGridX(), tile.getGridY()));
                }
                else if(prevPlantClicked == repeaterButton){
                    tile.addPlant(new Repeater(tile.getGridX(), tile.getGridY()));
                }
                
                gamePanel.decrementSun(prevCost);
                clearButton(prevPlantClicked);
                prevPlantClicked = null;
                prevCost = 0;
            }
            else if(tile.getPlant() == null && prevPlantClicked != null){
                clearButton(prevPlantClicked);
                prevPlantClicked = null;
                prevCost = 0;
            }
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
                    if(gamePanel.getSun() >= SunFlower.COST){  
                        prevCost = SunFlower.COST;
                        sunflowerButton.setBackground(Color.BLUE);
                        sunflowerButton.setForeground(Color.BLACK);
                    }
                    else{
                        prevCost = 0;
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
                    if(gamePanel.getSun() >= Walnut.COST){
                        prevCost = Walnut.COST;
                        walnutButton.setBackground(Color.BLUE);
                        walnutButton.setForeground(Color.BLACK);
                    }
                    else{
                        prevCost = 0;
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
                    if(gamePanel.getSun() >= PeaShooter.COST){
                        prevCost = PeaShooter.COST;
                        peashooterButton.setBackground(Color.BLUE);
                        peashooterButton.setForeground(Color.BLACK);
                    }
                    else{
                        prevCost = 0;
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
                    if(gamePanel.getSun() >= Repeater.COST){
                        prevCost = Repeater.COST;
                        repeaterButton.setBackground(Color.BLUE);
                        repeaterButton.setForeground(Color.BLACK);
                    }
                    else{
                        prevCost = 0;
                        repeaterButton.setBackground(Color.RED);
                        repeaterButton.setForeground(Color.BLACK);
                    }
                }
            }
        }
    }
}