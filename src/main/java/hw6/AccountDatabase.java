package hw6;

import java.io.Serializable;
import java.util.HashMap;

public class AccountDatabase extends HashMap<String, Account> implements Serializable {

    public Account getAccountInformation(final String email) {
        return get(email);
    }

    public void addAccount(final String email, final Account account) throws IllegalArgumentException {
        if(email == null || get(email) == account) {
            throw new IllegalArgumentException();
        }

        put(email, account);
    }

    public void removeAccount(final String email) throws IllegalArgumentException {
        if(email == null || get(email) == null) {
            throw new IllegalArgumentException();
        }

        remove(email);
    }
}
