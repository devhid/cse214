package hw3;

import hw3.commands.ActionCommand;

public class UndoRedoStack {

    private class Node {
        private ActionCommand data;
        private Node next;

        private Node(final ActionCommand data, final Node next) {
            this.data = data;
            this.next = next;
        }
    }

    private Node top;
    private int size;

    public UndoRedoStack() {
        this.top = null;
    }

    public void push(final ActionCommand command) {
        top = new Node(command, top);
        size++;
    }

    public ActionCommand pop() throws EmptyStackException {
        if(isEmpty()) {
            throw new EmptyStackException("The stack is empty.");
        }

        ActionCommand command = this.peek();

        top = top.next;
        size--;

        return command;
    }

    public ActionCommand peek() throws EmptyStackException {
        return top.data;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public int getSize() {
        return this.size;
    }
}
