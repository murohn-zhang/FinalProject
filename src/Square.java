import java.awt.*;

public class Square {
    private Player owner;
    private GameView window;
    private final int WIDTH = 2;
    private final int HEIGHT = 2;

    public Square(GameView window) {
        this.window = window;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public void draw(Graphics g) {
    }
}
