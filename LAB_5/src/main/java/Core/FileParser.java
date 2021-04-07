package Core;
import Data.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;


/**
 * @author Nguyen Ngoc Duc
 * this class is used to parse Jsonfile and save data into Collection
 */

public class FileParser {
    /*
        Constructor
    */
    public HashSet<Person> parse(String InputFileName){
        HashSet<Person> collectionInput = new HashSet<>();
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(InputFileName)) {
            Object obj = jsonParser.parse(reader);
            JSONArray personList = (JSONArray) obj;
            collectionInput = saveIntoCollection(personList);

        } catch(FileNotFoundException f){
            System.out.println("find not found!");
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

        return collectionInput;
    }

    /**
     * This method is used to convert JSONArray to HashSet then save into Collection
     * @param jsArr json array
     * @return HashSet is converted JSONArray
     */
    @SuppressWarnings("unchecked")
    private HashSet<Person> saveIntoCollection(JSONArray jsArr){
        HashSet<Person> HS = new HashSet<>();
        jsArr.forEach(p -> {
            try {
                HS.add(convertJsonObjIntoPerson((JSONObject) p));
            } catch (java.text.ParseException e) {
                e.printStackTrace();
            }
        });
        return HS;
    }

    /**
     * This method convert JsonObj to Person
     * @param jsonObject json object
     * @return Person which is converted from JsonObject
     * @throws java.text.ParseException throw if occurs error
     */
    private Person convertJsonObjIntoPerson(JSONObject jsonObject) throws java.text.ParseException {
        Person p = new Person();
        // set ID
        Long newID = (Long)jsonObject.get("id");
        if(CollectionManager.IDChecker.contains(newID)){
            System.out.println("ID is duplicate, please insert valid input!");
        }
        else {
            CollectionManager.IDChecker.add(newID);
            p.setId(newID);
        }
        //set Name
        p.setName((String)jsonObject.get("name"));

        // set Coordinates
        JSONObject coordinatesObj = (JSONObject) jsonObject.get("coordinates");
        p.setCoordinates(new Coordinates(Math.toIntExact((Long) coordinatesObj.get("x")), (Double)coordinatesObj.get("y")));

        /*
            parse String to LocalDateTime
        */

        // date in String
        String dateString = (String)jsonObject.get("creationDate");

        //build formatter
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        //Parse String to LocalDateTime
        LocalDateTime dateTime = LocalDateTime.parse(dateString,formatter);
        p.setCreationDate(dateTime);
        //System.out.println(p.getCreationDate());

        // set height
        p.setHeight((Long) jsonObject.get("height"));

        // set birthday
        String birthdayString = (String)jsonObject.get("birthday");
        SimpleDateFormat birthdayFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        Date birthdayDate = birthdayFormatter.parse(birthdayString);
        p.setBirthday(birthdayDate);

        // set weight
        p.setWeight(Math.toIntExact((Long)jsonObject.get("weight")));

        // set nationality
        String countryString = (String)jsonObject.get("nationality");
        Country countryEnum = Country.valueOf(countryString);
        p.setNationality(countryEnum);

        // set location
        JSONObject locationObj = (JSONObject)jsonObject.get("location");
        p.setLocation(new Location(
                Math.toIntExact((Long)locationObj.get("x")),
                (long)locationObj.get("y"),
                (String)locationObj.get("name")
        ));

        return p;
    }
}
