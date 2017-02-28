package hw2;

/**
 * The <code>Order</code> class implements an order with a name, special instruction, and a price.
 *
 * @author Mankirat Gulati
 *    email: mankirat.gulati@stonybrook.edu
 *    Stony Brook ID: 111161128
 */

public class Order {
    // The name of the order and the special instruction for it.
    private String name, specialInstruction;

    // The price of the order.
    private double price;

    /**
     * Initializes this <code>Order</code> object with a name, specialInstruction, and a price.
     *
     * @param name The name of this order.
     * @param specialInstruction The special instruction involved with this order.
     * @param price The price of this order.
     */
    public Order(final String name, final String specialInstruction, final double price) {
        this.name = name;
        this.specialInstruction = specialInstruction;
        this.price = price;
    }

    /**
     * Initializes this <code>Order</code> object with a name and a price.
     *
     * @param name The name of this order.
     * @param price the price of this order.
     */
    public Order(final String name, final double price) {
        this(name, "", price);
    }

    /**
     * Returns the name of this order.
     *
     * @return The name of this <code>Order</code> object.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the special instruction for this order.
     *
     * @return The special instruction for this <code>Order</code> object.
     */
    public String getSpecialInstruction() {
        return this.specialInstruction;
    }

    /**
     * Returns the price for this order.
     *
     * @return The price for this <code>Order</code> object.
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * Sets the name for this order to <code>name</code>.
     *
     * @param name The new name for this <code>Order</code> object.
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Sets the special instruction for this order to <code>specialInstruction</code>.
     *
     * @param specialInstruction The new special instruction for this <code>Order</code> object.
     */
    public void setSpecialInstruction(final String specialInstruction) {
        this.specialInstruction = specialInstruction;
    }

    /**
     * Sets the price for this order to <code>price</code>.
     *
     * @param price The new price for this <code>Order</code> object.
     */
    public void setPrice(final double price) {
        this.price = price;
    }

    /**
     * Checks if this order is equal to another object.
     *
     * @param object The object that is being checked for equality with this order.
     * @return <code>true</code> if this order and <code>object</code> is equal, <code>false</code> otherwise.
     */
    @Override
    public boolean equals(final Object object) {
        if(!(object instanceof Order)) {
            return false;
        }

        Order order = (Order) object;
        return order.name.equals(this.name)
                && order.specialInstruction.equals(this.specialInstruction)
                && order.price == this.price;
    }

    /**
     * Returns a string representation of this order.
     *
     * @return A string representation of this order consisting of the name, instruction, and price properties.
     */
    @Override
    public String toString() {
        return "[name: " + this.name + ", instruction: " + this.specialInstruction + ", price: " + this.price + "]";
    }
}
