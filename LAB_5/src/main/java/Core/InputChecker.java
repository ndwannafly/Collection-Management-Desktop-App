package Core;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * @author Nguyen Ngoc Duc
 * This class is used to check for validity of user's input
 */
public class InputChecker {
    public InputChecker(){}

    private  static final double eps = 1E-6;
    public boolean IntegerValidCheck(String s, int min, int max){
        try{
            int x = Integer.parseInt(s);
            if(x >= min && x <= max) return true;
            System.out.println("Input is invalid. Please enter the integer in correct range");
            return false;
        } catch(NumberFormatException e){
            System.out.println("Input is invalid. Please enter a integer number");
            return false;
        }
    }

    public boolean DoubleValidCheck(String s, Double min, Double max){
        try{
            Double x = Double.parseDouble(s);
            if( x - min > eps && max - x > eps){
                return true;
            }
            System.out.println("Input is invalid. Please enter the double number in correct range");
            return false;
        } catch (NumberFormatException e){
            System.out.println("Input is invalid. Please enter a double number");
            return false;
        }
    }

    public boolean LongValidCheck(String s, Long min, Long max){
        try{
            long x = Long.parseLong(s);
            if(x >= min && x <= max) return true;
            System.out.println("Input is invalid. Please enter the long number in correct range");
            return false;
        } catch(NumberFormatException e){
            System.out.println("Input is invalid. Please enter a long number");
            return false;
        }
    }

    public boolean BirthdayValidCheck(String s){
        SimpleDateFormat birthdayFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        try {
            birthdayFormatter.parse(s);
            return true;
        } catch (ParseException e) {
            System.out.println("please insert correct birthday's format!");
            return false;
        }

    }
}
