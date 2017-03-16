package hw4;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.stream.Stream;

public class OfficeProperties {
    private final Properties properties;

    public OfficeProperties() {
        this.properties = new Properties();
    }

    public void load(final String fileName) {
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

    public int getNumCourses() {
        return Integer.parseInt(properties.getProperty("numCourses"));
    }

    public int getMinTime() {
        return Integer.parseInt(properties.getProperty("minTime"));
    }

    public int getMaxTime() {
        return Integer.parseInt(properties.getProperty("maxTime"));
    }

    public int getNumCups() {
        return Integer.parseInt(properties.getProperty("numCups"));
    }

    public int getSimulationTime() {
        return Integer.parseInt(properties.getProperty("simulationTime"));
    }

    public int getNumTAs() {
        return Integer.parseInt(properties.getProperty("numTAs"));
    }

    public int[] getCourseNumbers() {
        return Stream.of(properties.getProperty("courseNumbers")
                .split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    public double[] getArrivalProbabilities() {
        return Stream.of(properties.getProperty("arrivalProbabilities")
                .split(" "))
                .mapToDouble(Double::parseDouble)
                .toArray();
    }
}
