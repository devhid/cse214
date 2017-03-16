package hw4;

import java.util.*;

public class OfficeHourSimulator {
    private static final OfficeProperties properties = new OfficeProperties();
    private static final Helper professor = new Helper(true);

    private static int totalTime = 0;
    private static Helper[] helpers;
    private static BooleanSource[] sources;
    private static StudentQueue<Student> students;

    private Course[] courses;

    private final Scanner input;
    private final Map<Integer, Double> courseMap;

    private OfficeHourSimulator() {
        this.input = new Scanner(System.in);
        this.courseMap = new HashMap<>();
    }

    private void init() {
        System.out.println("\t\tWelcome to the Office Hours Simulation");
        System.out.print("Please enter the file name (ending with .properties): ");

        String fileName = input.nextLine();
        properties.load(fileName);
        System.out.println("File " + fileName + " loaded.\n");

        this.setup();
    }

    private void setup() {
        setupCourses();
        setupTAs();
        setupSources();
        setupQueue();

        // Start the simulation.
        simulate(properties.getSimulationTime(), properties.getArrivalProbabilities(),
                courses, properties.getMinTime(), properties.getMaxTime(),
                properties.getNumCups(), properties.getNumTAs());
    }

    public static void main(String[] args) {
        new OfficeHourSimulator().init();
    }

    public static void simulate(int officeHourTime, double[] arrivalProbability, Course[] courses,
                                int minTime, int maxTime, int numCups, int numTAs) {

        System.out.printf("%-8s |%s\n", "Course", "Probability");
        System.out.println("----------------------");
        for(Course course: courses) {
            System.out.printf("%-8s |%s\n", course.getCourseNumber(), course.getArrivalProbability());
        }

        System.out.println();
        System.out.println("Number of TAs: " + numTAs);
        System.out.println("Coffee Cups: " + numCups);
        System.out.println("Base Time Interval: " + minTime + "-" + maxTime + " minutes");
        System.out.println("Time: " + officeHourTime + " minutes");

        System.out.println("\nBegin Simulation:");

        while(totalTime != officeHourTime) {
            System.out.println("Time Step " + ++totalTime);
            System.out.println("----------------------------------------------------");

            for(int i = 0; i < courses.length; i++) {
                if(sources[i].occurs()) {
                    Student student = new Student(totalTime, courses[i]);
                    student.setTimeRequired((int) (Math.random() * (maxTime - minTime + 1) + minTime));

                    students.enqueue(student);

                    System.out.println("Student " + student.getStudentId() + " has arrived for " + courses[i].getCourseNumber() + " requiring " + student.getTimeRequired() + " minutes.");
                } else {
                    System.out.println("No students have arrived for " + courses[i].getCourseNumber() + ".");
                }
            }

            System.out.println();

            if(professor.getTimeUntilFree() == 0) {
                Student student = students.dequeue();
                if(student == null) {
                    System.out.println("Professor is waiting for the next student to arrive.");
                } else {
                    int timeUntilFree = (student.getTimeRequired() - numCups) < 1 ? 1 : student.getTimeRequired() - numCups;
                    professor.setTimeUntilFree(timeUntilFree);
                    professor.setCurrentStudent(student);

                    System.out.println("Professor is helping Student " + student.getStudentId() + ", "
                            + timeUntilFree + " minutes remaining.");
                }
            } else {
                Student current = professor.getCurrentStudent();
                System.out.println("Professor is helping Student " + current.getStudentId() + ", "
                        + professor.getTimeUntilFree() + " minutes remaining.");
            }

            if(numTAs != 0) {
                for (int i = 0; i < numTAs; i++) {
                    Helper helper = helpers[i];

                    if (helper.getTimeUntilFree() == 0) {
                        Student student = students.dequeue();

                        if(student == null) {
                            System.out.println("TA " + (i + 1) + " is waiting for the next student to arrive.");
                        } else {
                            int timeUntilFree = student.getTimeRequired() * 2;

                            helper.setTimeUntilFree(timeUntilFree);
                            helper.setCurrentStudent(student);

                            System.out.println("TA " + (i + 1) + " is helping Student " + student.getStudentId() + ", "
                                    + helper.getTimeUntilFree() + " minutes remaining.");
                        }
                    } else {
                        Student current = helper.getCurrentStudent();

                        System.out.println("TA " + (i + 1) + " is helping Student " + current.getStudentId() + ", "
                                + helper.getTimeUntilFree() + " minutes remaining.");
                    }
                }
            }

            System.out.println();
            System.out.println("Student Queue:");
            System.out.printf("%-4s |%-10s |%-16s |%s\n", "ID", "Course", "Required Time", "Arrival Time");
            System.out.println("----------------------------------------------------");

            StudentQueue<Student> copy = new StudentQueue<>(students);
            while(!copy.isEmpty()) {
                Student student = copy.dequeue();
                System.out.printf("%-4d |%-10d |%-16d |%d\n",
                        student.getStudentId(), student.getCourse().getCourseNumber(),
                        student.getTimeRequired(), student.getTimeArrived());
            }

            int timeUntilFree = professor.getTimeUntilFree();
            professor.setTimeUntilFree(timeUntilFree == 0 ? 0 : professor.getTimeUntilFree() - 1);

            for(Helper helper: helpers) {
                timeUntilFree = helper.getTimeUntilFree();
                helper.setTimeUntilFree(timeUntilFree == 0 ? 0 : helper.getTimeUntilFree() - 1);
            }

            System.out.println();
        }
    }

    private void setupCourseMap() {
        this.courses = new Course[properties.getNumCourses()];

        for(int i = 0; i < courses.length; i++) {
            courseMap.put(properties.getCourseNumbers()[i], properties.getArrivalProbabilities()[i]);
        }
    }

    private int[] sort(int[] courseNumbers) {
        Arrays.sort(courseNumbers);
        return courseNumbers;
    }

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

    private void setupTAs() {
        helpers = new Helper[properties.getNumTAs()];

        for(int i = 0; i < helpers.length; i++) {
            helpers[i] = new Helper(false);
        }
    }

    private void setupSources() {
        sources = new BooleanSource[courses.length];

        for(int i = 0; i < sources.length; i++) {
            sources[i] = new BooleanSource(courses[i].getArrivalProbability());
        }
    }

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

    public static void checkCourseNumber(final int courseNumber) {
        for(int number: properties.getCourseNumbers()) {
            if(number == courseNumber) {
                return;
            }
        }

        throw new IllegalArgumentException("Invalid course number.");
    }
}