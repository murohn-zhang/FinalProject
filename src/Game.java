import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Game implements KeyListener, ActionListener {

    // Declare instance variables
    private GameView window;
    private Spot[][] grid;
    // The two players
    private Square sq;
    private Square comp;
    // For the animation of the squares
    private static Timer clock;
    private static final int SLEEP_TIME = 110;
    // Variable to know what window "face" should be displayed
    private int state;
    // Final areas of the players
    private int sqTotal;
    private int compTotal;

    public Game() {
        // Initialize and fill in grid
        grid = new Spot[GameView.WINDOW_HEIGHT / Square.SIDE][GameView.WINDOW_WIDTH / Square.SIDE];
        for (int i = 0 ; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                Spot newOne = new Spot(i,j);
                grid[i][j] = newOne;
            }
        }
        // Create the players
        sq = new Square(Square.SIDE / 2, GameView.WINDOW_WIDTH / 2, 0, GameView.WINDOW_HEIGHT - GameView.HEADER_HEIGHT, Color.magenta);
        comp = new Square(GameView.WINDOW_WIDTH / 2 + 1, GameView.WINDOW_WIDTH, 0, GameView.WINDOW_HEIGHT - GameView.HEADER_HEIGHT, Color.orange);
        // Initialize other variables
        window = new GameView(this, sq, comp, grid);
        window.addKeyListener(this);
        clock = new Timer(SLEEP_TIME, this);
        // Set window to start page
        state = 1;
        window.repaint();
    }

    public int getState() {
        return state;
    }

    public int getSqTotal() {
        return sqTotal;
    }

    public int getCompTotal() {
        return compTotal;
    }

    // Check if the entire grid is filled, set window to end page if true
    public boolean checkEnd() {
        for(int i = 1; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j].getOwner() == null) {
                    return false;
                }
            }
        }
        state = 3;
        return true;
    }

    // When the game is over, count how many squares each player has
    public void countArea() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j].getOwner() == sq) {
                    sq.addArea();
                }
                else {
                    comp.addArea();
                }
            }
        }
    }

    // Find each players percentage of area and return the winner
    public String findWinner() {
        int numSquares = grid.length * grid[0].length;
        countArea();
        sqTotal = Math.round(sq.getArea() * 100) / numSquares;
        compTotal = 100 - sqTotal;

        System.out.println("computer:" + compTotal);
        System.out.println("Player:" + sqTotal);

        if (sqTotal < compTotal) {
            return "The computer";
        }
        else {
            return "Congrats! You";
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    // Actions depending on which key is used:
    // SPACE starts the game and arrows call function to change direction of square
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            // Up
            case KeyEvent.VK_UP:
                sq.setUpVelocity();
                break;

            // Down
            case KeyEvent.VK_DOWN:
                sq.setDownVelocity();
                break;

            // Left
            case KeyEvent.VK_LEFT:
                sq.setLeftVelocity();
                break;

            // Right
            case KeyEvent.VK_RIGHT:
                sq.setRightVelocity();
                break;

            case KeyEvent.VK_SPACE:
                state = 2;
                window.repaint();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    // Updates made for every repaint
    public void actionPerformed(ActionEvent e) {
        // Keep checking if the game is over
        checkEnd();

        // If it's not over, keep moving both players and updating grid ownership accordingly
        if (state == 2) {
            comp.randomMove();
            sq.move();
            grid[sq.getRow()][sq.getCol()].setOwner(sq);
            grid[comp.getRow()][comp.getCol()].setOwner(comp);
        }

        // If it is over, stop moving the squares (to properly count final area)
        else if (state == 3) {
            clock.stop();
        }

        window.repaint();
    }

    // Main
    public static void main(String [] args) {
        Game g = new Game();
        // Start animation
        clock.start();
    }
}
