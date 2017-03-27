package hw4;

import java.io.*;
import java.util.Properties;
import java.util.stream.Stream;

/**
 * The {@code OfficeProperties} class handles the file I/O and grabs the required properties
 * to run the program.
 *
 * @author Mankirat Gulati
 *    email: mankirat.gulati@stonybrook.edu
 *    Stony Brook ID: 111161128
 */
public class OfficeProperties {
    private final Properties properties;
    //private final String[] values;

    /**
     * Initializes values to a {@code String} array of size {@code 8} to handle the 8 properties in the file.
     */
    public OfficeProperties() {
        this.properties = new Properties();
        //this.values = new String[8];

    }

    /**
     * Returns the file's properties as one string separated by the delimiter '~'.
     *
     * @param fileName The path or name of the file whose properties are being fetched.
     * @return The file's properties separated by a delimiter, '~' in {@code String} representation.
     * @throws IOException
     *     Indicates the path to the file is incorrect or a file by that name does not exist.
     */
    /*public String getData(final String fileName) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(fileName));

        String data = "";
        while(in.ready()) {
            data+=in.readLine() + "~";
        }

        parse(data.substring(0, data.length() - 1));
        return data;
    }*/

    public void load(final String fileName) throws IOException {
        InputStream stream = getClass().getClassLoader().getResourceAsStream(fileName);

        if(stream != null) {
            properties.load(stream);
        } else {
            throw new FileNotFoundException(String.format(Lang.INVALID_FILE, fileName));
        }
    }

    /**
     * Parses the string using the delimiter '~' and adds the values for each property in the values array.
     *
     * @param data The string that is being parsed.
     */
    /*private void parse(String data) {
        int i = 0;
        for(String property: data.split("~")) {
            values[i++] = property.substring(property.indexOf(":") + 1);
        }
    }*/

    /**
     * Returns the number of courses.
     *
     * @return The number of courses specified in the file.
     */
    public int getNumCourses() {
        return Integer.parseInt(properties.getProperty("numCourses"));
    }

    /**
     * Returns an array of the different course numbers.
     *
     * @return An {@code int} array the course numbers specified in the file.
     */
    public int[] getCourseNumbers() {
        return Stream.of(properties.getProperty("courseNumbers")
                .split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    /**
     * Returns an array of the arrival probabilities for each course.
     *
     * @return A {@code double} array of the arrival probabilities for each course
     * specified in the file.
     */
    public double[] getArrivalProbabilities() {
        return Stream.of(properties.getProperty("arrivalProbabilities")
                .split(" "))
                .mapToDouble(Double::parseDouble)
                .toArray();
    }

    /**
     * Returns the minimum time needed to help a student.
     *
     * @return The minimum time needed to help a student specified in the file.
     */
    public int getMinTime() {
        return Integer.parseInt(properties.getProperty("minTime"));
    }

    /**
     * Returns the maximum time needed to help a student.
     *
     * @return The maximum time needed to help a student specified in the file.
     */
    public int getMaxTime() {
        return Integer.parseInt(properties.getProperty("maxTime"));
    }

    /**
     * Returns the number of coffee of cups the professor drinks.
     *
     * @return The number of coffee of cups the professor drinks specified in the file.
     */
    public int getNumCups() {
        return Integer.parseInt(properties.getProperty("numCups"));
    }

    /**
     * Returns the time period a student can enter for office hours.
     *
     * @return The time period a student can enter for office hours specified in the file.
     */
    public int getSimulationTime() {
        return Integer.parseInt(properties.getProperty("simulationTime"));
    }

    /**
     * Returns the number of TAs that will be available to help students.
     *
     * @return The number of TAs that will be available to help students specified in the file.
     */
    public int getNumTAs() {
        return Integer.parseInt(properties.getProperty("numTAs"));
    }
}
