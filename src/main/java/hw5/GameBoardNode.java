package hw5;

import java.math.BigDecimal;

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

    private GameBoardNode(final GameBoardNode node) {
        this(node.board, node.currentTurn);

        for(int i = 0; i < node.getBoard().getCapacity(); i++) {
            board.getGrid()[i] = node.getBoard().getGrid()[i];
        }
    }

    public int getWinningNode() {
        int max = 0;

        for(int i = 0; i < configurations.length; i++) {
            if(configurations[max] == null) {
                max = i;
                continue;
            }
            break;
        }

        for(int i = max; i < configurations.length; i++) {
            GameBoardNode node = configurations[i];
            if(node != null) {
                node.setProbabilities();
                if(configurations[max].getLoseProbability() == node.getLoseProbability()) {
                    max = configurations[max].getWinProbability() < node.getWinProbability() ? max : i;
                } else {
                    max = configurations[max].getLoseProbability() > node.getLoseProbability() ? max : i;
                }
            }
        }

        return max;
    }

    public void setProbabilities() {
        GameBoardNode[] leaves = GameTree.getLeaves(this);
        double win = 0, lose = 0, draw = 0;

        for(GameBoardNode node: leaves) {
            if(node == null) { continue; }

            Box result = GameTree.getThreeInARow(node.getBoard().getGrid());

            if (result == currentTurn) {
                win++;
            } else if(result == Box.EMPTY) {
                draw++;
            } else {
                lose++;
            }
        }

        int leafCount = GameTree.getLeafCount();

        GameTree.clearLeaves();
        winProb = new BigDecimal(win / leafCount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        loseProb = new BigDecimal(lose / leafCount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        drawProb = new BigDecimal(draw / leafCount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public void buildConfigurations() {
        int numConfigs = board.emptyCount();
        int count = 0;

        GameBoard copy;
        for(int i = 0; i < configurations.length; i++) {
            copy = board.clone();

            if (copy.getBox(i) != Box.EMPTY) {
                continue;
            }

            copy.setBox(currentTurn, i);
            configurations[i] = (count++ == numConfigs) ?
                    null : new GameBoardNode(copy, currentTurn);
        }
    }

    public int getCapacity() { return SIZE; }

    public GameBoardNode[] getConfigurations() { return configurations; }

    public GameBoard getBoard() { return this.board; }

    public Box getCurrentTurn() { return this.currentTurn; }

    public Box getWinner() { return this.winner; }

    public boolean hasEnded() { return this.hasEnded; }

    public void setBoard(final GameBoard board) { this.board = board; }

    public void setCurrentTurn(final Box currentTurn) { this.currentTurn = currentTurn; }

    public void setWinner(final Box winner) { this.winner = winner; }

    public void setHasEnded(final boolean hasEnded) { this.hasEnded = hasEnded; }

    public double getWinProbability() { return this.winProb; }

    public double getLoseProbability() { return this.loseProb; }

    public double getDrawProbability() { return this.drawProb; }

    public int getSize() {
        int count = 0;
        for(GameBoardNode node: configurations) {
            if(node != null) {
                count++;
            }
        }
        return count;
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
