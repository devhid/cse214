package hw7;

import java.util.*;

public class Actor {
    private String name;

    private Set<Movie> movies;
    private Set<Actor> friends;

    private boolean visited;
    private LinkedList<String> path;

    public Actor(final String name) {
        this.name = name;

        this.movies = new LinkedHashSet<>();
        this.friends = new LinkedHashSet<>();

        this.visited = false;
        this.path = new LinkedList<>();
    }

    public String getName() {
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

    public void addMovie(final Movie movie) {
        movies.add(movie);
    }

    public void addFriend(final Actor friend) {
        friends.add(friend);
    }

    public void setFriends(Set<Actor> friends) {
        this.friends = friends;
    }

    public void setPath(LinkedList<String> path) {
        this.path = new LinkedList<>(path);
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
