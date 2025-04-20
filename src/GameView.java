import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame {
    private Game game;
    public static int WINDOW_WIDTH = 800;
    public static int WINDOW_HEIGHT = 800;
    public static int HEADER_HEIGHT = 23;
    private Player user;
    private Square sq;
    private Computer comp;

    public GameView(Game game, Square sq, Computer comp) {
        this.game = game;
        this.sq = sq;
        this.comp = comp;

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Paper.io");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
    }

    public void paint(Graphics g) {
//        g.setColor(Color.white);
//        g.fillRect(0,0, WINDOW_WIDTH,WINDOW_HEIGHT);
        sq.draw(g);
        comp.draw(g);


    }

    public void paintStart(Graphics g) {

    }

    public void paintPlay(Graphics g) {

    }

    public void paintEnd(Graphics g) {

    }
}
