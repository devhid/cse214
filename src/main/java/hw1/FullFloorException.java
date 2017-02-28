package hw1;

/**
 * The <code>FullFloorException</code> is a custom <code>Exception</code> that indicates when a <code>Floor</code> is full.
 *
 * @author Mankirat Gulati
 *    email: mankirat.gulati@stonybrook.edu
 *    Stony Brook ID: 111161128
 */

public class FullFloorException extends Exception {
    /**
     * Constructs a new <code>EmptyFloorException</code> with a specified <code>message</code>.
     *
     * @param message The error message that is passed through the <code>Exception</code> constructor.
     */
    public FullFloorException(String message) {
        super(message);
    }
}
