package lab8.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import lab8.Client.ConsoleManager;
import lab8.Client.Receiver;
import lab8.Data.Person;
import lab8.Main;
import lab8.Properties.Bundle;
import lab8.Utils.CollectionManager;

import java.io.IOException;
import java.util.Optional;
import java.util.ResourceBundle;

import static javafx.scene.control.TableView.CONSTRAINED_RESIZE_POLICY;

public class ShowController {

    @FXML
    private GridPane gridPane;

    @FXML
    private Button addButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button removeByIdButton;

    @FXML
    private Button clearButton;

    @FXML
    private Button removeGreaterButton;

    @FXML
    private Button removeLowerButton;

    @FXML
    private Button groupCountingByIdButton;

    @FXML
    private Button countLessThanBirthdayButton;

    @FXML
    private Button printFieldAscendingHeightButton;

    @FXML
    private FlowPane gridPane2;
    @FXML
            private Pane tablePane;

    ResourceBundle resourceBundle = Bundle.getResourceBundle();

    @FXML
    void initialize(){
        Receiver.showController = this;
        addButton.setText(resourceBundle.getString("add"));
        updateButton.setText(resourceBundle.getString("update"));
        removeByIdButton.setText(resourceBundle.getString("remove_by_id"));
        clearButton.setText(resourceBundle.getString("clear"));
        removeGreaterButton.setText(resourceBundle.getString("remove_greater"));
        removeLowerButton.setText(resourceBundle.getString("remove_lower"));
        groupCountingByIdButton.setText(resourceBundle.getString("group_counting_by_id"));
        countLessThanBirthdayButton.setText(resourceBundle.getString("count_less_than_birthday"));
        printFieldAscendingHeightButton.setText(resourceBundle.getString("print_field_ascending_height"));
        //openSort();
        addButton.setOnAction(actionEvent -> {
            Stage stage = new Stage();
            Parent root = null;
            try{
                root = FXMLLoader.load(Main.class.getResource("/add.fxml"));
                Scene scene = new Scene(root);
                stage.setTitle(resourceBundle.getString("add"));
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        updateButton.setOnAction(actionEvent -> {
            Stage stage = new Stage();
            Parent root = null;
            try{
                root = FXMLLoader.load(Main.class.getResource("/update.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scene scene = new Scene(root);
            stage.setTitle(resourceBundle.getString("update"));
            stage.setScene(scene);
            stage.show();
        });

        removeByIdButton.setOnAction(actionEvent -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle(resourceBundle.getString("remove_by_id"));
            dialog.setHeaderText(resourceBundle.getString("enterID"));
            Optional<String> result = dialog.showAndWait();
            result.ifPresent(name ->{
                try{
                    String res = result.get();
                    ConsoleManager.invoke("remove_by_id " + res);
                    //show();
                } catch(IOException e){
                    e.printStackTrace();
                }
            });
        });

        clearButton.setOnAction(actionEvent -> {
            try {
                ConsoleManager.invoke("clear");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        removeLowerButton.setOnAction(actionEvent -> {
            Stage stage = new Stage();
            Parent root = null;
            try{
                root = FXMLLoader.load(Main.class.getResource("/removeLower.fxml"));
                Scene scene = new Scene(root);
                stage.setTitle(resourceBundle.getString("remove_lower"));
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        removeGreaterButton.setOnAction(actionEvent -> {
            Stage stage = new Stage();
            Parent root = null;
            try{
                root = FXMLLoader.load(Main.class.getResource("/removeGreater.fxml"));
                Scene scene = new Scene(root);
                stage.setTitle(resourceBundle.getString("remove_greater"));
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        groupCountingByIdButton.setOnAction(actionEvent -> {

            try {
                ConsoleManager.invoke("group_counting_by_id");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        countLessThanBirthdayButton.setOnAction(actionEvent -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle(resourceBundle.getString("count_less_than_birthday"));
            dialog.setHeaderText(resourceBundle.getString("Enter the birthday."));
            Optional<String> result = dialog.showAndWait();
            result.ifPresent(name ->{
                try{
                    String res = result.get();
                    ConsoleManager.invoke("count_less_than_birthday " + res);
                } catch(IOException e){
                    e.printStackTrace();
                }
            });
        });
        printFieldAscendingHeightButton.setOnAction(actionEvent -> {

            try {
                ConsoleManager.invoke("print_field_ascending_height");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

   /* public void openSort(){
        Stage stage = new Stage();
        Parent root = null;
        try{
            root = FXMLLoader.load(Main.class.getResource("sort.fxml"));
        } catch(IOException e){
            e.printStackTrace();;
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }*/


    public void show() {
        ObservableList<Person> personObservableList = FXCollections.observableArrayList(
                CollectionManager.getCollection()
        );

        TableView<Person> table = new TableView<>(personObservableList);
//        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
//        table.setPrefWidth(primaryScreenBounds.getWidth());
//        table.setPrefHeight(primaryScreenBounds.getHeight() - 300);
//        table.setColumnResizePolicy(CONSTRAINED_RESIZE_POLICY);

        // id
        TableColumn<Person, Long> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        table.getColumns().add(idColumn);

        //owner
        TableColumn<Person, String> ownerColumn = new TableColumn<>(resourceBundle.getString("owner"));
        ownerColumn.setCellValueFactory(new PropertyValueFactory<>("owner"));
        table.getColumns().add(ownerColumn);

        //name
        TableColumn<Person, String> nameColumn = new TableColumn<>(resourceBundle.getString("name"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        table.getColumns().add(nameColumn);
        nameColumn.setEditable(true);
        nameColumn.setOnEditCommit(t -> t.getRowValue().setName(t.getNewValue()));

        // x
        TableColumn<Person, Double> xCoordinateColumn = new TableColumn<>("X");
        xCoordinateColumn.setCellValueFactory(new PropertyValueFactory<>("x"));
        table.getColumns().add(xCoordinateColumn);
        xCoordinateColumn.setEditable(true);

        // y
        TableColumn<Person, Double> yCoordinateColumn = new TableColumn<>("Y");
        yCoordinateColumn.setCellValueFactory(new PropertyValueFactory<>("y"));
        table.getColumns().add(yCoordinateColumn);
        yCoordinateColumn.setEditable(true);

        //creationDate
        TableColumn<Person, Double> dateColumn = new TableColumn<>(resourceBundle.getString("creationDate"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("creationDate"));
        table.getColumns().add(dateColumn);

        // height
        TableColumn<Person, Double> heightColumn = new TableColumn<>(resourceBundle.getString("height"));
        heightColumn.setCellValueFactory(new PropertyValueFactory<>("height"));
        table.getColumns().add(heightColumn);
        heightColumn.setEditable(true);

        // birthday
        TableColumn<Person, String> birthdayColumn = new TableColumn<>(resourceBundle.getString("birthday"));
        birthdayColumn.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        table.getColumns().add(birthdayColumn);
        birthdayColumn.setEditable(true);

        // weight
        TableColumn<Person, Double> weightColumn = new TableColumn<>(resourceBundle.getString("weight"));
        weightColumn.setCellValueFactory(new PropertyValueFactory<>("weight"));
        table.getColumns().add(weightColumn);
        weightColumn.setEditable(true);

        // nationality
        TableColumn<Person, String> nationalityColumn = new TableColumn<>(resourceBundle.getString("nationality"));
        nationalityColumn.setCellValueFactory(new PropertyValueFactory<>("nationality"));
        table.getColumns().add(nationalityColumn);
        nationalityColumn.setEditable(true);

        //x_location
        TableColumn<Person, Double> xLocationColumn = new TableColumn<>("xl");
        xLocationColumn.setCellValueFactory(new PropertyValueFactory<>("xl"));
        table.getColumns().add(xLocationColumn);
        xLocationColumn.setEditable(true);

        //y_location
        TableColumn<Person, Double> yLocationColumn = new TableColumn<>("yl");
        yLocationColumn.setCellValueFactory(new PropertyValueFactory<>("yl"));
        table.getColumns().add(yLocationColumn);
        yLocationColumn.setEditable(true);

        //name_location
        TableColumn<Person, String> nameLocationColumn = new TableColumn<>(resourceBundle.getString("name location"));
        nameLocationColumn.setCellValueFactory(new PropertyValueFactory<>("nameLocation"));
        table.getColumns().add(nameLocationColumn);
        nameLocationColumn.setEditable(true);

        table.maxWidth(tablePane.getScene().getWidth());
        table.prefWidth(tablePane.getScene().getWidth());
        table.maxHeight(tablePane.getScene().getHeight());

        tablePane.getChildren().add(table);
//        FlowPane root1 = new FlowPane(500, 500, table);
//        gridPane.add(gridPane2, 0,0,1,2);
//        gridPane.add(root1, 0, 1);
    }
}
