package hw7;

import big.data.DataSource;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * The {@code Movie) class represents a movie with a title, year, and actors.
 *
 *
 * @author Mankirat Gulati
 *    email: mankirat.gulati@stonybrook.edu
 *    Stony Brook ID: 111161128
 */
public class Movie {
    // The title of the movie.
    private String title;
    // The year of the movie.
    private int year;

    // The set of actors that star in this movie.
    private Set<Actor> actors;
    // The name of all the actors that star in this movie.
    private Set<String> actorNames;

    /**
     * Initializes {@code title} to the parameter specified in the constructor.
     * Also uses the BigData API to fetch movie information like the actual title, year, and actor names.
     *
     * @param title The title of the movie.
     */
    public Movie(final String title) {
        String prefix = "http://www.omdbapi.com/?t=";
        String postfix = "&y=&plot=short&r=xml";

        DataSource ds = DataSource.connectXML(prefix + title.replace(' ','+') + postfix);
        ds.load();

        this.title = ds.fetchString("movie/title");
        this.year = ds.fetchInt("movie/year");

        this.actorNames = loadActors(ds);
    }

    /**
     * Loads the actors into a set and returns it.
     *
     * @param source The {@code DataSource} object from the BigData API which handles core fetch operations.
     * @return A {@code LinkedHashSet} of all the actor names.
     */
    private Set<String> loadActors(final DataSource source) {
        actorNames = new LinkedHashSet<>();

        StringTokenizer tokenizer = new StringTokenizer(source.fetchString("movie/actors"), ",");
        while(tokenizer.hasMoreTokens()) {
            actorNames.add(tokenizer.nextToken().trim());
        }

        return actorNames;
    }

    /**
     * Returns the set of actor names.
     *
     * @return The {@code LinkedHashSet} containing the actor names.
     */
    public Set<String> getActorNames() {
        return this.actorNames;
    }

    /**
     * The title of this movie.
     *
     * @return The title of this {@code Movie} object.
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Returns the year of this movie.
     *
     * @return The year of this {@code Movie} object.
     */
    public int getYear() {
        return this.year;
    }

    /**
     * Returns the set of actor objects.
     *
     * @return A {@code LinkedHashSet} of {@code Actor} objects.
     */
    public Set<Actor> getActors() {
        return this.actors;
    }

    /**
     * Sets this movie's actors to the one specified in the parameter.
     *
     * @param actors The new set of {@code Actor} objects being set.
     */
    public void setActors(Set<Actor> actors) {
        this.actors = actors;
    }

    /**
     * Returns a string representation of this movie.
     *
     * @return A {@code String} of this {@code Movie}'s name.
     */
    public String toString() {
        return this.title;
    }
}
