package hw6;

import java.io.Serializable;
import java.util.HashMap;

/**
 * The {@code UserDatabase} class is a {@code Serializable} object acting as a database
 * holding all the users in the social network.
 *
 * @author Mankirat Gulati
 *    email: mankirat.gulati@stonybrook.edu
 *    Stony Brook ID: 111161128
 */
public class UserDatabase extends HashMap<String, User> implements Serializable {

    /**
     * Returns the user associated with the specified email.
     *
     * @param email The email being used to find the linked {@code User}.
     * @return The {@code User} object associated with the specified {@code email}.
     */
    public User getUser(final String email) {
        return get(email);
    }

    /**
     * Adds a user to the database.
     *
     * @param email The email associated with the user.
     * @param user The actual {@code User} being added.
     * @throws IllegalArgumentException
     *     Indicates either the email is {@code null} or this database already contains this email.
     */
    public void addUser(final String email, final User user) throws IllegalArgumentException {
        if(email == null || containsKey(email)) {
            throw new IllegalArgumentException();
        }

        put(email, user);
    }

    /**
     * Removes a user from the database.
     *
     * @param email The email linked to the user.
     * @throws IllegalArgumentException
     *     Indicates either the email is {@code null} or this database doesn't contain this email.
     */
    public void removeUser(final String email) throws IllegalArgumentException {
        if(email == null || !containsKey(email)) {
            throw new IllegalArgumentException();
        }

        remove(email);
    }
}
