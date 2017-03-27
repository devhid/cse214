package hw4;

/**
 * The {@code Helper} class represents a person at office hours that can help a {@code Student}.
 *
 * @author Mankirat Gulati
 *    email: mankirat.gulati@stonybrook.edu
 *    Stony Brook ID: 111161128
 */
public class Helper {
    // Field that determines whether the helper is a professor or a TA.
    private final boolean isProfessor;

    // The time (in minutes) left until the helper is available to help a student.
    private int timeUntilFree;
    // The current student the helper is currently helping.
    private Student currentStudent;

    /**
     * Initializes {@code isProfessor} to the parameter specified inside the constructor.
     *
     * @param isProfessor {@code true} if the {@code Helper} will be a professor, {@code false} if not.
     */
    public Helper(final boolean isProfessor) {
        this.isProfessor = isProfessor;
        this.timeUntilFree = 0;
    }

    /**
     * Sets the current student the helper is helping.
     *
     * @param currentStudent The {@code Student} that is being set.
     */
    public void setCurrentStudent(final Student currentStudent) {
        this.currentStudent = currentStudent;
    }

    /**
     * Returns the current student the helper is helping.
     *
     * @return The {@code Student} this {@code Helper} is helping.
     */
    public Student getCurrentStudent() {
        return this.currentStudent;
    }

    /**
     * Returns whether or not this helper is a professor.
     *
     * @return {@code true} if this {@code Helper} is a professor, {@code false} if not.
     */
    public boolean isProfessor() {
        return isProfessor;
    }

    /**
     * Returns the time left until the helper is free.
     *
     * @return The time left until this {@code Helper} is available to help.
     */
    public int getTimeUntilFree() {
        return this.timeUntilFree;
    }

    /**
     * Sets the time left until the helper is free.
     *
     * @param timeUntilFree The time until this {@code Helper} is free that is being set
     */
    public void setTimeUntilFree(final int timeUntilFree) {
        this.timeUntilFree = timeUntilFree;
    }
}
