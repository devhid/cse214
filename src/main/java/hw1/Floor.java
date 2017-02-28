package hw1;

import hw1.exceptions.EmptyFloorException;
import hw1.exceptions.FullFloorException;

/**
 * The <code>Floor</code> class implements a floor of <code>Student</code> objects.
 *
 * @author Mankirat Gulati
 *    email: mankirat.gulati@stonybrook.edu
 *    Stony Brook ID: 111161128
 */

public class Floor {
    // The maximum amount of students the floor can hold.
    private final int CAPACITY = 50;

    // An array holding the students in the Floor.
    private Student[] students;

    // The number of students currently in this floor.
    private int count;

    /**
     * Initializes students to a new <code>Student</code> array with a size of <code>CAPACITY</code>.<br>
     *
     * <br>
     * Postconditions:<br>
     *     This <code>Floor</code> has been initialized with an empty array of <code>Student</code> objects.
     */
    public Floor() {
        this.students = new Student[CAPACITY];
        this.count = 0;
    }

    /**
     * Returns the array of students.
     *
     * @return
     *     The array holding <code>Student</code> objects.
     */
    public Student[] getStudents() {
        return this.students;
    }

    /**
     * Returns the student at the specified <code>position</code>.
     *
     * @param position
     *     The index used to find the corresponding student in the array.
     * @return
     *     The student in the array located at that certain <code>position</code>.
     * @throws IllegalArgumentException
     *    Indicates that the <code>position</code> is negative or larger than or equal to <code>count()</code>.<br>
     *
     * <br>
     * Preconditions:<br>
     *    This <code>Floor</code> has been instantiated and 0 &#60;= <code>position</code> &#60; <code>count()</code>.<br>
     */
    public Student getStudent(final int position) throws IllegalArgumentException {
        if(position < 0 || position >= count()) {
            throw new IllegalArgumentException(Lang.EXCEPTION_INVALID_POSITION);
        }
        return students[position];
    }

    /**
     * Returns the student with the specified <code>name</code>.
     *
     * @param name
     *     The name of the student to retrieve.
     * @return
     *     The student whose name equals the specified <code>name</code>, <code>null</code> if no such student exists.
     * @throws NullPointerException
     *    Indicates that there is no student in the array by that name.
     */
    public Student getStudent(final String name) throws NullPointerException {
        for(int i = 0; i < count(); i++) {
            if(students[i].getName().equals(name)) {
                return students[i];
            }
        }
        return null;
    }

    /**
     * Sets the <code>student</code> in the specified <code>position</code> in the floor.
     *
     * @param student
     *     The student that will be set inside inside the array.
     * @param position
     *     The index in the array that the student will be placed in.
     * @throws IllegalArgumentException
     *    Indicates that the <code>position</code> is negative or larger than the <code>count()</code>.<br>
     *
     * <br>
     * Preconditions:<br>
     *     This <code>Floor</code> has been instantiated and 0 &#60;= <code>position</code> &#60;= <code>count()</code>.<br>
     *
     * <br>
     * Postconditions:<br>
     *     The <code>student</code> is now placed at the desired <code>position</code> in this <code>Floor</code>.
     */
    public void setStudent(final Student student, final int position) throws IllegalArgumentException {
        if(position < 0 || position > count()) {
            throw new IllegalArgumentException(Lang.EXCEPTION_INVALID_POSITION);
        }

        if(students[position] == null) {
            count++;
        }

        students[position] = student;
    }

    /**
     * Adds the <code>student</code> at the specified <code>position</code> in the floor.
     *
     * @param student
     *     The student to be added into the array.
     * @param position
     *     The index in the array the <code>student</code> will be placed in.
     * @throws IllegalArgumentException
     *    Indicates the <code>position</code> is negative or larger than <code>count()</code>.
     * @throws FullFloorException
     *    Indicates that no student can be added because the floor size has reached its <code>CAPACITY</code>.<br>
     *
     * <br>
     * Preconditions:<br>
     *     This <code>Floor</code> has been instantiated and 0 &#60;= <code>position</code> &#60; <code>count()</code>.<br>
     *     The number of students in this <code>Floor</code> is less than <code>CAPACITY</code>.<br>
     *
     * <br>
     * Postconditions:<br>
     *     The <code>student</code> will be placed in the specified <code>position</code> in this <code>Floor</code>.<br>
     *     All other students that were in positions greater than or equal to the specified <code>position</code> will be shifted one position to the right.
     */
    public void addStudent(final Student student, final int position) throws IllegalArgumentException, FullFloorException {
        if(position < 0 || position > count()) {
            throw new IllegalArgumentException(Lang.EXCEPTION_INVALID_POSITION);
        }

        if(count() == CAPACITY) {
            throw new FullFloorException(Lang.EXCEPTION_FULL_FLOOR);
        }

        System.arraycopy(students, position, students, position + 1, CAPACITY - 1 - position);

        count++;
        students[position] = student;
    }

    /**
     * Removes the student in the specified <code>position</code> in the floor.
     *
     * @param position
     *     The index used to find the student in the array.
     * @return
     *     The student that was previously located at <code>position</code> in the array.
     * @throws IllegalArgumentException
     *    Indicates the <code>position</code> is negative or larger than or equal to <code>count()</code>.
     * @throws EmptyFloorException
     *    Indicates the floor is empty, therefore no student can be removed.<br>
     *
     * <br>
     * Preconditions:<br>
     *    This <code>Floor</code> has been instantiated and 0 &#60;= <code>position</code> &#60; <code>count()</code>.<br>
     *
     * <br>
     * Postconditions:<br>
     *    The student at the desired <code>position</code> will be removed from this <code>Floor</code> and returned.<br>
     *    All students that are located to the right of the <code>position</code> will be shifted one position to the left.
     */
    public Student removeStudent(final int position) throws IllegalArgumentException, EmptyFloorException {
        if(position < 0 || position >= count()) {
            throw new IllegalArgumentException(Lang.EXCEPTION_INVALID_POSITION);
        }

        if(isEmpty()) {
            throw new EmptyFloorException(Lang.EXCEPTION_EMPTY_FLOOR);
        }

        Student student = getStudent(position);
        students[position] = null;

        count--;
        System.arraycopy(students, position + 1, students, position, CAPACITY - 1 - position);

        return student;
    }

    /**
     * Removes the <code>student</code> from the floor.
     *
     * @param student
     *     The student to be removed from the array.
     */
    public void removeStudent(final Student student) {
        for(int i = 0; i < count(); i++) {
            if(students[i].getName().equals(student.getName())) {
                try {
                    removeStudent(i);
                } catch (EmptyFloorException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }

    /**
     * Returns the number of students in the floor.
     *
     * @return
     *     The number of <code>Student</code> objects in the array.
     */
    public int count() {
        return this.count;
    }

    /**
     * Returns whether this floor is empty or not.
     *
     * @return
     *     <code>true</code> if array is empty, <code>false</code> if not.
     */
    public boolean isEmpty() {
        return count() == 0;
    }

    /**
     * Returns whether the floor already has a student with the specified <code>id</code>.
     *
     * @param id
     *     The id number that is being checked against the array of students.
     * @return
     *     <code>true</code> if the student array contains a student with the specified <code>id</code>, <code>false</code> otherwise.
     */
    public boolean hasID(final int id) {
        for(int i = 0; i < count(); i++) {
            if(students[i].getIdNumber() == id) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a deep copy of this floor.
     *
     * @return
     *     A new <code>Floor</code> with a deep clone of the students from this <code>Floor</code>.
     */
    @Override
    public Floor clone() {
        Floor clone = new Floor();

        for(int i = 0; i < count(); i++) {
            clone.getStudents()[i] = students[i].clone();
        }
        clone.count = this.count;

        return clone;
    }

    /**
     * Returns a string of all the students' names in the floor.
     *
     * @return
     *     A <code>String</code> representation of all the names of the students in the array.
     */
    @Override
    public String toString() {
        String out = "";

        for(int i = 0; i < count(); i++) {
            out += ", " + students[i].getName();
        }

        return out.substring(2);
    }

    /**
     * Checks if the specified object is equal to this floor.
     *
     * @param other
     *     The object that is being checked for equality.
     * @return
     *     <code>true</code> if this floor and <code>other</code> are equal, <code>false</code> if not.
     */
    @Override
    public boolean equals(Object other) {
        if(!(other instanceof Floor)) {
            return false;
        }

        Floor floor = (Floor) other;
        if(count != floor.count) {
            return false;
        }

        for(int i = 0; i < count(); i++) {
            if(students[i] != floor.getStudents()[i]) {
                return false;
            }
        }

        return true;
    }
}