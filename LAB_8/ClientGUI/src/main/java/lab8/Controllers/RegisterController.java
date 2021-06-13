package lab8.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lab8.Client.ConsoleManager;
import lab8.Client.Receiver;
import lab8.Properties.Bundle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

//import javax.swing.text.LabelView;

public class RegisterController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button submitButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Text warningText1;

    @FXML
    private TextField loginField;

    @FXML
    private Text warningText2;
    @FXML
    private Label logintext;

    @FXML
    private Label passwordtext;

    @FXML
    private Text emptyFieldsWarningText;

    @FXML
    private ColorPicker colorPicker;


    ResourceBundle resourceBundle = Bundle.getResourceBundle();

    @FXML
    void initialize() {
        loginField.setPromptText(resourceBundle.getString("Enter your username"));
        passwordField.setPromptText(resourceBundle.getString("Enter the password"));
        logintext.setText(resourceBundle.getString("login"));
        passwordtext.setText(resourceBundle.getString("password"));
        submitButton.setText(resourceBundle.getString("submit"));
        emptyFieldsWarningText.setText(resourceBundle.getString("warningEmptyFields"));
        warningText1.setText(resourceBundle.getString("serverProblem"));
        warningText1.setVisible(false);
        warningText2.setVisible(false);
        emptyFieldsWarningText.setVisible(false);
        submitButton.setOnAction(actionEvent -> {
            warningText1.setVisible(false);
            warningText2.setVisible(false);
            emptyFieldsWarningText.setVisible(false);
            String login = loginField.getText();
            String password = passwordField.getText();
            String color = colorPicker.getValue().toString();
            System.out.println("color: " + color);
            Receiver.registerController = this;
            if (!login.equals("") && !password.equals("")) {
                try {
                    ConsoleManager.invoke("register " + login + " " + password + " " + color);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                emptyFieldsWarning();
            }
        });
    }

    public void warning1() {
        warningText1.setVisible(true);
    }

    public void warning2() {
        warningText2.setVisible(true);
    }

    public void success() {
        Stage stage = new Stage();
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("mainn.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("organization database");
            stage.setScene(scene);
            stage.show();
            stage.setMinHeight(500);
            stage.setMinWidth(800);
            stage = (Stage) submitButton.getScene().getWindow();
            stage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void emptyFieldsWarning() {
        emptyFieldsWarningText.setVisible(true);
    }
}