package hw4;

import java.io.IOException;
import java.util.*;

/**
 * The {@code OfficeHourSimulator} class acts as the driver for the entire simulation and contains the algorithms
 * used to implement the simulation.
 *
 * @author Mankirat Gulati
 *    email: mankirat.gulati@stonybrook.edu
 *    Stony Brook ID: 111161128
 */
public class OfficeHourSimulator {
    // The properties class holding the file values.
    private static final OfficeProperties properties = new OfficeProperties();

    // A new instance of the Statistics class to keep track of data.
    private static final Statistics statistics = new Statistics();

    // A new instance of a helper being specifically a professor.
    private static final Helper professor = new Helper(true);

    // Used to keep track of the time steps.
    private static int totalTime = 0;
    // Checks whether the simulation is done or not.
    private static boolean done = false;

    // Array holding the TAs.
    private static Helper[] helpers;

    // Array holding the boolean sources.
    private static BooleanSource[] sources;

    // Priority queue used to keep track of students entering in for office hours.
    private static StudentQueue<Student> students;


    // Array holding all the courses.
    private Course[] courses;

    // Used to input file name.
    private final Scanner input;

    // Used to connect course number to arrival probability.
    private final Map<Integer, Double> courseMap;

    // Initializes input into a scanner object and courseMap into a HashMap.
    private OfficeHourSimulator() {
        this.input = new Scanner(System.in);
        this.courseMap = new HashMap<>();
    }

    // Initializes and sets up the simulation.
    private void init() {
        System.out.print(Lang.DESCRIPTION);
        System.out.print(Lang.INPUT_FILE);
        this.setup();
    }

    // Sets up the program and calls the simulate() method.
    private void setup() {
        setupFile();
        setupCourses();
        setupTAs();
        setupSources();
        setupQueue();

        // Start the simulation.
        simulate(properties.getSimulationTime(), properties.getArrivalProbabilities(),
                courses, properties.getMinTime(), properties.getMaxTime(),
                properties.getNumCups(), properties.getNumTAs());
    }

    // Validates file input and exits if there is bad file input.
    private static void validateProperties(int numCourses, int simTime, double[] arriveProb, int[] courseNums,
                                    int min, int max, int cups, int tas) {
        if(numCourses < 1) { throw new IllegalArgumentException(Lang.INVALID_NUM_COURSES); }
        if(simTime < 1) { throw new IllegalArgumentException(Lang.INVALID_SIMULATION_TIME); }
        if(min < 1) { throw new IllegalArgumentException(Lang.INVALID_MIN_TIME); }
        if(max < 1) { throw new IllegalArgumentException(Lang.INVALID_MAX_TIME); }

        if(cups < 0) { throw new IllegalArgumentException(Lang.INVALID_NUM_CUPS); }
        if(tas < 0) { throw new IllegalArgumentException(Lang.INVALID_NUM_TAS); }
        if(min > max) { throw new IllegalArgumentException(Lang.MIN_TIME_LARGER); }

        for(double value: arriveProb) {
            if(value < 0) { throw new IllegalArgumentException(Lang.INVALID_PROBABILITY); }
        }

        for(int value: courseNums) {
            if(value < 0) { throw new IllegalArgumentException(String.format(Lang.INVALID_COURSE_NUMBER, value)); }
        }

        if(numCourses != arriveProb.length || numCourses != courseNums.length) {
            throw new IllegalArgumentException(Lang.UNEQUAL_NUM_COURSES);
        }

        Set<Integer> dupes = new HashSet<>();
        for (int i = 0; i < courseNums.length; i++) {
            if (!dupes.add(courseNums[i])) {
                throw new IllegalArgumentException(Lang.DUPLICATE_COURSE_NUMBERS);
            }
        }
    }

    // Starts the main program function and catches exceptions.
    public static void main(String[] args) {
        OfficeHourSimulator simulator = new OfficeHourSimulator();
        try {
            simulator.init();
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            System.out.print("\n" + Lang.INVALID_NUMBER);
            System.out.print(Lang.ASK_TO_RERUN);
            System.exit(0);
        } catch (IllegalArgumentException ex) {
            System.out.print("\n" + ex.getMessage());
            System.out.print(Lang.ASK_TO_RERUN);
            System.exit(0);
        }
    }

    // Displays the file properties in a table fashion.
    private static void displayProperties(Course[] courses, int numTAs, int numCups, int minTime,
                                          int maxTime, int officeHourTime) {

        System.out.printf(Lang.PROPERTIES_LABEL_FORMAT, "Course", "Probability");
        System.out.print(Lang.PROPERTIES_DIVIDER);

        for(Course course: courses) {
            System.out.printf(Lang.PROPERTIES_DATA_FORMAT, course.getCourseNumber(), course.getArrivalProbability());
        }

        System.out.println();
        System.out.printf(Lang.PROPERTY_NUM_TAS, numTAs);
        System.out.printf(Lang.PROPERTY_NUM_CUPS, numCups);
        System.out.printf(Lang.PROPERTY_BASE_TIME_INTERVAL, minTime, maxTime);
        System.out.printf(Lang.PROPERTY_TIME, officeHourTime);
    }

    // Handles student arriving logic and display students who arrive.
    private static void displayStudents(Course[] courses, int minTime, int maxTime) {
        for(int i = 0; i < courses.length; i++) {
            if(sources[i].occurs() && totalTime <= properties.getSimulationTime()) {
                Student student = new Student(totalTime, courses[i]);
                student.setTimeRequired((int) (Math.random() * (maxTime - minTime + 1) + minTime));

                statistics.addStudent(student);
                students.enqueue(student);

                System.out.printf(Lang.STUDENT_ARRIVED, student.getStudentId(), courses[i].getCourseNumber(), student.getTimeRequired());
            } else {
                System.out.printf(Lang.NO_STUDENT_ARRIVED, courses[i].getCourseNumber());
            }
        }
    }

    // Handles professor logic and displays their status.
    private static void displayProfessor(int numCups) {
        if(professor.getTimeUntilFree() == 0) {
            Student student = students.dequeue();
            if(student == null) {
                System.out.println(Lang.PROFESSOR_WAITING);
            } else {
                int timeUntilFree = (student.getTimeRequired() - numCups) < 1 ? 1 : student.getTimeRequired() - numCups;
                student.setTimeHelped(totalTime);

                professor.setTimeUntilFree(timeUntilFree);
                professor.setCurrentStudent(student);

                System.out.printf(Lang.PROFESSOR_BUSY, student.getStudentId(), timeUntilFree);
            }
        } else {
            Student current = professor.getCurrentStudent();
            System.out.printf(Lang.PROFESSOR_BUSY, current.getStudentId(), professor.getTimeUntilFree());
        }

        for(Student student: students) {
            student.incrementWaitTime();
        }
    }

    // Handles TA logic and prints their status.
    private static void displayTAs(int numTAs) {
        if (numTAs == 0) { return; }

        for (int i = 0; i < numTAs; i++) {
            Helper helper = helpers[i];

            if (helper.getTimeUntilFree() == 0) {
                Student student = students.dequeue();

                if (student == null) {
                    System.out.printf(Lang.TA_FREE, (i + 1));
                } else {
                    int timeUntilFree = student.getTimeRequired() * 2;
                    student.setTimeHelped(totalTime);

                    helper.setTimeUntilFree(timeUntilFree);
                    helper.setCurrentStudent(student);

                    System.out.printf(Lang.TA_BUSY, (i + 1), student.getStudentId(), timeUntilFree);
                }
            } else {
                Student current = helper.getCurrentStudent();
                System.out.printf(Lang.TA_BUSY, (i + 1), current.getStudentId(), helper.getTimeUntilFree());
            }
        }
    }

    // Handles queue logic and prints the current queue.
    private static void displayQueue() {
        System.out.print(Lang.LABEL_STUDENT_QUEUE);
        System.out.printf(Lang.QUEUE_CATEGORY_FORMAT, "ID", "Course", "Required Time", "Arrival Time");
        System.out.print(Lang.QUEUE_DIVIDER);

        StudentQueue<Student> copy = new StudentQueue<>(students);

        while(!copy.isEmpty()) {
            Student student = copy.dequeue();

            System.out.printf(Lang.QUEUE_DATA_FORMAT,
                    student.getStudentId(), student.getCourse().getCourseNumber(),
                    student.getTimeRequired(), student.getTimeArrived());
        }
    }

    // Returns the students who are finished receiving help.
    private static String[] getFinishedStudents() {
        String[] finished = new String[properties.getNumTAs() + 1];
        Student current;

        if(professor.getTimeUntilFree() == 0) {
            current = professor.getCurrentStudent();

            if(current != null) {
                finished[0] = String.format(Lang.STUDENT_FINISHED, current.getStudentId(),
                        current.getCourse().getCourseNumber(),
                        totalTime - current.getTimedHelped());
                professor.setCurrentStudent(null);
            }
        }

        int i = 1;
        for(Helper helper: helpers) {
            if(helper.getTimeUntilFree() == 0) {
                current = helper.getCurrentStudent();

                if(current != null) {
                    finished[i++] = String.format(Lang.STUDENT_FINISHED, current.getStudentId(),
                            current.getCourse().getCourseNumber(),
                            totalTime - current.getTimedHelped());
                    helper.setCurrentStudent(null);
                }
            }
        }

        return finished;
    }

    // Displays statistics for average wait times.
    public static void displayStatistics(final Course[] courses) {
        System.out.print(Lang.STATISTICS_LABEL);
        System.out.printf(Lang.STATISTICS_FORMAT, "Course", "Students Helped", "Average Time");
        System.out.print(Lang.STATISTICS_DIVIDER);

        System.out.printf(Lang.STATISTICS_TOTAL, "Total", statistics.getTotalStudents(), statistics.getTotalAverageTime());
        for(Course course: courses) {
            int courseNum = course.getCourseNumber();
            System.out.printf(Lang.STATISTICS_DATA, courseNum, statistics.getStudentCount(courseNum), statistics.getAverageTime(courseNum));
        }
    }

    // Checks if the simulation is done.
    private static void checkIfDone() {
        if(professor.getTimeUntilFree() == 0 && totalTime >= properties.getSimulationTime()) { done = true; }

        for(Helper helper: helpers) {
            if(helper.getTimeUntilFree() != 0) {
                done = false;
                break;
            }
        }
    }

    // Handles helper logic (timeUntilFree property)
    private static void processHelpers() {
        checkIfDone();

        int timeUntilFree = professor.getTimeUntilFree();
        professor.setTimeUntilFree(timeUntilFree == 0 ? 0 : professor.getTimeUntilFree() - 1);

        for(Helper helper: helpers) {
            timeUntilFree = helper.getTimeUntilFree();
            helper.setTimeUntilFree(timeUntilFree == 0 ? 0 : helper.getTimeUntilFree() - 1);
        }
    }

    // Contains each logic modules and performs main simulation function.
    public static void simulate(int officeHourTime, double[] arrivalProbability, Course[] courses,
                                int minTime, int maxTime, int numCups, int numTAs) {

        // Display the initial properties used for this simulation.
        displayProperties(courses, numTAs, numCups, minTime, maxTime, officeHourTime);

        System.out.print(Lang.LABEL_BEGIN_SIMULATION);

        while(!done) {
            // Display time step.
            System.out.printf(Lang.TIME_STEP, ++totalTime);
            System.out.print(Lang.TIME_STEP_DIVIDER);

            String[] finished = getFinishedStudents();

            // Display the students that arrive for each course.
            displayStudents(courses, minTime, maxTime);

            System.out.println();

            // Display if the helpers are waiting or helping.
            displayProfessor(numCups);
            displayTAs(numTAs);

            System.out.println();

            // Display the current student queue.
            displayQueue();

            System.out.println();

            // Prints the students who are finished.
            for(int i = 0; i < finished.length; i++) {
                if(finished[i] != null) {
                    System.out.print(finished[i]);
                }
                finished[i] = null;
            }

            System.out.println();

            // Edit the timeUntilFree fields for the helpers.
            processHelpers();
        }

        // Display the statistics.
        displayStatistics(courses);

        System.out.print(Lang.END_SIMULATION);
    }

    // Loads file data.
    private void setupFile() {
        String fileName = input.nextLine();
        try {
            //properties.getData(fileName);
            properties.load(fileName);
        } catch (IOException ex) {
            System.out.printf("\n" + ex.getMessage(), fileName);
            System.out.print(Lang.ASK_TO_RERUN);
            System.exit(0);
        }

        System.out.printf(Lang.SUCCESS_FILE, fileName);

        validateProperties(properties.getNumCourses(), properties.getSimulationTime(),
                properties.getArrivalProbabilities(), properties.getCourseNumbers(),
                properties.getMinTime(), properties.getMaxTime(), properties.getNumCups(), properties.getNumTAs());
    }

    // Sets up the course map wit arrival probability and course numbers.
    private void setupCourseMap() {
        this.courses = new Course[properties.getNumCourses()];

        for(int i = 0; i < courses.length; i++) {
            courseMap.put(properties.getCourseNumbers()[i], properties.getArrivalProbabilities()[i]);
        }
    }

    // Sorts the course numbers.
    private int[] sort(int[] courseNumbers) {
        Arrays.sort(courseNumbers);
        return courseNumbers;
    }

    // Sets up the courses array and the difficulty for each course.
    private void setupCourses() {
        setupCourseMap();

        int[] courseNumbers = sort(properties.getCourseNumbers());
        int courseDifficulty = 0;

        for (int i = 0; i < courses.length; i++) {
            int courseNumber = courseNumbers[i];

            courses[i] = new Course(courseNumbers[i], courseMap.get(courseNumber));
            courses[i].setCourseDifficulty(courseDifficulty++);
        }
    }

    // Initializes the TAs.
    private void setupTAs() {
        helpers = new Helper[properties.getNumTAs()];

        for(int i = 0; i < helpers.length; i++) {
            helpers[i] = new Helper(false);
        }
    }

    // Sets up the boolean sources using the courses's arrival probability.
    private void setupSources() {
        sources = new BooleanSource[courses.length];

        for(int i = 0; i < sources.length; i++) {
            sources[i] = new BooleanSource(courses[i].getArrivalProbability());
        }
    }

    // Sets up the queue with a custom comparator.
    private void setupQueue() {
        Comparator<Student> comparator = (Student s1, Student s2) -> {
            int first = s1.getCourse().getCourseNumber();
            int second = s2.getCourse().getCourseNumber();

            if(first < second) { return 1; }
            if(first > second) { return -1; }

            return s1.getTimeArrived() - s2.getTimeArrived();
        };

        students = new StudentQueue<>(500, comparator);
    }

    // Checks if the specified course number is valid.
    public static void checkCourseNumber(final int courseNumber) {
        for(int number: properties.getCourseNumbers()) {
            if(number == courseNumber) {
                return;
            }
        }
        throw new IllegalArgumentException(String.format(Lang.INVALID_COURSE_NUMBER, courseNumber));
    }
}