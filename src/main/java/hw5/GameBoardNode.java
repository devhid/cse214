package hw5;

public class GameBoardNode {
    private static final int SIZE = 9;

    private final GameBoardNode[] configuration;
    private final GameBoard board;

    private boolean hasEnded;
    private Box currentTurn, winner;

    private double winProb, loseProb, drawProb;

    public GameBoardNode(final GameBoard board, final Box currentTurn) {
        if(board == null || currentTurn == Box.EMPTY) {
           throw new IllegalArgumentException();
        }

        this.board = board;
        this.currentTurn = currentTurn;
        this.configuration = new GameBoardNode[SIZE];
    }

    public int getSize() {
        return SIZE;
    }

    public GameBoardNode[] getConfiguration() {
        return configuration;
    }

    public GameBoard getBoard() {
        return this.board;
    }

    public boolean hasEnded() {
        return this.hasEnded;
    }

    public void setHasEnded(final boolean hasEnded) {
        this.hasEnded = hasEnded;
    }

    public Box getCurrentTurn() {
        return this.currentTurn;
    }

    public void setCurrentTurn(final Box currentTurn) {
        this.currentTurn = currentTurn;
    }

    public Box getWinner() {
        return this.winner;
    }

    public void setWinner(final Box winner) {
        this.winner = winner;
    }

    public double getWinProbability() {
        return this.winProb;
    }

    public double getLoseProbability() {
        return this.loseProb;
    }

    public double getDrawProbability() {
        return this.drawProb;
    }

    public void setProbabilities(final double probability) {
        this.winProb = probability;
        this.loseProb = probability;
        this.drawProb = probability;
    }

    public String toString() {
        return null;
    }

}
