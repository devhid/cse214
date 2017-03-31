package hw5;

public class GameBoardNode {
    private static final int SIZE = 9;
    private final GameBoardNode[] configurations;

    private GameBoard board;
    private Box currentTurn, winner;

    private boolean hasEnded;
    private double winProb, loseProb, drawProb;

    public GameBoardNode(final GameBoard board, final Box currentTurn) {
        if(board == null || currentTurn == Box.EMPTY) {
           throw new IllegalArgumentException();
        }

        this.hasEnded = false;
        this.board = board;
        this.currentTurn = currentTurn;
        this.configurations = new GameBoardNode[SIZE];
    }

    public GameBoardNode(final GameBoardNode node) {
        this(node.board, node.currentTurn);

        for(int i = 0; i < node.getBoard().getCapacity(); i++) {
            board.getGrid()[i] = node.getBoard().getGrid()[i];
        }
    }

    public int getSize() {
        return SIZE;
    }

    public void buildConfigurations() {
        int numConfigs = board.getSize();
        int count = 0;

        GameBoard copy;
        for(int i = 0; i < configurations.length; i++) {
            copy = board.clone();

            if(copy.getBox(i) != Box.EMPTY) {
                continue;
            }

            copy.setBox(currentTurn, i);
            configurations[i] = (count++ == numConfigs) ?
                    null : new GameBoardNode(copy, currentTurn);
        }
    }

    public GameBoardNode[] getConfigurations() {
        return configurations;
    }

    public GameBoard getBoard() {
        return this.board;
    }

    public void setBoard(final GameBoard board) {
        this.board = board;
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
        Box[] boxes = board.getGrid();

        for(int i = 0; i < boxes.length; i++) {
            result += "|" + (boxes[i] == Box.EMPTY ? "_" : boxes[i].toString());
            result += (i + 1) % 3 == 0 ? "|\n" : "";
        }

        return result;
    }

}
