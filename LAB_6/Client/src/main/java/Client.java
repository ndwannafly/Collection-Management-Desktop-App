import Ulties.ConsoleManager;

import java.io.IOException;

public class Client {


    public static void main(String[] args) {

        String host = args[0];
        String port = args[1];

        ConsoleManager consoleManager = new ConsoleManager();
        try {
            consoleManager.interactive(host, port);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
