package main;

import java.awt.BorderLayout;
import javax.swing.JFrame;

import music.MusicPlayer;

/**
 * The Main class holds a JFrame that holds the main panels, a music player, and calls the end screen.
 * @author Madhan M., Nate M.
 * @version 2023-30-05
 */
public class Main{
    /**
     * Creates window and adds panels. Plays the music and displays end screen after death.
     * @param args No parameters
     * @throws InterruptedException If sleep thread is interrupted
     */
    public static void main(String[] args) throws InterruptedException {
        
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("PVZ");

        // sound test
        MusicPlayer mp = new MusicPlayer();
        mp.setFile(MusicPlayer.SNEK_TRACK);
        mp.loop();

        PlantPanel pp = new PlantPanel();
        InfoPanel ip = new InfoPanel();
        GamePanel gp = new GamePanel(pp, ip);

        pp.addGamePanel(gp);

        window.add(ip, BorderLayout.PAGE_START);
        window.add(gp,BorderLayout.LINE_END);
        window.add(pp, BorderLayout.LINE_START);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
          
        gp.run();

        ip.displayDeath();
        mp.stop();
        mp.setFile(MusicPlayer.SCREAM);
        mp.loop();
        while(window.isActive()){
            Thread.sleep(4000);
        }
        System.exit(0);
    }
}