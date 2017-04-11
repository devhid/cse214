package hw6;

import java.io.Serializable;

public class User implements Serializable {
    private String name;

    public User(final String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
