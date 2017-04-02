package hw5;

import java.util.Scanner;

public class TicTacToeAI {
    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        playGame(new GameTree());
    }

    public static void playGame(final GameTree tree) {
        GameTree.buildTree(tree.getCursor(), tree.getCursor().getCurrentTurn());
        System.out.println(tree.getRoot().toString());

        while(!tree.getCursor().hasEnded()) {
            System.out.print("Please enter your location (1-9): ");
            int spot = Integer.parseInt(input.nextLine());

            try {
                tree.makeMove(spot);
            } catch (IllegalArgumentException ex) {
                System.out.print("\n" + ex.getMessage());
                continue;
            }

            Box result = GameTree.getThreeInARow(tree.getCursor().getBoard().getGrid());
            if (result != Box.EMPTY) {
                System.out.println(result.toString() + " has won.");
                tree.getCursor().setHasEnded(true);
            } else {
                if(tree.getCursor().getBoard().getSize() == 9) {
                    System.out.println("It's a draw.");
                    tree.getCursor().setHasEnded(true);
                }
            }

            if(!tree.getCursor().hasEnded()) {
                tree.makeMove(tree.getCursor().getWinningNode() + 1);

                System.out.println(tree.getCursor().toString());

                tree.getCursor().setProbabilities();

                System.out.println("The probability of winning is: " + tree.cursorProbability());
                System.out.println("The probability of losing is: " + tree.getCursor().getLoseProbability());
                System.out.println("The probability of drawing is: " + tree.getCursor().getDrawProbability());

                result = GameTree.getThreeInARow(tree.getCursor().getBoard().getGrid());
                if (result != Box.EMPTY) {
                    System.out.println(result.toString() + " has won.");
                    tree.getCursor().setHasEnded(true);
                } else {
                    if(tree.getCursor().getBoard().getSize() == 9) {
                        System.out.println("It's a draw.");
                        tree.getCursor().setHasEnded(true);
                    }
                }

                if(!tree.getCursor().hasEnded()) {
                    GameTree.buildTree(tree.getCursor(), tree.getCursor().getCurrentTurn());
                }
            } else {
                System.out.println(tree.getCursor().toString());
            }
        }

    }
}
