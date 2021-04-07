package Commands;

import Core.CollectionManager;
import Core.InputChecker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CountLessThanBirthdayCommand extends AbstractCommand{
    private final CollectionManager collectionManager;
    private final InputChecker inputChecker;
    public CountLessThanBirthdayCommand(CollectionManager cm, InputChecker ic){
        this.collectionManager = cm;
        this.inputChecker = ic;
    }

    @Override
    public boolean execute(String argument) {
        try{
            if(!inputChecker.birthdayValidCheck(argument)) return false;
        } catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Please insert birthday in the same line with the command!");
            return false;
        }
        SimpleDateFormat birthdayFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        Date birthday = null;
        try {
            birthday = birthdayFormatter.parse(argument);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(collectionManager.countLessThanBirthDay(birthday));
        return true;
    }
}
