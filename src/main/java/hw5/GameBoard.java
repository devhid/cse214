package hw5;

public class GameBoard {
    private static final int SIZE = 9;
    private final Box[] board;

    public GameBoard() {
        this.board = new Box[SIZE];
        this.setup();
    }

    private void setup() {
        for(int i = 0; i < board.length; i++) {
            board[i] = Box.EMPTY;
        }
    }

    public Box[] getGrid() {
        return this.board;
    }

    public Box getBox(int position) {
        return board[position];
    }

    public void setBox(final Box box, int position) {
        board[position] = box;
    }

    public int getSize() {
        int count = 0;

        for(Box box: board) {
            if(box == Box.EMPTY) {
                count++;
            }
        }
        return count;
    }

    public boolean isEmpty(int position) {
        return board[position - 1] == Box.EMPTY;
    }

    public int getCapacity() {
        return SIZE;
    }

    public GameBoard clone() {
        GameBoard clone = new GameBoard();

        int i = 0;
        for(Box box: board) {
            clone.setBox(box, i++);
        }

        return clone;
    }

}
