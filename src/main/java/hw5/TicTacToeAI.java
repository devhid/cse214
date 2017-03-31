package hw5;

import java.util.Scanner;

public class TicTacToeAI {
    private static final Scanner input = new Scanner(System.in);
    private static int turn = 0;

    public static void main(String[] args) {
        GameTree tree = new GameTree();
        playGame(tree);
        //GameTree.buildTree(tree.getRoot(), tree.getRoot().getCurrentTurn());
        //GameBoardNode[] leaves = GameTree.getLeaves(tree.getRoot(), 0);
        //for(GameBoardNode node: leaves) {
        //    if(node != null) {
        //        System.out.println(node.toString() + "\n");
        //    }
        //}

        //System.out.println(GameTree.getLeaves(tree.getRoot(), 0));
        //tree = new GameTree();
        //TicTacToeAI.playGame(tree);

        //root.buildConfigurations();

        /*for(int i = 0; i < config.length; i++) {
            if(config[i] != null) {
                config[i].buildConfigurations();
                System.out.print(config[i].toString() + "\n");
            }
        }


        for(int i = 0; i < config.length; i++) {
            for(int j = 0; j < config.length; j++) {
                if(config[i] != null) {
                    if (config[i].getConfigurations()[j] != null) {
                        System.out.println(config[i].getConfigurations()[j].toString());
                    }
                }
            }

        }*/

    }

    public static void playGame(final GameTree tree) {
        int i = 0;

        GameTree.buildTree(tree.getRoot(), tree.getRoot().getCurrentTurn());
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
            if(result != null && result != Box.EMPTY) {
                System.out.println(result.toString() + " has won.");
                tree.getCursor().setHasEnded(true);
            }

            GameBoard board = tree.getCursor().getBoard();

            // If player picked any of the 4 corners, let AI pick middle spot.
            if(!tree.getCursor().hasEnded()) {
                if (choseCorner(spot)) {
                    if(board.getBox(4) == Box.EMPTY) {
                        tree.makeMove(5);
                    } else {
                        switch(spot) {
                            case 3: tree.makeMove(7); break;
                            case 7: tree.makeMove(3); break;
                        }
                    }
                } else if (choseMiddle(spot)) {
                    // If player picked the middle, let AI pick any corner (we'll pick top left)
                    tree.makeMove(1);
                } else { // If player picked position 2, 4, 6, 8. let AI pick any opposite corner.
                    Box[] grid = tree.getCursor().getBoard().getGrid();
                    switch (spot) {
                        case 2:
                            if (grid[8] == Box.EMPTY) {
                                tree.makeMove(9);
                            }
                            break;
                        case 4:
                            if (grid[2] == Box.EMPTY) {
                                tree.makeMove(3);
                            }
                            break;
                        case 6:
                            if (grid[6] == Box.EMPTY) {
                                tree.makeMove(7);
                            }
                            break;
                        case 8:
                            if (grid[0] == Box.EMPTY) {
                                tree.makeMove(1);
                            }
                            break;
                        default:
                            break;
                    }
                }
            }

            System.out.println(tree.getCursor().toString());
            GameTree.getLeaves(tree.getCursor(), 0);
            System.out.println("The probability of winning is: " + tree.cursorProbability());

            if(board.getSize() == 9) {
                tree.getCursor().setHasEnded(true);
            } else {
                if(!tree.getCursor().hasEnded()) {
                    GameTree.buildTree(tree.getCursor(), tree.getCursor().getCurrentTurn());
                }
            }
        }

    }

    public static boolean choseEdge(final int position) {
        int actual = position - 1;
        return actual == 1 || actual == 3 || actual == 5 || actual == 7;
    }

    public static boolean choseCorner(final int position) {
        int actual = position - 1;
        return actual == 0 || actual == 2 || actual == 6 || actual == 8;
    }

    public static boolean choseMiddle(final int position) {
        return position - 1 == 4;
    }

    public static void makeBotMove(final int player, final GameBoard board, final GameTree tree) {
        if(choseEdge(player)) {
            switch(turn) {
                case 1:
                    switch(player) {
                        case 2:
                            if(board.isEmpty(7)) {
                                tree.makeMove(7);
                            } else {
                                tree.makeMove(9);
                            }
                            break;
                        case 4:
                            if(board.isEmpty(3)) {
                                tree.makeMove(3);
                            } else {
                                tree.makeMove(9);
                            }
                            break;
                        case 6:
                            if(board.isEmpty(1)) {
                                tree.makeMove(1);
                            } else {
                                tree.makeMove(7);
                            }
                            break;
                        case 8:
                            if(board.isEmpty(1)) {
                                tree.makeMove(1);
                            } else {
                                tree.makeMove(3);
                            }
                            break;
                    }
                    break;
                case 2:
            }

        }
    }

}
