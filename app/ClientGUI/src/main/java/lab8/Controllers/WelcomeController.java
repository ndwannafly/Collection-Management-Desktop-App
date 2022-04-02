package lab8.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lab8.Client.Receiver;
import lab8.Main;
import lab8.Bundles.Bundle;

import java.io.IOException;
import java.util.ResourceBundle;

public class WelcomeController {
    @FXML // fx:id="logInButton"
    private Button logInButton; // Value injected by FXMLLoader

    @FXML // fx:id="registerButton"
    private Button registerButton; // Value injected by FXMLLoader

    ResourceBundle resourceBundle = Bundle.getResourceBundle();

    @FXML
    private Text title1;

    @FXML
    private Text title2;

    @FXML
    private ChoiceBox<String> language;

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        ObservableList<String> types = FXCollections.observableArrayList("English", "Slovak", "Albanian", "Russian");
        language.setItems(types);
        language.setValue("English");
        logInButton.setText(resourceBundle.getString("login"));
        registerButton.setText(resourceBundle.getString("registration"));
        title1.setText(resourceBundle.getString("welcome"));
        title2.setText(resourceBundle.getString("PlsLogIn"));
        logInButton.setOnAction(actionEvent -> {
            String ll = language.getValue();
            Bundle.setResourceBundle(ll);
            Receiver.setResourceBundle(Bundle.getResourceBundle());
            //System.out.println("Bundle: " + Bundle.getResourceBundle().getLocale());
            Stage stage = new Stage();
            try {
                Parent root = FXMLLoader.load(Main.class.getResource("/login.fxml"));
                Scene scene = new Scene(root, 700, 400);
                stage.setTitle(resourceBundle.getString("welcome"));
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
                stage = (Stage) registerButton.getScene().getWindow();
                stage.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        registerButton.setOnAction(actionEvent -> {
            String ll = language.getValue();
            Bundle.setResourceBundle(ll);
            Receiver.setResourceBundle(Bundle.getResourceBundle());
            Stage stage = new Stage();
            try {
                Parent root = FXMLLoader.load(Main.class.getResource("/registration.fxml"));
                Scene scene = new Scene(root, 700, 400);
                stage.setTitle(resourceBundle.getString("registration"));
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
                stage = (Stage) registerButton.getScene().getWindow();
                stage.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}