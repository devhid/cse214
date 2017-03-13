package hw4;

public class Student {
    private static int studentCounter = 0;
    private int timeRequired;

    private final int studentId, timeArrived;
    private final Course course;

    public Student(final int timeArrived, final Course course) {
        if(timeArrived <= 0) {
            throw new IllegalArgumentException("Invalid arrival time.");
        }

        if(!containsCourseNumber(course.getCourseNumber())) {
            throw new IllegalArgumentException("Invalid course number");
        }

        this.timeArrived = timeArrived;
        this.course = course;
        this.studentId = ++studentCounter;
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

    public Course getCourse() {
        return this.course;
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
