package hw3;

/**
 * The {@code ActionType} enum specifies the type of command that is performed and stores the stack message.
 *
 * @author Mankirat Gulati
 *    email: mankirat.gulati@stonybrook.edu
 *    Stony Brook ID: 111161128
 */

public enum ActionType {
    ADD("ADD ‒ '%s' in position: '%d'"),
    REMOVE("REMOVE ‒ '%s' from position: '%d'"),
    MOVE("MOVE ‒ position: '%d' to position: '%d'"),
    SWAP("SWAP ‒ position: '%d' and position: '%d'");

    private final String action;

    /**
     * Allows the enum values to reference their individual actions.
     *
     * @param action The action that the specific command would perform in {@code String} representation.
     */
    ActionType(final String action) {
        this.action = action;
    }

    /**
     * The action connected to the command to be printed in stack-format.
     *
     * @return A {@code String} representation of the action performed used to print stack content.
     */
    @Override
    public String toString() {
        return this.action;

    }
}
