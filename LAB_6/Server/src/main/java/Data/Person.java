package Data;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

public class Person implements Comparable<Person>, Serializable {
    private long id;
    private String name;
    private Coordinates coordinates;
    private java.time.LocalDateTime creationDate;
    private Long height;
    private java.util.Date birthday;
    private int weight;
    private Country nationality;
    private Location location;


    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Long getHeight() {
        return height;
    }

    public void setHeight(Long height) {
        this.height = height;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Country getNationality() {
        return nationality;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public int getWeight() {
        return weight;
    }

    public Location getLocation() {
        return location;
    }

    public long getId() {
        return id;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNationality(Country nationality) {
        this.nationality = nationality;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        String info="Let me introduce new person\n";
        info += ("Name: " + name + '\n');
        info += ("ID: " + id + '\n');
        info += ("Coordinates: \n");
        info += ("             x: " + coordinates.getX() + '\n');
        info += ("             y: " + coordinates.getY() + '\n');
        info += ("creationDate: " + creationDate + '\n');
        info += ("height: " + height + '\n');
        info += ("birthday: " + birthday + '\n');
        info += ("weight: " + weight + '\n');
        info += ("nationality " + nationality + '\n');
        info += ("location: \n");
        info += ("           x: " + getLocation().getX() + '\n');
        info += ("           y: " + getLocation().getY() + '\n');
        info += ("           name: " + getLocation().getName() + "\n\n");
        return info;
    }

    @Override
    public int compareTo(Person o) {
        return (int) (height - o.getHeight());
    }
}