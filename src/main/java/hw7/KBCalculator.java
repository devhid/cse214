package hw7;

import java.util.Scanner;

public class KBCalculator {
    private static final Scanner input = new Scanner(System.in);
    private static final ActorGraph graph = new ActorGraph();

    public static void main(String[] args) {
        new KBCalculator().openMenu(true);
    }

    private void openMenu(boolean start) {
        if(start) { System.out.print("Menu:"); }

        String line = input.nextLine();
        selectOption(line.isEmpty() ? '0' : line.trim().toUpperCase().charAt(0));
    }

    private void selectOption(final char option) {
        switch(option) {
            case 'I':
                break;
            case 'A':
                break;
            case 'M':
                break;
            case 'P':
                break;
            case 'B':
                break;
            case 'L':
                break;
            case 'Q':
                break;
            case '0':
                break;
        }
    }

    private void importMovie(final String name) {
        if(graph.getMovieMap().containsKey(name)) {
            System.out.print("You already imported this movie.");
            return;
        }

        graph.getMovieMap().put(name, new Movie(name));

    }

    private void printMovies() {
        if(graph.getMovieMap().isEmpty()) {
            System.out.print("No movies are imported.");
            return;
        }

        graph.getMovieMap().forEach((s, movie) -> System.out.println(movie));
    }

    private void printActors() {

    }

    private void printShortestPath() {

    }

    private void printBFS() {

    }

    private void lookupActor(final String name) {

    }

    public static ActorGraph getGraph() {
        return graph;
    }
}
