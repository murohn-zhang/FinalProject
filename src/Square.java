import java.awt.*;

public class Square {
    private GameView window;
    public static final int SIDE = 30;
    private int xLoc;
    private int yLoc;
    private int row;
    private int col;
    private int dx;
    private int dy;
    private Color color;
    private Spot[][] grid;
    private int past;


    public Square(GameView window, int xMin, int xMax, int yMin, int yMax, Color color, Spot[][] grid) {
        this.window = window;
        xLoc = (int)(Math.random() * (xMax - xMin)) + xMin;
        yLoc = (int)(Math.random() * (yMax - yMin)) + yMin;
        this.grid = grid;
        row = (int)Math.round(xLoc / SIDE);
        col = (int)Math.round(yLoc / SIDE);
//        grid[(int)Math.round(xLoc / SIDE)][(int)Math.round(yLoc / SIDE)].setOwner(this);
        this.color = color;
        dx = SIDE;
        dy = 0;
        past = 3;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Color getColor(){
        return color;
    }

    public void setUpVelocity() {
        dx = 0;
        dy = -SIDE;
    }

    public void setDownVelocity() {
        dx = 0;
        dy = SIDE;
    }

    public void setRightVelocity() {
        dx = SIDE;
        dy = 0;
    }

    public void setLeftVelocity() {
        dx = -SIDE;
        dy = 0;
    }

    public void move() {
        shiftX(dx, 0, GameView.WINDOW_WIDTH);
        shiftY(dy, 0, GameView.WINDOW_HEIGHT);
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
        row = (int)Math.round(xLoc / SIDE);
        col = (int)Math.round(yLoc / SIDE);
    }

    public void shiftY(int shift, int low, int max) {
        // If it goes out of bounds down
        if (yLoc + SIDE + shift > max && shift > 0) {
            yLoc = max - SIDE - shift;
        }

        // If it goes out of bounds up
        else if (yLoc - (SIDE / 2) + shift < GameView.HEADER_HEIGHT && shift < 0) {
            yLoc = GameView.HEADER_HEIGHT + SIDE / 2;
        }

        // Otherwise, shift normally
        else {
            yLoc += shift;
        }
        row = (int)Math.round(xLoc / SIDE);
        col = (int)Math.round(yLoc / SIDE);
    }

    public void randomMove() {
        int possibility = (int)(Math.random() * 100);
        if (possibility < 25 && past != 2) {
            setUpVelocity();
            past = 1;
        }
        else if (possibility > 25 && possibility < 50 && past != 1) {
            setDownVelocity();
            past = 2;
        }

        else if (possibility > 50 && possibility < 75 && past != 4) {
            setRightVelocity();
            past = 3;
        }

        else if (possibility > 75 && possibility < 100 && past != 3) {
            setLeftVelocity();
            past = 4;
        }

        move();
    }


}
