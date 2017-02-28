package hw2;

/**
 * The <code>OrderListNode</code> class implements a node that references an <code>Order</code> object and the node before and after it (next and previous).
 *
 * @author Mankirat Gulati
 *    email: mankirat.gulati@stonybrook.edu
 *    Stony Brook ID: 111161128
 */

public class OrderListNode {
    // The order that is referenced by this node.
    private Order data;

    // The nodes that come after or before this node.
    private OrderListNode next, previous;

    /**
     * Initializes a new <code>OrderListNode</code> with the specified <code>data</code>.
     *
     * @param data The <code>Order</code> object that is being referenced by this node.
     * @throws IllegalArgumentException
     *     Indicates the <code>order</code> is null.
     */
    public OrderListNode(final Order data) throws IllegalArgumentException {
        if(data == null) {
            throw new IllegalArgumentException();
        }

        this.data = data;
        this.next = null;
        this.previous = null;
    }

    /**
     * Returns the order referenced by this node.
     *
     * @return The <code>Order</code> object referenced by this node.
     */
    public Order getData() {
        return this.data;
    }

    /**
     * Returns the node next (after) to this node.
     *
     * @return The <code>OrderListNode</code> object located after this node.
     */
    public OrderListNode getNext() {
        return this.next;
    }

    /**
     * Returns the node previous to this node.
     *
     * @return The <code>OrderListNode</code> object located before this node.
     */
    public OrderListNode getPrevious() {
        return this.previous;
    }

    /**
     * Sets this node's data to the specified data.
     *
     * @param data The <code>Order</code> object that is being set as this node's data.
     */
    public void setData(final Order data) {
        this.data = data;
    }

    /**
     * Sets the node located after this node.
     *
     * @param next The <code>OrderListNode</code> object that is being set after this node.
     */
    public void setNext(final OrderListNode next) {
        this.next = next;
    }

    /**
     * Sets the node located before this node.
     *
     * @param previous The <code>OrderListNode</code> object that is being set before this node.
     */
    public void setPrevious(final OrderListNode previous) {
        this.previous = previous;
    }
}
