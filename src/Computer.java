// should this extend square??

import java.awt.*;

public class Computer {
    private int xLoc;
    private int yLoc;
    private GameView window;
    private final int SIDE = 30;
    private final int SHIFT = 20;
    private Square sq;

    public Computer(GameView window, Square sq){
        this.window = window;
        this.sq = sq;
        // Set location of computer, but make sure it's not too close to the player
        int squareX = sq.getxLoc();
        int squareY = sq.getyLoc();
        do {
            xLoc = (int)(Math.random() * (GameView.WINDOW_WIDTH - (SIDE / 2)));
        }
        while(xLoc < squareX + SIDE && xLoc > squareX - SIDE);

        do {
            yLoc = (int)(Math.random() * (GameView.WINDOW_WIDTH - (SIDE / 2)));
        }
        while(yLoc < squareY + SIDE && yLoc > squareY - SIDE);
    }

    public void move() {

    }

    public void draw(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect(xLoc, yLoc, SIDE, SIDE);
    }

}
