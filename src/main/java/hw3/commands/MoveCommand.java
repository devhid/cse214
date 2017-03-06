package hw3.commands;

import hw3.ActionType;
import hw3.Lang;

import java.util.List;

public class MoveCommand implements ActionCommand {
    private int firstPosition, secondPosition;
    private final ActionType actionType;

    public MoveCommand(final int firstPosition, final int secondPosition) {
        this.firstPosition = firstPosition;
        this.secondPosition = secondPosition;
        this.actionType = ActionType.MOVE;
    }

    public int getFirstPosition() {
        return this.firstPosition;
    }

    public int getSecondPosition() {
        return this.secondPosition;
    }

    @Override
    public void perform(List<String> slideshow) {
        slideshow.add(secondPosition, slideshow.remove(firstPosition));
    }

    @Override
    public ActionCommand getInverse() {
        return new MoveCommand(secondPosition, firstPosition);
    }

    @Override
    public String getAction() {
        return String.format(actionType.toString(), firstPosition + 1, secondPosition + 1);
    }

    @Override
    public String toString() {
        return String.format(Lang.SUCCESS_MOVE_PHOTOS, firstPosition + 1, secondPosition + 1);
    }
}
