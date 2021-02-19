import java.text.ParseException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
// TO-DO
//      1. handle valid input, ask user reinsert if input is invalid
//      2. handle exception
//      3. handle LOOP read file
//      4. use command line argument
public class Main {
    static final String FILE_PATH = "D:\\first course 2020-2021\\semester 2\\Programming\\lab5\\src\\main\\java\\Data\\";
    public static void main(String[] args) {
        System.out.println("Welcome to my lab");
        CollectionManager collectionManager = new CollectionManager();
        collectionManager.readInputFromJsonFile(FILE_PATH+"InputData.Json");
        collectionManager.printCollection();
        Commander commander = new Commander(collectionManager, FILE_PATH + "OutputData.Json");
        try {
            commander.interactiveMode();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
