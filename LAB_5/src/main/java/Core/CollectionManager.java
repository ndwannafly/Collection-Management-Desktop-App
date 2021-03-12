package Core;

import Data.Person;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CollectionManager {
    private HashSet<Person> listPerson = new HashSet<>();
    private final FileParser fileParser = new FileParser();
    public static HashSet<Long> IDChecker = new HashSet<>();
    private final LocalDateTime creationDate = LocalDateTime.now();
    public void readInputFromJsonFile(String InputFileName){
        listPerson = fileParser.parse(InputFileName);
    }

    public ArrayList<Long> getArrayHeight(){
        ArrayList<Long> res = new ArrayList<>();
        listPerson.forEach( p -> res.add(p.getHeight()));
        Collections.sort(res);
        return res;
    }
    public int size(){
        return listPerson.size();
    }
    public LocalDateTime getCreationDate(){
        return this.creationDate;
    }
    public void PrintCollection(){
        listPerson.forEach(p -> System.out.println(p.toString()));
    }

    public void add(Person P){
        listPerson.add(P);
    }

    public void clear(){
        listPerson.clear();
    }

    public int countLessThanBirthDay(Date birthday){
        int res = 0;
        for (Person person : listPerson){
            if(birthday.after(person.getBirthday())) ++res;
        }
        return res;
    }

    public void groupCountingById(){
        listPerson.forEach(person -> System.out.println("Group of id " + person.getId() +  " has 1 element" ));
    }

    public void removeGreater(Person P){
        listPerson.removeIf(person -> person.compareTo(P) > 0 );
    }

    public boolean removeById(Long id){
        boolean flag = false;
        for(Iterator<Person> iterator = listPerson.iterator(); iterator.hasNext();){
            Person person = iterator.next();
            if(person.getId() == id){
                flag = true;
                iterator.remove();
            }
        }
        return !flag;
    }

    public void removeLower(Person P){
        listPerson.removeIf(person -> person.compareTo(P) < 0);
    }

    @SuppressWarnings("unchecked")
    public void save(String fileName){
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
