package hw3;

import hw3.commands.ActionCommand;

public class UndoRedoStack {

    public class Node {
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

    private int size;
    private Node top, bottom;

    public UndoRedoStack() {
        this.top = null;
        this.bottom = null;
    }

    public int getSize() {
        return this.size;
    }

    public Node getTop() {
        return this.top;
    }

    public Node getBottom() {
        return this.bottom;
    }

    public boolean isEmpty() {
        return top == null;
    }

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

        size++;
    }

    public ActionCommand pop() throws EmptyStackException {
        if(isEmpty()) {
            throw new EmptyStackException(Lang.EMPTY_STACK);
        }

        ActionCommand command = this.peek();

        top = top.getPrevious();
        size--;

        return command;
    }

    public ActionCommand peek() throws EmptyStackException {
        if(isEmpty()) {
            throw new EmptyStackException(Lang.EMPTY_STACK);
        }

        return top.getData();
    }

    public void clear() {
        top = null;
        size = 0;
    }
}
