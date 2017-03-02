package hw3;

import java.util.Scanner;

public class SlideShowManager {
    private final Scanner scanner;
    private final UndoRedoStack slideshow;

    public SlideShowManager() {
        this.scanner = new Scanner(System.in);
        this.slideshow = new UndoRedoStack();
    }

    private void init() {

    }

    private void reselect() {

    }

    public void openMenu() {
        switch(scanner.nextLine().isEmpty() ? scanner.nextLine().trim().charAt(0) : '0') {

        }
    }

}
