package lab8.Utils.Comparators;

import lab8.Data.Person;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

public class BirthdayComparator implements Comparator<Person> {

    @Override
    public int compare(Person o1, Person o2) {
        SimpleDateFormat birthdayFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        try {
            Date d1 = birthdayFormatter.parse(o1.getBirthday());
            Date d2 = birthdayFormatter.parse(o2.getBirthday());
            if(d1.before(d2)) return 1;
            else if(d1.after(d2)) return -1;
            else return 0;
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }
}