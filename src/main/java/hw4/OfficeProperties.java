package hw4;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.stream.Stream;

public class OfficeProperties {
    private final String fileName;
    private final Properties properties;

    public OfficeProperties(final String fileName) {
        this.fileName = fileName;
        this.properties = new Properties();

        load();
    }

    private void load() {
        try(InputStream stream = getClass().getClassLoader().getResourceAsStream(fileName)) {
            if(stream != null) {
                properties.load(stream);
            } else {
                throw new FileNotFoundException("The file, " + fileName + ".properties could not be found.");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public int numCourses() {
        return Integer.parseInt(properties.getProperty("numCourses"));
    }

    public int minTime() {
        return Integer.parseInt(properties.getProperty("minTime"));
    }

    public int maxTime() {
        return Integer.parseInt(properties.getProperty("maxTime"));
    }

    public int numCups() {
        return Integer.parseInt(properties.getProperty("numCups"));
    }

    public int simulationTime() {
        return Integer.parseInt(properties.getProperty("simulationTime"));
    }

    public int numTAs() {
        return Integer.parseInt(properties.getProperty("numTAs"));
    }

    public int[] courseNumbers() {
        return Stream.of(properties.getProperty("courseNumbers")
                .split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    public double[] arrivalProbabilities() {
        return Stream.of(properties.getProperty("arrivalProbabilites")
                .split(" "))
                .mapToDouble(Double::parseDouble)
                .toArray();
    }
}
