package hw3;

/**
 * The {@code Lang} class contains all the messages used throughout the program.
 *
 * @author Mankirat Gulati
 *    email: mankirat.gulati@stonybrook.edu
 *    Stony Brook ID: 111161128
 */

public final class Lang {
    /* Menu Messages */
    public static final String DESCRIPTION = "Welcome to Slideshow Manager";
    public static final String MENU = "Menu:\n\t %s\n\t %s\n\t %s\n\t %s\n\t %s\n\t %s\n\t %s\n\t %s\n";
    public static final String QUIT = "Thank you, and goodbye!";

    /* Input Messages */
    public static final String INPUT_OPTION = "\nPlease select an option: ";
    public static final String INPUT_PHOTO_NAME = "Please enter the photo name: ";
    public static final String INPUT_POSITION = "Please enter the %s: ";

    /* Labels */
    public static final String LABEL_ADD_PHOTO = "\nAdd Photo:";
    public static final String LABEL_REMOVE_PHOTO = "\nRemove Photo:";
    public static final String LABEL_SWAP_PHOTOS = "\nSwap Photos:";
    public static final String LABEL_MOVE_PHOTOS = "\nMove Photos:";
    public static final String LABEL_SLIDESHOW = "\nSlideshow:";
    public static final String LABEL_UNDO_STACK = "\nUndo Stack:\n" +
            "---------------------------------------";
    public static final String LABEL_REDO_STACK = "\nRedo Stack:\n" +
            "---------------------------------------";

    /* Error Messages */
    public static final String INVALID_OPTION = "Operation failed. Option is invalid.";
    public static final String INVALID_POSITION = "Operation failed. Position is invalid. ";
    public static final String INVALID_NUMBER = "Operation failed. Number is invalid.";
    public static final String CANNOT_UNDO = "Operation failed. Nothing left to undo.";
    public static final String CANNOT_REDO = "Operation failed. Nothing left to redo.";
    public static final String EMPTY_STACK = "Operation failed. Stack is empty.";
    public static final String NULL_COMMAND = "Operation failed. Command is null.";


    /* Success Messages */
    public static final String SUCCESS_ADD_PHOTO = "\nAdded '%s' to position: '%d'.\n";
    public static final String SUCCESS_REMOVE_PHOTO = "\nRemoved '%s' from position: '%d'.\n";
    public static final String SUCCESS_SWAP_PHOTOS = "\nSwapped image at position: '%d' and position: '%d'.\n";
    public static final String SUCCESS_MOVE_PHOTOS = "\nMoved image from position: '%d' to position: '%d'.\n";
    public static final String SUCCESS_UNDO = "%s";
    public static final String SUCCESS_REDO = "%s";
}
