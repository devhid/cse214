package hw3;

import hw3.commands.ActionCommand;

public class UndoRedoStack {

    private class Node {
        private ActionCommand data;
        private Node next, previous;

        private Node(final ActionCommand data) {
            this.data = data;
            this.next = null;
            this.previous = null;
        }

        public ActionCommand getData() {
            return this.data;
        }

        public Node getNext() {
            return this.next;
        }

        public Node getPrevious() {
            return this.previous;
        }

        public void setNext(final Node next) {
            this.next = next;
        }

        public void setPrevious(final Node previous) {
            this.previous = previous;
        }
    }

    // top references the tail of the doubly-linked list.
    // bottom references the head of the double-linked list.

    private Node top, bottom;
    private int size;

    public UndoRedoStack() {
        this.top = null;
        this.bottom = null;
    }

    public Node getTop() {
        return this.top;
    }

    public Node getBottom() {
        return this.bottom;
    }

    public int getSize() {
        return this.size;
    }

    public void push(final ActionCommand command) {
        Node node = new Node(command);

        if(top == null) {
            top = new Node(command);
            bottom = top;
        } else {
            top.setNext(node);
            node.setPrevious(top);
            top = node;
        }

        size++;
    }

    public ActionCommand pop() throws EmptyStackException {
        if(isEmpty()) {
            throw new EmptyStackException("The stack is empty.");
        }

        ActionCommand command = this.peek();

        top = top.getPrevious();
        top.setNext(null);

        size--;
        return command;
    }

    public ActionCommand peek() throws EmptyStackException {
        if(top == null) {
            throw new EmptyStackException("The stack is empty.");
        }

        return top.getData();
    }

    public boolean isEmpty() {
        return top == null;
    }
}
