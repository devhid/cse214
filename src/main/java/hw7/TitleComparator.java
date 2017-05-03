package hw7;

import java.util.Comparator;

/**
 * The {@code TitleComparator) class implements a {@code Comparator<Movie>} and orders {@code Movie} objects alphabetically.
 *
 *
 * @author Mankirat Gulati
 *    email: mankirat.gulati@stonybrook.edu
 *    Stony Brook ID: 111161128
 */
public class TitleComparator implements Comparator<Movie> {

    /**
     * Compares the names of the two movies.
     *
     * @param m1 The first movie being compared.
     * @param m2 The second movie being compared.
     * @return {@code 0} if the movies have the same name, {@code -1} if m1 comes before m2, {@code 1} if m2 comes before m1.
     */

    @Override
    public int compare(Movie m1, Movie m2) {
        return m1.getTitle().compareTo(m2.getTitle());
    }
}
