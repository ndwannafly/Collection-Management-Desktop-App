package lab8.Data;

import java.io.Serializable;

public class Coordinates implements Serializable {
    private final Double x;
    private final Double y;

    public Coordinates(Double x, Double y){
        this.x = x;
        this.y = y;
    }

    public Double getX() {
        return x;
    }

    public Double getY() {
        return y;
    }
}