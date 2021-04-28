import Ulties.CollectionManager;
import Ulties.Logging;
import Ulties.ServerController;

import java.util.logging.Level;

public class Server {

    public static void main(String[] args) {
        String fileName = args[0];
        String port = args[1];
        Logging.log(Level.WARNING, "Port must be int number! Please notice about it!");
        CollectionManager.setFileName(fileName);
        CollectionManager.readInputFromJsonFile();
        Runtime.getRuntime().addShutdownHook(new Thread(CollectionManager::save));
        ServerController serverController = new ServerController(port);
        serverController.run();
    }
}
