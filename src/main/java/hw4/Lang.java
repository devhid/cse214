package hw4;

/**
 * The {@code Lang} class contains all the messages used throughout the program.
 *
 * @author Mankirat Gulati
 *    email: mankirat.gulati@stonybrook.edu
 *    Stony Brook ID: 111161128
 */
public final class Lang {
    /* Misc */
    public static final String DESCRIPTION = "\t\tWelcome to Office Hours Simulation\n";
    public static final String LABEL_BEGIN_SIMULATION = "\nBegin Simulation:\n\n";
    public static final String END_SIMULATION = "\nWe hope you have a pleasant visit in your next actual office hours, good bye!";

    /* File */
    public static final String INPUT_FILE = "Please enter the file name: ";
    public static final String INVALID_FILE = "Error. File '%s' was not found.\n";
    public static final String SUCCESS_FILE = "File %s loaded.\n";
    public static final String ASK_TO_RERUN = "\nPlease fix the error and restart the program.";

    /* Properties */
    public static final String PROPERTIES_LABEL_FORMAT = "\n%-8s |%s\n";
    public static final String PROPERTIES_DIVIDER = "----------------------\n";
    public static final String PROPERTIES_DATA_FORMAT = "%-8d |%.1f\n";
    public static final String PROPERTY_NUM_TAS = "Number of TAs: %d\n";
    public static final String PROPERTY_NUM_CUPS = "Coffee Cups: %d\n";
    public static final String PROPERTY_BASE_TIME_INTERVAL = "Base Time Interval: %d - %d minutes\n";
    public static final String PROPERTY_TIME = "Time: %d minutes\n";

    /* Time Steps */
    public static final String TIME_STEP = "Time Step %d\n";
    public static final String TIME_STEP_DIVIDER = "----------------------------------------------------\n";

    /* Students */
    public static final String STUDENT_ARRIVED = "Student %d has arrived for %d requiring %d minutes.\n";
    public static final String NO_STUDENT_ARRIVED = "No students have arrived for %d.\n";
    public static final String STUDENT_FINISHED = "Student %d has finished being helped with CSE %d in %d minutes since arrival.\n";

    /* Helpers */
    public static final String PROFESSOR_BUSY = "Professor is helping Student %d, %d minute(s) remaining.\n";
    public static final String PROFESSOR_WAITING = "Professor is waiting for the next student to arrive.\n";
    public static final String TA_BUSY = "TA %d is helping Student %d, %d minute(s) remaining.\n";
    public static final String TA_FREE = "TA %d is waiting for the next student to arrive.\n";

    /* Queue */
    public static final String LABEL_STUDENT_QUEUE = "Student Queue:\n";
    public static final String QUEUE_CATEGORY_FORMAT = "%-4s |%-10s |%-16s |%s\n";
    public static final String QUEUE_DIVIDER = "----------------------------------------------------\n";
    public static final String QUEUE_DATA_FORMAT = "%-4d |%-10d |%-16d |%d\n";

    /* Statistics */
    public static final String STATISTICS_LABEL = "Statistics:\n";
    public static final String STATISTICS_FORMAT = "%-8s |%-17s |%s\n";
    public static final String STATISTICS_DIVIDER = "----------------------------------------------\n";
    public static final String STATISTICS_TOTAL = "%-8s |%-17d |%d minutes\n";
    public static final String STATISTICS_DATA = "%-8d |%-17d |%d minutes\n";

    /* Exceptions */
    public static final String INVALID_PROBABILITY = "Error. Probabilities must be within the range: (0,1].\n";
    public static final String INVALID_COURSE_NUMBER = "Error. Course number, '%d' is invalid.\n";
    public static final String INVALID_ARRIVAL_TIME = "Error. Arrival time, '%d' is invalid.\n";
    public static final String INVALID_NUM_COURSES = "Error. Number of courses must be greater than 0.\n";
    public static final String INVALID_SIMULATION_TIME = "Error. Simulation time must be greater than 0.\n";
    public static final String INVALID_MIN_TIME = "Error. Min time must be greater than 0.\n";
    public static final String INVALID_MAX_TIME = "Error. Max time must be greater than 0.\n";
    public static final String MIN_TIME_LARGER = "Error. The min time cannot be greater than the max time.\n";
    public static final String INVALID_NUM_TAS = "Error. The number of TAs cannot be negative.\n";
    public static final String INVALID_NUM_CUPS = "Error. The number of coffee cups cannot be negative.\n";
    public static final String INVALID_NUMBER = "Error. File contains empty property value(s).\n";
    public static final String UNEQUAL_NUM_COURSES = "Error. Number of courses must equal course numbers and arrival probability length.\n";
    public static final String DUPLICATE_COURSE_NUMBERS = "Error. File contains duplicate course numbers.\n";
}
