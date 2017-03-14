package hw4;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class OfficeHourSimulator {
    private static final OfficeProperties properties = new OfficeProperties();

    private Course[] courses;

    private final Scanner input;
    private final Map<Integer, Double> courseMap;

    public OfficeHourSimulator() {
        this.input = new Scanner(System.in);
        this.courseMap = new HashMap<>();
    }

    private void init() {
        System.out.print("Please enter the file name (ending with .properties): ");
        properties.setup(input.nextLine());

        setupCourses();

        simulate(properties.getSimulationTime(), properties.getArrivalProbabilities(),
                courses, properties.getMinTime(), properties.getMaxTime(),
                properties.getNumCups(), properties.getNumTAs());
    }



    public static void main(String[] args) {
        new OfficeHourSimulator().init();
    }

    public static void simulate(int officeHourTime, double[] arrivalProbability, Course[] courses,
                                int minTime, int maxTime, int numCups, int numTAs) {
        for(Course course: courses) {
            System.out.println(course.toString());
        }
    }

    private void setupCourses() {
        setupCourseMap();

        int[] courseNumbers = properties.getCourseNumbers();
        sortCourseNumbers(courseNumbers);

        int courseNumber, courseDifficulty = 0;
        for (int i = 0; i < courses.length; i++) {
            courseNumber = courseNumbers[i];

            courses[i] = new Course(courseNumbers[i], courseMap.get(courseNumber));
            courses[i].setCourseDifficulty(courseDifficulty++);
        }
    }

    private void sortCourseNumbers(int[] courseNumbers) {
        Arrays.sort(courseNumbers);
    }

    private void setupCourseMap() {
        for(int i = 0; i < courses.length; i++) {
            courseMap.put(properties.getCourseNumbers()[i], properties.getArrivalProbabilities()[i]);
        }
    }

    public static OfficeProperties getProperties() {
        return properties;
    }
}