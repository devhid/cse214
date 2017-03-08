package hw3.commands;

import hw3.ActionType;
import hw3.Lang;

import java.util.List;

/**
 * The {@code AddCommand} class implements an {@code ActionCommand} that will
 * add the specified {@code photo} at {@code position}.
 *
 * @author Mankirat Gulati
 *    email: mankirat.gulati@stonybrook.edu
 *    Stony Brook ID: 111161128
 */

public class AddCommand implements ActionCommand {
    // The photo being added to the slideshow.
    private String photo;

    // The position the photo will be added in.
    private int position;

    // The type of action being performed: add.
    private final ActionType actionType;

    /**
     * Initializes {@code photo} and {@code position} to the parameters specified
     * in the constructor parameters. Also initializes {@code actionType}
     * to {@code ActionType.ADD}.
     *
     * @param photo The image that is being added to the slideshow.
     * @param position The position that image is being added in, inside the slideshow.
     */
    public AddCommand(final String photo, final int position) {
        this.photo = photo;
        this.position = position;
        this.actionType = ActionType.ADD;
    }

    /**
     * Removes the image located at the specified position in the list.
     *
     * @param slideshow A list that will be modified by the command's action.
     */
    @Override
    public void perform(final List<String> slideshow) {
        slideshow.add(position, photo);
    }

    /**
     * Returns a remove command with the photo and position, so the action could be reversed.
     *
     * @return A new {@code RemoveCommand} with this command's {@code photo} and {@code position}.
     */
    @Override
    public ActionCommand getInverse() {
        return new RemoveCommand(photo, position);
    }

    /**
     * Returns the action performed by this command for printing stack content.
     *
     * @return A {@code String} of the action performed by this {@code AddCommand} used for printing stack content.
     */
    @Override
    public String getAction() {
        return String.format(actionType.toString(), photo, position + 1);
    }

    /**
     * Returns the action performed by this command for printing user messages.
     *
     * @return A {@code String} of the action performed by this {@code AddCommand} used for printing user messages.
     */
    @Override
    public String toString() {
        return String.format(Lang.SUCCESS_ADD_PHOTO, photo, position + 1);
    }
}
