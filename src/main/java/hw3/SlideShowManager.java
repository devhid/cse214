package hw3;

import hw3.commands.*;

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
                System.out.println(Lang.LABEL_ADD_PHOTO);

                System.out.print(Lang.INPUT_PHOTO_NAME);
                photoName = input.nextLine();

                System.out.printf(Lang.INPUT_POSITION, "position");
                position = Integer.parseInt(input.nextLine());

                this.addPhoto(photoName, position);

                break;
            case 'R':
                System.out.println(Lang.LABEL_REMOVE_PHOTO);

                System.out.printf(Lang.INPUT_POSITION, "position");
                position = Integer.parseInt(input.nextLine());

                this.removePhoto(position);

                break;
            case 'S':
                System.out.println(Lang.LABEL_SWAP_PHOTOS);

                System.out.printf(Lang.INPUT_POSITION, "first position");
                int firstPosition = Integer.parseInt(input.nextLine());

                System.out.printf(Lang.INPUT_POSITION, "second position");
                int secondPosition = Integer.parseInt(input.nextLine());

                this.swapPhotos(firstPosition, secondPosition);
                break;
            case 'M':
                System.out.println(Lang.LABEL_MOVE_PHOTOS);

                System.out.printf(Lang.INPUT_POSITION, "source position");
                int source = Integer.parseInt(input.nextLine());

                System.out.printf(Lang.INPUT_POSITION, "destination position");
                int destination = Integer.parseInt(input.nextLine());

                this.movePhotos(source, destination);
                break;
            case 'P':
                System.out.print("Slideshow:");
                System.out.print("\n-------------------------------------------" +
                        "-------------");

                String images = "";

                for(int i = 0; i < slideshow.size(); i++) {
                    String photo = slideshow.get(i);

                    if(photo != null) {
                        images += (i + 1) + ") " + photo + ", ";
                    }
                }

                System.out.print(images.substring(0, images.length() - 1));

                // Print Undo Stack
                System.out.print("Lang.LABEL_UNDO_STACK:");

                UndoRedoStack.Node undoNode = undo.getTop();
                while(undoNode != null) {
                    System.out.print("\n" + undoNode.getData().getAction());
                    undoNode = undoNode.getNext();
                }

                // Print Redo Stack
                System.out.print("Lang.LABEL_UNDO_STACK:");

                UndoRedoStack.Node redoNode = undo.getTop();
                while(redoNode != null) {
                    System.out.print("\n" + redoNode.getData().getAction());
                    redoNode = redoNode.getNext();
                }

                break;
            case 'Z':
                this.undo();
                break;
            case 'Y':
                this.redo();
                break;
            case 'Q':
                System.out.println(Lang.QUIT);
                System.exit(0);
                break;
            default:
                throw new IllegalArgumentException(Lang.INVALID_OPTION);
        }
    }

    private void addPhoto(final String photo, final int position) {
        checkPositions(position);

        redo.clear();

        ActionCommand command = new AddCommand(photo, position - 1);
        command.perform(slideshow);

        undo.push(command);
        System.out.printf(Lang.SUCCESS_ADD_PHOTO, photo, position);
        reselect();
    }

    private void removePhoto(final int position) {
        checkPositions(position);

        redo.clear();

        String photo = slideshow.get(position);

        ActionCommand command = new RemoveCommand(photo, position - 1);
        command.perform(slideshow);

        undo.push(command);
        System.out.printf(Lang.SUCCESS_REMOVE_PHOTO, photo, position);
        reselect();
    }

    private void swapPhotos(final int swap, final int swapWith) {
        checkPositions(swap, swapWith);

        redo.clear();

        ActionCommand command = new SwapCommand(swap - 1, swapWith - 1);
        command.perform(slideshow);

        undo.push(command);
        System.out.printf(Lang.SUCCESS_SWAP_PHOTOS, swap, swapWith);
    }

    private void movePhotos(final int source, final int destination) {
        checkPositions(source, destination);

        redo.clear();

        ActionCommand command = new MoveCommand(source - 1, destination - 1);
        command.perform(slideshow);

        undo.push(command);
        System.out.printf(Lang.SUCCESS_MOVE_PHOTOS, source, destination);
        reselect();
    }

    private void undo() throws EmptyStackException {
        if(undo.isEmpty()) {
            throw new EmptyStackException(Lang.CANNOT_UNDO);
        }

        ActionCommand command = undo.getTop().getData();

        redo.push(command);
        undo.pop().getInverse().perform(slideshow);

        System.out.printf(Lang.SUCCESS_UNDO, command.getAction());
        reselect();
    }

    private void redo() throws EmptyStackException {
        if(redo.isEmpty()) {
            throw new EmptyStackException(Lang.CANNOT_REDO);
        }

        ActionCommand command = undo.getTop().getData();

        undo.push(redo.getTop().getData());
        redo.pop().getInverse().perform(slideshow);

        System.out.printf(Lang.SUCCESS_REDO, command.getAction());
        reselect();
    }

    private void checkPositions(int... positions) {
        for(int position: positions) {
            if(position < 1 || position > slideshow.size()) {
                throw new IllegalArgumentException(Lang.INVALID_POSITION);
            }
        }
    }
}
