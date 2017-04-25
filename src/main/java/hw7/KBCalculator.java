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
}
