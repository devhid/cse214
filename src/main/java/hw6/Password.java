package hw6;

import java.io.Serializable;
/**
 * The {@code Password) is a {@code Serializable} class that acts as a wrapper for the actual password and ensures validation.
 *
 * @author Mankirat Gulati
 *    email: mankirat.gulati@stonybrook.edu
 *    Stony Brook ID: 111161128
 */
public class Password implements Serializable {
    // The actual password for the account.
    private String password;

    /**
     * Initializes {@code password} to the specified parameter in the constructor.
     *
     * @param password The password for an account.
     * @throws IllegalArgumentException
     *     Indicates the password was invalid: did not contain at least 1 uppercase letter, 1 lowercase letter, 1 number, and 1 symbol.
     */
    public Password(final String password) throws IllegalArgumentException {
        if(!isValid(password)) {
            throw new IllegalArgumentException(Lang.INVALID_PASSWORD);
        }

        this.password = password;
    }

    // Checks if a password is valid.
    private boolean isValid(final String password) {
        return password.matches(
                 "^(?=.*[a-z])" +       // has at least 1 lowercase letter
                        "(?=.*[A-Z])" +       // has at least 1 uppercase letter
                        "(?=.*\\d)" +         // has at least 1 number
                        "(?=.*[!@#$%^&*])" +  // has at least 1 symbol
                        ".+$"
        );
    }

    /**
     * Returns the password.
     *
     * @return A {@code String} representation of the password.
     */
    @Override
    public String toString() {
        return this.password;
    }
}
