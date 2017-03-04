package hw3.commands;

import hw3.ActionType;

import java.util.List;

public class AddCommand implements ActionCommand {
    private String photo;
    private int position;

    private final ActionType actionType;

    public AddCommand(final String photo, final int position) {
        this.photo = photo;
        this.position = position;
        this.actionType = ActionType.ADD;
    }

    public String getPhoto() {
        return this.photo;
    }

    public int getPosition() {
        return this.position;
    }

    @Override
    public void perform(final List<String> slideshow) {
        slideshow.add(position, photo);
    }

    @Override
    public ActionCommand getInverse() {
        return new RemoveCommand(photo, position);
    }

    @Override
    public String getAction() {
        return String.format(actionType.toString(), photo, position);
    }
}
