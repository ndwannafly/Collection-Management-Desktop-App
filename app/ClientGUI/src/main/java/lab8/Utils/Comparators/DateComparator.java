package lab8.Utils.Comparators;

import lab8.Data.Person;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

public class DateComparator implements Comparator<Person> {

    @Override
    public int compare(Person o1, Person o2) {
        if(o1.getCreationDate().isBefore(o2.getCreationDate())) return 1;
        else if(o1.getCreationDate().isAfter(o2.getCreationDate())) return -1;
        else return 0;
    }
}
