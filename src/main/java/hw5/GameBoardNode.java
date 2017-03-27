package hw5;

public class GameBoardNode {
    private static final int SIZE = 9;

    private final GameBoardNode[] configuration;
    private final GameBoard gameBoard;

    private boolean hasEnded;
    private Box currentTurn, winner;

    private double winProb, loseProb, drawProb;

    public GameBoardNode(final GameBoard gameBoard, final Box currentTurn) {
        if(gameBoard == null || currentTurn == Box.EMPTY) {
           throw new IllegalArgumentException();
        }

        this.gameBoard = gameBoard;
        this.currentTurn = currentTurn;
        this.configuration = new GameBoardNode[SIZE];
    }

    public int getSize() {
        return SIZE;
    }

    public GameBoardNode[] getConfiguration() {
        return configuration;
    }

    public GameBoard getGameBoard() {
        return this.gameBoard;
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

    public void setProbabilities() {

    }

    public String toString() {
        String result = "";
        Box[] boxes = gameBoard.getBoard();

        for(int i = 0; i < boxes.length; i++) {
            result += "|" + (boxes[i] == Box.EMPTY ? "_" : boxes[i].toString());
            result += (i + 1) % 3 == 0 ? "|\n" : "";
        }

        return result;
    }

}
