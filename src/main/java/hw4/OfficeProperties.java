package hw4;

import java.io.*;
import java.util.Properties;
import java.util.stream.Stream;

public class OfficeProperties {
    private final Properties properties;
    private final String[] values;

    public OfficeProperties() {
        this.properties = new Properties();
        this.values = new String[9];

    }

    public String getData(final String fileName) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader(fileName));

        String data = "";
        while(in.ready()) {
            data+=in.readLine() + "~";
        }

        parse(data.substring(0, data.length() - 1));
        return data;
    }

    public void load(final String fileName) throws IOException{
        InputStream stream = getClass().getClassLoader().getResourceAsStream(fileName);

        if(stream != null) {
            properties.load(stream);
        } else {
            throw new FileNotFoundException(String.format(Lang.INVALID_FILE, fileName));
        }
    }

    private void parse(String data) {
        int i = 0;
        for(String property: data.split("~")) {
            values[i++] = property.substring(property.indexOf(":") + 1);
        }
    }

    public int getNumCourses() {
        return Integer.parseInt(values[0]);//Integer.parseInt(properties.getProperty("numCourses"));
    }

    public int[] getCourseNumbers() {
        return Stream.of(values[1]/*properties.getProperty("courseNumbers")*/
                .split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    public double[] getArrivalProbabilities() {
        return Stream.of(values[2]/*properties.getProperty("arrivalProbabilities")*/
                .split(" "))
                .mapToDouble(Double::parseDouble)
                .toArray();
    }

    public int getMinTime() {
        return Integer.parseInt(/*properties.getProperty("minTime")*/values[3]);
    }

    public int getMaxTime() {
        return Integer.parseInt(/*properties.getProperty("maxTime")*/values[4]);
    }

    public int getNumCups() {
        return Integer.parseInt(/*properties.getProperty("numCups")*/values[5]);
    }

    public int getSimulationTime() {
        return Integer.parseInt(/*properties.getProperty("simulationTime")*/values[6]);
    }

    public int getNumTAs() {
        return Integer.parseInt(/*properties.getProperty("numTAs")*/values[7]);
    }
}
