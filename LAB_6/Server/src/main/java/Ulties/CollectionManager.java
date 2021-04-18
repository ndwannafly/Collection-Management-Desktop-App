package Ulties;

import Data.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CollectionManager {

    private static HashSet<Person> listPerson = new HashSet<>();
    private static final FileParser fileParser = new FileParser();
    private static final LocalDateTime creationDate = LocalDateTime.now();
    private static String fileName;
    public static HashSet<Long> IDChecker = new HashSet<>();

    public static void setFileName(String fileName) {
        CollectionManager.fileName = fileName;
    }

    public static void readInputFromJsonFile(){
        listPerson = fileParser.parse(fileName);
    }

    public static int getNumberOfPerson(){
        return listPerson.size();
    }

    public static LocalDateTime getCreationDate(){
        return creationDate;
    }

    public static String info(){
        String response = "";
        response += "Collection's type: " + listPerson.getClass().getSimpleName() + '\n';
        response += "Initialization date: "  + CollectionManager.getCreationDate() + '\n';
        response += "Collection's size: " + CollectionManager.getNumberOfPerson();
        return response;
    }

    public static String show(){
            StringBuilder str = new StringBuilder();
            if(listPerson.size() == 0){
                str.append("Collection is empty!");
            }
            else{
                ArrayList<Person> personArrayList = new ArrayList<>(listPerson);
                Collections.sort(personArrayList);
                for (Person p : personArrayList) {
                    str.append(p.toString());
                }
            }
            return String.valueOf(str);
    }

    public static String clear(){
        listPerson.clear();
        return "Collection is clear!";
    }

    public static String add(Object o){
        listPerson.add((Person) o);
        return "New person is added into collection!";
    }

    public static String countLessThanBirthDay(Date birthday){
        int res = 0;
        for (Person person : listPerson){
            if(birthday.after(person.getBirthday())) ++res;
        }
        return String.valueOf(res);
    }

    public static String groupByCountingID(){
        StringBuilder stringBuilder = new StringBuilder();
        listPerson.forEach(person -> stringBuilder.append("Group of id ").append(person.getId()).append(" has 1 element\n"));
        return String.valueOf(stringBuilder);
    }

    public static String printFieldAscendingHeight(){
        ArrayList<Long> heightArray = new ArrayList<>();
        listPerson.forEach(p -> heightArray.add(p.getHeight()));
        Collections.sort(heightArray);

        StringBuilder stringBuilder = new StringBuilder();

        heightArray.forEach(h -> stringBuilder.append(h).append('\n'));
        return String.valueOf(stringBuilder);
    }

    public static String removeByID(long id){
        for(Iterator<Person> iterator = listPerson.iterator(); iterator.hasNext();){
            Person person = iterator.next();
            if(person.getId() == id){
                iterator.remove();
                return "Person has ID = " + id + " is removed!";
            }
        }
        return "ID doesn't exist!";
    }

    public static String removeGreater(Person p){
        listPerson.removeIf(person -> person.compareTo(p) > 0);
        return "Removed all greater people!";
    }

    public static String removeLower(Person p){
        listPerson.removeIf(person -> person.compareTo(p) < 0);
        return "Removed all lower people";
    }

    public static String update(long id, Object o){
        for(Iterator<Person> iterator = listPerson.iterator(); iterator.hasNext();){
            Person person = iterator.next();
            if(person.getId() == id){
                iterator.remove();
                listPerson.add((Person) o);
                return "Person has ID = " + id + " is updated!";
            }
        }
        return "ID doesn't exist!";
    }
    @SuppressWarnings("unchecked")
    public static void save(){
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
            PrintWriter printWriter = new PrintWriter(fileName);
            System.out.println("working with printWriter");
            printWriter.write(PersonList.toJSONString());
            printWriter.flush();
            printWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
