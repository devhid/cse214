package hw5;

import java.math.BigDecimal;

public class GameTree {
    private static final int TOTAL_LEAVES = 9*8*7*6*5*4*3*2;

    private static GameBoardNode[] leaves = new GameBoardNode[TOTAL_LEAVES];
    private static int leafCount = 0;

    private GameBoardNode root, cursor;

    public GameTree() {
        this.root = new GameBoardNode(new GameBoard(), Box.X);
        this.cursor = root;
        buildTree(root, cursor.getCurrentTurn());
    }

    public void makeMove(final int position) {
        if(position < 1 || position > 9) {
            throw new IllegalArgumentException();
        }

        if(!cursor.getBoard().isEmpty(position - 1)) {
            throw new IllegalArgumentException("Invalid move. Position occupied or out of range.\n");
        }

        GameBoardNode node = cursor.getConfigurations()[position - 1];

        node.getBoard().setBox(cursor.getCurrentTurn(), position - 1);
        node.setCurrentTurn(cursor.getCurrentTurn() == Box.X ? Box.O : Box.X);

        cursor = node;
    }

    public static GameBoardNode buildTree(final GameBoardNode root, Box turn) {
        root.buildConfigurations();

        for(GameBoardNode node: root.getConfigurations()) {
            if(node != null) {
                node.setCurrentTurn(node.getCurrentTurn() == Box.X ? Box.O : Box.X);
                buildTree(node, turn);
            }
        }

        return root;
    }

    public static Box checkWin(final GameBoardNode node) {
        if(!isLeaf(node)) {
            return null;
        }

        return getThreeInARow(node.getBoard().getGrid());
    }

    public double cursorProbability() {
        return cursor.getWinProbability();
        /*int count = 0;

        for(GameBoardNode node: leaves) {
            if(node == null) { continue; }

            if (getThreeInARow(node.getBoard().getGrid()) == Box.X) {
                count++;
            }
        }

        double result = (double) count / leafCount;

        clearLeaves();
        return new BigDecimal(result).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();*/
    }

    public static GameBoardNode[] getLeaves(final GameBoardNode node) {
        if(isLeaf(node)) {
            leaves[leafCount] = node;
            leafCount++;
        } else {
            for(GameBoardNode child: node.getConfigurations()) {
                if(child != null) {
                    getLeaves(child);
                }
            }
        }

        return leaves;
    }

    public static Box getThreeInARow(final Box[] grid) {
        // Check rows.
        for(int i = 0; i < 9; i+=3) {
            if(grid[i] == grid[i + 1] && grid[i] == grid[i + 2] && grid[i] != Box.EMPTY) {
                return grid[i];
            }
        }

        // Check columns.
        for(int i = 0; i < 3; i++) {
            if(grid[i] == grid[i + 3] && grid[i] == grid[i + 6] && grid[i] != Box.EMPTY) {
                return grid[i];
            }
        }

        // Check diagonals.
        if(grid[2] == grid[4] && grid[2] == grid[6] && grid[2] != Box.EMPTY) {
            return grid[2];
        }

        if(grid[0] == grid[4] && grid[0] == grid[8] && grid[0] != Box.EMPTY) {
            return grid[0];
        }

        return Box.EMPTY;
    }

    /* HELPER METHODS */

    private static int getFirstAvailablePosition() {
        for(int i = 0; i < leaves.length; i++) {

        }
        int i = 0;
        while(leaves[i] != null) {
            i++;
        }
        return i;
    }

    /*public static Set<GameBoardNode> getAllLeafNodes(GameBoardNode node) {
        Set<GameBoardNode> leafNodes = new HashSet<>();
        if (isLeaf(node)) {
            leafNodes.add(node);
        } else {
            for (GameBoardNode child : node.getConfigurations()) {
                if(child != null) {
                    leafNodes.addAll(getAllLeafNodes(child));
                }
            }
        }
        return leafNodes;
    }*/

    private static boolean isLeaf(final GameBoardNode node) {
        for(GameBoardNode child: node.getConfigurations()) {
            if(child != null) {
                return false;
            }
        }
        return true;
    }

    public static void clearLeaves() {
        for(int i  = 0; i < leaves.length; i++) {
            leaves[i] = null;
        }
        leafCount = 0;
    }

    /*###### GETTERS & SETTERS ######*/

    public static GameBoardNode[] getLeaves() { return leaves; }

    public GameBoardNode getRoot() { return this.root; }

    public GameBoardNode getCursor() { return this.cursor; }

    public static int getLeafCount() { return leafCount; }

    public static int getTotalLeaves() { return TOTAL_LEAVES; }

    public void setRoot(final GameBoardNode root) { this.root = root; }

    public void setCursor(final GameBoardNode cursor) { this.cursor = cursor; }
}
