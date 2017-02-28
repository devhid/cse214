package hw3.commands;

import hw3.ActionType;

import java.util.List;

public class RemoveCommand implements ActionCommand {
    private int position;
    private String photo;

    private final ActionType actionType;

    public RemoveCommand() {
        this.actionType = ActionType.REMOVE;
    }

    @Override
    public void perform(final List<String> slideshow) {

    }

    @Override
    public ActionCommand getInverse() {
        return new AddCommand();
    }
}
