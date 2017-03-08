package hw3;

import hw3.commands.ActionCommand;

/**
 * The {@code Node} class implements a node that references an {@code ActionCommand} object and the node before and after it (next and previous).
 *
 * @author Mankirat Gulati
 *    email: mankirat.gulati@stonybrook.edu
 *    Stony Brook ID: 111161128
 */

public class Node {
    // The command that is referenced by this node.
    private ActionCommand data;

    // The nodes that come after or before this node.
    private Node next, previous;

    /**
     * Initializes a new {@code Node} with the specified {@code data}.
     *
     * @param data The {@code ActionCommand} object that is being referenced by this node.
     * @throws IllegalArgumentException
     *     Indicates the {@code data} is null.
     */
    public Node(final ActionCommand data) {
        if(data == null) {
            throw new IllegalArgumentException(Lang.NULL_COMMAND);
        }

        this.data = data;
        this.next = null;
        this.previous = null;
    }

    /**
     * Returns the action command referenced by this node.
     *
     * @return The {@code ActionCommand} object referenced by this node.
     */
    public ActionCommand getData() {
        return this.data;
    }

    /**
     * Returns the node next (after) to this node.
     *
     * @return The {@code Node} object located after this node.
     */
    public Node getNext() {
        return this.next;
    }

    /**
     * Returns the node previous to this node.
     *
     * @return The {@code Node} object located before this node.
     */
    public Node getPrevious() {
        return this.previous;
    }

    /**
     * Sets the node located after this node.
     *
     * @param next The {@code Node} object that is being set after this node.
     */
    public void setNext(final Node next) {
        this.next = next;
    }

    /**
     * Sets the node located before this node.
     *
     * @param previous The {@code Node} object that is being set before this node.
     */
    public void setPrevious(final Node previous) {
        this.previous = previous;
    }
}