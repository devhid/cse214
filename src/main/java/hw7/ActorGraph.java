package hw7;

import java.util.*;
/**
 * The {@code ActorGraph) class allows breadth first search and finds the shortest path for all actors in the movies imported.
 *
 * @author Mankirat Gulati
 *    email: mankirat.gulati@stonybrook.edu
 *    Stony Brook ID: 111161128
 */
public class ActorGraph {
    // Map with the name of the actor as the key and the actor as an object.
    private static Map<String, Actor> actorsByName = new HashMap<>();
    private static Map<String, Movie> moviesByName = new HashMap<>();

    public static LinkedList<String> bfs(final String name) {
        // Reset visitors before we start the traversal.
        actorsByName.forEach((s, actor) -> {
            actor.setVisited(false);
            actor.getPath().clear();
        });

        // Get the root actor and add them to the path and enqueue.
        Actor actor = actorsByName.get(name);
        actor.getPath().add(name);
        actor.setVisited(true);

        Queue<Actor> queue = new LinkedList<>();
        queue.add(actor);

        LinkedList<String> path = new LinkedList<>();
        path.add(name);

        // Breadth first search traversal along with setting paths from parent to friends (shortest path).
        while(!queue.isEmpty()) {
            Actor parent = queue.poll();

            for(Actor friend: parent.getFriends()) {
                if(friend.isVisited()) { continue; }
                friend.setVisited(true);

                LinkedList<String> parentPath = new LinkedList<>(parent.getPath());
                parentPath.add(friend.getName());
                friend.setPath(parentPath);

                path.add(friend.getName());
                queue.add(friend);
            }
        }

        return path;
    }

    /**
     * Checks if the movie was imported.
     *
     * @param title The title of the movie.
     * @return {@code true} if the movie was imported, {@code false} otherwise.
     */
    public boolean hasMovie(final String title) {
        return moviesByName.containsKey(title);
    }

    /**
     * Checks if the actor was imported.
     *
     * @param name The name of the movie.
     * @return {@code true} if the actor was imported, {@code false} otherwise.
     */
    public boolean hasActor(final String name) {
        return actorsByName.containsKey(name);
    }

    /**
     * Adds an actor to the actor map.
     *
     * @param actor The {@code Actor} object being added.
     */
    public void addActor(final Actor actor) {
        actorsByName.put(actor.getName(), actor);
    }

    /**
     * Adds a movie to the movie map.
     *
     * @param movie The {@code Movie} object being added.
     */
    public void addMovie(final Movie movie) {
        moviesByName.put(movie.getTitle(), movie);
    }

    /**
     * Returns the actor map.
     *
     * @return The actor map with the actor name as the key and {@code Actor} object as the value.
     */
    public Map<String, Actor> getActorMap() {
        return actorsByName;
    }

    /**
     * Returns the movie map.
     *
     * @return The movie map with the movie title as the key and {@code Movie} object as the value.
     */
    public Map<String, Movie> getMovieMap() {
        return moviesByName;
    }

    /**
     * Returns the values for the movie map.
     *
     * @return The {@code Movie} object values in the movie map.
     */
    public Collection<Movie> getMovies() {
        return moviesByName.values();
    }

    /**
     * Returns the values for the actor map.
     *
     * @return The {@code Actor} object values in the actor map.
     */
    public Collection<Actor> getActors() {
        return actorsByName.values();
    }
}
