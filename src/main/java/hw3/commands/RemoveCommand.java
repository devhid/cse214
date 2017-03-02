package hw3.commands;

import hw3.ActionType;
import java.util.List;

public class RemoveCommand implements ActionCommand {
    private String photo;
    private int position;

    private final ActionType actionType;

    public RemoveCommand(final String photo, final int position) {
        this.photo = photo;
        this.position = position;

        this.actionType = ActionType.REMOVE;
    }

    @Override
    public void perform(final List<String> slideshow) {

    }

    @Override
    public ActionCommand getInverse() {
        return new AddCommand(photo, position);
    }
}
