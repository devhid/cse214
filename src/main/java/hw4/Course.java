package hw4;

/**
 * The {@code Course} class represents a computer science course taken by a {@code Student}.
 *
 * @author Mankirat Gulati
 *    email: mankirat.gulati@stonybrook.edu
 *    Stony Brook ID: 111161128
 */
public class Course {
    // The probability of students in this course entering office hours.
    private final double arrivalProbability;
    // The number or identifier for this course.
    private final int courseNumber;

    // The difficulty of this course.
    private int courseDifficulty;

    /**
     * Initializes {@code courseNumber} and {@code arrivalProbability} to the parameters specified
     * in the constructor. The course number is checked for validity before being initialized.
     *
     * @param courseNumber
     * @param arrivalProbability
     */
    public Course(final int courseNumber, final double arrivalProbability) {
        OfficeHourSimulator.checkCourseNumber(courseNumber);

        this.courseNumber = courseNumber;
        this.arrivalProbability = arrivalProbability;
    }

    /**
     * Returns the course number for this course.
     *
     * @return The {@code courseNumber} for this {@code Course}.
     */
    public int getCourseNumber() {
        return this.courseNumber;
    }

    /**
     * Returns the arrival probability for this course.
     *
     * @return The {@code arrivalProbability} for this {@code Course}.
     */
    public double getArrivalProbability() {
        return this.arrivalProbability;
    }

    /**
     * Returns the course difficulty for this course.
     *
     * @return The {@code courseDifficulty} for this {@code Course}.
     */
    public int getCourseDifficulty() {
        return this.courseDifficulty;
    }

    /**
     * Sets the course difficulty for this course.
     *
     * @param courseDifficulty The difficulty that will be set for this course.
     */
    public void setCourseDifficulty(final int courseDifficulty) {
        this.courseDifficulty = courseDifficulty;
    }

    /**
     * Returns a string representation of this course's properties.
     *
     * @return A {@code String} representation of this course's {@code courseNumber}, {@code courseDifficulty},
     * and {@code arrivalProbability}.
     */
    @Override
    public String toString() {
        return "courseNumber: " + courseNumber + ", courseDifficulty: " + courseDifficulty
                + ", arrivalProbability: " + arrivalProbability;
    }
}
