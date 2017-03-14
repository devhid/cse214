package hw4;

public class Course {
    private final OfficeProperties properties = OfficeHourSimulator.getProperties();

    private final double arrivalProbability;
    private final int courseNumber;

    private int courseDifficulty;

    public Course(final int courseNumber, final double arrivalProbability) {
        if(!containsCourseNumber(courseNumber)) {
            throw new IllegalArgumentException("Invalid course number.");
        }

        this.courseNumber = courseNumber;
        this.arrivalProbability = arrivalProbability;
    }

    private boolean containsCourseNumber(final int courseNumber) {
        for(int num: properties.getCourseNumbers()) {
            if(courseNumber == num) {
                return true;
            }
        }
        return false;
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
