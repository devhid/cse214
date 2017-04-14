package hw6;

import java.io.Serializable;

public class Password implements Serializable {
    private String password;

    public Password(final String password) throws IllegalArgumentException {
        if(!validate(password)) {
            throw new IllegalArgumentException();
        }

        this.password = password;
    }

    private boolean validate(final String password) {
        return password.matches(
                 "^(?=.*[a-z])" +       // has at least 1 lowercase letter
                        "(?=.*[A-Z])" +       // has at least 1 uppercase letter
                        "(?=.*\\d)" +         // has at least 1 number
                        "(?=.*[!@#$%^&*])" +  // has at least 1 symbol
                        ".+$"
        );
    }

    @Override
    public boolean equals(Object object) {
        return !(object instanceof Password) || !password.equals( ((Password) object).password );
    }

}
