package lab8.Utils;

import lab8.Data.Person;
import lab8.Database.DatabaseCommunicator;
import lab8.Database.PersonBase;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CollectionManager {

    private static Set<Person> listPerson = Collections.synchronizedSet(new HashSet<>());
    private static final LocalDateTime creationDate = LocalDateTime.now();
    private static String fileName;

    public static int getNumberOfPerson(){
        return listPerson.size();
    }
    public static LocalDateTime getCreationDate() {
        return creationDate;
    }

    public static void initializeCollection(){
        listPerson = new HashSet<>();
    }

    public static Set<Person> getCollection(){
        return listPerson;
    }

    public static void setFileName(String fileName) {
        CollectionManager.fileName = fileName;
    }

    public static String getFileName() {
        return fileName;
    }

    public static String info() {
        return "Collection's type: " + listPerson.getClass().getSimpleName() + '\n' +
                "Initialization date: " + CollectionManager.getCreationDate() + '\n' +
                "Collection's size: " + CollectionManager.getNumberOfPerson();
    }

    public static String show(){
        StringBuilder str = new StringBuilder();
        if(listPerson.size() == 0) str.append("Collection is empty!");
        else listPerson.stream().sorted((o1, o2) -> {
            if(o1.getId() == o2.getId()) return 0;
            else return o1.getId() > o2.getId() ? 1 : -1;
        }).forEach(p -> str.append(p.toString()));
        return String.valueOf(str);
    }

    public static void clearCollectionOnDataBase(String owner) throws SQLException {
        PersonBase.clearCollectionOnDataBase(owner);
    }

    public static String add() throws SQLException {
        PersonBase.loadCollection(getCollection());
        return "New element was added into Collection!";
    }

    public static String countLessThanBirthDay(String birthdayStr) throws ParseException {
        SimpleDateFormat birthdayFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        Date birthdayDate = birthdayFormatter.parse(birthdayStr);
        //System.out.println(birthdayDate);
        return String.valueOf(listPerson.stream().filter(p -> {
            try {
                 return birthdayDate.after(birthdayFormatter.parse(p.getBirthday()));
            } catch (ParseException e) {
                e.printStackTrace();
                return false;
            }
        }).count());
    }

    public static String groupByCountingID() {
        StringBuilder stringBuilder = new StringBuilder();
        listPerson.forEach(p -> stringBuilder.append(p.getId()).append(" has 1 element\n"));
        return String.valueOf(stringBuilder);
    }

    public static String printFieldAscendingHeight(){
        StringBuilder stringBuilder = new StringBuilder();

        listPerson.stream().sorted().forEach(p -> stringBuilder.append(p.getHeight()).append('\n'));

        return String.valueOf(stringBuilder);
    }

    public static String removeByID(long id) throws SQLException {
        String response = "";
        for (Person person : listPerson) {
            if (person.getId() == id) {
                DatabaseCommunicator.getPersonBase().deleteOrganizationFromDataBase((int) id);
                response = "Person has ID = " + id + " is removed!";
                break;
            }
        }
        PersonBase.loadCollection(getCollection());
        if(response.equals("")) return "ID doesn't exist!";
        else return  response;
    }

    public static String removeGreater(Person p, String owner) throws SQLException {

        for (Person person : listPerson) {
            //Logging.log(Level.INFO, person.getOwner() + " " + owner);
            if (person.getOwner().equals(owner) && person.compareTo(p) > 0) {
                DatabaseCommunicator.getPersonBase().deleteOrganizationFromDataBase((int) person.getId());
            }
        }
        PersonBase.loadCollection(getCollection());
        //listPerson.removeIf(person -> person.compareTo(p) > 0);
        return "Removed all greater people!";
    }

    public static String removeLower(Person p, String owner) throws SQLException {

        for (Person person : listPerson) {
            if (person.getOwner().equals(owner) && person.compareTo(p) < 0) {
                DatabaseCommunicator.getPersonBase().deleteOrganizationFromDataBase((int) person.getId());
            }
        }
        PersonBase.loadCollection(getCollection());

        //listPerson.removeIf(person -> person.compareTo(p) > 0);
        //listPerson.removeIf(person -> person.compareTo(p) < 0);
        return "Removed all lower people";
    }

    public static String update(long id) throws SQLException {
        PersonBase.loadCollection(getCollection());
        return "Person has id = " + id + " was updated";
    }

    @SuppressWarnings("unchecked")
    public static void save() {
        JSONArray personList = new JSONArray();
        for(Person person : listPerson){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", person.getId());
            jsonObject.put("owner", person.getOwner());
            jsonObject.put("name", person.getName());

            // write coordinate objects into file
            JSONObject coordinatesObj = new JSONObject();
            coordinatesObj.put("x", person.getCoordinates().getX());
            coordinatesObj.put("y", person.getCoordinates().getY());
            jsonObject.put("coordinates", coordinatesObj);

            //write creation date into file
            LocalDateTime date = person.getCreationDate();
            DateTimeFormatter fmt = DateTimeFormatter.ISO_DATE_TIME;
            String stringDateTime = date.format(fmt);
            jsonObject.put("creationDate", stringDateTime);

            // height
            jsonObject.put("height", person.getHeight());

            // write Date birthday into file
            jsonObject.put("birthday", person.getBirthday());

            //weight
            jsonObject.put("weight", person.getWeight());

            //nationality
            jsonObject.put("nationality", person.getNationality().toString());

            JSONObject locationObj = new JSONObject();
            locationObj.put("x", person.getLocation().getX());
            locationObj.put("y", person.getLocation().getY());
            locationObj.put("name", person.getLocation().getName());
            jsonObject.put("location", locationObj);

            jsonObject.put("color", person.getColor());

            personList.add(jsonObject);
        }

        // write into file
        try{
            PrintWriter printWriter = new PrintWriter(fileName);
            printWriter.write(personList.toJSONString());
            printWriter.flush();
            printWriter.close();
        } catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }
}
