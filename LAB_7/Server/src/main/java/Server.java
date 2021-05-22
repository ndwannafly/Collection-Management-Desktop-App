import Database.DatabaseCommunicator;
import Database.PersonBase;
import Utils.CollectionManager;
import Utils.Logging;
import Utils.ServerController;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;

public class Server {
    public static void main(String[] args) {
        try {
            String port = args[0];
            Logging.log(Level.WARNING, "Port must be int number! Please notice about it!");
            DatabaseCommunicator databaseCommunicator = new DatabaseCommunicator();
            databaseCommunicator.start();
            CollectionManager.initializeCollection();
            //System.exit(0);
            PersonBase.loadCollection(CollectionManager.getCollection());
            ServerController serverController = new ServerController(port);
            serverController.run();
        } catch(ArrayIndexOutOfBoundsException e){
            Logging.log(Level.INFO, "Invalid port!");
        } catch (InterruptedException | SQLException | ParseException e) {
            e.printStackTrace();
        }
    }
}
