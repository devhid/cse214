package hw3;

/**
 * The {@code EmptyStackException} class extends the {@code Exception} and acts as a custom exception to handle stack operations for {@code ActionCommand} objects
 *
 * @author Mankirat Gulati
 *    email: mankirat.gulati@stonybrook.edu
 *    Stony Brook ID: 111161128
 */

public class EmptyStackException extends Exception {
    /**
     * Constructs a new {@code EndOfListException} with a specified {@code message}.
     *
     * @param message The error message that is passed through the {@code Exception} constructor.
     */
    public EmptyStackException(final String message) {
        super(message);
    }
}
