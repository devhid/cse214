package hw4;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class OfficeHourSimulator {
    private final Scanner input;

    private static OfficeProperties properties;
    private Course[] courses;
    private Map<Integer, Double> courseMap;

    public OfficeHourSimulator() {
        this.input = new Scanner(System.in);
        this.courseMap = new HashMap<>();
    }

    private void init() {
        System.out.print("Please enter the file name (ending with .properties): ");
        properties = new OfficeProperties(input.nextLine());

        courses = new Course[properties.numCourses()];
        setupCourses();

        simulate(properties.simulationTime(), properties.arrivalProbabilities(), courses,
                properties.minTime(), properties.maxTime(), properties.numCups(), properties.numTAs());
    }

    public static void main(String[] args) {
        new OfficeHourSimulator().init();
    }

    public static void simulate(int officeHourTime, double[] arrivalProbability, Course[] courses,
                                int minTime, int maxTime, int numCups, int numTAs) {

    }

    private void setupCourses() {
        setupCourseMap();
        sortCourseNumbers();

        int courseNumber, courseDifficulty = 0;
        for (int i = 0; i < courses.length; i++) {
            courseNumber = properties.courseNumbers()[i];

            courses[i] = new Course(properties.courseNumbers()[i], courseMap.get(courseNumber));
            courses[i].setCourseDifficulty(courseDifficulty++);
        }
    }

    private void sortCourseNumbers() {
        Arrays.sort(properties.courseNumbers());
    }

    private void setupCourseMap() {
        for(int i = 0; i < courses.length; i++) {
            courseMap.put(properties.courseNumbers()[i], properties.arrivalProbabilities()[i]);
        }
    }

    public static OfficeProperties getProperties() {
        return properties;
    }
}
