package lab8.Data;

import java.io.Serializable;

public class Location implements Serializable {
    private final Double x;
    private final Double y;
    private final String name;

    public Location(Double x, Double y, String name){
        this.x = x;
        this.y = y;
        this.name = name;
    }

    public Double getX() {
        return x;
    }

    public String getName() {
        return name;
    }

    public Double getY() {
        return y;
    }
}