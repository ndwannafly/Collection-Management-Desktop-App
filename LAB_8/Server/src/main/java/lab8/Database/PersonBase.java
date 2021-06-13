package lab8.Database;

import lab8.Data.Coordinates;
import lab8.Data.Country;
import lab8.Data.Location;
import lab8.Data.Person;
import lab8.Utils.CollectionManager;
import lab8.Utils.Logging;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.logging.Level;

public class PersonBase {

    private static Connection connection;

    public PersonBase(Connection connection) throws SQLException {
        PersonBase.connection = connection;
        Statement statement = connection.createStatement();
        String createTableSQL = "CREATE TABLE IF NOT EXISTS listperson " +
                "(id  BIGSERIAL NOT NULL PRIMARY KEY, " +
                "owner  VARCHAR(50) NOT NULL, " +
                "person_name VARCHAR(50) NOT NULL, " +
                "x DECIMAL(5,2) NOT NULL, " +
                "y DECIMAL(5,2) NOT NULL, " +
                "creationDate VARCHAR(50) NOT NULL, " +
                "height INT NOT NULL, " +
                "birthday VARCHAR(50) NOT NULL, " +
                "weight INT NOT NULL, " +
                "nationality VARCHAR(50) NOT NULL, " +
                "location_x DECIMAL(5,2) NOT NULL, " +
                "location_y DECIMAL(5,2) NOT NULL, " +
                "location_name VARCHAR(50) NOT NULL," +
                "color VARCHAR(50) NOT NULL )";
        statement.execute(createTableSQL);
    }

    public static boolean validation(String handle, String password) throws SQLException {
/*
        CallableStatement callableStatement = connection.prepareCall("{? = call getNumberOfPerson()}");
        callableStatement.registerOutParameter(1, Types.INTEGER);
        callableStatement.execute();
        int res = callableStatement.getInt(1);
        System.out.println(res);
*/

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

    public static void clearCollectionOnDataBase(String owner) throws SQLException {
        String sql = "DELETE FROM listperson Where owner = ?;";
        //String sql = "DELETE FROM listperson WHERE owner = '" + owner + "';";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, owner);
        //Statement statement = connection.createStatement();
        preparedStatement.executeUpdate();
        PersonBase.loadCollection(CollectionManager.getCollection());
    }

    public static void loadCollection(Set<Person> listPerson) throws SQLException {
        String query = " SELECT * FROM listperson ORDER by id";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        listPerson.clear();
        while(resultSet.next()){
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            listPerson.add(new Person(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3),
                    new Coordinates(resultSet.getDouble(4), resultSet.getDouble(5)),
                    LocalDateTime.parse(resultSet.getString(6), dtf),
                    resultSet.getInt(7), resultSet.getString(8),
                    resultSet.getInt(9), Country.valueOf(resultSet.getString(10)),
                    new Location(resultSet.getDouble(11), resultSet.getDouble(12),
                            resultSet.getString(13)), resultSet.getString(14)));
        }
    }


    public void addPersonToDataBase(Person person, int id) throws SQLException {
        if(id == -1){
            String sql = "INSERT INTO listperson (owner, person_name, x, y, creationDate, height, birthday, weight, " +
                    "nationality, location_x, location_y, location_name, color) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, person.getOwner());
            preparedStatement.setString(2, person.getName());
            preparedStatement.setDouble(3, person.getCoordinates().getX());
            preparedStatement.setDouble(4, person.getCoordinates().getY());
            LocalDateTime localDate = person.getCreationDate();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            String strDate = localDate.format(dtf);
            Logging.log(Level.INFO, strDate);
            preparedStatement.setString(5, strDate);
            preparedStatement.setInt(6, person.getHeight());
            Logging.log(Level.INFO, person.getBirthday());
            preparedStatement.setString(7, person.getBirthday());
            preparedStatement.setInt(8, person.getWeight());
            preparedStatement.setString(9, person.getNationality().getString());
            preparedStatement.setDouble(10, person.getLocation().getX());
            preparedStatement.setDouble(11, person.getLocation().getY());
            preparedStatement.setString(12, person.getLocation().getName());
            preparedStatement.setString(13, person.getColor());
            preparedStatement.execute();
        }else{
            String sql = "INSERT INTO listperson (id, owner, person_name, x, y, creationDate, height, birthday, weight," +
                    "nationality, location_x, location_y, location_name, color) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.setString(2, person.getOwner());
            preparedStatement.setString(3, person.getName());
            preparedStatement.setDouble(4, person.getCoordinates().getX());
            preparedStatement.setDouble(5, person.getCoordinates().getY());
            LocalDateTime localDate = person.getCreationDate();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            String strDate = localDate.format(dtf);
            preparedStatement.setString(6, strDate);
            preparedStatement.setInt(7, person.getHeight());
            preparedStatement.setString(8, person.getBirthday());
            preparedStatement.setInt(9, person.getWeight());
            preparedStatement.setString(10, person.getNationality().getString());
            preparedStatement.setDouble(11, person.getLocation().getX());
            preparedStatement.setDouble(12, person.getLocation().getY());
            preparedStatement.setString(13, person.getLocation().getName());
            preparedStatement.setString(14, person.getColor());
            preparedStatement.execute();
        }
    }

    public void deleteOrganizationFromDataBase(int deleteId) throws SQLException {
/*        String sql = "CALL deleteBYID(?)";
        CallableStatement callableStatement = connection.prepareCall(sql);
        callableStatement.setInt(1, deleteId);
        callableStatement.execute();
        callableStatement.close();*/

        String sql = "DELETE FROM listperson WHERE id = ?;";
        //String sql = "DELETE FROM listperson WHERE ID = '" + id + "';";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, deleteId);
        preparedStatement.executeUpdate();
    }
}
