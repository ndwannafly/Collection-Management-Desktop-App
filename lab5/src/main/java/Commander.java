import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
/**
 * TO-DO
 *      1. make CommandAsker static
 */

/**
 * @author Ngoc Duc Nguyen
 * This class builds interactive mode
 */
public class Commander {
    public  static Deque<String> recorder = new ArrayDeque<>(14);
    public static HashMap<String, Boolean> inStack = new HashMap<>();
    private  static CollectionManager collectionManager;
    private  static final CommandAsker commandAsker = new CommandAsker();
    private static String outputFile;
    private static final ArrayList<String> validCommand= new ArrayList<>();
    private static final InputChecker inputChecker = new InputChecker();

    static{
        validCommand.add("help");
        validCommand.add("info");
        validCommand.add("show");
        validCommand.add("add");
        validCommand.add("update");
        validCommand.add("remove_by_id");
        validCommand.add("clear");
        validCommand.add("save");
        validCommand.add("execute_script");
        validCommand.add("exit");
        validCommand.add("remove_greater");
        validCommand.add("remove_lower");
        validCommand.add("history");
        validCommand.add("group_counting_by_id");
        validCommand.add("count_less_than_birthday");
        validCommand.add("print_field_ascending_height");
    }

    static final String FILE_PATH = "D:\\first course 2020-2021\\semester 2\\Programming\\lab5\\src\\main\\java\\Data\\";

    public Commander(CollectionManager C, String outFile){
        collectionManager = C;
        outputFile = outFile;
    }

    public  static void updateHistory(String[] newCommand){
        String s;
        s = String.join(" ",newCommand);

        if(recorder.size() == 14 ) recorder.removeFirst();
        recorder.addLast(s);
    }

    private static final Scanner scanner =  new Scanner(System.in);
    public void interactiveMode() throws ParseException {
        while(true) {
            String[] userCommand = scanner.nextLine().trim().split(" ");
            if(userCommand.length > 2 ){
                System.out.println("Invalid command! Valid command should contain 1 or 2 arguments." +
                        " Please insert again!");
                continue;
            }
            if (!NameCommandValidCheck(userCommand)){
                System.out.println("Your command is not supported. Please insert correct name!\n" +
                        "Use help command to show the guideline.");
                continue;
            }
            if(!CategorizeCommand(userCommand)){
                System.out.println("Because of incorrectly insert argument. Please insert again!");
                continue;
            }
            updateHistory(userCommand);
            System.out.println("----------------------");
        }
    }

    public static boolean NameCommandValidCheck(String[] userCommand){
        boolean flag = false;
        for(String s : validCommand){
            if (userCommand[0].equals(s)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    /**
     * This method is used to categorize user commands also examine whether command can be executed or not.
     * @param userCommand is an array which contains command and argument
     * @return true if command can be executed
     * @throws ParseException default
     */
    public static boolean CategorizeCommand(String[] userCommand) throws ParseException {
        switch (userCommand[0]){
            case "execute_script":
                try {
                    File scriptFile = new File(FILE_PATH + userCommand[1]);
                    boolean exists = scriptFile.exists();
                    if(!exists){
                        System.out.println("Script file " + userCommand[1] + " doesn't exit!");
                        return false;
                    }

                    if(inStack.get(userCommand[1]) == null || !inStack.get(userCommand[1])) {
                        inStack.put(userCommand[1],true);
                        collectionManager.execute_script(scriptFile);
                        inStack.put(userCommand[1], false);
                        return true;
                    }
                    System.out.println("To avoid infinite recursion. File " + userCommand[1] + " can't be executed.");
                    return false;
                } catch(ArrayIndexOutOfBoundsException e){
                    System.out.println("Please insert file_name in the same line with the command.");
                    return false;
                }
            case "help":
                collectionManager.help();
                return true;
            case "info":
                System.out.println("Executing command info");
                collectionManager.info();
                return true;
            case "show":
                System.out.println("Executing command show");
                collectionManager.show();
                return true;
            case "add":
                collectionManager.add(commandAsker.createPerson());
                return true;
            case "update":
                System.out.println("Executing update id command");
                try {
                    if (inputChecker.LongValidCheck(userCommand[1], (long) 0, Long.MAX_VALUE)) {
                        long id = Long.parseLong(userCommand[1]);
                        if (!collectionManager.update(id)) {
                            System.out.println("Id doesn't exist. Please insert the existing id!");
                            return false;
                        }
                        return true;
                    }
                } catch(ArrayIndexOutOfBoundsException e){
                    System.out.println("Please insert the ID in the same line with the command.");
                }
                System.out.println("the ID is invalid. Please insert a long number in correct range value!");
                return false;
            case "remove_by_id":
                System.out.println("Executing remove_by_id command");
                try {
                    if (inputChecker.LongValidCheck(userCommand[1], (long) 0, Long.MAX_VALUE)) {
                        long id = Long.parseLong(userCommand[1]);
                        if (!collectionManager.remove_by_id(id)) {
                            System.out.println("Id doesn't exist. Please insert the existing id!");
                            return false;
                        }
                        return true;
                    }
                } catch(ArrayIndexOutOfBoundsException e){
                    System.out.println("Please insert the ID in the same line with the command.");
                }
                System.out.println("the ID is invalid. Please insert a long number in correct range value!");
                return false;
            case "clear":
                System.out.println("Executing clear");
                collectionManager.clear();
                return true;
            case "save":
                System.out.println("Executing save");
                collectionManager.save(outputFile);
                return true;
            case "exit":
                System.out.println("Executing exit");
                collectionManager.exit();
                return true;
            case "remove_greater":
                System.out.println("Executing remove_greater");
                System.out.println("Executing command remove_greater");
                collectionManager.remove_greater(commandAsker.createPerson());
                return true;
            case "remove_lower":
                System.out.println("Executing remove_lower");
                System.out.println("Executing command remove_lower");
                collectionManager.remove_lower(commandAsker.createPerson());
                return true;
            case "history":
                System.out.println("Executing history command");
                collectionManager.history((ArrayDeque<String>) recorder);
                return true;
            case "group_counting_by_id":
                System.out.println("Executing group_counting_by_id command");
                collectionManager.group_counting_by_id();
                return true;
            case "count_less_than_birthday":
                System.out.println("Executing count_less_than_birthday command");
                try {
                    if (!inputChecker.BirthdayValidCheck(userCommand[1])) {
                        return false;
                    }
                } catch(ArrayIndexOutOfBoundsException e){
                    System.out.println("Please insert birthday in the same line with the command!");
                    return false;
                }
                SimpleDateFormat birthdayFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
                Date birthday = birthdayFormatter.parse(userCommand[1]);
                System.out.println(collectionManager.count_less_than_birthday(birthday));
                return true;
            case "print_field_ascending_height":
                System.out.println("Executing print_field_ascending_height command");
                collectionManager.print_field_ascending_height();
                return true;
            default:
                System.out.println("Your command is not supported. Please insert correct name!\n" +
                        "Use help command to show the guideline.");
                return false;
        }
    }

}
