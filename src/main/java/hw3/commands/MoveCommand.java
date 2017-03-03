package hw3.commands;

import hw3.ActionType;

import java.util.List;

public class MoveCommand implements ActionCommand {
    private int firstPosition, secondPosition;
    private final ActionType actionType;

    public MoveCommand(final int firstPosition, final int secondPosition) {
        this.firstPosition = firstPosition;
        this.secondPosition = secondPosition;

        this.actionType = ActionType.MOVE;
    }

    @Override
    public void perform(List<String> slideshow) {

    }

    @Override
    public ActionCommand getInverse() {
        return null; //new MoveCommand(secondPosition, firstPosition);
    }
}
