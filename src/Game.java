import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Game implements KeyListener {
    private GameView window;
    private ArrayList<Player> leaderboard;
    private Player computer;
    private Square sq;
    private Computer comp;
    private final int shiftBy = 20;

    public Game() {
        leaderboard = new ArrayList<Player>();
        sq = new Square(window);
        comp = new Computer(window, sq);
        window = new GameView(this, sq, comp);
        window.addKeyListener(this);
    }

    public void computerWork() {

    }

    public void playGame() {

    }

    public static void main(String [] args) {
        Game g = new Game();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            // Up
            case KeyEvent.VK_UP:
                sq.shiftY(-shiftBy, 0, GameView.WINDOW_HEIGHT);
                break;

            // Down
            case KeyEvent.VK_DOWN:
                sq.shiftY(shiftBy, 0, GameView.WINDOW_HEIGHT);
                break;

            // Left
            case KeyEvent.VK_LEFT:
                sq.shiftX(-shiftBy, 0, GameView.WINDOW_WIDTH);
                break;

            // Right
            case KeyEvent.VK_RIGHT:
                sq.shiftX(shiftBy, 0, GameView.WINDOW_WIDTH);
                break;
        }
        window.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
