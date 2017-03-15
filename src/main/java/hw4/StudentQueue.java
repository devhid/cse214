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
}
