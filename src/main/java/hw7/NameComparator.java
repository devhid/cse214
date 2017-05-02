package hw7;

import java.util.Comparator;

public class NameComparator implements Comparator<Actor> {

    @Override
    public int compare(Actor a1, Actor a2) {
        return a1.getName().compareTo(a2.getName());
    }
}
