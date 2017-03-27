package hw4;

/**
 * The {@code BooleanSource} class acts as the determiner for whether a student arrives for office hours.
 *
 * @author Mankirat Gulati
 *    email: mankirat.gulati@stonybrook.edu
 *    Stony Brook ID: 111161128
 */
public class BooleanSource {
    // The probability that an event will occur.
    private final double probability;

    /**
     * Initializes {@code probability} to the parameter specified inside the constructor.
     *
     * @param probability The set probability of an event occurring for this {@code BooleanSource}
     */
    public BooleanSource(final double probability) {
        if(probability <= 0 || probability > 1) {
            throw new IllegalArgumentException(Lang.INVALID_PROBABILITY);
        }

        this.probability = probability;
    }

    /**
     * Checks if an event has occurred or not.
     *
     * @return {@code true} if the event has occurred, {@code false} otherwise
     */
    public boolean occurs() {
        return Math.random() <= probability;
    }
}
