package hw6;

import java.io.Serializable;
import java.util.HashMap;

/**
 * The {@code AccountDatabase} class is a {@code Serializable} object representing a database which holds
 * the accounts for all the users in the social network.
 *
 * @author Mankirat Gulati
 *    email: mankirat.gulati@stonybrook.edu
 *    Stony Brook ID: 111161128
 */
public class AccountDatabase extends HashMap<String, Account> implements Serializable {

    /**
     * Returns the account associated with the specified email.
     *
     * @param email The email address being used to find the {@code Account} associated with it.
     * @return The {@code Account} associated with the specified {@code email}.
     */
    public Account getAccountInformation(final String email) {
        return get(email);
    }

    /**
     * Adds an account into the account database.
     *
     * @param email The email of the account.
     * @param account The {@code Account} being added.
     * @throws IllegalArgumentException
     *     Indicates that either the email is {@code null}, or the account database already contains the specified account.
     */
    public void addAccount(final String email, final Account account) throws IllegalArgumentException {
        if(email == null || containsKey(email)) {
            throw new IllegalArgumentException();
        }

        put(email, account);
    }

    /**
     * Removes an account from the account database.
     *
     * @param email The email of the account.
     * @throws IllegalArgumentException
     *     Indicates the email specified is {@code null}, or the specified account is already not in the account database.
     */
    public void removeAccount(final String email) throws IllegalArgumentException {
        if(email == null || !containsKey(email)) {
            throw new IllegalArgumentException();
        }

        remove(email);
    }
}
