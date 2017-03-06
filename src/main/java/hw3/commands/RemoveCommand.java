package hw3.commands;

import hw3.ActionType;
import hw3.Lang;

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

    public String getPhoto() {
        return this.photo;
    }

    public int getPosition() {
        return this.position;
    }

    @Override
    public void perform(final List<String> slideshow) {
        slideshow.remove(position);
    }

    @Override
    public ActionCommand getInverse() {
        return new AddCommand(photo, position);
    }

    @Override
    public String getAction() {
        return String.format(actionType.toString(), photo, position + 1);
    }

    @Override
    public String toString() {
        return String.format(Lang.SUCCESS_REMOVE_PHOTO, photo, position + 1);
    }
}
