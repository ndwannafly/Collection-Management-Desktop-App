package lab8;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lab8.Client.ConsoleManager;

import java.util.List;

public class Main extends Application {

    // final String host = "localhost";
    //private final String port = "3436";

    @Override
    public void start(Stage stage) throws Exception{
        Parameters parameters = getParameters();
        List<String> args = parameters.getRaw();
        try{
            ConsoleManager consoleManager = new ConsoleManager();
            consoleManager.start(args.get(0), args.get(1));
        } catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Incorrect enter argument! Please insert host and port.");
        }

        Parent root = FXMLLoader.load(Main.class.getResource("/welcome.fxml"));
        stage.setTitle("Welcome");
        stage.setScene(new Scene(root, 700, 400));
        stage.setResizable(false);
        stage.show();

    }



    public static void main(String[] args) {
        Application.launch(args);
    }
}
