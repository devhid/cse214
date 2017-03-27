package hw4;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * The {@code Statistics} class handles basic statistics involving student wait times and student count.
 *
 * @author Mankirat Gulati
 *    email: mankirat.gulati@stonybrook.edu
 *    Stony Brook ID: 111161128
 */
public class Statistics {
    // A set of all students that have been helped.
    private final Set<Student> students;

    /**
     * Initializes {@code students} to a new {@code HashSet<Student>}.
     */
    public Statistics() {
        this.students = new HashSet<>();
    }

    /**
     * Adds a student to the set.
     *
     * @param student The {@code Student} to be added into the set.
     */
    public void addStudent(final Student student) {
        students.add(student);
    }

    /**
     * Calculates the total average waiting time for all students.
     *
     * @return The average waiting time for all students for all courses.
     */
    public int getTotalAverageTime() {
        int total = 0;

        for(Student student: students) {
            total += student.getWaitTime();
        }

        return new BigDecimal(students.size() == 0 ? 0 : (double) total/students.size()).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
    }

    /**
     * Returns the average waiting time for students taking a specific course.
     *
     * @param courseNumber The number that corresponds to a specific course.
     * @return The average waiting time for students in a specific course with the specified {@code courseNumber}.
     */
    public int getAverageTime(final int courseNumber) {
        int total = 0;
        int count = 0;

        for(Student student: students) {
            if(student.getCourse().getCourseNumber() == courseNumber) {
                count++;
                total+=student.getWaitTime();
            }
        }

        return new BigDecimal(count == 0 ? 0 : (double) total/count).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
    }

    /**
     * Returns the number of students in a specific course.
     *
     * @param courseNumber The number that is linked to a specific course.
     * @return The number of students that are taking the course linked to the specified {@code courseNumber}.
     */
    public int getStudentCount(final int courseNumber) {
        int count = 0;
        for(Student student: students) {
            if(student.getCourse().getCourseNumber() == courseNumber) {
                count++;
            }
        }
        return count;
    }

    /**
     * Returns the total number of students in the set.
     *
     * @return The total number of students in {@code students}.
     */
    public int getTotalStudents() {
        return students.size();
    }
}
