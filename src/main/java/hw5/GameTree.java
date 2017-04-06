package hw5;

/**
 * The {@code GameTree} is a 9-ary tree data structure used for getting all possible
 * configurations for the game of tic-tac-toe.
 *
 * @author Mankirat Gulati
 *    email: mankirat.gulati@stonybrook.edu
 *    Stony Brook ID: 111161128
 */
public class GameTree {
    // root - the first node in the tree at height 0.
    // cursor - the node that is linked to the current configuration in the game
    private GameBoardNode root, cursor;

    /**
     * Initializes {@code root} to a new {@code GameBoardNode} object with a new {@code GameBoard}.
     * The symbol for the player is initalized as {@code Box.X}.
     * Also initializes {@code cursor} to the value of {@code root}.
     * Finally, builds the whole tree.
     */
    public GameTree() {
        this.root = new GameBoardNode(new GameBoard(), Box.X);
        this.cursor = root;

        buildTree(root, root.getCurrentTurn());
    }

    /**
     * Makes a move on the tic-tac-toe board with the given position.
     *
     * @param position The location selected on the grid for the move.
     */
    public void makeMove(final int position) throws IllegalArgumentException {
        if(position < 0 || position > 9) {
            throw new IllegalArgumentException(Lang.INVALID_MOVE);
        }

        if(!cursor.getBoard().isEmpty(position - 1)) {
            throw new IllegalArgumentException(Lang.ILLEGAL_MOVE);
        }

        cursor = cursor.getConfig(position - 1);
    }

    /**
     * Builds a complete 9-ary tree for tic-tac-toe configurations and returns the root node.
     *
     * @param root The {@code GameBoardNode} being recursively used for each subtree of the tree.
     * @param turn The {@code Box} to represent whose turn it should be.
     * @return The root node for this tree.
     */
    public static GameBoardNode buildTree(final GameBoardNode root, Box turn) {
        if(checkWin(root) == null) {
            root.buildConfigurations();
        }

        for(GameBoardNode child: root.getConfigurations()) {
            if(child != null) {
                child.setCurrentTurn(child.getCurrentTurn() == Box.X ? Box.O : Box.X);
                buildTree(child, turn);
            }
        }

        return root;
    }

    /**
     * Checks if the specified node is a winning, losing, or drawing configuration.
     *
     * @param node The {@code GameBoardNode} being checked for a winning, losing, or drawing state.
     * @return The {@code Box} type of whoever won, {@code Box.EMPTY} if it was a tie, {@code null}, if the node isn't a leaf.
     */
    public static Box checkWin(final GameBoardNode node) {
        if(node.hasEnded()) {
            return node.getWinner();
        }

        return null;
    }

    /**
     * Returns the winning probability at the cursor.
     *
     * @return The probability of winning the game with the cursor's current configuration.
     */
    public double cursorProbability() {
        return cursor.getWinProbability();
    }

    /**
     * Returns the root for this tree.
     *
     * @return The {@code GameBoardNode} object acting as the root for this {@code GameTree}.
     */
    public GameBoardNode getRoot() {
        return this.root;
    }

    /**
     * Returns the cursor for this tree.
     *
     * @return The {@code GameBoardNode} object acting as the cursor for this {@code GameTree}.
     */
    public GameBoardNode getCursor() {
        return this.cursor;
    }

    /**
     * Sets the cursor for this tree to the specified cursor.
     *
     * @param cursor The {@code GameBoardNode} that is being set as the new cursor.
     */
    public void setCursor(final GameBoardNode cursor) {
        this.cursor = cursor;
    }
}
