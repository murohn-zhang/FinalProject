import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame {
    private Game game;
    private final int WINDOW_WIDTH = 200;
    private final int WINDOW_HEIGHT = 200;
    private Player user;

    public GameView(Game game) {
        this.game = game;

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Paper.io");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
    }

    public void paint(Graphics g) {

    }

    public void paintStart(Graphics g) {

    }

    public void paintPlay(Graphics g) {

    }

    public void paintEnd(Graphics g) {

    }
}
