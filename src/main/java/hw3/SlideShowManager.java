package hw3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SlideShowManager {
    private final Scanner scanner;
    private final UndoRedoStack commands;
    private final List<String> slideshow;

    public SlideShowManager() {
        this.scanner = new Scanner(System.in);
        this.commands = new UndoRedoStack();
        this.slideshow = new ArrayList<>();
    }

    public static void main(String[] args) {
        new SlideShowManager().init();
    }

    private void init() {
        // Print Description
        // Print Menu
        reselect();
    }

    private void reselect() {
        // Select an option:
        openMenu();
    }

    public void openMenu() {
        switch(scanner.nextLine().isEmpty() ? scanner.nextLine().trim().charAt(0) : '0') {
            case 'A':

                break;
            case 'R':

                break;
            case 'S':

                break;
            case 'M':

                break;
            case 'P':

                break;
            case 'Z':

                break;
            case 'Y':

                break;
            case 'Q':

                break;
        }
    }

}
