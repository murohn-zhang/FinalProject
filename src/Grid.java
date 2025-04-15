import java.awt.*;

public class Grid {
    private boolean complete;
    private final int HEIGHT = 20;
    private final int WIDTH = 20;
    private Square[][] grid;
    private GameView window;

    public Grid(GameView window) {
        complete = false;
        this.window = window;
    }

    public void draw(Graphics g) {

    }

    public boolean isComplete () {
        return complete;
    }
}
