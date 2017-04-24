package hw7;

import java.util.Set;

public class Movie {
    private String title;
    private int year;

    private Set<Actor> actors;

    public Movie(final String title) {
        this.title = title;
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
