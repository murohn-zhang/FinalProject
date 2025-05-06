import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class GameView extends JFrame {
    private Game game;
    public static final int WINDOW_WIDTH = 300;
    public static final int WINDOW_HEIGHT = 300;
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
        createBufferStrategy(2);
    }
    public void paint(Graphics g) {
        BufferStrategy bf = this.getBufferStrategy();
        if (bf == null)
            return;
        Graphics g2 = null;
        try {
            g2 = bf.getDrawGraphics();
            myPaint(g2);
        }
        finally {
            g2.dispose();
        }
        bf.show();
        Toolkit.getDefaultToolkit().sync();
    }

    public void myPaint(Graphics g) {
        if (game.getState() == 1) {
            paintStart(g);
        }

        else if (game.getState() == 2) {
            g.setColor(Color.WHITE);
            g.fillRect( 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
            paintPlay(g);
        }

        else if (game.getState() == 3) {
            paintEnd(g);
        }
    }

    public void paintStart(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect( 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

        g.setColor(Color.blue);
        g.setFont(new Font("Serif", Font.PLAIN, 50));
        g.drawString("Welcome to PAPER.IO", 150, 200);

        g.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        g.drawString("Try to beat the computer (BLUE) by getting the most area!", 120, 300);
        g.drawString("Use the arrow keys to move your RED square.", 120, 325);
        g.drawString("Press the SPACE key to play. Good luck!", 200, 700);
    }

    public void paintPlay(Graphics g) {
        g.setColor(Color.WHITE);
        for (int i = 0 ; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j].getOwner() != null) {
                    grid[i][j].draw(g);
                }
            }
        }
    }

    public void paintEnd(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect( 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

        g.setColor(Color.blue);
        g.setFont(new Font("Serif", Font.PLAIN, 50));
        g.drawString("GAME END!", 150, 200);
    }

}
