package hw7;

import big.data.DataSource;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Movie {
    private String title;
    private int year;

    private Set<Actor> actors;
    private Set<String> actorNames;

    public Movie(final String title) {
        String prefix = "http://www.omdbapi.com/?t=";
        String postfix = "&y=&plot=short&r=xml";

        DataSource ds = DataSource.connectXML(prefix + title.replace(' ','+') + postfix);
        ds.load();

        this.title = ds.fetchString("movie/title");
        this.year = ds.fetchInt("movie/year");

        this.actorNames = loadActors(ds);
    }

    private Set<String> loadActors(final DataSource source) {
        actorNames = new LinkedHashSet<>();

        StringTokenizer tokenizer = new StringTokenizer(source.fetchString("movie/actors"), ",");
        while(tokenizer.hasMoreTokens()) {
            actorNames.add(tokenizer.nextToken().trim());
        }

        return actorNames;
    }

    public Set<String> getActorNames() {
        return this.actorNames;
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

    public void setActors(Set<Actor> actors) {
        this.actors = actors;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String toString() {
        return this.title;
    }
}
