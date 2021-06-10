package lab8.Database;


import lab8.Utils.Logging;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;

public class DatabaseCommunicator {

    // on helios
    // private static final String DB_URL = "jdbc:postgresql://pg:5432/studs";

    // on local PC
    private static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/postgres";

    private static Connection connection;
    private static PersonBase personBase;
    private static SecretBase users;

    public void start(){
        Logging.log(Level.INFO, "Connecting to database ....... !");
        try {
            Scanner scanner = new Scanner(System.in);
            Logging.log(Level.INFO, "Enter your account:");
            //System.out.println("Enter your account:");
            String user = scanner.nextLine();
            Logging.log(Level.INFO, "Enter your password:");
            //System.out.println("Enter your password:");
            String pass = scanner.nextLine();
            connection = DriverManager.getConnection(DB_URL, user, pass);
            users = new SecretBase(connection);
            personBase = new PersonBase(connection);
        } catch (SQLException e) {
            Logging.log(Level.INFO, "Errors occur!");
            //e.printStackTrace();
            //System.exit(0);
        }

        if (connection != null){
            Logging.log(Level.INFO,"Successfully connect to DataBase!");
        }
        else{
            Logging.log(Level.INFO, "Unsuccessfully connect to DataBase!");
            System.exit(0);
        }
    }

    public static PersonBase getPersonBase(){
        return personBase;
    }

    public static SecretBase getUsers(){
        return users;
    }
}
