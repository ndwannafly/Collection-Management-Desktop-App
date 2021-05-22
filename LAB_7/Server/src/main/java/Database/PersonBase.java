package Database;

import Data.Coordinates;
import Data.Country;
import Data.Location;
import Data.Person;
import Utils.CollectionManager;
import Utils.Logging;

import java.sql.*;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.logging.Level;

public class PersonBase {

    private Statement statement;
    private static Connection connection;

    public PersonBase(Connection connection) throws SQLException {
        PersonBase.connection = connection;
        this.statement = connection.createStatement();
        String createTableSQL = "CREATE TABLE IF NOT EXISTS listperson " +
                "(id  BIGSERIAL NOT NULL PRIMARY KEY, " +
                "owner  VARCHAR(50) NOT NULL, " +
                "person_name VARCHAR(50) NOT NULL, " +
                "x INT NOT NULL, " +
                "y DECIMAL(5,2) NOT NULL, " +
                "creationDate VARCHAR(50) NOT NULL, " +
                "height INT NOT NULL, " +
                "birthday VARCHAR(50) NOT NULL, " +
                "weight INT NOT NULL, " +
                "nationality VARCHAR(50) NOT NULL, " +
                "location_x INT NOT NULL, " +
                "location_y INT NOT NULL, " +
                "location_name VARCHAR(50) NOT NULL )";
        statement.execute(createTableSQL);
    }

    public static boolean validation(String handle, String password) throws SQLException {
        String query = "SELECT * FROM users;";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while(resultSet.next()){
            if(resultSet.getString(1).equals(handle) &&
                    resultSet.getString(2).equals(SecretBase.HashPsw(password))){
                return true;
            }
        }
        return false;
    }

    public static boolean validationPermissionEdit(int id, String handle) throws SQLException {
        String query = "SELECT * FROM listperson;";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while(resultSet.next()){
            if(resultSet.getInt("id") == id && resultSet.getString("owner").equals(handle)){
                return true;
            }
        }
        return false;
    }

    public static void clearCollectionOnDataBase(String owner) throws SQLException, ParseException {
        String sql = "DELETE FROM listperson WHERE owner = '" + owner + "';";
        Statement statement = connection.createStatement();
        statement.execute(sql);
        PersonBase.loadCollection(CollectionManager.getCollection());
    }

    public static void loadCollection(Set<Person> listPerson) throws SQLException, ParseException {
        String query = " SELECT * FROM listperson ORDER by id";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        listPerson.clear();
        while(resultSet.next()){
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            listPerson.add(new Person(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3),
                    new Coordinates(resultSet.getInt(4), resultSet.getDouble(5)),
                    LocalDateTime.parse(resultSet.getString(6), dtf),
                    resultSet.getInt(7), resultSet.getString(8),
                    resultSet.getInt(9), Country.valueOf(resultSet.getString(10)),
                    new Location(resultSet.getInt(11), resultSet.getLong(12),
                            resultSet.getString(13))));
        }
    }


    public void addPersonToDataBase(Person person, int id) throws SQLException {
        if(id == -1){
            String sql = "INSERT INTO listperson (owner, person_name, x, y, creationDate, height, birthday, weight, " +
                    "nationality, location_x, location_y, location_name) VALUES (?,?,?,?,?,?,?,?,?,?,?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, person.getOwner());
            preparedStatement.setString(2, person.getName());
            preparedStatement.setInt(3, person.getCoordinates().getX());
            preparedStatement.setDouble(4, person.getCoordinates().getY());
            LocalDateTime localDate = person.getCreationDate();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            String strDate = localDate.format(dtf);
            Logging.log(Level.INFO, strDate);
            preparedStatement.setString(5, strDate);
            preparedStatement.setInt(6, person.getHeight());
            Logging.log(Level.INFO, person.getBirthday().toString());
            preparedStatement.setString(7, person.getBirthday().toString());
            preparedStatement.setInt(8, person.getWeight());
            preparedStatement.setString(9, person.getNationality().getString());
            preparedStatement.setInt(10, person.getLocation().getX());
            preparedStatement.setLong(11, person.getLocation().getY());
            preparedStatement.setString(12, person.getLocation().getName());
            preparedStatement.execute();
        }else{
            String sql = "INSERT INTO listperson (id, owner, person_name, x, y, creationDate, height, birthday, weight," +
                    "nationality, location_x, location_y, location_name) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.setString(2, person.getOwner());
            preparedStatement.setString(3, person.getName());
            preparedStatement.setInt(4, person.getCoordinates().getX());
            preparedStatement.setDouble(5, person.getCoordinates().getY());
            LocalDateTime localDate = person.getCreationDate();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            String strDate = localDate.format(dtf);
            preparedStatement.setString(6, strDate);
            preparedStatement.setInt(7, person.getHeight());
            preparedStatement.setString(8, person.getBirthday().toString());
            preparedStatement.setInt(9, person.getWeight());
            preparedStatement.setString(10, person.getNationality().getString());
            preparedStatement.setInt(11, person.getLocation().getX());
            preparedStatement.setLong(12, person.getLocation().getY());
            preparedStatement.setString(13, person.getLocation().getName());
            preparedStatement.execute();
        }
    }

    public void deleteOrganizationFromDataBase(int id) throws SQLException {
        String sql = "DELETE FROM listperson WHERE ID = '" + id + "';";
        statement.execute(sql);
    }
}
