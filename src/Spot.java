import java.awt.*;

public class Spot {
    private Square owner;
    private int row;
    private int col;

    public Spot(int row, int col){
        this.row = row;
        this.col = col;
    }

    public void setOwner(Square owner) {
        this.owner = owner;
    }

    public void draw(Graphics g) {
        Color color = owner.getColor();
        int xLoc = row * Square.SIDE;
        int yLoc = col * Square.SIDE;

        g.setColor(color);
        g.fillRect(xLoc, yLoc, Square.SIDE, Square.SIDE);
    }
}
