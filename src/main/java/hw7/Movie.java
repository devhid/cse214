package hw7;

import big.data.DataSource;

import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Movie {
    private String title;
    private int year;

    private Set<Actor> actors;

    public Movie(final String title) {
        String prefix = "http://www.omdbapi.com/?t=";
        String postfix = "&y=&plot=short&r=xml";

        DataSource ds = DataSource.connectXML(prefix + title.replace(' ','+') + postfix);
        ds.load();

        this.title = ds.fetchString("movie/title");
        this.actors = new HashSet<>();

        StringTokenizer tokenizer = new StringTokenizer(ds.fetchString("movie/actors"), ",");
        while(tokenizer.hasMoreTokens()) {
            actors.add(new Actor(tokenizer.nextToken().trim()));
        }

        actors.forEach(System.out::println);

        this.year = ds.fetchInt("movie/year");
    }

    public String getTitle() {
        return this.title;
    }

    public int getYear() {
        return this.year;
    }

    public Set<Actor> getActors() {
        return this.actors;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
