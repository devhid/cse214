package hw5;

/**
 * The {@code Lang} class contains all the messages used throughout the program.
 *
 * @author Mankirat Gulati
 *    email: mankirat.gulati@stonybrook.edu
 *    Stony Brook ID: 111161128
 */

public class Lang {
    public static final String DESCRIPTION = "\t\tWelcome to Tic Tac Toe!\n";
    public static final String LABEL_BOARD = "This is the board:\n";
    public static final String END = "Thanks for playing!";

    public static final String MAKE_MOVE = "\nPlease make a move (1-9): ";
    public static final String INVALID_MOVE = "Invalid move. Position must be between 1-9.\n";
    public static final String ILLEGAL_MOVE = "Illegal move. That box is already filled.\n";

    public static final String PROBABILITY = "Probability of %s: %.2f\n";
    public static final String WINNER = "\n%s has won.\n";
    public static final String DRAW = "\nIt's a draw.\n";

    public static final String NULL_BOARD = "Error. Board cannot be null.";
    public static final String EMPTY_CURRENT_TURN = "Error. Current turn cannot be empty.";
    public static final String CANNOT_UNDO = "Error. You cannot undo anymore.\n";
}
