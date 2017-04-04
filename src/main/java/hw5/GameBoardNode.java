package hw5;

/**
 * The {@code GameBoardNode} class represents a possible configuration of boxes that is
 * linked to other configurations in a 9-ary tree.
 *
 * @author Mankirat Gulati
 *    email: mankirat.gulati@stonybrook.edu
 *    Stony Brook ID: 111161128
 */
public class GameBoardNode {
    // The capacity of the number of configurations.
    private static final int SIZE = 9;

    // The array holding the child configurations of this node.
    private final GameBoardNode[] configurations;

    // The board object linked to this configuration.
    private GameBoard board;

    // currentTurn - whoever is supposed to place the next box.
    // winner - the winner of tic-tac-toe if this configuration has a winning or draw state.
    private Box currentTurn, winner;

    // Whether or not this configuration has a winning, losing, or drawing state.
    private boolean hasEnded;

    // Probabilities of winning, losing, and drawing from the current configuration.
    private double winProb, loseProb, drawProb;

    /**
     * Initializes {@code board} and {@code currentTurn} to the corresponding
     * parameters in the constructor.
     *
     * @param board The {code GameBoard} object that will be set to this node's board.
     * @param currentTurn The {@code Box} object that will be set to this node's current turn.
     * @throws IllegalArgumentException
     *     Indicates that {@code board} is {@code null} or {@code currentTurn} is {@code Box.EMPTY}.
     */
    public GameBoardNode(final GameBoard board, final Box currentTurn) throws IllegalArgumentException {
        if(board == null) {
            throw new IllegalArgumentException(Lang.NULL_BOARD);
        }

        if(currentTurn == null) {
            throw new IllegalArgumentException(Lang.EMPTY_CURRENT_TURN);
        }

        this.hasEnded = false;
        this.board = board;
        this.currentTurn = currentTurn;
        this.configurations = new GameBoardNode[SIZE];
    }

    // Gets the number of winning, losing, and draw leaves.
    private void probHelper(final GameBoardNode node) {
        if(node.hasEnded()) {
            Box result = node.getWinner();

            winProb =  (result == currentTurn) ? winProb + 1 : winProb;
            drawProb = (result == Box.EMPTY) ? drawProb + 1 : drawProb;
            loseProb = (result != currentTurn && result != Box.EMPTY) ? loseProb + 1 : loseProb;
        } else {
            for(GameBoardNode child: node.getConfigurations()) {
                if(child != null) {
                    probHelper(child);
                }
            }
        }
    }

    /**
     * Calculates the probabilities of winning, losing, and drawing for this node
     * and sets it equal to the appropriate instance fields.
     */
    public void setProbabilities() {
        probHelper(this);

        double leafCount = winProb + loseProb + drawProb;

        winProb  = winProb  / leafCount;
        loseProb = loseProb / leafCount;
        drawProb = drawProb / leafCount;
    }

    /**
     * Builds all possible child configurations from this current node.
     */
    public void buildConfigurations() {
        GameBoard copy;

        for(int i = 0; i < configurations.length; i++) {
            if(board.getBox(i) != Box.EMPTY) {
                continue;
            }
            copy = board.clone();

            copy.setBox(currentTurn, i);
            configurations[i] = new GameBoardNode(copy, currentTurn);
        }
    }

    /**
     * Checks whether or not this configuration node has a winning, losing, or drawing state.
     *
     * @return {@code true} if this configuration has a winning, losing, or drawing state, {@code false} otherwise.
     */
    public boolean hasEnded() {
        Box[] grid = board.getGrid();

        checkRowWin(grid);
        checkColumnWin(grid);
        checkDiagonalWin(grid);

        checkDraw();
        return winner != null;
    }

    // Checks the rows for paths of 3 of one type of box.
    private void checkRowWin(final Box[] grid) {
        for(int i = 0; i < 9; i+=3) {
            if(grid[i] == grid[i + 1] && grid[i] == grid[i + 2] && grid[i] != Box.EMPTY) {
                this.winner = grid[i];
            }
        }
    }

    // Checks the columns for paths of 3 of one type of box.
    private void checkColumnWin(final Box[] grid) {
        for(int i = 0; i < 3; i++) {
            if(grid[i] == grid[i + 3] && grid[i] == grid[i + 6] && grid[i] != Box.EMPTY) {
                winner = grid[i];
            }
        }
    }

    // Checks the diagonals for paths of 3 of one type of box.
    private void checkDiagonalWin(final Box[] grid) {
        if(grid[2] == grid[4] && grid[2] == grid[6] && grid[2] != Box.EMPTY) {
            winner = grid[2];
        }

        if(grid[0] == grid[4] && grid[0] == grid[8] && grid[0] != Box.EMPTY) {
            winner = grid[0];
        }
    }

    // Checks if the current configuration has a drawing state.
    private void checkDraw() {
        if(board.getSize() == 9 && winner == null) {
            winner = Box.EMPTY;
        }
    }

    /**
     * Returns the configurations for this node.
     *
     * @return A {@code GameBoardNode} array of possible configurations from this node.
     */
    public GameBoardNode[] getConfigurations() {
        return configurations;
    }

    /**
     * Gets the configuration in the array at the specified position.
     *
     * @param position The index used to get the configuration in the array.
     * @return The {@code GameBoardNode} object found at the specified {@code position} in the array.
     */
    public GameBoardNode getConfig(int position) {
        return configurations[position];
    }

    /**
     * Returns this node's board.
     *
     * @return The {@code GameBoard} object for this node.
     */
    public GameBoard getBoard() {
        return this.board;
    }

    /**
     * Returns whose turn it currently is for this configuration.
     *
     * @return Returns the {@code Box} type for whose turn it currently is at this point.
     */
    public Box getCurrentTurn() {
        return this.currentTurn;
    }

    /**
     * Returns the winner if there is one for this node configuration.
     *
     * @return The {@code Box} object representing the winner for this configuration, {@code null} if none exists.
     */
    public Box getWinner() {
        return this.winner;
    }

    /**
     * Sets the current turn for this node to the specified turn.
     *
     * @param currentTurn The {@code Box} object being set for this node's {@code currentTurn}.
     */
    public void setCurrentTurn(final Box currentTurn) {
        this.currentTurn = currentTurn;
    }

    /**
     * Returns the winning probability for this configuration.
     *
     * @return The probability of {@code currentTurn} winning with this configuration.
     */
    public double getWinProbability() {
        return this.winProb;
    }

    /**
     * Returns the losing probability for this configuration.
     *
     * @return The probability of {@code currentTurn} losing with this configuration.
     */
    public double getLoseProbability() {
        return this.loseProb;
    }

    /**
     * Returns the drawing probability for this configuration.
     *
     * @return The probability of {@code currentTurn} drawing with this configuration.
     */
    public double getDrawProbability() {
        return this.drawProb;
    }

    /**
     * Returns a string representation of the tic-tac-toe board with this configuration.
     *
     * @return A {@code String} representation of this configuration in a tic-tac-toe board.
     */
    public String toString() {
        String result = "";
        Box[] boxes = board.getGrid();

        for(int i = 0; i < boxes.length; i++) {
            result += "|" + (boxes[i] == Box.EMPTY ? "_" : boxes[i].toString());
            result += (i + 1) % 3 == 0 ? "|\n" : "";
        }

        return result;
    }

}
