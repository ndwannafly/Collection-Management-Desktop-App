package lab8.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import lab8.Client.ConsoleManager;
import lab8.Client.Reader;
import lab8.Client.Receiver;
import lab8.Data.Country;
import lab8.Data.Person;
import lab8.Properties.Bundle;
import lab8.Utils.Creator;

import java.io.IOException;
import java.util.ResourceBundle;

public class UpdateController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField xField;

    @FXML
    private TextField yField;

    @FXML
    private TextField heightField;

    @FXML
    private TextField birthdayField;

    @FXML
    private TextField weightField;

    @FXML
    private ChoiceBox<String> nationalityField;

    @FXML
    private TextField xLocationField;

    @FXML
    private TextField yLocationField;

    @FXML
    private TextField nameLocationField;

    @FXML
    private Button addButton;

    @FXML
    private Text addText;

    @FXML
    private TextField idField;

    public static String final_name;
    public static Double final_x;
    public static Double final_y;
    public static Long final_height;
    public static String final_birthday;
    public static Integer final_weight;
    public static Country final_nationality;
    public static Double final_xLocation;
    public static Double final_yLocation;
    public static String final_nameLocation;

    ResourceBundle resourceBundle = Bundle.getResourceBundle();

    @FXML
    void initialize(){
        nameField.setPromptText(resourceBundle.getString("Enter a name"));
        xField.setPromptText(resourceBundle.getString("Enter x coordinate of the person (must be greater than -801)."));
        yField.setPromptText(resourceBundle.getString("Enter y coordinate of the person (no more than 687)."));
        heightField.setPromptText(resourceBundle.getString("Enter the height."));
        birthdayField.setPromptText(resourceBundle.getString("Enter the birthday."));
        weightField.setPromptText(resourceBundle.getString("Enter the weight."));
        //nationalityField.setPromptText(resourceBundle.getString("Enter the nationality."));
        ObservableList<String> types = FXCollections.observableArrayList("FRANCE", "GERMANY", "CHINA", "ITALY", "SPAIN");
        nationalityField.setItems(types);
        nationalityField.setValue("FRANCE");
        xLocationField.setPromptText(resourceBundle.getString("Enter the x coordinate of the location."));
        yLocationField.setPromptText(resourceBundle.getString("Enter the y coordinate of the location."));
        nameLocationField.setPromptText(resourceBundle.getString("Enter the name of the location."));
        idField.setPromptText(resourceBundle.getString("enterID"));
        addText.setText(resourceBundle.getString("add"));
        Receiver.updateController = this;
        addButton.setOnAction(actionEvent -> {
            String name = nameField.getText();
            final_name = Reader.readName(name);
            String x = xField.getText();
            final_x = Reader.readX(x);
            String y = yField.getText();
            final_y = Reader.readY(y);
            String height = heightField.getText();
            final_height = Reader.readHeight(height);
            String birthday = birthdayField.getText();
            final_birthday = Reader.readBirthday(birthday);
            String weight = weightField.getText();
            final_weight = Reader.readWeight(weight);
            String nationality = nationalityField.getValue();
            final_nationality = Reader.readNationality(nationality);
            String xLocation = xLocationField.getText();
            final_xLocation = Reader.readXLocation(xLocation);
            String yLocation = yLocationField.getText();
            final_yLocation = Reader.readYLocation(yLocation);
            String nameLocation = nameLocationField.getText();
            final_nameLocation = Reader.readNameLocation(nameLocation);
            String ID = idField.getText();
            if(final_name != null && final_x != null && final_y != null && final_height != null && final_birthday != null
                && final_weight != null && final_nationality != null && final_xLocation != null && final_yLocation != null
                &&  final_nameLocation != null && ID != null){
                create();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ой!");
                alert.setHeaderText("Убедитесь, пожалуйста, в том, что вы всё ввели правильно..");
                alert.showAndWait();
            }
            try {
                ConsoleManager.invoke("update " + ID);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    public Person create() {
        return Creator.create(final_name, final_x, final_y, final_height, final_birthday, final_weight, final_nationality,
                final_xLocation, final_yLocation, final_nameLocation);
    }
}
