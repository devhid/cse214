package hw1;

import java.util.Scanner;

/**
 * The <code>ResidenceManager</code> class is a driver class that handles I/O for menu operations.
 *
 * @author Mankirat Gulati
 *    email: mankirat.gulati@stonybrook.edu
 *    Stony Brook ID: 111161128
 */

public class ResidenceManager {
    // Handles all input from the user.
    private final Scanner input;

    // Array of floors.
    private final Floor[] floors;

    // Keep track of the current floor.
    private int currentFloor = 0;

    // Initializes the Scanner object and the Floor array with three floors.
    private ResidenceManager() {
        this.input = new Scanner(System.in);
        this.floors = new Floor[] { new Floor(), new Floor(), new Floor() };
    }

    public static void main(String[] args) {
        new ResidenceManager().init();
    }

    private void init() {
        System.out.println(Lang.DESCRIPTION);
        System.out.printf(Lang.MENU, "A) Add A Student", "R) Remove A Student",
                "S) Swap Students", "M) Move Student", "S) Select Floor",
                "C) Copy Floor", "P) Print Current Floor",
                "W) Write Up Student", "Q) Quit");

        reoperate();
    }

    // Used to ask for the next operation upon invocation.
    private void reoperate() {
        System.out.print(Lang.INPUT_OPTION);

        try {
            operateMenu();
        } catch (IllegalArgumentException ex) {
            System.out.print("\n" + ex.getMessage());
            reoperate();
        }
    }

    // Handles the I/O for all menu operations.
    private void operateMenu() {
        switch (input.nextLine().toUpperCase().trim()) {
            case "A":
                System.out.println(Lang.LABEL_ADD_STUDENT);

                System.out.print(Lang.INPUT_NAME);
                String name = validate("STRING", Lang.INVALID_NAME);

                System.out.print(Lang.INPUT_ID);
                int id = Integer.parseInt(validate("INTEGER", Lang.INVALID_ID));

                System.out.printf(Lang.INPUT_ROOM, "a");
                int addSpot = Integer.parseInt(validate("INTEGER", format(Lang.INVALID_ROOM, "a")));

                this.addStudent(name, id, addSpot);
                break;
            case "R":
                System.out.println(Lang.LABEL_REMOVE_STUDENT);

                System.out.printf(Lang.INPUT_ROOM, "a");
                int remSpot = Integer.parseInt(validate("INTEGER", format(Lang.INVALID_ROOM, "a")));

                this.removeStudent(remSpot);
                break;
            case "S":
                System.out.println(Lang.LABEL_SWAP_STUDENTS);

                System.out.printf(Lang.INPUT_FLOOR, "Student #1's");
                int firstFloor = Integer.parseInt(validate("INTEGER", format(Lang.INVALID_FLOOR, "Student #1's")));

                System.out.printf(Lang.INPUT_ROOM, "Student #1's");
                int firstRoom = Integer.parseInt(validate("INTEGER", format(Lang.INVALID_ROOM, "Student #1's")));

                System.out.printf(Lang.INPUT_FLOOR, "Student #2's");
                int secondFloor = Integer.parseInt(validate("INTEGER", format(Lang.INVALID_FLOOR, "Student #2's")));

                System.out.printf(Lang.INPUT_ROOM, "Student #2's");
                int secondRoom = Integer.parseInt(validate("INTEGER", format(Lang.INVALID_ROOM, "Student #2's")));

                this.swapStudents(firstFloor, firstRoom, secondFloor, secondRoom);
                break;
            case "M":
                System.out.println(Lang.LABEL_MOVE_STUDENT);

                System.out.printf(Lang.INPUT_FLOOR, "source");
                int sourceFloor = Integer.parseInt(validate("INTEGER", format(Lang.INVALID_FLOOR, "source")));

                System.out.printf(Lang.INPUT_ROOM, "source");
                int sourceRoom = Integer.parseInt(validate("INTEGER", format(Lang.INVALID_ROOM, "source")));

                System.out.printf(Lang.INPUT_FLOOR, "destination");
                int destFloor = Integer.parseInt(validate("INTEGER", format(Lang.INVALID_FLOOR, "destination")));

                System.out.printf(Lang.INPUT_ROOM, "destination");
                int destRoom = Integer.parseInt(validate("INTEGER", format(Lang.INVALID_ROOM, "destination")));

                this.moveStudent(sourceFloor, sourceRoom, destFloor, destRoom);
                break;
            case "F":
                System.out.println(Lang.LABEL_SELECT_FLOOR);

                System.out.printf(Lang.INPUT_FLOOR, "a");
                int floor = Integer.parseInt(validate("INTEGER", format(Lang.INVALID_FLOOR, "a")));

                this.selectFloor(floor);
                break;
            case "C":
                System.out.println(Lang.LABEL_COPY_FLOOR);

                System.out.printf(Lang.INPUT_FLOOR, "source");
                int copy = Integer.parseInt(validate("INTEGER", format(Lang.INVALID_FLOOR, "source")));

                System.out.printf(Lang.INPUT_FLOOR, "destination");
                int copyTo = Integer.parseInt(validate("INTEGER", format(Lang.INVALID_FLOOR, "destination")));

                this.copyFloor(copy, copyTo);
                break;
            case "P":
                System.out.println("\nFloor " + (currentFloor + 1) + ": ");
                System.out.printf("%-9s %-32s %-5s %s\n",
                        "Room", "Name", "ID", "Writeups");
                System.out.print("------------------------------" +
                        "-------------------------------\n");

                for(int i = 0; i < floors[currentFloor].count(); i++) {
                    Student student = floors[currentFloor].getStudent(i);

                    System.out.printf(" %-8d %-32s %-8d %d\n",
                            i + 1, student.getName(), student.getIdNumber(),
                            student.getNumWriteups());

                }
                reoperate();
                break;
            case "W":
                System.out.println(Lang.LABEL_WRITE_UP_STUDENT);

                System.out.print(Lang.INPUT_NAME);
                String written = validate("STRING", Lang.INVALID_NAME);

                this.writeUpStudent(written);
                break;
            case "Q":
                System.out.print(Lang.QUIT);
                System.exit(0);
                break;
            default:
                System.out.print(Lang.INVALID_OPERATION);
                reoperate();
                break;
        }
    }

    private void addStudent(final String name, final int id, final int room) {
        // Check if the floor already has a student with the specified id.
        if(floors[currentFloor].hasID(id)) {
            throw new IllegalArgumentException(Lang.EXCEPTION_ALREADY_HAS_ID);
        }

        try {
            floors[currentFloor].addStudent(new Student(name, id, 0), room - 1);
        } catch (FullFloorException ex) {
            System.out.print("\n" + ex.getMessage());
            reoperate();
        }

        System.out.printf(Lang.SUCCESS_ADD_STUDENT,
                name, currentFloor + 1, room);
        reoperate();
    }

    private void removeStudent(final int room) {
        Student removed = null;

        try {
            removed = floors[currentFloor].removeStudent(room - 1);
        } catch (EmptyFloorException ex) {
            System.out.print("\n" + ex.getMessage());
            reoperate();
        }

        System.out.printf(Lang.SUCCESS_REMOVE_STUDENT,
                removed.getName(), currentFloor + 1);
        reoperate();
    }

    private void swapStudents(final int sourceFloor, final int sourceRoom, final int destFloor, final int destRoom) {
        this.checkFloors(sourceFloor, destFloor);

        Student swap = floors[sourceFloor - 1].getStudent(sourceRoom - 1);
        Student swapWith = floors[destFloor - 1].getStudent(destRoom - 1);

        floors[sourceFloor - 1].setStudent(swapWith, sourceRoom - 1);
        floors[destFloor - 1].setStudent(swap, destRoom - 1);

        System.out.printf(Lang.SUCCESS_SWAP_STUDENTS,
                swap.getName(), swapWith.getName());
        reoperate();
    }

    private void moveStudent(final int sourceFloor, final int sourceRoom, final int destFloor, final int destRoom) {
        this.checkFloors(sourceFloor, destFloor);

        Student student = null;

        // Attempt to move the player.
        try {
            // Remove the specified player.
            student = floors[sourceFloor - 1].removeStudent(sourceRoom - 1);

            // Add the player to the new destination.
            floors[destFloor - 1].addStudent(student, destRoom - 1);
        } catch (IllegalArgumentException | EmptyFloorException | FullFloorException ex) {

            // If it fails, make sure the student remains where he or she was.
            if(ex.getMessage().equals(Lang.EXCEPTION_INVALID_POSITION)) {
                try {
                    floors[sourceFloor - 1].addStudent(student, sourceRoom - 1);
                } catch (FullFloorException exc) {
                    System.out.print("\n" + exc.getMessage());
                    reoperate();
                }
            }

            System.out.print("\n" + ex.getMessage());
            reoperate();
        }

        System.out.printf(Lang.SUCCESS_MOVE_STUDENT,
                student.getName(), destFloor, destRoom);
        reoperate();
    }

    private void selectFloor(final int floor) {
        this.checkFloors(floor);

        currentFloor = floor - 1;

        System.out.printf(Lang.SUCCESS_SELECT_FLOOR, floor);
        reoperate();
    }

    private void copyFloor(final int copy, final int copyTo) {
        this.checkFloors(copy, copyTo);

        floors[copyTo - 1] = floors[copy - 1].clone();

        System.out.printf(Lang.SUCCESS_COPY_FLOOR, copy, copyTo);
        reoperate();
    }

    private void writeUpStudent(final String name) {
        Student student = floors[currentFloor].getStudent(name);
        if(student == null) {
            System.out.print("\n" + Lang.EXCEPTION_NULL_STUDENT);
            reoperate();
            return;
        }
        student.setNumWriteups(student.getNumWriteups() + 1);

        // Remove student if he or she reaches maximum write-ups.
        if(student.getNumWriteups() == student.getMaxWriteups()) {
            floors[currentFloor].removeStudent(student);

            System.out.printf(Lang.SUCCESS_WRITE_UP_STUDENT_REMOVED,
                    student.getName(), currentFloor + 1);
        } else {
            System.out.printf(Lang.SUCCESS_WRITE_UP_STUDENT,
                    student.getName(), student.getNumWriteups());
        }

        reoperate();
    }

    // Used to replace placeholders in messages with a specific string.
    private String format(final String msg, final String replace) {
        return msg.replace("%s", replace);
    }

    // Checks if the floor numbers are valid.
    private void checkFloors(final int... floors) {
        for(int floor: floors) {
            if(floor < 1 || floor > this.floors.length) {
                throw new IllegalArgumentException(Lang.EXCEPTION_INVALID_FLOOR);
            }
        }
    }

    // Ensures input is of the expected type.
    private String validate(final String expected, final String errorMessage) {
        String regex, valid;

        switch(expected.toUpperCase()) {
            case "STRING": regex = "^[a-zA-Z ]+$"; break;
            case "INTEGER": regex = "[0-9]+"; break;
            default: regex = "[a-zA-Z0-9]+";
        }

        // Keeps on asking for input if input is invalid.
        valid = input.nextLine();
        while(!valid.matches(regex)) {
            System.out.print(errorMessage);
            valid = input.nextLine();
        }

        // Removes any extra spaces that might be part of the input.
        return expected.equalsIgnoreCase("INTEGER")
                ? valid.replace(" ", "") : valid.trim().replaceAll("\\s+", " ");
    }

}