import java.awt.BorderLayout;
import java.io.IOException;
import javax.swing.JFrame;

public class Main{
    
    public static void main(String[] args) throws IOException {
        
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("PVZ");

        //sound test
        MusicPlayer mp = new MusicPlayer();
        mp.setFile(0);
        mp.play();
        mp.loop();

        PlantPanel pp = new PlantPanel();
        InfoPanel ip = new InfoPanel();
        GamePanel gp = new GamePanel(pp, ip);

        PlantPanel.addGamePanel(gp);

        window.add(ip, BorderLayout.PAGE_START);
        window.add(gp,BorderLayout.LINE_END);
        window.add(pp, BorderLayout.LINE_START);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
          
        gp.run();
    }
}