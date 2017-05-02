package hw7;

import java.util.*;

public class ActorGraph {
    private static Map<String, Actor> actorsByName = new HashMap<>();
    private static Map<String, Movie> moviesByName = new HashMap<>();

    public static LinkedList<String> bfs(final String name) {
        // Reset visitors before we start the traversal.
        actorsByName.forEach((s, actor) -> {
            actor.setVisited(false);
            actor.getPath().clear();
        });

        Actor actor = actorsByName.get(name);
        actor.getPath().add(name);

        Queue<Actor> queue = new LinkedList<>();
        queue.add(actor);

        LinkedList<String> path = new LinkedList<>();
        while(!queue.isEmpty()) {
            Actor parent = queue.poll();

            parent.setVisited(true);
            path.add(parent.getName());

            for(Actor friend: parent.getFriends()) {
                if(friend.isVisited()) { continue; }

                LinkedList<String> parentPath = (LinkedList<String>) parent.getPath().clone();
                parentPath.add(friend.getName());
                friend.setPath(parentPath);

                queue.add(friend);
            }
        }

        return path;
    }

    public boolean hasMovie(final String title) {
        return moviesByName.containsKey(title);
    }

    public boolean hasActor(final String name) {
        return actorsByName.containsKey(name);
    }

    public void addActor(final Actor actor) {
        actorsByName.put(actor.getName(), actor);
    }

    public void addMovie(final Movie movie) {
        moviesByName.put(movie.getTitle(), movie);
    }

    public Map<String, Actor> getActorMap() {
        return actorsByName;
    }

    public Map<String, Movie> getMovieMap() {
        return moviesByName;
    }

    public Collection<Movie> getMovies() {
        return moviesByName.values();
    }

    public Collection<Actor> getActors() {
        return actorsByName.values();
    }
}
