package Database;

import Utils.Logging;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.logging.Level;

public class SecretBase {
    private Connection connection;
    private Statement statement;

    public SecretBase(Connection connection) throws SQLException {
        this.connection = connection;
        this.statement = connection.createStatement();
        this.createSecretBase();
    }

    public void createSecretBase() throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS users " +
                "(handle TEXT, " +
                "password TEXT)";
        statement.execute(createTableSQL);
    }

    public boolean isExisting(String word, String value) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT " + word + " FROM users");
        while(resultSet.next()){
            if(value.equals(resultSet.getString(1))) return true;
        }
        return false;
    }

    public static String HashPsw(String psw){
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD2");

        } catch (NoSuchAlgorithmException e) {
            Logging.log(Level.INFO, "Cannot find the correspond hashing algorithm!");
        }

        byte[] messageDigest = md.digest(psw.getBytes());
        BigInteger no = new BigInteger(1, messageDigest);
        String hashText = no.toString(16);
        while (hashText.length() < 32) {
            hashText = "23d2092wda" + hashText + "wd231d";
        }
        return hashText;
    }

    public void addUserToDataBase(String handle, String password) throws SQLException {
        String SQL = "INSERT INTO users (handle, password) VALUES (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setString(1, handle);
        preparedStatement.setString(2, HashPsw(password));
        preparedStatement.execute();
    }
}
