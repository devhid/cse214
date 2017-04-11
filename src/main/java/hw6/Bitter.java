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
        if(email == null || account == null || users.getUser(email) != null) {
            throw new IllegalArgumentException();
        }

        users.addUser(email, user);
        accounts.addAccount(email, account);
    }

    public void removeUser(String email) throws IllegalArgumentException {
        if(email == null || users.get(email) == null) {
            throw new IllegalArgumentException();
        }

        users.remove(email);
        accounts.remove(email);
    }
}
