package hw4;

public class BooleanSource {
    private final double probability;

    public BooleanSource(final double probability) {
        if(probability <= 0 || probability > 1) {
            throw new IllegalArgumentException(Lang.INVALID_PROBABILITY);
        }

        this.probability = probability;
    }

    public boolean occurs() {
        return Math.random() <= probability;
    }
}
