import java.awt.*;

public class Spot {
    // Declare instance variables
    private Square owner;
    private int row;
    private int col;

    // Constructor
    public Spot(int row, int col){
        this.row = row;
        this.col = col;
    }

    // Getter and setter for owner
    public Square getOwner() {
        return owner;
    }

    public void setOwner(Square owner) {
        this.owner = owner;
    }

    // Fills in a 30 by 30 pixel square at its specified location with a specific color
    public void draw(Graphics g) {
        Color color = owner.getColor();
        int xLoc = col * Square.SIDE;
        int yLoc = row * Square.SIDE;

        g.setColor(color);
        g.fillRect(xLoc, yLoc, Square.SIDE, Square.SIDE);
    }

}
