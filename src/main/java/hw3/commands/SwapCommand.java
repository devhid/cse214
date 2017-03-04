package hw3.commands;

import hw3.ActionType;
import java.util.List;

public class SwapCommand implements ActionCommand {
    private int firstPosition, secondPosition;
    private final ActionType actionType;

    public SwapCommand(final int firstPosition, final int secondPosition) {
        this.firstPosition = firstPosition;
        this.secondPosition = secondPosition;
        this.actionType = ActionType.SWAP;
    }

    public int getFirstPosition() {
        return this.firstPosition;
    }

    public int getSecondPosition() {
        return this.secondPosition;
    }

    @Override
    public void perform(List<String> slideshow) {
        String first = slideshow.get(firstPosition);

        slideshow.set(firstPosition, slideshow.get(secondPosition));
        slideshow.set(secondPosition, first);
    }

    @Override
    public ActionCommand getInverse() {
        return new SwapCommand(secondPosition, firstPosition);
    }

    @Override
    public String getAction() {
        return String.format(actionType.toString(), firstPosition, secondPosition);
    }
}
