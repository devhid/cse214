package hw4;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * The {@code StudentQueue<E>} class extends a generic PriorityQueue<E> that enqueues and dequeues
 * students in a FIFO system and according to priority.
 *
 * @author Mankirat Gulati
 *    email: mankirat.gulati@stonybrook.edu
 *    Stony Brook ID: 111161128
 */
public class StudentQueue<E> extends PriorityQueue<E> {
    /**
     * Calls the parent constructor taking in a {@code capacity} and a {@code comparator}.
     *
     * @param capacity The maximum number of elements this {@code StudentQueue} can hold.
     * @param comparator A custom comparator that enqueues students based on a specified sorting method.
     */
    public StudentQueue(int capacity, Comparator<E> comparator) {
        super(capacity, comparator);
    }

    /**
     * Calls the parent instructor taking in another {@code PriorityQueue<E>}. Instantiating this
     * {@code StudentQueue} constructor will make a queue equivalent to the specified {@code queue}.
     *
     * @param queue The {@code PriorityQueue<E>} that is being used to create this {@code StudentQueue}.
     */
    public StudentQueue(PriorityQueue<E> queue) {
        super(queue);
    }

    /**
     * Default constructor that calls parent {@code PriorityQueue<E>} constructor.
     */
    public StudentQueue() {
        super();
    }

    /**
     * Enqueues the generic object by calling the parent's add(E e) method.
     *
     * @param e The generic object being enqueued.
     */
    public void enqueue(final E e) {
        add(e);
    }

    /**
     * Dequeues and returns the generic object located at the front of the queue.
     *
     * @return The generic object located at the front of the queue.
     */
    public E dequeue() {
        return poll();
    }

    /**
     * Returns the generic object located at the front of the queue by calling the parent's peek() method.
     *
     * @return The generic object located at the front of the queue.
     */
    @Override
    public E peek() {
        return super.peek();
    }

    /**
     * Returns the size of this queue by calling the parent's size() method.
     *
     * @return The size of this queue.
     */
    @Override
    public int size() {
        return super.size();
    }

    /**
     * Checks whether the queue is empty or not by calling the parent's isEmpty() method.
     *
     * @return {@code true} if the queue is empty, {@code false} if not.
     */
    @Override
    public boolean isEmpty() {
        return super.isEmpty();
    }
}
