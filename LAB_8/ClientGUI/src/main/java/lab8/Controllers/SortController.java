package lab8.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lab8.Client.ConsoleManager;
import lab8.Client.Receiver;
import lab8.Properties.Bundle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SortController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private Text questionText;

    @FXML
    private Button okButton;

    ResourceBundle resourceBundle = Bundle.getResourceBundle();

    @FXML
    void initialize() {
        ObservableList<String> types = FXCollections.observableArrayList("ID", resourceBundle.getString("owner"), resourceBundle.getString("name"), "X", "Y", resourceBundle.getString("creationDate"), resourceBundle.getString("height"), resourceBundle.getString("birthday"), resourceBundle.getString("weight"), resourceBundle.getString("nationality"), "xl", "yl", resourceBundle.getString("name location"));
        questionText.setText(resourceBundle.getString("how to sort?"));
        choiceBox.setItems(types);
        choiceBox.setValue("ID");
        okButton.setOnAction(actionEvent -> {
            Receiver.sortController = this;
            String type = choiceBox.getValue();
            if ("ID".equals(type)) {
                try {
                    ConsoleManager.invoke("sort_by_id");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (type.equals(resourceBundle.getString("owner"))) {
                try {
                    ConsoleManager.invoke("sort_by_owner");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (type.equals(resourceBundle.getString("name"))) {
                try {
                    ConsoleManager.invoke("sort_by_name");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if ("X".equals(type)) {
                try {
                    ConsoleManager.invoke("sort_by_x");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if ("Y".equals(type)) {
                try {
                    ConsoleManager.invoke("sort_by_y");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (resourceBundle.getString("creationDate").equals(type)) {
                try {
                    ConsoleManager.invoke("sort_by_date");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (resourceBundle.getString("height").equals(type)) {
                try {
                    ConsoleManager.invoke("sort_by_height");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (resourceBundle.getString("birthday").equals(type)) {
                try {
                    ConsoleManager.invoke("sort_by_birthday");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (resourceBundle.getString("weight").equals(type)) {
                try {
                    ConsoleManager.invoke("sort_by_weight");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (resourceBundle.getString("nationality").equals(type)) {
                try {
                    ConsoleManager.invoke("sort_by_nationality");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if ("xl".equals(type)) {
                try {
                    ConsoleManager.invoke("sort_by_xl");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if ("yl".equals(type)) {
                try {
                    ConsoleManager.invoke("sort_by_yl");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (resourceBundle.getString("name location").equals(type)) {
                try {
                    ConsoleManager.invoke("sort_by_name_location");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void death() {
        Stage stage = (Stage) okButton.getScene().getWindow();
        stage.close();
    }
}
