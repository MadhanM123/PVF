import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class MusicPlayer {

    private Clip clip;
    private File[] musicPaths = new File[30];
   
    public MusicPlayer(){
        musicPaths[0] = new File("resources/sound/zombie.wav");
    }

    public void setFile(int i){
        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(musicPaths[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);

        } catch(Exception e){
            System.out.println("setFile error");
        }
    }

    public void play(){
        clip.start();
    }

    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop(){
        clip.stop();
    }
}
