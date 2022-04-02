package lab8.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lab8.Client.ConsoleManager;
import lab8.Client.Receiver;
import lab8.Main;
import lab8.Bundles.Bundle;

import java.io.IOException;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainController {

    @FXML
    private Text loginText;

    @FXML
    private Button executeScriptButton;

    @FXML
    private Button helpButton;

    @FXML
    private Button historyButton;

    @FXML
    private Button infoButton;

    @FXML
    private Button showButton;

    @FXML
    private Button visualizeButton;

    @FXML
    private Button exitButton;

    @FXML
    private Button logoutButton;

    @FXML
    void initialize() {
        ResourceBundle resourceBundle = Bundle.getResourceBundle();
        System.out.println("Main: " + resourceBundle.getLocale());
        executeScriptButton.setText(resourceBundle.getString("execute_script"));
        helpButton.setText(resourceBundle.getString("helpButton"));
        logoutButton.setText(resourceBundle.getString("logoutButton"));
        visualizeButton.setText(resourceBundle.getString("visualizeButton"));
        exitButton.setText(resourceBundle.getString("exitButton"));
        showButton.setText(resourceBundle.getString("showButton"));
        infoButton.setText(resourceBundle.getString("infoButton"));
        historyButton.setText(resourceBundle.getString("historyButton"));
        loginText.setText(Receiver.handle);
        loginText.setFill(Paint.valueOf(Receiver.color));
        executeScriptButton.setOnAction(actionEvent -> {
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle(resourceBundle.getString("execute_script"));
                dialog.setHeaderText(resourceBundle.getString("Enter file path"));
                Optional<String> result = dialog.showAndWait();
                result.ifPresent(name ->{
                    try {
                        String res = result.get();
                        ConsoleManager.invoke("execute_script " + res);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
        });
        exitButton.setOnAction(actionEvent -> {
            try {
                ConsoleManager.invoke("exit");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        logoutButton.setOnAction(actionEvent -> {
            try {
                ConsoleManager.invoke("logout");
                Stage stage = new Stage();
                try {
                    Parent root = FXMLLoader.load(Main.class.getResource("/welcome.fxml"));
                    Scene scene = new Scene(root, 700, 400);
                    stage.setTitle(resourceBundle.getString("authorization"));
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.show();
                    stage = (Stage) logoutButton.getScene().getWindow();
                    stage.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        helpButton.setOnAction(actionEvent -> {
            try {
                ConsoleManager.invoke("help");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        infoButton.setOnAction(actionEvent -> {
            try {
                ConsoleManager.invoke("info");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        historyButton.setOnAction(actionEvent -> {
            try {
                ConsoleManager.invoke("history");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        showButton.setOnAction(actionEvent -> {
            try {
                ConsoleManager.invoke("show");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        visualizeButton.setOnAction(actionEvent -> {
            try {
                ConsoleManager.invoke("visualize");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}