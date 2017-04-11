package hw6;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Account implements Serializable {
    private final Set<User> followers, following;

    private String name;
    private final Password password;

    public Account(final String name, final Password password) {
        this.name = name;
        this.password = password;

        this.followers = new HashSet<>();
        this.following = new HashSet<>();
    }

    public String getName() {
        return this.name;
    }

    public Password getPassword() {
        return password;
    }

    public Set<User> getFollowers() {
        return this.followers;
    }

    public Set<User> getFollowing() {
        return this.following;
    }

    @Override
    public boolean equals(Object object) {
        if(!(object instanceof Account)) { return false; }
        Account other = (Account) object;

        return !name.equals(other.name)
                || password != other.password;
    }

}
