package hw2;

/**
 * The <code>Lang</code> class contains all the messages used throughout the program.
 *
 * @author Mankirat Gulati
 *    email: mankirat.gulati@stonybrook.edu
 *    Stony Brook ID: 111161128
 */

public final class Lang {
    /* Menu Messages */
    public static final String DESCRIPTION = "Welcome to Star Duck Coffee, the number one coffee shop for flannel enthusiasts.";
    public static final String MENU = "Menu:\n\t %s\n\t %s\n\t %s\n\t %s\n";
    public static final String QUIT = "Only traitors go to Dunkin, see you soon!";

    /* Input Messages */
    public static final String INPUT_OPTION = "\nPlease select an option: ";
    public static final String INPUT_DRINK = "Please enter drink name: ";
    public static final String INPUT_SPECIAL_INSTRUCTION = "Please enter special requests: ";
    public static final String INPUT_PRICE = "Please enter the price: ";
    public static final String INPUT_BARISTA = "Please select a barista (1 or 2): ";
    public static final String INPUT_LOCATION = "Where should the order be added?\n" +
            "Options: F - Front of List, B - Back of List, A - After Cursor, S - After Similar Order.\n" +
            "Please select a location: ";
    public static final String INPUT_CURSOR = "Please enter a cursor: (1 or 2): ";
    public static final String INPUT_CURSOR_OPTION = "Cursor options: " +
            "F - Forward, B - Backward, H - To Head, T - To Tail, R - Remove, C - Cut, P - Paste.\n" +
            "Please select a cursor option: ";

    /* Exception Messages */
    public static final String END_OF_LIST = "Operation failed. You are at the end of the list.";
    public static final String BEGINNING_OF_LIST = "Operation failed. You are at the beginning of the list.";
    public static final String EMPTY_LIST = "Operation failed. List is empty.";

    public static final String INVALID_OPTION = "Operation failed. Option is invalid.";
    public static final String INVALID_ORDER = "Operation failed. Order is invalid.";
    public static final String INVALID_BARISTA = "Operation failed. Barista is invalid.";
    public static final String INVALID_LOCATION = "Operation failed. Location is invalid.";
    public static final String INVALID_CURSOR = "Operation failed. Cursor is invalid.";
    public static final String INVALID_CURSOR_OPTION = "Operation failed. Cursor option is invalid.";
    public static final String INVALID_PASTE = "Operation failed. There is nothing to paste.";
    public static final String INVALID_NUMBER = "Operation failed. Number is invalid.";

    /* Success Messages */
    public static final String SUCCESS_ORDER = "\nYou have successfully ordered: %s\n";
    public static final String SUCCESS_CURSOR_FORWARD = "\nCursor has moved forward.\n";
    public static final String SUCCESS_CURSOR_BACKWARD = "\nCursor has moved backward.\n";
    public static final String SUCCESS_CURSOR_HEAD = "\nCursor has moved to the head.\n";
    public static final String SUCCESS_CURSOR_TAIL = "\nCursor has moved to the tail.\n";
    public static final String SUCCESS_CURSOR_REMOVE = "Order at cursor has been removed.";
    public static final String SUCCESS_CURSOR_CUT = "\n%s is now in the clipboard.\n";
    public static final String SUCCESS_CURSOR_PASTE = "\n%s has been pasted in list %d cursor.\n";

}
