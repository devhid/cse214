package hw5;

public class GameBoardNode {
    private static final int SIZE = 9;
    private final GameBoardNode[] configurations;

    private GameBoard board;
    private Box currentTurn, winner;

    private boolean hasEnded;
    private double winProb, loseProb, drawProb;

    public GameBoardNode(final GameBoard board, final Box currentTurn) {
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

    public void setProbabilities() {
        probHelper(this);

        double leafCount = winProb + loseProb + drawProb;

        winProb  = winProb  / leafCount;
        loseProb = loseProb / leafCount;
        drawProb = drawProb / leafCount;
    }

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

    public boolean hasEnded() {
        Box[] grid = board.getGrid();

        checkRowWin(grid);
        checkColumnWin(grid);
        checkDiagonalWin(grid);

        checkDraw();
        return winner != null;
    }

    private void checkRowWin(final Box[] grid) {
        for(int i = 0; i < 9; i+=3) {
            if(grid[i] == grid[i + 1] && grid[i] == grid[i + 2] && grid[i] != Box.EMPTY) {
                this.winner = grid[i];
            }
        }
    }

    private void checkColumnWin(final Box[] grid) {
        for(int i = 0; i < 3; i++) {
            if(grid[i] == grid[i + 3] && grid[i] == grid[i + 6] && grid[i] != Box.EMPTY) {
                winner = grid[i];
            }
        }
    }

    private void checkDiagonalWin(final Box[] grid) {
        if(grid[2] == grid[4] && grid[2] == grid[6] && grid[2] != Box.EMPTY) {
            winner = grid[2];
        }

        if(grid[0] == grid[4] && grid[0] == grid[8] && grid[0] != Box.EMPTY) {
            winner = grid[0];
        }
    }

    private void checkDraw() {
        if(board.getSize() == 9 && winner == null) {
            winner = Box.EMPTY;
        }
    }

    public GameBoardNode[] getConfigurations() {
        return configurations;
    }

    public GameBoardNode getConfig(int position) {
        return configurations[position];
    }

    public GameBoard getBoard() {
        return this.board;
    }

    public Box getCurrentTurn() {
        return this.currentTurn;
    }

    public Box getWinner() {
        return this.winner;
    }

    public int getCapacity() {
        return SIZE;
    }

    public void setBoard(final GameBoard board) {
        this.board = board;
    }

    public void setCurrentTurn(final Box currentTurn) {
        this.currentTurn = currentTurn;
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
