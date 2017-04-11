package hw6;

import java.util.HashMap;

public class UserDatabase extends HashMap<String, User> {

    public void addUser(final String email, final User user) throws IllegalArgumentException {
        put(email, user);
    }

    public User getUser(final String email) {
        return get(email);
    }

    public void removeUser(final String email) throws IllegalArgumentException {
        remove(email);
    }
}
