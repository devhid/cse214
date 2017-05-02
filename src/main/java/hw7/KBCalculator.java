package hw7;

import big.data.DataInstantiationException;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class KBCalculator {
    private static final Scanner input = new Scanner(System.in);
    private static final ActorGraph graph = new ActorGraph();

    public static void main(String[] args) {
        KBCalculator kb = new KBCalculator();

        kb.importMovie("The Big Lebowski");
        kb.importMovie("K-Pax");
        kb.importMovie("Titanic");
        kb.importMovie("The Wolf of Wall Street");
        kb.importMovie("Apollo 13");
        kb.importMovie("Catch Me if you Can");

        kb.openMenu(true);
    }

    private void openMenu(boolean start) {
        if(start) { System.out.printf("Options:\n\t%s\n\t%s\n\t%s\n\t%s\n\t%s\n\t%s\n\t%s\n", "I) Import Movie", "A) Print all actors", "M) Print all movies",
                "P) Print shortest path", "B) Print the BFS from an actor", "L) Lookup Actor", "Q) Quit"); }

        System.out.print("\nPlease select an option: ");

        try {
            String line = input.nextLine();
            selectOption(line.isEmpty() ? '0' : line.trim().toUpperCase().charAt(0));
        } catch (DataInstantiationException ex) {
            System.out.println("No movie exists by this title.");
            openMenu(false);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
            openMenu(false);
        }
    }

    private void selectOption(final char option) {
        switch(option) {
            case 'I':
                System.out.print("Enter the movie's title: ");
                this.importMovie(input.nextLine());
                break;
            case 'A':
                System.out.println("\nACTORS\n----------------------------------------");
                this.printActors();
                break;
            case 'M':
                System.out.println("\nMOVIES:\n----------------------------------------");
                this.printMovies();
                break;
            case 'P':
                System.out.print("Enter the first actor's name: ");
                String first = input.nextLine();

                System.out.print("Enter the second actor's name: ");
                String second = input.nextLine();

                this.printShortestPath(first, second);
                break;
            case 'B':
                System.out.print("Enter an actor's name: ");
                this.printBFS(input.nextLine());
                break;
            case 'L':
                System.out.print("Enter the actor's name: ");

                this.lookupActor(input.nextLine());
                break;
            case 'Q':
                System.out.println("Shutting down...");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid option.");
                openMenu(false);
                break;
        }
    }

    private void addFriends(final Movie movie) {
        for(Actor actor: movie.getActors()) {
            for(Actor ac: movie.getActors()) {
                if(!actor.equals(ac)) {
                    actor.addFriend(ac);
                }
            }
        }
    }

    private void importMovie(final String title) {
        Movie movie = new Movie(title);

        if(graph.hasMovie(movie.getTitle())) {
            throw new IllegalArgumentException("You already imported this movie.");
        }

        graph.addMovie(movie);

        // Ensures that actors with the same name are established as unique objects.
        LinkedHashSet<Actor> actors = new LinkedHashSet<>();
        movie.getActorNames().forEach(name -> {
            Actor actor = null;

            if(graph.hasActor(name)) {
                actor = graph.getActorMap().get(name);
            } else {
                actor = new Actor(name);
                graph.addActor(actor);
            }

            actors.add(actor);
            actor.addMovie(movie);
        });

        movie.setActors((LinkedHashSet<Actor>) actors.clone());
        addFriends(movie);

        System.out.println(movie.getTitle() + "(" + movie.getYear() + ") starring: " + movie.getActorNames());
        //openMenu(false);
    }

    private void printMovies() {
        if(graph.getMovieMap().isEmpty()) {
            System.out.print("No movies are imported.\n");
        } else {
            Stream<Movie> movies = graph.getMovies().stream().sorted(new TitleComparator());
            movies.forEach(System.out::println);
        }

        openMenu(false);
    }

    private void printActors() {
        if(graph.getActorMap().isEmpty()) {
            System.out.println("No actors are imported.\n");
        } else {
            graph.getActors().stream().sorted(new NameComparator()).forEach(System.out::println);
        }

        openMenu(false);
    }

    private void printShortestPath(String firstActor, String secondActor) {
        if(!graph.hasActor(firstActor) || !graph.hasActor(secondActor)) {
            throw new IllegalArgumentException("No actor(s) by those name(s) found (names are case-sensitive).");
        }

        ActorGraph.bfs(firstActor);
        LinkedList<String> path = graph.getActorMap().get(secondActor).getPath();

        if(path.isEmpty()) {
            System.out.println("No path.");
        } else {
            System.out.println(path.stream().collect(Collectors.joining(", ")));
        }

        openMenu(false);
    }

    private void printBFS(String actor) {
        if(!graph.hasActor(actor)) {
            throw new IllegalArgumentException("No actor by that name found (names are case-sensitive).");
        }

        System.out.println(ActorGraph.bfs(actor).stream().collect(Collectors.joining(", ")));
        openMenu(false);
    }

    private void lookupActor(final String name) {
        if(!graph.hasActor(name)) {
            throw new IllegalArgumentException("No actor by this name found (names are case-sensitive).");
        }

        Actor actor = graph.getActorMap().get(name);
        String actorInfo = "Actor: " + actor.getName() + "\nFriends: ";

        for(Actor friend: actor.getFriends()) { actorInfo = actorInfo.concat(friend.getName() + ", "); }
        actorInfo = actorInfo.substring(0, actorInfo.length() - 2).concat("\nMovies: ");

        for(Movie movie: actor.getMovies()) { actorInfo = actorInfo.concat(movie.getTitle() + "(" + movie.getYear() + "), "); }
        actorInfo = actorInfo.substring(0, actorInfo.length() - 2);

        System.out.println(actorInfo);
        openMenu(false);
    }

}
