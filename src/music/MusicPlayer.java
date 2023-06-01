package music;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * The MusicPlayer class holds the song clip and loops background music.
 * @author Andrew X.
 * @version 2023-05-31
 */
public class MusicPlayer{

    private Clip clip;
    private File[] musicPaths = new File[9];

    /**
     * {@value #SNEK_TRACK} - Main Track
     */
    public static final int SNEK_TRACK = 0;

    /**
     * {@value #ZOMBIE_BITE} - Zombie attack sound
     */
    public static final int ZOMBIE_BITE = 1;

    /**
     * {@value #ZOMBIE_SMASH} - KingKwong walk sound
     */
    public static final int ZOMBIE_SMASH = 2;

    /**
     * {@value #KWONG_DEATH} - KingKwong death sound
     */
    public static final int KWONG_DEATH = 3;

    /**
     * {@value #SUN_PROD} - Sun Production sound
     */
    public static final int SUN_PROD = 4;

    /**
     * {@value #PEA_SHOOT} - Peashooting sound
     */
    public static final int PEA_SHOOT = 5;

    /**
     * {@value #SPLAT} - Pea collision sound
     */
    public static final int SPLAT = 6;

    /**
     * {@value #SCREAM} - Game end sound
     */
    public static final int SCREAM = 7;

    /**
     * {@value #BRAINS} - Periodic brains sound 
     */
    public static final int BRAINS = 8;
   
    /**
     * Initializes audio files
     */
    public MusicPlayer(){
        musicPaths[0] = new File("resources/sound/zombie.wav");
        musicPaths[1] = new File("resources/sound/bite.wav");
        musicPaths[2] = new File("resources/sound/smash.wav");
        musicPaths[3] = new File("resources/sound/kwongDeath.wav");
        musicPaths[4] = new File("resources/sound/sun.wav");
        musicPaths[5] = new File("resources/sound/shoot.wav");
        musicPaths[6] = new File("resources/sound/splat.wav");
        musicPaths[7] = new File("resources/sound/scream.wav");
        musicPaths[8] = new File("resources/sound/fulkbrains.wav");
    }

    /**
     * Sets up indexed audio file
     * @param i Index to access audio file
     */
    public void setFile(int i){
        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(musicPaths[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);

        } catch(Exception e){
            System.out.println("setFile error");
        }
    }

    /**
     * Restarts Audio I/O line
     */
    public void play(){
        clip.setFramePosition(0);
        clip.start();
    }

    /**
     * Loops clip indefinitely
     */
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    /**
     * Stops Audio I/O line
     */
    public void stop(){
        clip.stop();
    }
}
