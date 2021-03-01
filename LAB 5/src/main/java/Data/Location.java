package Data;

public class Location {
    private Integer x;
    private long y;
    private String name;

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
