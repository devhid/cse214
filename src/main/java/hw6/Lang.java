package hw6;

/**
 * The {@code Lang} class contains all the messages used throughout the program.
 *
 * @author Mankirat Gulati
 *    email: mankirat.gulati@stonybrook.edu
 *    Stony Brook ID: 111161128
 */
public class Lang {
    static final String CLASS_NOT_FOUND = "Error. Could not find Bitter class.";
    static final String SAVE_SUCCESS = "Shutting down... Current program state saved to 'bitter.ser'";
    static final String SAVE_FAIL = "Error. Could not save current program state to 'bitter.ser'";

    static final String WELCOME_MSG = "Hello, and welcome to Bitter, a more tasteful social network. %s\n";
    static final String LOGIN_MENU = "\nYou are not logged in. \n\t%s \n\t%s \n\t%s\n";
    static final String USER_MENU = "\nMenu: \n\t%s \n\t%s \n\t%s \n\t%s \n\t%s \n\t%s \n\t%s\n";

    static final String INVALID_OPTION = "Invalid option. Please select a valid option.\n";
    static final String INPUT_OPTION = "\nPlease select an option: ";
    static final String INPUT_EMAIL = "\nPlease enter your email: ";
    static final String INPUT_PASSWORD = "Please enter your password: ";
    static final String INPUT_NAME = "Please enter your name: ";

    static final String EMPTY_EMAIL = "Your email field cannot be empty.\n";
    static final String EMPTY_NAME = "Your name field cannot be empty.\n";
    static final String USER_NOT_FOUND = "No user was found with that email.\n";
    static final String USER_ALREADY_EXISTS = "Error. User already exists with that email.\n";
    static final String REMOVE_NON_EXISTING_USER = "Error. Cannot remove non-existing user.\n";
    static final String INCORRECT_PASSWORD = "Incorrect password. Please try to login again.\n";
    static final String INVALID_PASSWORD = "Your password must contain at least 1 of each: uppercase letter, lowercase letter, number, symbol (!@#$%^&*).\n";
    static final String SIGNED_UP_SUCCESS = "Thanks for signing up! Welcome to Bitter!\n";

    static final String FOLLOW_TOGGLE = "\nPlease enter the email of the user you would like to %s: ";
    static final String NOT_FOLLOWING = "You were not following %s.\n";
    static final String ALREADY_FOLLOWED = "You are already following %s.\n";
    static final String CANNOT_FOLLOW_SELF = "You cannot follow yourself.\n";
    static final String FOLLOW_SUCCESS = "You are now following: %s.\n";
    static final String UNFOLLOW_SUCCESS = "You are no longer following: %s.\n";

    static final String ACCOUNT_CLOSED = "%s's account has been closed.\n";
}
