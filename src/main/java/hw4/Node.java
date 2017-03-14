package hw4;

public class Node {
    private Student data;
    private Node next, previous;

    public Node(final Student data) {
        if(data == null) {
            throw new IllegalArgumentException("Data is null.");
        }

        this.data = data;
        this.next = null;
        this.previous = null;
    }

    public void setData(final Student data) {
        this.data = data;
    }

    public void setNext(final Node next) {
        this.next = next;
    }

    public void setPrevious(final Node previous) {
        this.previous = previous;
    }

    public Student getData() {
        return this.data;
    }

    public Node getNext() {
        return this.next;
    }

    public Node getPrevious() {
        return this.previous;
    }
}
