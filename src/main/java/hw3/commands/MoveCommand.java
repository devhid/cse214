package hw3.commands;

import hw3.ActionType;
import hw3.Lang;

import java.util.List;

/**
 * The {@code MoveCommand} class implements an {@code ActionCommand} that will
 * move the image located in {@code positionOne} to {@code positionTwo}.
 *
 * @author Mankirat Gulati
 *    email: mankirat.gulati@stonybrook.edu
 *    Stony Brook ID: 111161128
 */

public class MoveCommand implements ActionCommand {
    private int positionOne, positionTwo;
    private final ActionType actionType;

    /**
     * Initializes {@code positionOne} and {@code positionTwo} to the parameters specified
     * in the constructor parameters. Also initializes {@code actionType}
     * to {@code ActionType.MOVE}.
     *
     * @param positionOne The position of the image that will be moved.
     * @param positionTwo The position the image located at positionOne will be moving to.
     */
    public MoveCommand(final int positionOne, final int positionTwo) {
        this.positionOne = positionOne;
        this.positionTwo = positionTwo;
        this.actionType = ActionType.MOVE;
    }

    /**
     * Moves the image located at {@code positionOne} to {@code positionTwo}.
     *
     * @param slideshow A list that will be modified by the command's action.
     */
    @Override
    public void perform(List<String> slideshow) {
        slideshow.add(positionTwo, slideshow.remove(positionOne));
    }

    /**
     * Returns a move command with the positions reversed, so the action could be reversed.
     *
     * @return A new {@code MoveCommand} with {@code positionOne} and {@code positionTwo} in reversed order.
     */
    @Override
    public ActionCommand getInverse() {
        return new MoveCommand(positionTwo, positionOne);
    }

    /**
     * Returns the action performed by this command for printing stack content.
     *
     * @return A {@code String} of the action performed by this {@code MoveCommand} used for printing stack content.
     */
    @Override
    public String getAction() {
        return String.format(actionType.toString(), positionOne + 1, positionTwo + 1);
    }

    /**
     * Returns the action performed by this command for printing user messages.
     *
     * @return A {@code String} of the action performed by this {@code MoveCommand} used for printing user messages.
     */
    @Override
    public String toString() {
        return String.format(Lang.SUCCESS_MOVE_PHOTOS, positionOne + 1, positionTwo + 1);
    }
}
