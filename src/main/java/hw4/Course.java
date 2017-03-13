package hw4;

public class Course {
    private final int courseNumber;
    private final double arrivalProbability;

    private int courseDifficulty;

    public Course(final int courseNumber, final double arrivalProbability) {
        if(!containsCourseNumber(courseNumber)) {
            throw new IllegalArgumentException("Invalid course number.");
        }

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

    private boolean containsCourseNumber(final int courseNumber) {
        for(int num: getProperties().courseNumbers()) {
            if(courseNumber == num) {
                return true;
            }
        }
        return false;
    }

    private OfficeProperties getProperties() {
        return OfficeHourSimulator.getProperties();
    }
}
