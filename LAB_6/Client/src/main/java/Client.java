import Ulties.ConsoleManager;

import java.io.IOException;

public class Client {

    private static String host = "localhost";
    private static String port = "123";

    public static void main(String[] args) {

        ConsoleManager consoleManager = new ConsoleManager();
        try {
            consoleManager.interactive(host, port);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
