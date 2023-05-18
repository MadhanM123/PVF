import java.awt.BorderLayout;
import java.io.IOException;
import javax.swing.JFrame;

public class Main{
    public static void main(String[] args) throws IOException {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("PVZ");

        GamePanel gp = new GamePanel();
        PlantPanel pp = new PlantPanel(gp);
        gp.add(pp);

        window.add(gp,BorderLayout.LINE_END);
        window.add(pp, BorderLayout.LINE_START);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
          
        gp.run();
    }
}