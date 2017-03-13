package hw4;

public class Helper {
    private int timeUntilFree;
    private final boolean isProfessor;

    public Helper(final boolean isProfessor) {
        this.isProfessor = isProfessor;
        this.timeUntilFree = 0;
    }
}
