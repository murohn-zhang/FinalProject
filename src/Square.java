import java.awt.*;

public class Square {
    private Player owner;
    private GameView window;
    public static final int SIDE = 30;
    private int xLoc;
    private int yLoc;


    public Square(GameView window, int xMin, int xMax, int yMin, int yMax) {
        this.window = window;
        xLoc = (int)(Math.random() * (xMax - xMin)) + xMin;
        yLoc = (int)(Math.random() * (yMax - yMin)) + yMin;

    }


    public int getxLoc() {
        return xLoc;
    }

    public int getyLoc() {
        return yLoc;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public void shiftX(int shift, int low, int max) {
        // If it goes out of bounds to the left
        if (xLoc - (SIDE / 2) + shift < low && shift < 0) {
            xLoc = 0;
        }

        // If it goes out of bounds to the right
        else if (xLoc + SIDE + shift > max && shift > 0) {
            xLoc = max - SIDE;
        }

        // Otherwise, shift normally
        else {
            xLoc += shift;
        }
    }

    public void shiftY(int shift, int low, int max) {
        // If it goes out of bounds down
        if (yLoc + SIDE + shift > max && shift > 0) {
            yLoc = max - SIDE;
        }

        // If it goes out of bounds up
        else if (yLoc - (SIDE / 2) + shift < GameView.HEADER_HEIGHT && shift < 0) {
            yLoc = GameView.HEADER_HEIGHT;
        }

        // Otherwise, shift normally
        else {
            yLoc += shift;
        }
    }

    public void draw(Graphics g, Color color) {
        g.setColor(color);
        g.fillRect(xLoc, yLoc, SIDE, SIDE);
    }


}
