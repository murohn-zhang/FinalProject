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

    public Game() {
        grid = new Spot[GameView.WINDOW_HEIGHT / Square.SIDE][GameView.WINDOW_WIDTH / Square.SIDE];
        // Fill in grid
        for (int i = 0 ; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                Spot newOne = new Spot(i,j);
                grid[i][j] = newOne;
            }
        }

        sq = new Square(window, Square.SIDE / 2, GameView.WINDOW_WIDTH / 2, 0, GameView.WINDOW_HEIGHT - Square.SIDE / 2, Color.red, grid);
        comp = new Square(window, GameView.WINDOW_WIDTH / 2 + 1, GameView.WINDOW_WIDTH, 0, GameView.WINDOW_HEIGHT - Square.SIDE / 2, Color.blue, grid);
        window = new GameView(this, sq, comp, grid);
        window.addKeyListener(this);
    }

    public void computerWork() {

    }

    public void playGame() {

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
        }
        window.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void actionPerformed(ActionEvent e) {
        comp.randomMove();
        sq.move();
        window.repaint();
    }

    public static void main(String [] args) {
        Game g = new Game();
        Timer clock = new Timer(SLEEP_TIME, g);
        clock.start();
    }
}
