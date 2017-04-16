package hw6;

import java.io.Serializable;

/**
 * The {@code Bitter} class is a {@code Serializable} class that holds and manages the data from the two databases.
 *
 * @author Mankirat Gulati
 *    email: mankirat.gulati@stonybrook.edu
 *    Stony Brook ID: 111161128
 */
public class Bitter implements Serializable {
    // The database holding the users.
    private final UserDatabase users;
    // The database holding the accounts.
    private final AccountDatabase accounts;

    /**
     * Initializes {@code users} to a new {@code UserDatabase} and {@code accounts} to a new {@code AccountDatabase}.
     */
    public Bitter() {
        this.users = new UserDatabase();
        this.accounts = new AccountDatabase();
    }

    /**
     * Adds a user to the both databases.
     *
     * @param email The email of the user.
     * @param user The {@code User} object encapsulating the user's email and name.
     * @param account The {@code Account} of the user holding the user's name and password.
     * @throws IllegalArgumentException
     *     Indicates that email is {@code null} or {@code account} is null or the user or account database already contains the user's email.
     */
    public void addUser(String email, User user, Account account) throws IllegalArgumentException {
        if(email == null || account == null || users.containsKey(email) || accounts.containsKey(email)) {
            throw new IllegalArgumentException(Lang.USER_ALREADY_EXISTS);
        }

        users.addUser(email, user);
        accounts.addAccount(email, account);
    }

    /**
     * Removes a user from both databases.
     *
     * @param email The email of the user being removed.
     * @throws IllegalArgumentException
     *     Indicates that the email is {@code null} or the user is not in the social network.
     */
    public void removeUser(String email) throws IllegalArgumentException {
        if(email == null || !users.containsKey(email)) {
            throw new IllegalArgumentException(Lang.REMOVE_NON_EXISTING_USER);
        }

        users.removeUser(email);
        accounts.removeAccount(email);
    }

    /**
     * Returns the account associated with the specified email.
     *
     * @param email The email that is being used to find the associated {@code Account}.
     * @return The {@code Account} associated with the specified {@code email}.
     */
    public Account getAccount(final String email) {
        return accounts.getAccountInformation(email);
    }

    /**
     * Returns the user associated with the specified email.
     *
     * @param email The email that is being used to find the associated {@code User}.
     * @return The {@code User} associated with the specified {@code email}.
     */
    public User getUser(final String email) {
        return users.getUser(email);
    }

    /**
     * Returns the user database.
     *
     * @return A reference to the {@code UserDatabase} object.
     */
    public UserDatabase getUsers() {
        return this.users;
    }

    /**
     * Returns the account database.
     *
     * @return A reference to the {@code AccountDatabase} object.
     */
    public AccountDatabase getAccounts() {
        return this.accounts;
    }
}
