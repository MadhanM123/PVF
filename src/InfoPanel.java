import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InfoPanel extends JPanel {
    private final int scoreboardHeight = 50;
    private final int scoreboardWidth = 100;
    private int score;
    private StringBuffer scoreStr;
    private JLabel scoreBoard;

    private final int waveboardHeight = 50;
    private final int waveboardWidth = 100;
    private int wave;
    private StringBuffer waveStr;
    private JLabel waveBoard;

    private final int sunboardHeight = 50;
    private final int sunboardWidth = 100;
    private int sun;
    private StringBuffer sunStr;
    private JLabel sunBoard;

    private FlowLayout flowLayout;

    public InfoPanel(){
        this.score = 0;
        this.scoreBoard = new JLabel();
        this.scoreStr = new StringBuffer("SCORE:");
        setupScoreBoard();

        this.wave = 0;
        this.waveBoard = new JLabel();
        this.waveStr = new StringBuffer("WAVE:");
        setupWaveBoard();

        this.sun = 0;
        this.sunBoard = new JLabel();
        this.sunStr = new StringBuffer("SUN:");
        setupSunBoard();

        flowLayout = new FlowLayout();
        this.setLayout(flowLayout);
        
        this.add(scoreBoard);
        this.add(waveBoard);
        this.add(sunBoard);

        this.setBorder(BorderFactory.createLineBorder(Color.MAGENTA));
    }

    private void setupScoreBoard(){
        scoreBoard.setIcon(new ImageIcon(new ImageIcon("resources/sprites/scoreboard.png").getImage().getScaledInstance(scoreboardWidth, scoreboardHeight, Image.SCALE_DEFAULT)));
        scoreBoard.setVerticalAlignment(JLabel.CENTER);
        scoreBoard.setHorizontalAlignment(JLabel.CENTER);
        scoreBoard.setForeground(Color.RED);

        scoreBoard.setText(scoreStr.append(score).toString());
        scoreBoard.setVerticalTextPosition(JLabel.CENTER);
        scoreBoard.setHorizontalTextPosition(JLabel.CENTER);

        scoreBoard.setBorder(BorderFactory.createLineBorder(Color.GRAY));
    }

    private void setupWaveBoard(){
        waveBoard.setIcon(new ImageIcon(new ImageIcon("resources/sprites/scoreboard.png").getImage().getScaledInstance(waveboardWidth, waveboardHeight, Image.SCALE_DEFAULT)));
        waveBoard.setVerticalAlignment(JLabel.CENTER);
        waveBoard.setHorizontalAlignment(JLabel.CENTER);
        waveBoard.setForeground(Color.GREEN);

        waveBoard.setText(waveStr.append(wave).toString());
        waveBoard.setVerticalTextPosition(JLabel.CENTER);
        waveBoard.setHorizontalTextPosition(JLabel.CENTER);

        waveBoard.setBorder(BorderFactory.createLineBorder(Color.GRAY));
    }

    private void setupSunBoard(){
        sunBoard.setIcon(new ImageIcon(new ImageIcon("resources/sprites/scoreboard.png").getImage().getScaledInstance(sunboardWidth, sunboardHeight, Image.SCALE_DEFAULT)));
        sunBoard.setVerticalAlignment(JLabel.CENTER);
        sunBoard.setHorizontalAlignment(JLabel.CENTER);
        sunBoard.setForeground(Color.YELLOW);

        sunBoard.setText(sunStr.append(sun).toString());
        sunBoard.setVerticalTextPosition(JLabel.CENTER);
        sunBoard.setHorizontalTextPosition(JLabel.CENTER);

        sunBoard.setBorder(BorderFactory.createLineBorder(Color.GRAY));
    }

    public void increaseSun(int amt){
        sun += amt;
        sunBoard.setText(sunStr.append(sun).toString());
    }

    public void increaseWave(){
        wave++;
        waveBoard.setText(waveStr.replace(5, waveStr.length(), "" + wave).toString());
    }

    public void increaseScore(int amt){
        score += amt;
        scoreBoard.setText(scoreStr.append(score).toString());
    }

}
