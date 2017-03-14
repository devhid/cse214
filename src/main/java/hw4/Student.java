package hw4;

public class Student {
    private final OfficeProperties properties = OfficeHourSimulator.getProperties();

    private final int studentId, timeArrived;
    private final Course course;

    private static int studentCounter = 0;
    private int timeRequired;

    public Student(final int timeArrived, final Course course) {
        if(timeArrived <= 0) {
            throw new IllegalArgumentException("Invalid arrival time.");
        }

        if(!containsCourseNumber(course.getCourseNumber())) {
            throw new IllegalArgumentException("Invalid course number.");
        }

        this.timeArrived = timeArrived;
        this.course = course;
        this.studentId = ++studentCounter;
    }

    private boolean containsCourseNumber(final int courseNumber) {
        for(int num: properties.getCourseNumbers()) {
            if(courseNumber == num) {
                return true;
            }
        }
        return false;
    }

    public int getStudentId() {
        return this.studentId;
    }

    public int getTimeArrived() {
        return this.timeArrived;
    }

    public int getTimeRequired() {
        return this.timeRequired;
    }

    public void setTimeRequired(final int timeRequired) {
        this.timeRequired = timeRequired;
    }

    public Course getCourse() {
        return this.course;
    }
}
