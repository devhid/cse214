package hw6;

import java.io.Serializable;

/**
 * The {@code User) class is a {@code Serializable} class that
 * contains user's name and email for the social media account.
 *
 * @author Mankirat Gulati
 *    email: mankirat.gulati@stonybrook.edu
 *    Stony Brook ID: 111161128
 */
public class User implements Serializable {
    // Name of the user.
    private String name;
    // Email of the user.
    private String email;

    /**
     * Initializes {@code name} to the parameter in the constructor.
     *
     * @param name The name of the user.
     */
    public User(final String name) {
        this.name = name;
    }

    /**
     * Calls default constructor and also initializes {@code email} to the parameter in this constructor.
     *
     * @param name The name of the user.
     * @param email The email of the user.
     */
    public User(final String name, final String email) {
        this(name);
        this.email = email;
    }

    /**
     * Returns the user's email.
     *
     * @return The email of this {@code User}.
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Returns the user's name.
     *
     * @return The name of this {@code User}.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets this user's name to the specified name.
     *
     * @param name The new name for this {@code User}.
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Sets this user's email to the specified email.
     *
     * @param email the new email for this {@code User}.
     */
    public void setEmail(final String email) {
        this.email = email;
    }
}
