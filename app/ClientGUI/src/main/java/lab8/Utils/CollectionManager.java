package lab8.Utils;

import lab8.Data.Person;

import java.time.LocalDateTime;
import java.util.HashSet;

public class CollectionManager {
    private static HashSet<Person> listPerson = new HashSet<>();
    private static final FileParser fileParser = new FileParser();
    public static HashSet<Long> IDChecker = new HashSet<>();

    public static void readInputFromJsonFile(String inputFileName){
        listPerson = fileParser.parse(inputFileName);
    }

    public static HashSet<Person> getCollection(){
        return listPerson;
    }
}
