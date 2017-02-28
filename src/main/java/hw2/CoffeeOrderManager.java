package hw2;

import java.util.Scanner;

/**
 * The <code>CoffeeOrderManager</code> class is a driver class that handles I/O for menu operations.
 *
 * @author Mankirat Gulati
 *    email: mankirat.gulati@stonybrook.edu
 *    Stony Brook ID: 111161128
 */

public class CoffeeOrderManager {
    // Handles all input from the user.
    private final Scanner input;

    // Array of order lists (representing baristas)
    private final OrderList[] orderLists;

    // Keep track of what is cut.
    private Order cut;

    // Keep track of the current barista
    private int currentBarista;

    private CoffeeOrderManager() {
        this.input = new Scanner(System.in);
        this.orderLists = new OrderList[] {new OrderList(), new OrderList()};

        this.currentBarista = 0;
    }

    public static void main(String[] args) {
        new CoffeeOrderManager().init();
    }

    private void init() {
        System.out.println(Lang.DESCRIPTION);
        System.out.printf(Lang.MENU, "O) Order", "P) Print Order Lists",
                "C) Cursor Options", "Q) Quit");

        reselect();
    }

    // Used to continuously ask for input.
    private void reselect() {
        System.out.print(Lang.INPUT_OPTION);

        try {
            this.selectOption();
        } catch (NumberFormatException ex) {
            System.out.print("\n" + Lang.INVALID_NUMBER);
            reselect();
        } catch (EndOfListException | IllegalArgumentException ex) {
            System.out.print("\n" + ex.getMessage());
            reselect();
        }
    }

    // Handles the I/O for all menu operations.
    private void selectOption() throws EndOfListException {
        String option = input.nextLine().trim().toUpperCase();

        switch(option.isEmpty() ? '1' : option.charAt(0)) {
            case 'O':
                System.out.print(Lang.INPUT_DRINK);
                String drinkName = input.nextLine();

                System.out.print(Lang.INPUT_SPECIAL_INSTRUCTION);
                String specialInstruction = input.nextLine();

                System.out.print(Lang.INPUT_PRICE);
                double price = Double.parseDouble(input.nextLine());

                System.out.print(Lang.INPUT_BARISTA);
                int barista = Integer.parseInt(input.nextLine());

                System.out.print(Lang.INPUT_LOCATION);
                char location = input.nextLine().trim().toUpperCase().charAt(0);

                Order order = new Order(drinkName, specialInstruction, price);
                this.placeOrder(order, location, barista);

                break;
            case 'P':
                for(int x = 0; x < 2; x++) {
                    System.out.println("\nBarista " + (x + 1) + ": ");
                    System.out.printf("%-26s |%-64s |%s\n",
                            "Order Name", "Special Instructions", "Price");
                    System.out.print("------------------------------" +
                            "---------------------------------------" +
                            "------------------------------\n");

                    OrderList list = orderLists[x];
                    OrderListNode head = list.getHead();

                    while(head != null) {
                        String cursorOffset = (head == list.getCursor()) ? "->%-24s " : "%-26s ";

                        System.out.printf(cursorOffset + "|%-64s | %.2f\n",
                                head.getData().getName(),
                                head.getData().getSpecialInstruction(),
                                head.getData().getPrice());

                        head = head.getNext();
                    }

                    System.out.println();
                }

                reselect();
                break;
            case 'C':
                System.out.print(Lang.INPUT_CURSOR);

                int cursor = Integer.parseInt(input.nextLine());
                this.selectCursor(cursor);

                System.out.print(Lang.INPUT_CURSOR_OPTION);

                String nextLine = input.nextLine();
                char cursorOption = (nextLine.isEmpty()) ? '1' : nextLine.trim().toUpperCase().charAt(0);

                this.selectCursorOption(cursorOption);
                break;
            case 'Q':
                System.out.println(Lang.QUIT);
                System.exit(0);
                break;
            default:
                throw new IllegalArgumentException(Lang.INVALID_OPTION);
        }
    }

    private void placeOrder(final Order order, final char location, final int barista) throws EndOfListException {
        if(barista != 1 && barista != 2) {
            throw new IllegalArgumentException(Lang.INVALID_BARISTA);
        }

        OrderList list = orderLists[barista - 1];
        switch (location) {
            case 'F': list.appendToHead(order); break;
            case 'B': list.appendToTail(order); break;
            case 'A': list.insertAfterCursor(order); break;
            case 'S': list.insertAfterSimilarOrder(order); break;
            default: throw new IllegalArgumentException(Lang.INVALID_LOCATION);
        }

        System.out.printf(Lang.SUCCESS_ORDER, order.getName());
        reselect();
    }

    private void selectCursor(final int cursor) {
        if(cursor != 1 && cursor != 2) {
            throw new IllegalArgumentException(Lang.INVALID_CURSOR);
        }

        this.currentBarista = cursor - 1;
    }

    private void selectCursorOption(final char option) throws EndOfListException {
        OrderList list = orderLists[currentBarista];

        switch(option) {
            case 'F':
                list.cursorForward();
                System.out.print(Lang.SUCCESS_CURSOR_FORWARD);
                break;
            case 'B':
                list.cursorBackward();
                System.out.print(Lang.SUCCESS_CURSOR_BACKWARD);
                break;
            case 'H':
                list.resetCursorToHead();
                System.out.print(Lang.SUCCESS_CURSOR_HEAD);
                break;
            case 'T':
                list.resetCursorToTail();
                System.out.print(Lang.SUCCESS_CURSOR_TAIL);
                break;
            case 'R':
                list.removeCursor();
                System.out.print(Lang.SUCCESS_CURSOR_REMOVE);
                break;
            case 'C':
                this.cut(list);
                System.out.printf(Lang.SUCCESS_CURSOR_CUT, cut.getName());
                break;
            case 'P':
                this.paste(list);
                System.out.printf(Lang.SUCCESS_CURSOR_PASTE, list.getCursorOrder().getName(), currentBarista + 1);
                break;
            default: throw new IllegalArgumentException(Lang.INVALID_CURSOR_OPTION);
        }

        reselect();
    }

    private void cut(final OrderList list) throws EndOfListException {
        this.cut = list.removeCursor();
    }

    private void paste(final OrderList list) {
        if(cut == null) {
            throw new IllegalArgumentException(Lang.INVALID_PASTE);
        }

        list.insertAfterCursor(cut);
        cut = null;
    }
}
