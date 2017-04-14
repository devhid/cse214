package hw6;

import java.io.Serializable;

public class User implements Serializable {
    private String name;
    private String email;

    public User(final String name) {
        this.name = name;
    }

    public User(final String name, final String email) {
        this(name);
        this.email = email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
