package hw5;

public class GameTree {
    private GameBoardNode root, cursor;

    public GameTree() {

    }

    public void makeMove(final int position) {
        if(position < 0 || position > 9) {
            throw new IllegalArgumentException();
        }
    }

    public static GameBoardNode buildTree(final GameBoardNode root, final Box turn) {
        return null;
    }

    public static Box checkWin(final GameBoardNode node) {
        return null;
    }

    public double cursorProbability() {
        return 0.0;
    }
}
