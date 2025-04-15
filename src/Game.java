import java.util.ArrayList;

public class Game {
    private GameView window;
    private ArrayList<Player> leaderboard;
    private Player computer;

    public Game() {
        leaderboard = new ArrayList<Player>();
        window = new GameView(this);
    }

    public void computerWork() {

    }

    public void playGame() {

    }

    public static void main(String [] args) {

    }
}
