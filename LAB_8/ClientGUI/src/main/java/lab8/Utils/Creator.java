package lab8.Utils;

import lab8.Client.Receiver;
import lab8.Data.Coordinates;
import lab8.Data.Country;
import lab8.Data.Location;
import lab8.Data.Person;

public class Creator {

    public static Person create(String name, Double x, Double y, Long height, String birthday, Integer weight, Country
                                nationality, Double xLocation, Double yLocation, String nameLocation){
        return new Person(name, new Coordinates(x,y), height, birthday, weight, nationality, new Location(xLocation,
                yLocation, nameLocation), Receiver.handle, Receiver.color);
    }
}
