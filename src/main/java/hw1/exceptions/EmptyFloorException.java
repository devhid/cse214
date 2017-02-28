package hw1.exceptions;

/**
 * The <code>EmptyFloorException</code> is a custom <code>Exception</code> that indicates when a <code>Floor</code> is empty.
 *
 * @author Mankirat Gulati
 *    email: mankirat.gulati@stonybrook.edu
 *    Stony Brook ID: 111161128
 */

public class EmptyFloorException extends Exception {
    /**
     * Constructs a new <code>EmptyFloorException</code> with a specified <code>message</code>.
     *
     * @param message The error message that is passed through the Exception constructor.
     */
    public EmptyFloorException(String message) {
        super(message);
    }
}