package hw3.commands;

import hw3.ActionType;
import hw3.Lang;

import java.util.List;

/**
 * The {@code SwapCommand} class implements an {@code ActionCommand} that will
 * swap the images located in {@code positionOne} and {@code positionTwo}.
 *
 * @author Mankirat Gulati
 *    email: mankirat.gulati@stonybrook.edu
 *    Stony Brook ID: 111161128
 */

public class SwapCommand implements ActionCommand {
    // The two positions whose images will be swapped.
    private int positionOne, positionTwo;

    // The type of action this command will perform: swap.
    private final ActionType actionType;

    /**
     * Initializes {@code positionOne} and {@code positionTwo} to the parameters specified
     * in the constructor parameters. Also initializes {@code actionType}
     * to {@code ActionType.SWAP}.
     *
     * @param positionOne The first position of the image that will be swapped.
     * @param positionTwo The second position of the image that will be swapped.
     */
    public SwapCommand(final int positionOne, final int positionTwo) {
        this.positionOne = positionOne;
        this.positionTwo = positionTwo;
        this.actionType = ActionType.SWAP;
    }

    /**
     * Swaps the images located at two specified positions in the list.
     *
     * @param slideshow A list that will be modified by the command's action.
     */
    @Override
    public void perform(List<String> slideshow) {
        String first = slideshow.get(positionOne);

        slideshow.set(positionOne, slideshow.get(positionTwo));
        slideshow.set(positionTwo, first);
    }

    /**
     * Returns a swap command with the positions reversed, so the action could be reversed.
     *
     * @return A new {@code SwapCommand} with {@code positionOne} and {@code positionTwo} in reversed order.
     */
    @Override
    public ActionCommand getInverse() {
        return new SwapCommand(positionTwo, positionOne);
    }

    /**
     * Returns the action performed by this command for printing stack content.
     *
     * @return A {@code String} of the action performed by this {@code SwapCommand} used for printing stack content.
     */
    @Override
    public String getAction() {
        return String.format(actionType.toString(), positionOne + 1, positionTwo + 1);
    }

    /**
     * Returns the action performed by this command for printing user messages.
     *
     * @return A {@code String} of the action performed by this {@code SwapCommand} used for printing user messages.
     */
    @Override
    public String toString() {
        return String.format(Lang.SUCCESS_SWAP_PHOTOS, positionOne + 1, positionTwo + 1);
    }
}
