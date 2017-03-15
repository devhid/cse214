package hw4;

import java.util.Comparator;
import java.util.PriorityQueue;

public class StudentQueue<E> extends PriorityQueue<E> {
    public StudentQueue(int capacity, Comparator<E> comparator) {
        super(capacity, comparator);
    }

    public StudentQueue(PriorityQueue<E> queue) {
        super(queue);
    }

    @Override
    public int size() {
        return super.size();
    }

    @Override
    public boolean isEmpty() {
        return super.isEmpty();
    }

    @Override
    public E peek() {
        return super.peek();
    }

    public void enqueue(final E e) {
        add(e);
    }

    public E dequeue() {
        return poll();
    }

    /*private Node front, rear;
    private int size;

    public StudentQueue() {
        this.front = null;
        this.rear = null;
    }

    public Node getFront() {
        return this.front;
    }

    public Node getRear() {
        return this.rear;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public void enqueue(final Student student) {
        if(student == null) {
            throw new IllegalArgumentException("Student cannot be null.");
        }

        Node node = new Node(student);

        if(isEmpty()) {
            front = node;
            rear = node;
        } else {
            Node current = front;

            while(current != null) {
                if(current.getData().getCourse().getCourseNumber() < student.getCourse().getCourseNumber()) {
                    node.setPrevious(current.getPrevious());
                    node.setNext(current);

                    if(current != front) {
                        current.getPrevious().setNext(node);
                    }

                    current.setPrevious(node);
                    break;
                }

                current = current.getNext();
            }
        }

        size++;
    }

    public Student dequeue() {
        if(isEmpty()) {
            return null;
        }

        Student student = this.peek();

        if(front.getNext() != null) {
            front = front.getNext();
            front.setPrevious(null);
        } else {
            front = null;
        }

        size--;
        return student;
    }

    public Student peek() {
        if(isEmpty()) {
            return null;
        }

        return front.getData();
    }*/
}
