package Database;

import Utils.Logging;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;

public class DatabaseCommunicator {

    private static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/postgres";
    private static final String USER   = "postgres";
    private static final String PASS   = "zyo564";
    private static Connection connection;
    private static Statement statement;
    private static PersonBase personBase;
    private static SecretBase users;

    public void start(){

        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = connection.createStatement();
            users = new SecretBase(connection);
            personBase = new PersonBase(connection);
        } catch (SQLException e) {
            Logging.log(Level.INFO, "Errors occur!");
            e.printStackTrace();
            System.exit(0);
        }

        if (connection != null){
            Logging.log(Level.INFO,"Successfully connect to DataBase!");
        }
        else{
            Logging.log(Level.INFO, "Unsuccessfully connect to DataBase!");
        }
    }

    public static PersonBase getPersonBase(){
        return personBase;
    }

    public static SecretBase getUsers(){
        return users;
    }
}
