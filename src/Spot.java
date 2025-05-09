import java.awt.*;

public class Spot {
    private Square owner;
    private int row;
    private int col;

    public Spot(int row, int col){
        this.row = row;
        this.col = col;
    }

    public Square getOwner() {
        return owner;
    }

    public void setOwner(Square owner) {
        this.owner = owner;
    }

    public void draw(Graphics g) {
        Color color = owner.getColor();
        int xLoc = col * Square.SIDE;
        int yLoc = row * Square.SIDE;

        g.setColor(color);
        g.fillRect(xLoc, yLoc, Square.SIDE, Square.SIDE);
    }

}
