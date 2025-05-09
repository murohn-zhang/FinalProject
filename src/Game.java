import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Game implements KeyListener, ActionListener {
    private GameView window;
    private Square sq;
    private Square comp;
    private static final int SLEEP_TIME = 110;
    private Spot[][] grid;
    private int state;
    private static Timer clock;
    private int sqTotal;
    private int compTotal;

    public Game() {
        grid = new Spot[GameView.WINDOW_HEIGHT / Square.SIDE][GameView.WINDOW_WIDTH / Square.SIDE];
        // Fill in grid
        for (int i = 0 ; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                Spot newOne = new Spot(i,j);
                grid[i][j] = newOne;
            }
        }

        sq = new Square(window, Square.SIDE / 2, GameView.WINDOW_WIDTH / 2, 0, GameView.WINDOW_HEIGHT - Square.SIDE / 2, Color.red);
        comp = new Square(window, GameView.WINDOW_WIDTH / 2 + 1, GameView.WINDOW_WIDTH, 0, GameView.WINDOW_HEIGHT - Square.SIDE / 2, Color.blue);
        window = new GameView(this, sq, comp, grid);
        window.addKeyListener(this);
        clock = new Timer(SLEEP_TIME, this);
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

    public void countArea() {
        for(int i = 0; i < grid.length; i++) {
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

    public Square findWinner() {
        int numSquares = (GameView.WINDOW_WIDTH / Square.SIDE) * (GameView.WINDOW_HEIGHT / Square.SIDE);
        countArea();
        sqTotal = (int)(sq.getArea() / numSquares * 100);
        compTotal = (int)(sq.getArea() / numSquares * 100);

        if (sqTotal < compTotal) {
            return comp;
        }

        else {
            return sq;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

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

    public void actionPerformed(ActionEvent e) {
        if (state == 2) {
            comp.randomMove();
            sq.move();
            grid[sq.getRow()][sq.getCol()].setOwner(sq);
            grid[comp.getRow()][comp.getCol()].setOwner(comp);

        }
        checkEnd();
        if (state == 3) {
            clock.stop();
        }
        window.repaint();
    }


    public static void main(String [] args) {
        Game g = new Game();
        clock.start();
    }
}
