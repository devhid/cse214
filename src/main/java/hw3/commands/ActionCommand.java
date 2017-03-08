package hw3.commands;

import java.util.List;

/**
 * The {@code ActionCommand} interface provides the common methods the command classes will implement.
 *
 * @author Mankirat Gulati
 *    email: mankirat.gulati@stonybrook.edu
 *    Stony Brook ID: 111161128
 */

public interface ActionCommand {

    /**
     * Performs a specified action on the slideshow.
     *
     * @param slideshow A list that will be modified by the command's action.
     */
    void perform(final List<String> slideshow);

    /**
     * Returns a command that will do the inverse of the acting command class.
     *
     * @return Returns an {@code ActionCommand} object that will perform the opposite of the acting command class.
     */
    ActionCommand getInverse();

    /**
     * Returns the action performed by the command used for printing stack content.
     *
     * @return A {@code String} representation of the command action that will be displayed for stack prints.
     */
    String getAction();

    /**
     * Returns the action performed by the command used for printing user messages.
     *
     * @return A {@code String} representation of the command action that will be displayed for regular user output.
     */
    String toString();

}
