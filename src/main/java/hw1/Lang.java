package hw1;

/**
 * The <code>Lang</code> class contains all the messages used throughout the program.
 *
 * @author Mankirat Gulati
 *    email: mankirat.gulati@stonybrook.edu
 *    Stony Brook ID: 111161128
 */

public final class Lang {

    /* Menu Messages */
    public static final String DESCRIPTION = "Welcome to Rockstar Rez, the second worst housing management System at SBU.";
    public static final String MENU = "Menu:\n\t %s\n\t %s\n\t %s\n\t %s\n\t %s\n\t %s\n\t %s\n\t %s\n\t %s\n";
    public static final String QUIT = "\nSee you later, alligator!";

    /* Input Messages */
    public static final String INPUT_OPTION = "\nPlease select an option: ";
    public static final String INPUT_NAME = "Please enter a name: ";
    public static final String INPUT_ID = "Please enter an ID: ";
    public static final String INPUT_FLOOR = "Please enter %s floor: ";
    public static final String INPUT_ROOM = "Please enter %s room: ";

    /* Label Messages */
    public static final String LABEL_ADD_STUDENT = "\nAdd Student:";
    public static final String LABEL_REMOVE_STUDENT = "\nRemove Student:";
    public static final String LABEL_SWAP_STUDENTS = "\nSwap Students:";
    public static final String LABEL_MOVE_STUDENT = "\nMove Students:";
    public static final String LABEL_COPY_FLOOR = "\nCopy Floor: ";
    public static final String LABEL_WRITE_UP_STUDENT = "\nWrite Up Student:";
    public static final String LABEL_SELECT_FLOOR = "\nSelect Floor:";

    /* Error Messages */
    public static final String INVALID_OPERATION = "\nInvalid operation.";
    public static final String INVALID_NAME = "Invalid name. Please enter a name: ";
    public static final String INVALID_ID = "Invalid ID. Please enter an ID: ";
    public static final String INVALID_ROOM = "Invalid spot. Please enter %s room: ";
    public static final String INVALID_FLOOR = "Invalid floor. Please enter %s floor: ";

    /* Exception Messages */
    public static final String EXCEPTION_FULL_FLOOR = "Error. The floor is too full.";
    public static final String EXCEPTION_EMPTY_FLOOR = "Error. The floor is empty.";
    public static final String EXCEPTION_INVALID_POSITION = "Invalid position. Position is negative or too high.";
    public static final String EXCEPTION_INVALID_FLOOR = "Error. Floor number is invalid.";
    public static final String EXCEPTION_ALREADY_HAS_ID = "Error. Student already exists with this ID";
    public static final String EXCEPTION_NULL_STUDENT = "Error. No student exists with this name.";

    /* Success Messages */
    public static final String SUCCESS_ADD_STUDENT= "\n%s added to Floor %d Room %d.\n";
    public static final String SUCCESS_REMOVE_STUDENT = "\n%s removed from Floor %d.\n";
    public static final String SUCCESS_SWAP_STUDENTS = "\n%s and %s have swapped.\n";
    public static final String SUCCESS_MOVE_STUDENT = "\n%s has moved to Floor %d Room %d.\n";
    public static final String SUCCESS_COPY_FLOOR = "\nFloor %d copied to Floor %d.\n";
    public static final String SUCCESS_WRITE_UP_STUDENT = "\n%s has %d writeup(s).\n";
    public static final String SUCCESS_WRITE_UP_STUDENT_REMOVED= "\n%s removed from Floor %d.\n";
    public static final String SUCCESS_SELECT_FLOOR = "\nFloor %d selected.\n";
}
