package hw5;

public class GameBoard {
    private final Box[] board;
    private final int size = 9;

    public GameBoard() {
        this.board = new Box[size];
    }

    public Box[] getBoard() {
        return this.board;
    }

    public void setBox(final Box box, int position) {
        board[position] = box;
    }

    public int getSize() {
        return this.size;
    }

}
