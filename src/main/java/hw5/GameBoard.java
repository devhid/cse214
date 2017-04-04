package hw5;

/**
 * The {@code GameBoard} class represents the physical tic-tac-toe board and
 * contains a {@code Box} array to handle the boxes placed on each turn.
 *
 * @author Mankirat Gulati
 *    email: mankirat.gulati@stonybrook.edu
 *    Stony Brook ID: 111161128
 */
public class GameBoard {
    // The capacity of the board or number of spots in the tic-tac-toe board.
    private static final int BOARD_SIZE = 9;

    // An array of boxes correspondent to this board's grid.
    private final Box[] board;

    /**
     * Initializes {@code board} to a {@code Box} array of size, {@code BOARD_SIZE}.
     * Also initializes all of the boxes in the array to {@code Box.EMPTY}.
     */
    public GameBoard() {
        this.board = new Box[BOARD_SIZE];
        this.setup();
    }

    // Sets all the boxes to be empty.
    private void setup() {
        for(int i = 0; i < board.length; i++) {
            board[i] = Box.EMPTY;
        }
    }

    /**
     * Returns the tic-tac-toe grid.
     *
     * @return The {@code Box} array representing this {@code GameBoard}'s grid.
     */
    public Box[] getGrid() {
        return this.board;
    }

    /**
     * Returns the box in the grid at the specified position.
     *
     * @param position The location of where we will get the {@code Box} from the array.
     * @return The {@code Box} object located at {@code position} in {@code board}.
     */
    public Box getBox(int position) {
        return board[position];
    }

    /**
     * Sets a box at the specified position in the grid.
     *
     * @param box The {@code Box} we are setting at the specified {@code position}.
     * @param position The location of where we will set the {@code Box} in the array.
     */
    public void setBox(final Box box, int position) {
        board[position] = box;
    }

    /**
     * Checks if the box at the specified position is empty.
     *
     * @param position The location in the array that is being checked to see if its box is empty.
     * @return {@code true} if the box at {@code position} is {@code Box.EMPTY}, {@code false} otherwise.
     */
    public boolean isEmpty(int position) {
        return board[position] == Box.EMPTY;
    }

    /**
     * Returns the size of the board.
     *
     * @return The number of boxes currently placed in the grid.
     */
    public int getSize() {
        int count = 0;

        for(Box box: board) {
            if(box != Box.EMPTY) {
                count++;
            }
        }
        return count;
    }

    /**
     * Returns a copy of this board.
     *
     * @return A deep clone of this {@code GameBoard} and its properties.
     */
    public GameBoard clone() {
        GameBoard clone = new GameBoard();

        int i = 0;
        for(Box box: board) {
            clone.setBox(box, i++);
        }

        return clone;
    }

}
