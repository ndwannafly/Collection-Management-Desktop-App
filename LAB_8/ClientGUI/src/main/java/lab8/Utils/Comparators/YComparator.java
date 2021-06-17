package lab8.Utils.Comparators;

import lab8.Data.Person;

import java.util.Comparator;

public class YComparator  implements Comparator<Person> {

    @Override
    public int compare(Person o1, Person o2) {
        return o1.getY().compareTo(o2.getY());
    }
}