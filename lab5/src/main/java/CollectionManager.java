import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author Ngoc Duc Nguyen
 * This class builds the collection.
 */
public class CollectionManager {
    public static HashSet<Long> IDChecker = new HashSet<Long>();
    private CommandAsker commandAsker = new CommandAsker();
    private LocalDateTime DateNow = LocalDateTime.now();
    private HashSet<Person> listPerson = new HashSet<Person>();
    private FileParser fileParser = new FileParser();

    static final String FILE_PATH = "D:\\first course 2020-2021\\semester 2\\Programming\\lab5\\src\\main\\java\\Data\\";

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
        System.out.println("update_id {id}        -      update new values for element has corresponding id");
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
        System.out.println("initialization date: " + DateNow);
        System.out.println(listPerson.size());
    }

    /**
     * display all elements in string representation
     */
    public void show(){
        listPerson.forEach(p->{
            System.out.println(p.toString());
        });
    }

    /**
     * this command adds new person into the collection
     */
    public void add (Person P){
        listPerson.add(P);
    }

    /**
     * this command finds the element which has corresponding id, then update this element's values
     * @param id - id of element has to be updated
     */
    public void update_id(long id) throws ParseException {
        for(Person person : listPerson){
           if (person.getId() == id){
               listPerson.remove(person);
               listPerson.add(commandAsker.createPerson());
           }
        }
    }

    public void remove_by_id (long id){
        for(Person person : listPerson){
            if(person.getId() == id){
                listPerson.remove(person);
            }
        }
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

            /**
             * write coordinate objects into file
             */
            JSONObject coordinatesObj = new JSONObject();
            coordinatesObj.put("x",person.getCoordinates().getX());
            coordinatesObj.put("y",person.getCoordinates().getY());
            jsonObject.put("coordinates", coordinatesObj);

            /**
             * write creation date into file
             */
            LocalDateTime date = person.getCreationDate();
            DateTimeFormatter fmt = DateTimeFormatter.ISO_DATE_TIME;
            String stringDateTime = date.format(fmt);
            jsonObject.put("creationDate", stringDateTime);

            jsonObject.put("height", person.getHeight());

            /**
             * write Date birthday into file
             */
            Date dateBirthday = person.getBirthday();
            SimpleDateFormat birthdayFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
            String stringDate = birthdayFormatter.format(dateBirthday);
            System.out.println(stringDate);
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
        try (FileWriter file = new FileWriter(FILE_PATH + "outputData.json")){
            file.write(PersonList.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void execute_script(String file_name){
        // TO-DO execute commands from script file

    }

    public void exit(){
        System.exit(0);
    }

    public void remove_greater(Person P){
        Iterator<Person> itPerson = listPerson.iterator();
        while(itPerson.hasNext()){
            Person person = itPerson.next();
            if(person.compareTo(P)>0){
                itPerson.remove();
            }
        }
    }

    public void remove_lower(Person P){
        Iterator<Person> itPerson = listPerson.iterator();
        while(itPerson.hasNext()){
            Person person = itPerson.next();
            if(person.compareTo(P)<0){
                itPerson.remove();
            }
        }
    }

    public void history(ArrayDeque<String> dq){
        for(String cm : dq ){
            System.out.println(cm);
        }
    }

    public void group_counting_by_id(){
        /**
         * such a wonderful command while all the IDs are unique??? I love automatic generating variant <3
         * because the IDs are unique so groups are unique so size of each group equals to 1
         * Result = size of collection ))
         */
        listPerson.forEach(person -> {
            System.out.println("Group of id " + person.getId() + " 1 \n" );
        });
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
        ArrayList<Long> heightArray = new ArrayList<Long>();
        listPerson.forEach(person -> {
            heightArray.add(person.getHeight());
        });

        Collections.sort(heightArray);
        System.out.println("list all the values of height-field of all elements in ascending order\n");
        heightArray.forEach( h->{
            System.out.println(h);
        });
    }
    public void printCollection(){
        listPerson.forEach(p->{
            System.out.println(p.toString());
        });
    }

    public boolean IDChecker(Long id){
        for(Person person : listPerson){
            if(person.getId() == id) {
                return false;
            }
        }
        return true;
    }
}
