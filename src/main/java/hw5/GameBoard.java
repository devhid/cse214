package hw5;

public class GameBoard {
    private static final int BOARD_SIZE = 9;
    private final Box[] board;

    public GameBoard() {
        this.board = new Box[BOARD_SIZE];
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

    public int getCapacity() {
        return BOARD_SIZE;
    }

    public boolean isEmpty(int position) {
        return board[position] == Box.EMPTY;
    }

    public int getSize() {
        int count = 0;

        for(Box box: board) {
            if(box != Box.EMPTY) {
                count++;
            }
        }
        return count;
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
