import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame {
    private Game game;
    public static final int WINDOW_WIDTH = 900;
    public static final int WINDOW_HEIGHT = 900;
    public static final int HEADER_HEIGHT = 23;
    private Square sq;
    private Square comp;
    private Spot[][] grid;


    public GameView(Game game, Square sq, Square comp, Spot[][] grid) {
        this.game = game;
        this.grid = grid;
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
        for (int i = 0 ; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j].getOwner() != null) {
                    grid[i][j].draw(g);
                }
            }
        }


    }

    public void paintStart(Graphics g) {

    }

    public void paintPlay(Graphics g) {

    }

    public void paintEnd(Graphics g) {

    }
}
