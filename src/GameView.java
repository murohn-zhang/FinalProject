import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class GameView extends JFrame {
    // Declare instance variables
    private Game game;
    public static final int WINDOW_WIDTH = 750;
    public static final int WINDOW_HEIGHT = 750;
    public static final int HEADER_HEIGHT = 23;
    private Square sq;
    private Square comp;
    private Spot[][] grid;
    private Image startPage;
    private Image endPage;

    // Constructor
    public GameView(Game game, Square sq, Square comp, Spot[][] grid) {
        // Initialize variables
        this.game = game;
        this.grid = grid;
        this.sq = sq;
        this.comp = comp;
        startPage = new ImageIcon("Resources/startBG.jpg").getImage();
        endPage = new ImageIcon("Resources/endBG.png").getImage();

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Paper.io");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
        createBufferStrategy(2);
    }

    // Paint function for buffer
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

    // Personal paint function for different window pages
    public void myPaint(Graphics g) {
        // Welcome page
        if (game.getState() == 1) {
            paintStart(g);
        }

        // Playing game
        else if (game.getState() == 2) {
            g.setColor(Color.WHITE);
            g.fillRect( 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
            paintPlay(g);
        }

        // End page
        else if (game.getState() == 3) {
            paintEnd(g);
        }
    }

    // Design for start page
    public void paintStart(Graphics g) {
        // Set background
        g.drawImage(startPage, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);

        // Instructions
        g.setColor(Color.white);
        g.setFont(new Font("Serif", Font.PLAIN, 50));
        g.drawString("Welcome to", 250, 250);
        g.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        g.drawString("Try to beat the computer (YELLOW) by getting the most area!", 125, 510);
        g.drawString("The game ends when the entire window is filled (by you and the computer).", 100, 535);
        g.drawString("Use the ARROW KEYS to move your PINK square.", 205, 560);
        g.drawString("Press the SPACE key to play. Good luck!", 225, 585);
    }

    // Design for playing page
    public void paintPlay(Graphics g) {
        g.setColor(Color.WHITE);

        // Draw each square in grid according to its owner, with border around leader and normal for everything else
        for (int i = 0 ; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j].getOwner() != null) {
                    grid[i][j].draw(g);
                    grid[i][j].getOwner().drawSquare(g);
                }
            }
        }
    }

    // Design for end page
    public void paintEnd(Graphics g) {
        g.drawImage(endPage, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);

        g.setColor(Color.black);
        g.setFont(new Font("Serif", Font.PLAIN, 100));
        g.drawString("GAME OVER!", 65, 300);

        // Call findWinner() to get player totals and winner, print results
        String winner = game.findWinner();
        g.setFont(new Font("Serif", Font.PLAIN, 30));
        g.drawString("Player area: " + game.getSqTotal() + "%", 275, 450);
        g.drawString("Computer area: " + game.getCompTotal() + "%", 250, 500);
        g.drawString(winner + " won!", 260, 550);
    }

}
