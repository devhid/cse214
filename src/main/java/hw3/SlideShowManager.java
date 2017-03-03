package hw3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SlideShowManager {
    private final Scanner input;
    private final List<String> slideshow;
    private final UndoRedoStack redo, undo;

    private SlideShowManager() {
        this.input = new Scanner(System.in);
        this.slideshow = new ArrayList<>();

        this.redo = new UndoRedoStack();
        this.undo = new UndoRedoStack();
    }

    public static void main(String[] args) {
        new SlideShowManager().init();
    }

    private void init() {
        System.out.println(Lang.DESCRIPTION);
        System.out.printf(Lang.MENU, "A) Add A Photo", "R) Remove A Photo",
                "S) Swap Photos", "M) Move Photo", "P) Print", "Z) Undo",
                "Y) Redo", "Q) Quit");

        reselect();
    }

    private void reselect() {
        System.out.print(Lang.INPUT_OPTION);

        try {
            selectOption();
        } catch (NumberFormatException ex) {
            System.out.print("\n" + Lang.INVALID_NUMBER);
        } catch (EmptyStackException | IllegalArgumentException ex) {
            System.out.print("\n" + ex.getMessage());
        } finally {
            reselect();
        }
    }

    private void selectOption() throws EmptyStackException {
        String photoName;
        int position;

        switch(input.nextLine().isEmpty() ? input.nextLine().trim().charAt(0) : '0') {
            case 'A':
                System.out.print(Lang.INPUT_PHOTO_NAME);
                photoName = input.nextLine();

                System.out.printf(Lang.INPUT_POSITION, "position");
                position = Integer.parseInt(input.nextLine());

                this.addPhoto(photoName, position);

                break;
            case 'R':
                System.out.printf(Lang.INPUT_POSITION, "position");
                position = Integer.parseInt(input.nextLine());

                this.removePhoto(position);

                break;
            case 'S':
                System.out.printf(Lang.INPUT_POSITION, "first position");
                int firstPosition = Integer.parseInt(input.nextLine());

                System.out.printf(Lang.INPUT_POSITION, "second position");
                int secondPosition = Integer.parseInt(input.nextLine());

                this.swapPhotos(firstPosition, secondPosition);
                break;
            case 'M':
                System.out.printf(Lang.INPUT_POSITION, "source position");
                int source = Integer.parseInt(input.nextLine());

                System.out.printf(Lang.INPUT_POSITION, "destination position");
                int destination = Integer.parseInt(input.nextLine());

                this.movePhotos(source, destination);
                break;
            case 'P':

                break;
            case 'Z':
                this.undo();
                break;
            case 'Y':
                this.redo();
                break;
            case 'Q':
                System.exit(0);
                break;
            default:
                throw new IllegalArgumentException(Lang.INVALID_OPTION);
        }
    }

    private void addPhoto(final String photo, final int position) {

    }

    private void removePhoto(final int position) {

    }

    private void swapPhotos(final int swap, final int swapWith) {

    }

    private void movePhotos(final int source, final int destination) {

    }

    private void undo() {

    }

    private void redo() {

    }

}
