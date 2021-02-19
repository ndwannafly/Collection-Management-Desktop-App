import java.security.spec.RSAOtherPrimeInfo;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CommandAsker {
    /*
        TO-DO:
            1. handle check IDAsker
            2. handle check name
            3. handle check
     */
    private InputChecker inputChecker = new InputChecker();
    private  static final double eps = 1E-6;

    public CommandAsker(){};


    private  static Scanner scanner = new Scanner(System.in);
    static final String FILE_PATH = "D:\\first course 2020-2021\\semester 2\\Programming\\lab5\\src\\main\\java\\Data\\";


    public Person createPerson() throws ParseException {
        Person newPerson = new Person();
        System.out.println("Let's create new person");

        newPerson.setId(IdAsker());

        newPerson.setName(NameAsker());

        newPerson.setCoordinates(CoordinatesAsker());

        newPerson.setCreationDate(DateAsker());

        newPerson.setHeight(HeightAsker());

        newPerson.setBirthday(BirthdayAsker());

        newPerson.setWeight(WeightAsker());

        newPerson.setNationality(CountryAsker());

        newPerson.setLocation(LocationAsker());
        return newPerson;
    }

    public Long IdAsker(){
        System.out.println("Id is automatically generated.");
        Long newID = new Random().nextLong();
        if(CollectionManager.IDChecker.contains(newID) || newID < 0){
            System.out.println("Input is invalid. Let's generate the new one!");
            return IdAsker();
        }
        else{
            CollectionManager.IDChecker.add(newID);
            return newID;
        }
    }

    public String NameAsker(){
        System.out.println("Insert name: ");
        return (scanner.nextLine());
    }

    public Coordinates CoordinatesAsker(){
        System.out.println("Insert coordinates: ");
        while(true){
            System.out.println("Insert x and y:");
            String[] inputNumber = scanner.nextLine().trim().split(" ");
            if(inputNumber.length != 2 ){
                System.out.println("please insert exactly two number!");
                continue;
            }
            else{
                if(inputChecker.IntegerValidCheck(inputNumber[0],-801,Integer.MAX_VALUE) == false) continue;
                if(inputChecker.DoubleValidCheck(inputNumber[1], Double.MIN_VALUE, 687.0) == false ) continue;
                int x = Integer.parseInt(inputNumber[0]);
                Double y = Double.parseDouble(inputNumber[1]);
                return new Coordinates(x, y);
            }
        }
    }

    public LocalDateTime DateAsker(){
        return java.time.LocalDateTime.now();
    }

    public Long HeightAsker(){
        while(true) {
            System.out.println("Insert height: ");
            String[] inputNumber = scanner.nextLine().trim().split(" ");
            if(inputNumber.length != 1 ){
                System.out.println("Please insert exactly one number!");
                continue;
            }
            else{
                if(inputChecker.LongValidCheck(inputNumber[0], Long.MIN_VALUE, Long.MAX_VALUE) == false ) continue;
                return Long.parseLong(inputNumber[0]);
            }
        }
    }

    public Date BirthdayAsker() {
        while(true) {
            System.out.println("Insert birthday: ");
            String[] inputNumber = scanner.nextLine().trim().split(" ");
            if(inputNumber.length != 1){
                System.out.println("Please insert one date only!");
                continue;
            }
            else {
                SimpleDateFormat birthdayFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
                System.out.println("number of date: " + inputNumber.length);
                try {
                    Date birthday = birthdayFormatter.parse(inputNumber[0]);
                    return birthday;
                } catch (ParseException e){
                    System.out.println("Input is invalid. Correct birthday's format is dd-MM-yyyy");
                    continue;
                }
            }
        }
    }

    public Integer WeightAsker(){
        return intAsker(Integer.MIN_VALUE,Integer.MAX_VALUE);
    }

    public Country CountryAsker(){
        while(true) {
            System.out.println("Insert Country: ");
            String[] inputNumber = scanner.nextLine().trim().split(" ");
            if(inputNumber.length != 1) {
                System.out.println("Please insert exactly one country!");
                continue;
            }
            Country countryEnum = Country.valueOf(inputNumber[0]);
            return countryEnum;
        }
    }

    public Location LocationAsker(){
        System.out.println("Insert Location: ");
        System.out.println("Insert X: ");
        Integer LocationX = scanner.nextInt();
        System.out.println("Insert Y: ");
        long LocationY = scanner.nextLong();
        System.out.println("Insert name: ");
        String name = scanner.next();
        return new Location(LocationX, LocationY, name);
    }

    public String fileNameAsker(){
        System.out.println("Insert File Name");
        return FILE_PATH + scanner.next();
    }

    public Double doubleAsker(Double min, Double max){
        while(true){
            String[] inputNumber = scanner.nextLine().trim().split(" ");
            if(inputNumber.length != 1 ){
                System.out.println("please enter exactly one double number: ");
                continue;
            }
            else{
                if(inputChecker.DoubleValidCheck(inputNumber[0],min,max) == false) continue;
                return Double.parseDouble(inputNumber[0]);
            }
        }
    }

    public Integer intAsker(int min, int max){
        while(true){
            String[] inputNumber = scanner.nextLine().trim().split(" ");
            if(inputNumber.length != 1 ){
                System.out.println("please enter exactly one integer: ");
                continue;
            }
            else{
                int x = 0 ;
                try {
                    x = Integer.parseInt(inputNumber[0]);
                    if( x < min ) continue;
                    if( x > max) continue;
                    return x;
                } catch (NumberFormatException e){
                    System.out.println("please insert an integer number");
                    continue;
                }
            }
        }
    }
}
