import java.awt.*;

public class Square {
    private Player owner;
    private GameView window;
    private final int WIDTH = 50;
    private final int HEIGHT = 50;
    private int xLoc;
    private int yLoc;


    public Square(GameView window) {
        this.window = window;
        xLoc = 90;
        yLoc = 90;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public void shiftX(int shift, int low, int max) {
        // If it goes out of bounds to the left
        if (xLoc - (WIDTH / 2) + shift < low && shift < 0) {
            xLoc = 0;
        }

        // If it goes out of bounds to the right
        else if (xLoc + WIDTH + shift > max && shift > 0) {
            xLoc = max - WIDTH;
        }

        // Otherwise, shift normally
        else {
            xLoc += shift;
        }
    }

    public void shiftY(int shift, int low, int max) {
        // If it goes out of bounds down
        if (yLoc - HEIGHT + shift < low && shift > 0) {
            yLoc = low + HEIGHT;
        }

        // If it goes out of bounds up
        else if (yLoc + (HEIGHT / 2) + shift > max && shift < 0) {
            yLoc = max - (HEIGHT / 2);
        }

        // Otherwise, shift normally
        else {
            yLoc += shift;
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(xLoc, yLoc, WIDTH, HEIGHT);
    }


}
