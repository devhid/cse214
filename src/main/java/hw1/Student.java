package hw1;

/**
 * The <code>Student</code> class implements a student with a certain name, id, and number of write-ups.
 *
 * @author Mankirat Gulati
 *    email: mankirat.gulati@stonybrook.edu
 *    Stony Brook ID: 111161128
 */
public class Student {
    // Maximum number of writeups a student can have.
    private final int MAX_WRITEUPS = 3;

    // Name of the student.
    private String name;

    // The id-number of the student and the number of writeups the student has.
    private int idNumber, numWriteups;

    /**
     * Constructs a <code>Student</code> with the specified <code>name</code>, <code>idNumber</code>, and <code>numWriteups</code>.
     *
     * @param name
     *     The name of the student.
     * @param idNumber
     *     The id-number of the student.
     * @param numWriteups
     *     The number of write-ups the student has.
     *
     * <dt>Postconditions:</dt>
     *     This <code>Student</code> has been initialized with a
     *     specified name, id, and number of writeups.
     */
    public Student(final String name, final int idNumber, final int numWriteups) {
        this.name = name;
        this.idNumber = idNumber;
        this.numWriteups = numWriteups;
    }

    /**
     * Returns the maximum write-ups.
     *
     * @return
     *     The maximum write-ups a student is allowed to have.
     */
    public int getMaxWriteups() {
        return this.MAX_WRITEUPS;
    }

    /**
     * Returns the name of this student.
     *
     * @return
     *     The name property for this student.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of this student with the specified <code>name</code>.
     *
     * @param name
     *     The new name to be set for this student.
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Returns the id-number for this student.
     *
     * @return
     *     The id-number for this student.
     */
    public int getIdNumber() {
        return this.idNumber;
    }

    /**
     * Sets the id-number of this student with the specified <code>idNumber</code>.
     *
     * @param idNumber
     *     The new id-number for this student.
     */
    public void setIdNumber(final int idNumber) {
        this.idNumber = idNumber;
    }

    /**
     * Returns the number of write-ups.
     *
     * @return
     *     The number of write-ups this student currently has.
     */
    public int getNumWriteups() {
        return this.numWriteups;
    }

    /**
     * Sets the number of write-ups for this student with the specified <code>numWriteups</code>.
     *
     * @param numWriteups
     *     The number of write-ups to be set for this student.
     */
    public void setNumWriteups(final int numWriteups) {
        this.numWriteups = numWriteups;
    }

    /**
     * Returns a deep clone of this student.
     *
     * @return
     *     A new <code>Student</code> with the same name, id-number, and number of write-ups.
     */
    @Override
    public Student clone() {
        return new Student(this.name, this.idNumber, this.numWriteups);
    }

    /**
     * Returns a string of this student's name, id-number, and number of write-ups.
     *
     * @return
     *     A string representation of this student's name, id-number, and number of write-ups.
     */
    @Override
    public String toString() {
        return "[name: " + this.getName() + ", id: " + this.getName() + ", write-ups: " + this.numWriteups + "]";
    }

    /**
     * Checks if the specified object is equal to this student.
     *
     * @param other
     *     The object that is being checked for equality.
     * @return
     *     <code>true</code> if this student and <code>other</code> are equal, <code>false</code> if not.
     */
    @Override
    public boolean equals(Object other) {
        if(!(other instanceof Student)) {
            return false;
        }

        Student student = (Student) other;
        return student.getName().equals(this.name)
                && student.getIdNumber() == this.idNumber
                && student.getNumWriteups() == this.numWriteups;
    }
}
