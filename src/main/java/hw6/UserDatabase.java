package hw6;

import java.util.HashMap;

public class UserDatabase extends HashMap<String, User> {

    public void addUser(final String email, final User user) throws IllegalArgumentException {
        if(email == null || get(email).getName().equals(user.getName())) {
            throw new IllegalArgumentException();
        }

        put(email, user);
    }

    public User getUser(final String email) {
        return get(email);
    }

    public void removeUser(final String email) throws IllegalArgumentException {
        if(email == null || get(email) == null) {
            throw new IllegalArgumentException();
        }

        remove(email);
    }
}
