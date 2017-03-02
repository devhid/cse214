package hw3.commands;

import hw3.ActionType;

import java.util.List;

public class SwapCommand implements ActionCommand {
    private int firstPosition, secondPosition;

    private ActionType type;

    public SwapCommand(final int firstPosition, final int secondPosition) {
        this.firstPosition = firstPosition;
        this.secondPosition = secondPosition;

        this.type = ActionType.SWAP;
    }

    @Override
    public void perform(List<String> slideshow) {

    }

    @Override
    public ActionCommand getInverse() {
        return new SwapCommand(secondPosition, firstPosition);
    }
}
