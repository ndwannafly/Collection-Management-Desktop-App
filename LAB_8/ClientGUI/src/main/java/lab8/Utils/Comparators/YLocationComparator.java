package lab8.Utils.Comparators;

import lab8.Data.Person;

import java.util.Comparator;

public class YLocationComparator  implements Comparator<Person> {

    @Override
    public int compare(Person o1, Person o2) {
        return o1.getYl().compareTo(o2.getYl());
    }
}
