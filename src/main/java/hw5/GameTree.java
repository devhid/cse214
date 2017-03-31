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
    }

    public GameBoardNode getRoot() {
        return this.root;
    }

    public void setRoot(final GameBoardNode root) {
        this.root = root;
    }

    public GameBoardNode getCursor() {
        return this.cursor;
    }

    public void setCursor(final GameBoardNode cursor) {
        this.cursor = cursor;
    }

    public void makeMove(final int position) {
        if(position < 1 || position > 9) {
            throw new IllegalArgumentException();
        }

        Box box = cursor.getBoard().getGrid()[position - 1];

        if(box != Box.EMPTY) {
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

    public static GameBoardNode[] getLeaves(final GameBoardNode node, int i) {
        if(isLeaf(node)) {
            leaves[getFirstAvailablePosition()] = node;
            //System.out.println(leaves[getFirstAvailablePosition() - 1].toString());
            leafCount++;
        } else {
            for(GameBoardNode child: node.getConfigurations()) {
                if(child != null) {
                    getLeaves(child, i);
                }
            }
        }

        return leaves;
    }

    public static int getFirstAvailablePosition() {
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

    public static Box checkWin(final GameBoardNode node) {
        if(!isLeaf(node)) {
            return null;
        }

        return getThreeInARow(node.getBoard().getGrid());
    }

    private static boolean isLeaf(final GameBoardNode node) {
        for(GameBoardNode child: node.getConfigurations()) {
            if(child != null) {
                return false;
            }
        }
        return true;
    }

    public static Box getThreeInARow(final Box[] grid) {
        // Check rows.
        for(int i = 0; i < 3; i+=3) {
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

    public int getTotalLeaves() {
        return TOTAL_LEAVES;
    }

    public void clearLeaves() {
        leafCount = 0;
        for(int i  = 0; i < leaves.length; i++) {
            leaves[i] = null;
        }
    }

    public double cursorProbability() {
        int count = 0;

        for(GameBoardNode node: leaves) {
            if(node != null) {
                if (getThreeInARow(node.getBoard().getGrid()) == Box.X) {
                    count++;
                }
            }
        }

        double result = (double) count / leafCount;

        clearLeaves();
        return new BigDecimal(result).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();//getAllLeafNodes(cursor).size();
    }
}
