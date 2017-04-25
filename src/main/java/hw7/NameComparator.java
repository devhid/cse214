package hw7;

import java.util.Comparator;

public class NameComparator implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        return ((Actor) o1).getName().compareTo(((Actor) o2).getName());
    }
}
