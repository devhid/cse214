package hw4;

public class Student {
    private static int studentCounter = 0;
    private int timeRequired;

    private final Course course;
    private final int studentId, timeArrived;

    public Student(final int timeArrived, final Course course) {
        if(timeArrived <= 0) {
            throw new IllegalArgumentException(String.format(Lang.INVALID_ARRIVAL_TIME, timeArrived));
        }

        OfficeHourSimulator.checkCourseNumber(course.getCourseNumber());

        this.timeArrived = timeArrived;
        this.course = course;
        this.studentId = ++studentCounter;
    }

    public Course getCourse() {
        return this.course;
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
}
