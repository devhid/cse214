package hw2;

/**
 * The <code>EndOfListException</code> class extends the <code>Exception</code> and acts as a custom exception to handle <code>OrderList</code> operations.
 *
 * @author Mankirat Gulati
 *    email: mankirat.gulati@stonybrook.edu
 *    Stony Brook ID: 111161128
 */
public class EndOfListException extends Exception {
    /**
     * Constructs a new <code>EndOfListException</code> with a specified <code>message</code>.
     *
     * @param message The error message that is passed through the <code>Exception</code> constructor.
     */
    public EndOfListException(final String message) {
        super(message);
    }
}
