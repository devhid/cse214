package hw5;

public class GameTree {
    private GameBoardNode root, cursor;

    public GameTree() {
        root = new GameBoardNode(new GameBoard(), Box.X);
        this.cursor = root;
    }

    public GameBoardNode getRoot() {
        return this.root;
    }

    public GameBoardNode getCursor() {
        return this.cursor;
    }

    public void makeMove(final int position) {
        if(position < 1 || position > 9) {
            throw new IllegalArgumentException();
        }

        Box box = cursor.getGameBoard().getBoard()[position - 1];

        if(box != Box.EMPTY) {
            throw new IllegalArgumentException();
        }

        cursor.getGameBoard().setBox(Box.X, position);
        cursor.setCurrentTurn(cursor.getCurrentTurn() == Box.X ? Box.O : Box.X);
    }

    public static GameBoardNode buildTree(final GameBoardNode root, final Box turn) {

        return root;
    }

    public static Box checkWin(final GameBoardNode node) {
        return null;
    }

    public double cursorProbability() {
        return 0.0;
    }
}
