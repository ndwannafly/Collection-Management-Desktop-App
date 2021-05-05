package Data;

import java.io.Serializable;

public class Location implements Serializable {
    private final Integer x;
    private final long y;
    private final String name;

    public Location(Integer x, long y, String name){
        this.x = x;
        this.y = y;
        this.name = name;
    }

    public Integer getX() {
        return x;
    }

    public String getName() {
        return name;
    }

    public long getY() {
        return y;
    }
}