package hw4;

public class Course {
    private final double arrivalProbability;
    private final int courseNumber;

    private int courseDifficulty;

    public Course(final int courseNumber, final double arrivalProbability) {
        OfficeHourSimulator.checkCourseNumber(courseNumber);

        this.courseNumber = courseNumber;
        this.arrivalProbability = arrivalProbability;
    }

    public int getCourseNumber() {
        return this.courseNumber;
    }

    public double getArrivalProbability() {
        return this.arrivalProbability;
    }

    public int getCourseDifficulty() {
        return this.courseDifficulty;
    }

    public void setCourseDifficulty(final int courseDifficulty) {
        this.courseDifficulty = courseDifficulty;
    }

    @Override
    public String toString() {
        return "courseNumber: " + courseNumber + ", courseDifficulty: " + courseDifficulty
                + ", arrivalProbability: " + arrivalProbability;
    }
}
