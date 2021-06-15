package lab8.Client;


import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import lab8.Commands.SerializedCommands.SerializedArgumentCommand;
import lab8.Commands.SerializedCommands.SerializedCombinedCommand;
import lab8.Commands.SpecificCommands.*;
import lab8.Controllers.AddController;
import lab8.Controllers.LoginController;
import lab8.Controllers.RegisterController;
import lab8.Controllers.ShowController;
import lab8.Data.Person;
import lab8.Main;
import lab8.Properties.Bundle;
import lab8.Utils.CollectionManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Receiver {
    public static LoginController loginController;
    public static RegisterController registerController;
    public static AddController addController;
    public static ShowController showController;

    private final Invoker invoker;
    private final Sender sender;
    //private final CommandAsker commandAsker = new CommandAsker(new InputChecker());
    private final HashMap<String, Boolean> inStack = new HashMap<>();

    ResourceBundle resourceBundle = Bundle.getResourceBundle();

    public Receiver(Invoker invoker, Sender sender) {
        this.invoker = invoker;
        this.sender = sender;
    }

    public static boolean isLogin = false;
    public static String handle = "";
    public static String password = "";
    public static String color = "";

    public void help(){
        StringBuilder response = new StringBuilder();
        invoker.getCommands().forEach((name, command) -> {
            response.append(command.aboutCommand());
        });
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Help");
        alert.setHeaderText("Информация о командах:");
        alert.setContentText(String.valueOf(response));
        alert.showAndWait();
    }

    public void info() throws IOException {
        sender.sendObject(new SerializedArgumentCommand(new InfoCommand(), handle + " " + password));
        byte[] responseBytes = new byte[256];
        DatagramPacket datagramPacket = new DatagramPacket(responseBytes, responseBytes.length);
        try {
            sender.getDatagramSocket().setSoTimeout(5000);
            sender.getDatagramSocket().receive(datagramPacket);
            String response = new String(responseBytes);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(resourceBundle.getString("info"));
            alert.setHeaderText(resourceBundle.getString("info"));
            response = response.replace("Collection's type:", resourceBundle.getString("Type of collection:"));
            response = response.replace("Initialization date:", resourceBundle.getString("Initialization date:"));
            response = response.replace("Collection's size:", resourceBundle.getString("Number of elements:"));
            alert.setContentText(response);
            alert.showAndWait();
        } catch(SocketException | SocketTimeoutException e){
            System.out.println("Problem occurred on the server!");
        }
            /*
            datagramSocket.setSoTimeout(5000);
            datagramSocket.receive(datagramPacket);*/
        String response = new String(responseBytes);
        //response = response.substring(7);
        response = response.trim();
        System.out.println(response);
    }

    public void login(String acc, String pass) throws IOException {

        sender.sendObject(new SerializedArgumentCommand(new LoginCommand(), acc + " " + pass));
        byte[] responseBytes = new byte[256];
        DatagramPacket datagramPacket = new DatagramPacket(responseBytes, responseBytes.length);
        try {
            sender.getDatagramSocket().setSoTimeout(5000);
            sender.getDatagramSocket().receive(datagramPacket);
            String response = new String(responseBytes);
            System.out.println(response);
            //response = response.substring(7);
            response = response.trim();
            String[] resultArray = response.split(" ");
            if(resultArray[0].equals("Unsuccessfully")){
                isLogin = false;
                handle = "";
                password = "";
                color = "";
                loginController.warning2();
            } else{
                isLogin = true;
                handle = acc;
                password = pass;
                color = resultArray[1];
                loginController.success();
            }
        } catch(SocketException | SocketTimeoutException e){
            loginController.warning1();
            //loginController.success();
            //System.out.println("Problem occurred on the server!");

        }
    }

    public void register(String login, String pass, String c) throws IOException {
        sender.sendObject(new SerializedArgumentCommand(new RegisterCommand(), login + " " + pass + " " + c));
        byte[] responseBytes = new byte[256];
        DatagramPacket datagramPacket = new DatagramPacket(responseBytes, responseBytes.length);
        try {
            sender.getDatagramSocket().setSoTimeout(5000);
            sender.getDatagramSocket().receive(datagramPacket);
            String response = new String(responseBytes);
            response = response.trim();
            System.out.println(response);
            if(response.equals("Unsuccessfully")){
                isLogin = false;
                handle = "";
                password = "";
                color = "";
                registerController.warning2();
            }
            else{
                isLogin = true;
                handle = login;
                password = pass;
                color = c;
                registerController.success();
            }
        } catch(SocketException | SocketTimeoutException e){
            registerController.warning1();
            //System.out.println("Problem occurred on the server!");
        }
    }
    public void show() throws IOException {
        sender.sendObject(new SerializedArgumentCommand(new ShowCommand(), handle + " " + password));
        byte[] responseBytes = new byte[8196];
        DatagramPacket datagramPacket = new DatagramPacket(responseBytes, responseBytes.length);
        try {
            sender.getDatagramSocket().setSoTimeout(5000);
            //System.out.println("waiting......");
            sender.getDatagramSocket().receive(datagramPacket);
            String response = new String(responseBytes);
            //System.out.println(response.trim());
            FileWriter writer = new FileWriter("copy.json", false);
            writer.write(response.trim());
            writer.flush();
            CollectionManager.readInputFromJsonFile("copy.json");
            Stage stage = new Stage();
            System.out.println("showing...");
            Parent root = FXMLLoader.load(Main.class.getResource("/show.fxml"));
            System.out.println("waiting...");
            Scene scene = new Scene(root);
            showController.show();
            stage.setTitle("show");
            stage.setScene(scene);
            stage.show();;
        } catch(SocketException | SocketTimeoutException e){
            System.out.println("Problem occurred on the server!");
        }
        /*        datagramSocket.setSoTimeout(5000);
        datagramSocket.receive(datagramPacket);*/
    }

    public void exit(){
        //System.out.println("Client: exit!");
        System.exit(0);
    }

    public void clear() throws IOException {
        sender.sendObject(new SerializedArgumentCommand(new ClearCommand(), handle + " " + password));
        byte[] responseBytes = new byte[256];
        DatagramPacket datagramPacket = new DatagramPacket(responseBytes, responseBytes.length);
        try {
            sender.getDatagramSocket().setSoTimeout(5000);
            sender.getDatagramSocket().receive(datagramPacket);
        } catch(SocketException | SocketTimeoutException e){
            System.out.println("Problem occurred on the server!");
        }
        /*        datagramSocket.setSoTimeout(5000);
        datagramSocket.receive(datagramPacket);*/
        String response = new String(responseBytes);
        //response = response.substring(7);
        response = response.trim();
        System.out.println(response);
    }

    public void executeScript(String fileName) throws IOException {
        if(inStack.get(fileName) != null){
            if(inStack.get(fileName)){
                System.out.println("To avoid infinite recursion. File " + fileName + " can't be executed!");
                return;
            }
        }

        inStack.put(fileName, true);

        File scriptFile = new File(fileName);
        Scanner fileScanner;
        try {
            fileScanner = new Scanner(scriptFile);
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("Script file doesn't exist. Please check the file path!");
            return ;
        }

        while(fileScanner.hasNextLine()){
            String[] userCommand = fileScanner.nextLine().trim().split(" ");
            invoker.executeCommand(userCommand);
            System.out.println("----------------------------------------");
        }

        inStack.put(fileName, false);
    }

/*    public void add() throws IOException {
        Person person = commandAsker.createPerson();
        person.setId(-1);
        person.setOwner(handle);
        sender.sendObject(new SerializedCombinedCommand(new AddCommand(), handle + " " + password, person));
        byte[] responseBytes = new byte[1024];
        DatagramPacket datagramPacket = new DatagramPacket(responseBytes, responseBytes.length);
        try {
            sender.getDatagramSocket().setSoTimeout(5000);
            sender.getDatagramSocket().receive(datagramPacket);
        } catch(SocketException | SocketTimeoutException e){
            System.out.println("Problem occurred on the server!");
        }
        String response = new String(responseBytes);
        //response = response.substring(7);
        response = response.trim();
        System.out.println(response);
    }*/

/*    public void countLessThanBirthDay(String args) throws IOException {
        if(!commandAsker.birthdayValidCheck(args)){
            System.out.println("Birthday is inserted in incorrect format! Please check for validness!");
            return;
        }

        sender.sendObject(new SerializedCombinedCommand(
                new CountLessThanBirthdayCommand(), handle + " " + password, args
        ));
        byte[] responseBytes = new byte[256];
        DatagramPacket datagramPacket = new DatagramPacket(responseBytes, responseBytes.length);
        try {
            sender.getDatagramSocket().setSoTimeout(5000);
            sender.getDatagramSocket().receive(datagramPacket);
        } catch(SocketException | SocketTimeoutException e){
            System.out.println("Problem occurred on the server!");
        }
        *//*        datagramSocket.setSoTimeout(5000);
        datagramSocket.receive(datagramPacket);*//*
        String response = new String(responseBytes);
        //response = response.substring(7);
        response = response.trim();
        System.out.println(response);
    }*/

    public void groupCountingByID() throws IOException {
        sender.sendObject(new SerializedArgumentCommand(new GroupCountingByIDCommand(), handle + " " + password));
        byte[] responseBytes = new byte[1024];
        DatagramPacket datagramPacket = new DatagramPacket(responseBytes, responseBytes.length);
        try {
            sender.getDatagramSocket().setSoTimeout(5000);
            sender.getDatagramSocket().receive(datagramPacket);
        } catch(SocketException | SocketTimeoutException e){
            System.out.println("Problem occurred on the server!");
        }
        /*        datagramSocket.setSoTimeout(5000);
        datagramSocket.receive(datagramPacket);*/
        String response = new String(responseBytes);
        //response = response.substring(7);
        response = response.trim();
        System.out.println(response);
    }

    public void printFieldAscendingHeight() throws IOException {
        sender.sendObject(new SerializedArgumentCommand(new PrintFieldAscendingHeightCommand(), handle + " " + password));
        byte[] responseBytes = new byte[1024];
        DatagramPacket datagramPacket = new DatagramPacket(responseBytes, responseBytes.length);
        try {
            sender.getDatagramSocket().setSoTimeout(5000);
            sender.getDatagramSocket().receive(datagramPacket);
        } catch(SocketException | SocketTimeoutException e){
            System.out.println("Problem occurred on the server!");
        }
        /*        datagramSocket.setSoTimeout(5000);
        datagramSocket.receive(datagramPacket);*/
        String response = new String(responseBytes);
        //response = response.substring(7);
        response = response.trim();
        System.out.println(response);
    }

    public void removeByID(String arg) throws IOException {
        sender.sendObject(new SerializedArgumentCommand(new RemoveByIdCommand(), handle + " " + password + " " + arg));
        byte[] responseBytes = new byte[256];
        DatagramPacket datagramPacket = new DatagramPacket(responseBytes, responseBytes.length);
        try {
            sender.getDatagramSocket().setSoTimeout(5000);
            sender.getDatagramSocket().receive(datagramPacket);
        } catch(SocketException | SocketTimeoutException e){
            System.out.println("Problem occurred on the server!");
        }
        String response = new String(responseBytes);
        //response = response.substring(7);
        response = response.trim();
        System.out.println(response);
    }

/*    public void removeGreater() throws IOException {
        Person person = commandAsker.createPerson();
        person.setOwner(handle);
        sender.sendObject(new SerializedCombinedCommand(new RemoveGreaterCommand(), handle + " " + password, person));
        byte[] responseBytes = new byte[256];
        DatagramPacket datagramPacket = new DatagramPacket(responseBytes, responseBytes.length);
        try {
            sender.getDatagramSocket().setSoTimeout(5000);
            sender.getDatagramSocket().receive(datagramPacket);
        } catch(SocketException | SocketTimeoutException e){
            System.out.println("Problem occurred on the server!");
        }
        String response = new String(responseBytes);
        response = response.trim();
        System.out.println(response);
    }*/

/*    public void removeLower() throws IOException {
        Person person = commandAsker.createPerson();
        person.setOwner(handle);
        sender.sendObject(new SerializedCombinedCommand(new RemoveLowerCommand(), handle + " " + password, person));
        byte[] responseBytes = new byte[256];
        DatagramPacket datagramPacket = new DatagramPacket(responseBytes, responseBytes.length);
        sender.getDatagramSocket().setSoTimeout(5000);
        try {
            sender.getDatagramSocket().receive(datagramPacket);
        } catch(SocketException e){
            System.out.println("Problem occurred on the server!");
        }
        String response = new String(responseBytes);
        response = response.trim();
        System.out.println(response);
    }*/

/*    public void update() throws IOException{
        String id = commandAsker.idAsker();
        Person person = commandAsker.createPerson();
        person.setOwner(handle);
        person.setId(Integer.parseInt(id));
        sender.sendObject(new SerializedCombinedCommand(new UpdateCommand(),
                handle + " " + password + " " + id, person));
        byte[] responseBytes = new byte[256];
        DatagramPacket datagramPacket = new DatagramPacket(responseBytes, responseBytes.length);
        sender.getDatagramSocket().setSoTimeout(5000);
        try {
            sender.getDatagramSocket().receive(datagramPacket);
        } catch(SocketException e){
            System.out.println("Problem occurred on the server!");
        }
        String response = new String(responseBytes);
        response = response.trim();
        System.out.println(response);
    }*/

    public void visualize() throws IOException {
        sender.sendObject(new SerializedArgumentCommand(new ShowCommand(), handle + " " + password));
        byte[] responseBytes = new byte[8192];
        DatagramPacket datagramPacket = new DatagramPacket(responseBytes, responseBytes.length);
        try {
            sender.getDatagramSocket().setSoTimeout(5000);
            sender.getDatagramSocket().receive(datagramPacket);
            String response = new String(responseBytes);
            System.out.println(response.trim());
            //FileWriter writer = new FileWriter("InputData.json", false);
            //writer.write(response.trim());
            //writer.flush();
            CollectionManager.readInputFromJsonFile("copy.json");
            System.out.println("size " +  CollectionManager.getCollection().size());
            painObjects(CollectionManager.getCollection());
        } catch(SocketException | SocketTimeoutException e){
            System.out.println("Problem occurred on the server!");
        }
    }

    public void painObjects(HashSet<Person> listPerson){
        Stage stage = new Stage();
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        Group root = new Group();
        Scene scene = new Scene(root, primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());
        stage.setScene(scene);
        listPerson.forEach(person -> {
            double x = person.getCoordinates().getX();
            double y = person.getCoordinates().getY();

            if(person.getCoordinates().getY() >= primaryScreenBounds.getHeight()){
                y = Math.ceil(Math.abs(Math.cos(person.getCoordinates().getY())) * primaryScreenBounds.getMaxY());
                System.out.println(y);
            }
            if(person.getCoordinates().getX() >= primaryScreenBounds.getWidth()){
                x = Math.ceil(Math.abs(Math.cos(person.getCoordinates().getX())) * primaryScreenBounds.getMaxX());
                System.out.println(x);
            }
            Circle circle = new Circle(x, y, person.getHeight());
            //System.out.println("duc");
            person.setColor("0x000000ff");
            circle.setFill(Paint.valueOf(person.getColor()));
            //System.out.println("play");
            PathTransition tt = new PathTransition(Duration.millis(1500), circle);
            tt.setNode(circle);
            tt.setPath(new Line(x, y + 20, x, y));
            tt.setAutoReverse(true);
            tt.setCycleCount(Animation.INDEFINITE);
            System.out.println("overthere");
            root.getChildren().add(circle);
            try{
                Thread.sleep((int) (Math.random() * 1000));
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            tt.play();
        });
        scene.setOnMouseClicked(e -> {
            double mouse_x = e.getX();
            double mouse_y = e.getY();
            listPerson.forEach(person -> {
                double mouse_x_max = mouse_x + person.getHeight();
                double mouse_x_min = mouse_x - person.getHeight();
                double mouse_y_max = mouse_y + person.getHeight();
                double mouse_y_min = mouse_y - person.getHeight();
                double xx = person.getCoordinates().getX();
                double yy = person.getCoordinates().getY();
                double alternative_x = Math.ceil(Math.abs(Math.cos(person.getCoordinates().getX())) * primaryScreenBounds.getMaxX());
                double alternative_y = Math.ceil(Math.abs(Math.cos(person.getCoordinates().getY())) * primaryScreenBounds.getMaxY());
                if ((((xx >= mouse_x_min) && (xx <= mouse_x_max)) || ((alternative_x >= mouse_x_min) &&
                        (alternative_x <= mouse_x_max))) && (((yy >= mouse_y_min) &&
                        (yy <= mouse_y_max)) || ((alternative_y <= mouse_y_max) && (alternative_y >= mouse_y_min)))) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("!");
                    alert.setHeaderText(person.getName() + '\n' + person.getHeight() + '\n' + person.getOwner());
                    alert.showAndWait();
                }
            });
        });
        scene.setRoot(root);
        stage.show();
    }
}
