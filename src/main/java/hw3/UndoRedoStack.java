package hw3;

import hw3.commands.ActionCommand;

/**
 * The <code>UndoRedoStack</code> class is a custom {@code Stack} implemented using a doubly-linked list.
 * The top of the stack references the tail of the doubly-linked list,
 * and the bottom of the stack references the head of the doubly-linked list.
 *
 *
 * @author Mankirat Gulati
 *    email: mankirat.gulati@stonybrook.edu
 *    Stony Brook ID: 111161128
 */

public class UndoRedoStack {
    // Nodes to keep a reference of top and bottom of stack.
    private Node top, bottom;

    /**
     * Initializes {@code top} and {@code bottom} to {@code null}.
     */
    public UndoRedoStack() {
        this.top = null;
        this.bottom = null;
    }

    /**
     * Returns the top of the stack.
     *
     * @return The {@code Node} referencing the top of the stack.
     */
    public Node getTop() {
        return this.top;
    }

    /**
     * Returns the bottom of the stack.
     *
     * @return The {@code Node} referencing the bottom of the stack.
     */
    public Node getBottom() {
        return this.bottom;
    }

    /**
     * Checks if the stack is empty.
     *
     * @return {@code true} if the stack is empty, {@code false} otherwise.
     */
    public boolean isEmpty() {
        return top == null;
    }

    /**
     * Adds an action command to the top of the stack.
     *
     * @param command The action command being pushed to the top of the stack.
     */
    public void push(final ActionCommand command) {
        Node node = new Node(command);

        if(isEmpty()) {
            top = node;
            bottom = node;
        } else {
            node.setPrevious(top);
            top.setNext(node);

            top = node;
        }
    }

    /**
     * Removes and returns the action command at the top of the stack.
     *
     * @return The {@code ActionCommand} located at the of the stack.
     * @throws EmptyStackException
     *     Indicates the stack is empty, therefore no elements can further be removed.
     */
    public ActionCommand pop() throws EmptyStackException {
        if(isEmpty()) {
            throw new EmptyStackException(Lang.EMPTY_STACK);
        }

        ActionCommand command = this.peek();
        top = top.getPrevious();

        return command;
    }

    /**
     * Returns the action command at the top of the stack.
     *
     * @return Returns the {@code ActionCommand} located at the top of stack.
     * @throws EmptyStackException
     *     Indicates the stack is empty, therefore there is no element to "peek" from.
     */
    public ActionCommand peek() throws EmptyStackException {
        if(isEmpty()) {
            throw new EmptyStackException(Lang.EMPTY_STACK);
        }

        return top.getData();
    }

    /**
     * Clears the stack.
     *
     * By setting {@code top} to {@code null}, the doubly-linked list is broken and the other elements
     * are collected as garbage by the JVM.
     */

    public void clear() {
        top = null;
    }
}
