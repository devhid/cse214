package hw5;

import java.math.BigDecimal;
import java.util.Scanner;

public class TicTacToeAI {
    private static final Scanner input = new Scanner(System.in);
    private static GamePhase phase = GamePhase.PLAYER_TURN;

    private enum GamePhase {
        PLAYER_TURN,
        CHECK_WIN,
        BOT_TURN,
    }

    public static void main(String[] args) {
        showRoot();
        replay(new GameTree());
    }

    private static void replay(final GameTree tree) {
        try {
            play(tree);
        } catch (NumberFormatException ex) {
            System.out.print("\n" + Lang.INVALID_MOVE);
            replay(tree);
        } catch (IllegalArgumentException ex) {
            System.out.print("\n" + ex.getMessage());
            replay(tree);
        }
    }

    public static void play(final GameTree tree) {
        switch (phase) {
            case PLAYER_TURN:
                System.out.print(Lang.MAKE_MOVE);
                int position = Integer.parseInt(input.nextLine());
                tryMove(tree, position);

                phase = GamePhase.CHECK_WIN;
            case CHECK_WIN:
                if(tree.getCursor().hasEnded()) {
                    if(tree.getCursor().getWinner() != Box.O) {
                        printCursor(tree);
                    }

                    if(tree.getCursor().getWinner() == Box.EMPTY) {
                        System.out.print(Lang.DRAW);
                    } else {
                        System.out.printf(Lang.WINNER, tree.getCursor().getWinner());
                    }

                    System.out.print(Lang.END);
                    System.exit(0);
                }

                phase = tree.getCursor().getCurrentTurn() == Box.X ? GamePhase.PLAYER_TURN : GamePhase.BOT_TURN;
                replay(tree);
                break;
            case BOT_TURN:
                tree.makeMove(getWinningPosition(tree.getCursor()));

                printCursor(tree);

                phase = GamePhase.CHECK_WIN;
                replay(tree);
                break;
        }

    }

    private static void printCursor(final GameTree tree) {
        System.out.println(tree.getCursor());

        tree.getCursor().setProbabilities();
        System.out.printf(Lang.PROBABILITY, "winning", round(tree.cursorProbability()));
        System.out.printf(Lang.PROBABILITY, "losing", round(tree.getCursor().getLoseProbability()));
        System.out.printf(Lang.PROBABILITY, "drawing", round(tree.getCursor().getDrawProbability()));
    }

    private static void showRoot() {
        System.out.print(Lang.DESCRIPTION);
        System.out.print(Lang.LABEL_BOARD);

        System.out.print(getStartingBoard());
    }

    private static void tryMove(final GameTree tree, final int position) {
        try {
            tree.makeMove(position);
        } catch(IllegalArgumentException ex) {
            System.out.print("\n" + ex.getMessage());
            replay(tree);
        }
    }

    private static int getWinningPosition(final GameBoardNode cursor) {
        GameBoardNode[] configurations = cursor.getConfigurations();
        int position = 0;

        for(int i = 0; i < configurations.length; i++) {
            if(configurations[i] != null) {
                if(configurations[position] == null) {
                    position = i;
                }

                GameBoardNode node = configurations[i];
                node.setProbabilities();

                if (configurations[position].getWinProbability() == node.getWinProbability()) {
                    position = configurations[position].getLoseProbability() > node.getLoseProbability() ? position : i;
                } else {
                    position = configurations[position].getWinProbability() < node.getWinProbability() ? position : i;
                }
            }
        }

        return position + 1;
    }

    private static double round(double value) {
        return BigDecimal.valueOf(value).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    private static String getStartingBoard() {
        String result = "";

        for(int i = 1; i < 10; i++) {
            result += "|" + (i);
            result += (i % 3) == 0 ? "|\n" : "";
        }

        return result;
    }
}
