package hw3.commands;

import hw3.ActionType;
import hw3.Lang;

import java.util.List;

/**
 * The {@code RemoveCommand} class implements an {@code ActionCommand} that will
 * remove the image located in {@code position}.
 *
 * @author Mankirat Gulati
 *    email: mankirat.gulati@stonybrook.edu
 *    Stony Brook ID: 111161128
 */

public class RemoveCommand implements ActionCommand {
    // The photo corresponding to the specified index.
    private String photo;

    // The position of the image.
    private int position;

    // The type of action this command will perform: remove.
    private final ActionType actionType;

    /**
     * Initializes {@code photo} and {@code position} to the parameters specified
     * in the constructor parameters. Also initializes {@code actionType}
     * to {@code ActionType.REMOVE}.
     *
     * @param photo The image that is being removed from the slideshow.
     * @param position The position that corresponds to that image in the slideshow.
     */
    public RemoveCommand(final String photo, final int position) {
        this.photo = photo;
        this.position = position;
        this.actionType = ActionType.REMOVE;
    }

    /**
     * Removes the image located at the specified position in the list.
     *
     * @param slideshow A list that will be modified by the command's action.
     */
    @Override
    public void perform(final List<String> slideshow) {
        slideshow.remove(position);
    }

    /**
     * Returns an add command with the photo and position, so the action could be reversed.
     *
     * @return A new {@code AddCommand} with this command's {@code photo} and {@code position}.
     */
    @Override
    public ActionCommand getInverse() {
        return new AddCommand(photo, position);
    }

    /**
     * Returns the action performed by this command for printing stack content.
     *
     * @return A {@code String} of the action performed by this {@code RemoveCommand} used for printing stack content.
     */
    @Override
    public String getAction() {
        return String.format(actionType.toString(), photo, position + 1);
    }

    /**
     * Returns the action performed by this command for printing user messages.
     *
     * @return A {@code String} of the action performed by this {@code RemoveCommand} used for printing user messages.
     */
    @Override
    public String toString() {
        return String.format(Lang.SUCCESS_REMOVE_PHOTO, photo, position + 1);
    }
}
