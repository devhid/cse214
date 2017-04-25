package hw7;

import java.util.Comparator;

public class TitleComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        return ((Movie) o1).getTitle().compareTo(((Movie) o2).getTitle());
    }
}
