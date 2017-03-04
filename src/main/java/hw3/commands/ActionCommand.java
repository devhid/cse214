package hw3.commands;

import hw3.ActionType;

import java.util.List;

public interface ActionCommand {

    void perform(final List<String> slideshow);

    ActionCommand getInverse();

    String getAction();

}
