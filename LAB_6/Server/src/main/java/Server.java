import Ulties.CollectionManager;
import Ulties.ServerController;

public class Server {

    public static void main(String[] args) {
        String fileName = "copy.json";
        String port = "123";
        CollectionManager.setFileName(fileName);
        CollectionManager.readInputFromJsonFile();
        Runtime.getRuntime().addShutdownHook(new Thread(CollectionManager::save));
        ServerController serverController = new ServerController(port);
        serverController.run();
    }
}
