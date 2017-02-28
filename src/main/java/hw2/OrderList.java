package hw2;

/**
 * The <code>OrderList</code> class implements a DoubleLinkedList that holds <code>Order</code> objects.
 *
 * @author Mankirat Gulati
 *    email: mankirat.gulati@stonybrook.edu
 *    Stony Brook ID: 111161128
 */
public class OrderList {
    // Nodes to keep a reference of head of list, tail of list, and the current node (cursor).
    private OrderListNode head, cursor, tail;

    // The number of orders currently in this list.
    private int numOrders;

    /**
     * Initializes <code>head</code>, <code>tail</code>, and <code>tail</code> to <code>null</code>.
     */
    public OrderList() {
        this.head = null;
        this.cursor = null;
        this.tail = null;

        this.numOrders = 0;
    }

    /**
     * Returns the head of the list.
     *
     * @return
     *     The <code>OrderListNode</code> referencing the head of the list.
     */
    public OrderListNode getHead() {
        return this.head;
    }

    /**
     * Returns the cursor of the list.
     *
     * @return
     *     The <code>OrderListNode</code> referencing the cursor of the list.
     */
    public OrderListNode getCursor() {
        return this.cursor;
    }

    /**
     * Returns the tail of the list.
     *
     * @return
     *     The <code>OrderListNode</code> referencing the tail of the list.
     */
    public OrderListNode getTail() {
        return this.tail;
    }

    /**
     * Returns the number of orders in this list.
     *
     * @return
     *     The number of <code>Order</code> objects currently in this list.
     */
    public int numOrders() {
        return this.numOrders;
    }

    /**
     * Returns the sum of all the order's prices in this list.
     *
     * @return The total price of all the <code>Order</code> objects in this list.
     */
    public double getTotalPrice() {
        double total = 0.00;

        if(isEmpty()) {
            return total;
        }

        OrderListNode node = head;
        while(node != null) {
            total += node.getData().getPrice();
            node = node.getNext();
        }

        return total;
    }

    /**
     * Checks if the list is empty.
     *
     * @return
     *      <code>true</code> if head, cursor, or tail is <code>null</code>, <code>false</code> otherwise.
     */
    public boolean isEmpty() {
        return head == null || cursor == null || tail == null;
    }

    /**
     * Moves the cursor to the head of the list.
     */
    public void resetCursorToHead() {
        cursor = head;
    }

    /**
     * Moves the cursor to the tail of the list.
     */
    public void resetCursorToTail() {
        cursor = tail;
    }

    /**
     * Returns the order located at cursor.
     *
     * @return
     *     The <code>Order</code> object referenced by the cursor <code>OrderListNode</code>.
     */
    public Order getCursorOrder() {
        return cursor != null ? cursor.getData() : null;
    }

    /**
     * Moves the cursor to the next position in the list.
     *
     * @throws EndOfListException
     *     Indicates the cursor is already at the tail, thus it cannot move forward.
     */
    public void cursorForward() throws EndOfListException {
        if(cursor == tail) {
            throw new EndOfListException(Lang.END_OF_LIST);
        }

        cursor = cursor.getNext();
    }

    /**
     * Moves the cursor to the previous position in the list.
     *
     * @throws EndOfListException
     *     Indicates the cursor is already at the head, thus it cannot move backward.
     */
    public void cursorBackward() throws EndOfListException {
        if(cursor == head) {
            throw new EndOfListException(Lang.BEGINNING_OF_LIST);
        }

        cursor = cursor.getPrevious();
    }

    /**
     * Appends an order to the head of the list.
     *
     * @param order The <code>Order</code> object to be appended to the head of the list.
     * @throws IllegalArgumentException
     *     Indicates the order is <code>null</code>, thus it cannot be added.
     */
    public void appendToHead(final Order order) throws IllegalArgumentException {
        if(order == null) {
            throw new IllegalArgumentException(Lang.INVALID_ORDER);
        }

        OrderListNode node = new OrderListNode(order);

        if(head == null) {
            tail = node;
        } else {
            node.setNext(head);
            head.setPrevious(node);
        }

        head = node;
        cursor = node;

        numOrders++;
    }

    /**
     * Appends an order to the tail of the list.
     *
     * @param order The <code>Order</code> object to be appended to the tail of the list.
     * @throws IllegalArgumentException
     *     Indicates the order is <code>null</code>, thus it cannot be added.
     */
    public void appendToTail(final Order order) throws IllegalArgumentException {
        if(order == null) {
            throw new IllegalArgumentException(Lang.INVALID_ORDER);
        }

        OrderListNode node = new OrderListNode(order);

        if(tail == null) {
            head = node;
            cursor = node;
        } else {
            node.setPrevious(tail);
            tail.setNext(node);
        }

        tail = node;
        numOrders++;
    }

    /**
     * Inserts the specified order after the cursor in the list.
     *
     * @param order The <code>Order</code> object to be inserted after the cursor in the list.
     * @throws IllegalArgumentException
     *     Indicates the order is <code>null</code>, thus it cannot be added.
     */
    public void insertAfterCursor(final Order order) throws IllegalArgumentException {
        if(order == null) {
            throw new IllegalArgumentException(Lang.INVALID_ORDER);
        }

        OrderListNode node = new OrderListNode(order);

        if(isEmpty()) {
            head = node;
            tail = node;
        } else {
            // Check for cursor == tail
            if(cursor.getNext() != null) {
                node.setNext(cursor.getNext());
                node.getNext().setPrevious(node);
            } else {
                tail = node;
            }

            node.setPrevious(cursor);
            cursor.setNext(node);
        }

        cursor = node;
        numOrders++;
    }

    /**
     * Inserts the order after a similar order or at the end of the list if no such similar order exists.
     *
     * @param order The <code>Order</code> object to be inserted in the list.
     * @throws IllegalArgumentException
     *     Indicates the order is <code>null</code>, thus it cannot be added.
     */
    public void insertAfterSimilarOrder(final Order order) throws IllegalArgumentException {
        if(order == null) {
            throw new IllegalArgumentException(Lang.INVALID_ORDER);
        }

        OrderListNode node = new OrderListNode(order);

        if(isEmpty()) {
            head = node;
            tail = node;
        } else {
            resetCursorToHead();

            while(cursor != tail) {
                if(cursor.getData().equals(node.getData())) {
                    break;
                }

                cursor = cursor.getNext();
            }

            if(cursor != tail) {
                node.setNext(cursor.getNext());
                node.getNext().setPrevious(node);
            } else {
                tail = node;
            }

            node.setPrevious(cursor);
            cursor.setNext(node);
        }

        cursor = node;
        numOrders++;
    }

    /**
     * Removes the order located at cursor.
     *
     * @return The <code>Order</code> object that was removed at the cursor.
     * @throws EndOfListException
     *     Indicates no order could be removed because the list was empty.
     */
    public Order removeCursor() throws EndOfListException {
        if(cursor == null) {
            throw new EndOfListException(Lang.EMPTY_LIST);
        }

        Order order = cursor.getData();
        // Check if cursor != head.
        if(cursor.getPrevious() != null) {
            // Check if cursor != tail
            if(cursor.getNext() != null) {
                cursor.getNext().setPrevious(cursor.getPrevious());
                cursor.getPrevious().setNext(cursor.getNext());
            } else {
                cursor.getPrevious().setNext(null);
            }

            cursor = cursor.getPrevious();
        } else {
            if(cursor.getNext() != null) {
                cursor.getNext().setPrevious(null);
                head = cursor.getNext();
            } else {
                head = null;
                tail = null;
            }

            cursor = head;
        }

        numOrders--;
        return order;
    }

    /**
     * Returns the string representation of the list.
     *
     * @return A string representation of the names for all orders in the list.
     */
    @Override
    public String toString() {
        if(isEmpty()) {
            return "[]";
        }

        String out = "";
        OrderListNode node = head;

        while(node != null) {
            out += node.getData().getName() + ", ";
            node = node.getNext();
        }

        return out;
    }
}