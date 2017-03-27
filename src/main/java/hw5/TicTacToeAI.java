package hw5;

public class TicTacToeAI {
    public static void main(String[] args) {
        GameTree tree = new GameTree();
        for(int i = 0; i < tree.getRoot().getGameBoard().getBoard().length; i++) {
            tree.getRoot().getGameBoard().getBoard()[i] = Box.EMPTY;
        }

        System.out.print(tree.getCursor().toString());
    }

    public static void playGame(final GameTree tree) {

    }

}
