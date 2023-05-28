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
    private File[] musicPaths = new File[30];
   
    /**
     * Initializes audio files
     */
    public MusicPlayer(){
        musicPaths[0] = new File("resources/sound/zombie.wav");
        musicPaths[1] = new File("resources/sound/pvz.wav");
        musicPaths[2] = new File("resources/sound/bite.wav");
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
     * Starts Audio I/O line
     */
    public void play(){
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
