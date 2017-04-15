package hw6;

public class Lang {
    public static final String CLASS_NOT_FOUND = "Error. Could not find Bitter class.";
    public static final String SAVE_SUCCESS = "Shutting down... Current program state saved to 'Bitter.ser'";
    public static final String SAVE_FAIL = "Error. Could not save current program state to 'bitter.ser'";

    public static final String WELCOME_MSG = "Hello, and welcome to Bitter, a more tasteful social network. %s\n";
    public static final String LOGIN_MENU = "\nYou are not logged in. \n\t%s \n\t%s \n\t%s\n";
    public static final String USER_MENU = "\nMenu: \n\t%s \n\t%s \n\t%s \n\t%s \n\t%s \n\t%s \n\t%s\n";

    public static final String INVALID_OPTION = "Invalid option. Please select a valid option.";
    public static final String INPUT_OPTION = "\nPlease select an option: ";
    public static final String INPUT_EMAIL = "\nPlease enter your email: ";
    public static final String INPUT_PASSWORD = "Please enter your password: ";
    public static final String INPUT_NAME = "Please enter your name: ";

    public static final String USER_NOT_FOUND = "No user was found with that email.\n";
    public static final String USER_ALREADY_EXISTS = "Error. User already exists with that email.\n";
    public static final String REMOVE_NON_EXISTING_USER = "Error. Cannot remove non-existing user.\n";
    public static final String INCORRECT_PASSWORD = "Incorrect password. Please try to login again.\n";
    public static final String INVALID_PASSWORD = "Your password must contain at least 1 of each: uppercase letter, lowercase letter, number, symbol (!@#$%^&*).\n";
    public static final String SIGNED_UP_SUCCESS = "Thanks for signing up! Welcome to Bitter!\n";

    public static final String FOLLOW_TOGGLE = "\nPlease enter the email of the user you would like to %s: ";
    public static final String NOT_FOLLOWING = "You were not following %s.\n";
    public static final String ALREADY_FOLLOWED = "You are already following %s.\n";
    public static final String CANNOT_FOLLOW_SELF = "You cannot follow yourself.\n";
    public static final String FOLLOW_SUCCESS = "You are now following: %s.\n";
    public static final String UNFOLLOW_SUCCESS = "You are no longer following: %s.\n";

    public static final String ACCOUNT_CLOSED = "%s's account has been closed.";
}
