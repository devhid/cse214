package hw7;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Actor {
    private String name;

    private Set<Movie> movies;
    private Set<Actor> friends;

    private boolean visited;
    private LinkedList<String> path;

    public Actor(final String name) {
        this.name = name;

        this.movies = new HashSet<>();
        this.friends = new HashSet<>();

        this.visited = false;
        this.path = new LinkedList<>();
    }

    private String getName() {
        return this.name;
    }

    public Set<Movie> getMovies() {
        return this.movies;
    }

    public Set<Actor> getFriends() {
        return this.friends;
    }

    public boolean isVisited() {
        return this.visited;
    }

    public LinkedList<String> getPath() {
        return this.path;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }


}
