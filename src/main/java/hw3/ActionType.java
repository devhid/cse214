package hw3;

public enum ActionType {
    ADD("ADD: '%s' in position: '%d'"),
    REMOVE("REMOVE: '%s' from position: '%d'"),
    MOVE("MOVE: position: '%d' to position: '%d'"),
    SWAP("SWAP: position: '%d' and position: '%d'");

    private final String action;

    ActionType(final String action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return this.action;

    }
}
