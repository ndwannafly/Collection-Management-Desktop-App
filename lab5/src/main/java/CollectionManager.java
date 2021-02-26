import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author Ngoc Duc Nguyen
 * This class builds the collection.
 */
public class CollectionManager {

    public static HashSet<Long> IDChecker = new HashSet<>();
    private final CommandAsker commandAsker = new CommandAsker();
    private final LocalDateTime DateNow = LocalDateTime.now();
    private HashSet<Person> listPerson = new HashSet<>();
    private final FileParser fileParser = new FileParser();

    public void readInputFromJsonFile(String InputFileName){
        listPerson = fileParser.parse(InputFileName);
    }

    /**
     * display which commands can be executed
     */
    public void help(){
        System.out.println("help - display which commands can be executed.");
        System.out.println("info - display info of the collection ( type, creationDate, number of elements...");
        System.out.println("show - display all the elements of the collection");
        System.out.println("add {element} - add new element into the collection");
        System.out.println("update {id}        -      update new values for element has corresponding id");
        System.out.println("remove_by_id {id}     -    remove element has corresponding id from the collection");
        System.out.println("clear - clear collection");
        System.out.println("save -  save the collection into file");
        System.out.println("execute_script file_name -    read and execute script from corresponding file. The script\n" +
                "contains commands in the same form in which the user enters them interactively.");
        System.out.println("exit - exit the program without saving to file");
        System.out.println("remove_greater {element}- remove all elements from the collection, which are greater than ");
    }

    /**
     * display info of the collection ( type, creationDate, number of elements...)
     */
    public void info(){
        System.out.println("Collection's type : HashSet");
        System.out.println("Initialization date: " + DateNow);
        System.out.println("Number of elements " + listPerson.size());
    }

    /**
     * display all elements in string representation
     */
    public void show(){
        listPerson.forEach(p-> System.out.println(p.toString()));
    }

    /**
     * this command adds new person into the collection
     */
    public void add (Person P){
        listPerson.add(P);
        System.out.println("Successfully added new person. Let's look at our newbie");
        System.out.println(P.toString());
    }

    /**
     * this command finds the element which has corresponding id, then update this element's values
     * @param id - id of element has to be updated
     */
    public boolean update(long id) {
        boolean flag = false;
        for(Person person : listPerson){
           if (person.getId() == id){
               flag = true;
               listPerson.remove(person);
               listPerson.add(commandAsker.createPerson());
           }
        }
        return flag;
    }

    public boolean remove_by_id (long id){
        boolean flag = false;
        for(Person person : listPerson){
            if(person.getId() == id){
                flag = true;
                listPerson.remove(person);
            }
        }
        return flag;
    }

    public void clear(){
        listPerson.clear();
    }
    @SuppressWarnings("unchecked")
    public void save(String OutputFileName){
        // TO-DO save the collection into outputJson data
        JSONArray PersonList = new JSONArray();
        for(Person person : listPerson){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id",person.getId());
            jsonObject.put("name", person.getName());

            /*
             * write coordinate objects into file
             */
            JSONObject coordinatesObj = new JSONObject();
            coordinatesObj.put("x",person.getCoordinates().getX());
            coordinatesObj.put("y",person.getCoordinates().getY());
            jsonObject.put("coordinates", coordinatesObj);

            /*
             * write creation date into file
             */
            LocalDateTime date = person.getCreationDate();
            DateTimeFormatter fmt = DateTimeFormatter.ISO_DATE_TIME;
            String stringDateTime = date.format(fmt);
            jsonObject.put("creationDate", stringDateTime);

            jsonObject.put("height", person.getHeight());

            /*
             * write Date birthday into file
             */
            Date dateBirthday = person.getBirthday();
            SimpleDateFormat birthdayFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
            String stringDate = birthdayFormatter.format(dateBirthday);
            //System.out.println(stringDate);
            jsonObject.put("birthday",stringDate);

            jsonObject.put("weight", person.getWeight());

            jsonObject.put("nationality", person.getNationality().toString());

            JSONObject locationObj = new JSONObject();
            locationObj.put("x", person.getLocation().getX());
            locationObj.put("y",person.getLocation().getY());
            locationObj.put("name", person.getLocation().getName());
            jsonObject.put("location", locationObj);
            PersonList.add(jsonObject);
        }

        /*
            write into output file
         */
        try {
            PrintWriter printWriter = new PrintWriter(OutputFileName);
            System.out.println("working with printWriter");
            printWriter.write(PersonList.toJSONString());
            printWriter.flush();
            printWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void execute_script(File FileName) throws ParseException {
        System.out.println("Executing script file " + FileName.getAbsolutePath());
        Scanner sc = null;
        try {
            sc = new Scanner(FileName);
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("Script file doesn't exist");
        }
        while(true){
            assert sc != null;
            if (!sc.hasNextLine()) break;
            String[] userCommand = sc.nextLine().trim().split(" ");
            if(userCommand.length > 2 ){
                System.out.println("Can't execute! Invalid command! Valid command should contain 1 or 2 arguments.");
                continue;
            }
            if (!Commander.NameCommandValidCheck(userCommand)){
                System.out.println("Can't execute! The command is not supported.");
                continue;
            }
            if(!Commander.CategorizeCommand(userCommand)){
                System.out.println("Can't execute! Because of incorrectly command's argument!");
                continue;
            }
            Commander.updateHistory(userCommand);
            System.out.println("-------------------");
        }
    }

    public void exit(){
        System.exit(0);
    }

    public void remove_greater(Person P){
        listPerson.removeIf(person -> person.compareTo(P) > 0);
    }

    public void remove_lower(Person P){
        listPerson.removeIf(person -> person.compareTo(P) < 0);
    }

    public void history(ArrayDeque<String> dq){
        for(String cm : dq ){
            System.out.println(cm);
        }
    }

    public void group_counting_by_id(){
        /*
         * such a wonderful command while all the IDs are unique??? I love automatic generating variant <3
         * because the IDs are unique so groups are unique so size of each group equals to 1
         * Result = size of collection ))
         */
        listPerson.forEach(person -> System.out.println("Group of id " + person.getId() +  " has 1 element" ));
    }

    public int count_less_than_birthday (Date birthday){
        int res = 0;
        for (Person person: listPerson) {
            if(birthday.after(person.getBirthday())){
                ++res;
            }
        }
        return res;
    }

    public void print_field_ascending_height(){
        ArrayList<Long> heightArray = new ArrayList<>();
        listPerson.forEach(person -> heightArray.add(person.getHeight()));

        Collections.sort(heightArray);
        System.out.println("list all the values of height-field of all elements in ascending order\n");
        heightArray.forEach(System.out::println);
    }
    public void printCollection(){
        listPerson.forEach(p-> System.out.println(p.toString()));
    }

}
