package hw4;

public class Helper {
    private final boolean isProfessor;

    private int timeUntilFree;
    private Student currentStudent;

    public Helper(final boolean isProfessor) {
        this.isProfessor = isProfessor;
        this.timeUntilFree = 0;
    }

    public void setCurrentStudent(final Student currentStudent) {
        this.currentStudent = currentStudent;
    }

    public Student getCurrentStudent() {
        return this.currentStudent;
    }

    public boolean isProfessor() {
        return isProfessor;
    }

    public int getTimeUntilFree() {
        return this.timeUntilFree;
    }

    public void setTimeUntilFree(final int timeUntilFree) {
        this.timeUntilFree = timeUntilFree;
    }
}
