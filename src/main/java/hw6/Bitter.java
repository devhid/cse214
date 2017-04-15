package hw6;

import java.io.Serializable;

public class Bitter implements Serializable {
    private final UserDatabase users;
    private final AccountDatabase accounts;

    public Bitter() {
        this.users = new UserDatabase();
        this.accounts = new AccountDatabase();
    }

    public void addUser(String email, User user, Account account) throws IllegalArgumentException {
        if(email == null || account == null || users.getUser(email) != null || accounts.getAccountInformation(email) != null) {
            throw new IllegalArgumentException(Lang.USER_ALREADY_EXISTS);
        }

        users.addUser(email, user);
        accounts.addAccount(email, account);
    }

    public void removeUser(String email) throws IllegalArgumentException {
        if(email == null || users.get(email) == null) {
            throw new IllegalArgumentException(Lang.REMOVE_NON_EXISTING_USER);
        }

        users.removeUser(email);
        accounts.removeAccount(email);
    }

    public Account getAccount(final String email) {
        return accounts.getAccountInformation(email);
    }

    public User getUser(final String email) {
        return users.getUser(email);
    }

    public UserDatabase getUsers() {
        return this.users;
    }

    public AccountDatabase getAccounts() {
        return this.accounts;
    }
}
