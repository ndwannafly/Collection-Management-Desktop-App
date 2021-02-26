import java.text.ParseException;
// TO-DO
//      1. use command line argument  -
//      2. Use Scanner to read JsonFile
//      3. Make static for some command
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
//