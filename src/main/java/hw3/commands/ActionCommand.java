package hw3.commands;

import java.util.List;

public interface ActionCommand {

    void perform(final List<String> slideshow);

    ActionCommand getInverse();

}
