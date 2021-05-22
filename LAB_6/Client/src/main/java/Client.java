import Ulties.ConsoleManager;

import java.io.IOException;

public class Client {


    public static void main(String[] args) {
        try {
            String host = args[0];
            String port = args[1];
            if(Integer.parseInt(port) < 0){
                System.out.println("Please insert non negative port");
                System.exit(0);
            }
            ConsoleManager consoleManager = new ConsoleManager();
            try {
                consoleManager.interactive(host, port);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Please insert host and port!");
        }


    }
}
