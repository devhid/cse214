package hw4;

public class BooleanSource {
    private final double probability;

    public BooleanSource(final double probability) {
        if(probability <= 0 || probability > 1) {
            throw new IllegalArgumentException("Invalid initial probability.");
        }

        this.probability = probability;
    }

    public boolean occurs() {
        return Math.random() <= probability;
    }
}
