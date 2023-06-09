package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Represents the panel that holds current health, sun, and wave.
 * @author Madhan M.
 * @version 2023-05-31
 */
public class InfoPanel extends JPanel {
    private final int boardHeight = 90;
    private final int boardWidth = 350;

    private StringBuffer healthStr;
    private JLabel healthBoard;

    private StringBuffer waveStr;
    private JLabel waveBoard;

    private StringBuffer sunStr;
    private JLabel sunBoard;

    private StringBuffer deathStr;
    private JLabel deathBoard;

    private FlowLayout flowLayout;

    /**
     * Initializes health, sun, and wave data onto panel
     */
    public InfoPanel(){
        this.healthBoard = new JLabel();
        this.healthStr = new StringBuffer("HEALTH: ");
        setupHealthBoard();

        this.waveBoard = new JLabel();
        this.waveStr = new StringBuffer("WAVE: ");
        setupWaveBoard();

        this.sunBoard = new JLabel();
        this.sunStr = new StringBuffer("SUN: ");
        setupSunBoard();

        this.deathStr = new StringBuffer("CS Teachers Ate Your Brain on Wave ");
        this.deathBoard = new JLabel(deathStr.toString());
        setUpDeathBoard();

        flowLayout = new FlowLayout();
        this.setLayout(flowLayout);
        
        this.add(healthBoard);
        this.add(waveBoard);
        this.add(sunBoard);

        this.setBackground(Color.BLACK);
    }

    private void setupHealthBoard(){
        healthBoard.setIcon(new ImageIcon(new ImageIcon("resources/sprites/scoreboard.png").getImage().getScaledInstance(boardWidth, boardHeight, Image.SCALE_DEFAULT)));
        healthBoard.setVerticalAlignment(JLabel.CENTER);
        healthBoard.setHorizontalAlignment(JLabel.CENTER);
        healthBoard.setForeground(Color.RED);

        healthBoard.setText(healthStr.append(4).toString());
        healthBoard.setFont(new Font("DialogInput", Font.BOLD, 30));
        healthBoard.setVerticalTextPosition(JLabel.CENTER);
        healthBoard.setHorizontalTextPosition(JLabel.CENTER);
    }

    private void setupWaveBoard(){
        waveBoard.setPreferredSize(new Dimension(boardWidth, boardHeight));
        waveBoard.setIcon(new ImageIcon(new ImageIcon("resources/sprites/scoreboard.png").getImage().getScaledInstance(boardWidth, boardHeight, Image.SCALE_DEFAULT)));
        waveBoard.setVerticalAlignment(JLabel.CENTER);
        waveBoard.setHorizontalAlignment(JLabel.CENTER);
        waveBoard.setForeground(Color.GREEN);

        waveBoard.setText(waveStr.append(0).toString());
        waveBoard.setFont(new Font("DialogInput", Font.BOLD, 30));
        waveBoard.setVerticalTextPosition(JLabel.CENTER);
        waveBoard.setHorizontalTextPosition(JLabel.CENTER);
    }

    private void setupSunBoard(){
        sunBoard.setIcon(new ImageIcon(new ImageIcon("resources/sprites/scoreboard.png").getImage().getScaledInstance(boardWidth, boardHeight, Image.SCALE_DEFAULT)));
        sunBoard.setVerticalAlignment(JLabel.CENTER);
        sunBoard.setHorizontalAlignment(JLabel.CENTER);
        sunBoard.setForeground(Color.YELLOW);

        sunBoard.setText(sunStr.append(0).toString());
        sunBoard.setFont(new Font("DialogInput", Font.BOLD, 30));
        sunBoard.setVerticalTextPosition(JLabel.CENTER);
        sunBoard.setHorizontalTextPosition(JLabel.CENTER);
    }

    private void setUpDeathBoard(){
        deathBoard.setIcon(new ImageIcon(new ImageIcon("resources/sprites/scoreboard.png").getImage().getScaledInstance(boardWidth, boardHeight, Image.SCALE_DEFAULT)));
        deathBoard.setVerticalAlignment(JLabel.CENTER);
        deathBoard.setHorizontalAlignment(JLabel.CENTER);
        deathBoard.setForeground(Color.PINK);
        deathBoard.setFont(new Font("DialogInput", Font.BOLD, 60));
        deathBoard.setVerticalTextPosition(JLabel.CENTER);
        deathBoard.setHorizontalTextPosition(JLabel.CENTER);
    }

    /**
     * Sets the current amount of sun
     * @param sun New amount of sun
     */
    public void setSun(int sun){
        sunBoard.setText(sunStr.replace(5, sunStr.length(), "" + sun).toString());
    }

    /**
     * Sets the current wave
     * @param wave New wave
     */
    public void setWave(int wave){
        waveBoard.setText(waveStr.replace(6, waveStr.length(), "" + wave).toString());
    }

    /**
     * Sets the current health
     * @param health New health
     */
    public void setHealth(int health){
        healthBoard.setText(healthStr.replace(8, healthStr.length(), "" + health).toString());
    }

    /**
     * Clears data and displays death message
     */
    public void displayDeath(){
        deathBoard.setText(deathStr.append(waveStr.substring(6)).toString());
        removeAll();
        this.add(deathBoard);
        validate();
        repaint();
    }
}
