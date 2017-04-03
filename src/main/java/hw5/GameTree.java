package hw5;

public class GameTree {
    private static final int TOTAL_LEAVES = 255168;

    private static GameBoardNode[] leaves = new GameBoardNode[TOTAL_LEAVES];
    private static int leafCount = 0;

    private GameBoardNode root, cursor;

    public GameTree() {
        this.root = new GameBoardNode(new GameBoard(), Box.X);
        this.cursor = root;

        buildTree(root, root.getCurrentTurn());
    }

    public void makeMove(final int position) {
        if(position < 1 || position > 9) {
            throw new IllegalArgumentException(Lang.INVALID_MOVE);
        }

        if(!cursor.getBoard().isEmpty(position - 1)) {
            throw new IllegalArgumentException(Lang.ILLEGAL_MOVE);
        }

        cursor = cursor.getConfig(position - 1);
    }

    public static GameBoardNode buildTree(final GameBoardNode root, Box turn) {
        if(!root.hasEnded()) {
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

    public static Box checkWin(final GameBoardNode node) {
        if(node.hasEnded()) {
            return node.getWinner();
        }

        return null;
    }

    public double cursorProbability() {
        return cursor.getWinProbability();
    }

    public static GameBoardNode[] buildLeaves(final GameBoardNode node) {
        if(node.hasEnded()) {
            leaves[leafCount++] = node;
        } else {
            for(GameBoardNode child: node.getConfigurations()) {
                if(child != null) {
                    buildLeaves(child);
                }
            }
        }

        return leaves;
    }

    public static void clearLeaves() {
        leafCount = 0;

        for(int i  = 0; i < leaves.length; i++) {
            leaves[i] = null;
        }

    }

    public static GameBoardNode[] getLeaves() {
        return leaves;
    }

    public GameBoardNode getRoot() {
        return this.root;
    }

    public GameBoardNode getCursor() {
        return this.cursor;
    }

    public static int getLeafCount() {
        return leafCount;
    }

    public static int getTotalLeaves() {
        return TOTAL_LEAVES;
    }

    public void setRoot(final GameBoardNode root) {
        this.root = root;
    }

    public void setCursor(final GameBoardNode cursor) {
        this.cursor = cursor;
    }
}
