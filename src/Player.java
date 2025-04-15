public class Player {
    private String name;
    private int area;
    private boolean isEliminated;

    public Player(String name) {
        this.name = name;
        isEliminated = false;
    }

    // Getters and Setters
    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public boolean isEliminated() {
        return isEliminated;
    }

    public void setEliminated(boolean eliminated) {
        isEliminated = eliminated;
    }
}
