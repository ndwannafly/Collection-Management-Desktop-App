package Ulties;

import Data.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;

public class CollectionManager {

    private static HashSet<Person> listPerson = new HashSet<>();
    private static final FileParser fileParser = new FileParser();
    private static String fileName;
    public static HashSet<Long> IDChecker = new HashSet<>();

    public static void setFileName(String fileName) {
        CollectionManager.fileName = fileName;
    }

    public static void readInputFromJsonFile(){
        listPerson = fileParser.parse(fileName);
    }

    public static int getPerson(){
        return listPerson.size();
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
