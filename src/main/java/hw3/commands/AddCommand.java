package hw3.commands;

import hw3.ActionType;

import java.util.List;

public class AddCommand implements ActionCommand {
    private String photo;
    private int position;

    private final ActionType actionType;

    public AddCommand() {
        this.actionType = ActionType.ADD;
    }

    @Override
    public void perform(final List<String> slideshow) {

    }

    @Override
    public ActionCommand getInverse() {
        return new RemoveCommand();
    }
}
