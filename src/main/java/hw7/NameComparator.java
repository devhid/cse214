package hw7;

import java.util.Comparator;
/**
 * The {@code NameComparator) class implements a {@code Comparator<Actor>} and orders {@code Actor} objects alphabetically.
 *
 *
 * @author Mankirat Gulati
 *    email: mankirat.gulati@stonybrook.edu
 *    Stony Brook ID: 111161128
 */
public class NameComparator implements Comparator<Actor> {

    /**
     * Compares the names of the two actors.
     *
     * @param a1 The first actor being compared.
     * @param a2 The second actor being compared.
     * @return {@code 0} if the actors have the same name, {@code -1} if a1 comes before a2, {@code 1} if a2 comes before a1.
     */
    @Override
    public int compare(Actor a1, Actor a2) {
        return a1.getName().compareTo(a2.getName());
    }
}
