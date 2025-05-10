import java.awt.*;

public class Square {
    // Declare instance variables
    public static final int SIDE = 30;
    private Color color;
    private int area;
    // Pixel location
    private int xLoc;
    private int yLoc;
    // Location in terms of squares in grid
    private int row;
    private int col;
    // Direction/velocity
    private int dx;
    private int dy;
    // Variable for the computer to move randomly while not going back and forth b/w left & right or up & down
    private int past;


    // Constructor
    public Square( int xMin, int xMax, int yMin, int yMax, Color color) {
        // Initialize variables
        this.color = color;
        area = 0;
        // Randomly assign a location. Player will start on left half of screen and computer starts on right half
        // xMax/Min and yMax/Min are used to restrict different squares
        xLoc = (int)(Math.random() * (xMax - xMin)) + xMin;
        yLoc = (int)(Math.random() * (yMax - yMin)) + yMin;
        // Location of the square in the grid
        row = (int)Math.round(xLoc / SIDE);
        col = (int)Math.round(yLoc / SIDE);
        this.color = color;
        // Have the player start moving right
        dx = SIDE;
        dy = 0;
        past = 3;
    }


    // Give access to color so individual squares can be filled in Spot according to their owner
    public Color getColor(){
        return color;
    }

    // Getting and changing area
    public int getArea() {
        return area;
    }

    public void addArea() {
        area++;
    }

    // Getting grid location
    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    // Change direction of square
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
        shiftX(dx, GameView.WINDOW_WIDTH);
        shiftY(dy, GameView.WINDOW_HEIGHT);
    }

    // Moves square left and right
    public void shiftX(int shift, int max) {
        // If it goes out of bounds to the left
        if (xLoc + shift < 0 && shift < 0) {
            xLoc = 0;
        }

        // If it goes out of bounds to the right
        else if (xLoc + shift >= max && shift > 0) {
            xLoc = max - SIDE / 2;
        }

        // Otherwise, shift normally
        else {
            xLoc += shift;
        }

        // Update grid location
        col = (int)Math.floor(xLoc / SIDE);
    }

    // Moves square up and down
    public void shiftY(int shift, int max) {
        // If it goes out of bounds down
        if (yLoc + shift >= max && shift > 0) {
            yLoc = max - SIDE / 2;
        }

        // If it goes out of bounds up
        else if (yLoc + shift < GameView.HEADER_HEIGHT && shift < 0) {
            yLoc = GameView.HEADER_HEIGHT + SIDE / 2;
        }

        // Otherwise, shift normally
        else {
            yLoc += shift;
        }

        // Update grid location
        row = (int)Math.floor(yLoc / SIDE);

    }

    // Random movement of the computer. Movement is the same logic as the player square, these different conditions are
    // to make the movement random but natural (as explained for the 'past' variable)
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

    // Draw the leading square with a border so it's clear where the squares currently are
    public void drawSquare(Graphics g) {
        int xPlace = col * Square.SIDE;
        int yPlace = row * Square.SIDE;

        // Square
        g.setColor(color);
        g.fillRect(xPlace, yPlace, Square.SIDE, Square.SIDE);

        // Border
        g.setColor(Color.black);
        g.drawRect(xPlace, yPlace, Square.SIDE, Square.SIDE);
    }
}
