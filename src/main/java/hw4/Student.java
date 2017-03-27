package hw4;

/**
 * The {@code Student} class represents a student that is taking a {@code Course} and is helped by
 * either a professor or TA. A student has a distinct {@code studentId}.
 *
 * @author Mankirat Gulati
 *    email: mankirat.gulati@stonybrook.edu
 *    Stony Brook ID: 111161128
 */
public class Student {
    // Used to get the ID of the student.
    private static int studentCounter = 0;

    // timeRequired = time needed to be helped.
    // waitTime = time spent waiting to be helped.
    // timeHelped = time this student receives help.
    private int timeRequired, waitTime, timeHelped;

    // The course this student is taking.
    private final Course course;

    // The id of the student | the time the student arrives.
    private final int studentId, timeArrived;

    /**
     * Initializes {@code timeArrived} and {@code course} to the parameters specified in the constructor.
     * If {@code timArrived} is <=0, an {@code IllegalArgumentException} is thrown.
     *
     * @param timeArrived The time this student arrives to get help.
     * @param course The course this student is taking.
     */
    public Student(final int timeArrived, final Course course) {
        if(timeArrived <= 0) {
            throw new IllegalArgumentException(String.format(Lang.INVALID_ARRIVAL_TIME, timeArrived));
        }

        OfficeHourSimulator.checkCourseNumber(course.getCourseNumber());

        this.timeArrived = timeArrived;
        this.waitTime = 0;
        this.course = course;
        this.studentId = ++studentCounter;
    }

    /**
     * Returns the course this student is taking.
     *
     * @return The {@code Course} this student is currently taking.
     */
    public Course getCourse() {
        return this.course;
    }

    /**
     * Returns the id of this student.
     *
     * @return The {@code studentId} of this {@code Student}.
     */
    public int getStudentId() {
        return this.studentId;
    }

    /**
     * Returns the time this student arrived to office hours.
     *
     * @return The time this {@code Student} arrived to office hours.
     */
    public int getTimeArrived() {
        return this.timeArrived;
    }

    /**
     * Returns the time required for this student to be helped completely.
     *
     * @return The time required for this {@code Student} to be helped completely.
     */
    public int getTimeRequired() {
        return this.timeRequired;
    }

    /**
     * Returns the time this student started receiving help.
     *
     * @return The time this {@code Student} started receiving help.
     */
    public int getTimedHelped() {
        return this.timeHelped;
    }

    /**
     * Sets the time this student started receiving help.
     *
     * @param timeHelped The time being set for this student's {@code timeHelped} field.
     */
    public void setTimeHelped(int timeHelped) {
        this.timeHelped = timeHelped;
    }

    /**
     * Returns the time that this student waited to receive help.
     *
     * @return The time that this {@code Student} waited to receive help.
     */
    public int getWaitTime() {
        return waitTime;
    }

    /**
     * Increments the {@code waitTime} for this {@code Student} by 1.
     */
    public void incrementWaitTime() {
        waitTime++;
    }

    /**
     * Sets the time required to be fully helped for this student.
     *
     * @param timeRequired The time being set for this student's timeRequired field.
     */
    public void setTimeRequired(final int timeRequired) {
        this.timeRequired = timeRequired;
    }
}
