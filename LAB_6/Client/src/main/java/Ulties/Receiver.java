package Ulties;

import Commands.SerializedCommands.SerializedArgumentCommand;
import Commands.SerializedCommands.SerializedCombinedCommand;
import Commands.SerializedCommands.SerializedObjectCommand;
import Commands.SerializedCommands.SerializedSimplyCommand;
import Commands.SpecificCommands.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Receiver {

    private final Invoker invoker;
    private final Sender sender;
    private final CommandAsker commandAsker = new CommandAsker(new InputChecker());
    private final Communicator communicator;
    private DatagramChannel datagramChannel;
    private final HashMap<String, Boolean> inStack = new HashMap<>();


    public Receiver(Invoker invoker, Sender sender, Communicator communicator){
        this.invoker = invoker;
        this.sender = sender;
        this.communicator = communicator;
    }

    private void setWriteChannel() throws IOException {
        sender.setDatagramChannel(communicator.selectWriteChannel());
    }

    public void help(){
        invoker.getCommands().forEach((name, command) -> command.aboutCommand());
    }

    public void info() throws IOException {
        SerializedSimplyCommand serializedSimplyCommand = new SerializedSimplyCommand(new InfoCommand());
        setWriteChannel();
        sender.sendObject(serializedSimplyCommand);
        byte[] responseBytes = new byte[4096];
        ByteBuffer responseBuffer = ByteBuffer.wrap(responseBytes);
        datagramChannel = communicator.selectReadChannel();
        if(datagramChannel == null) return;
        datagramChannel.receive(responseBuffer);
        String response = new String(responseBytes);
        response = response.substring(7);
        response = response.trim();
        System.out.println(response);
    }

    public void show() throws IOException {
        SerializedSimplyCommand serializedSimplyCommand = new SerializedSimplyCommand(new ShowCommand());
        setWriteChannel();
        sender.sendObject(serializedSimplyCommand);
        byte[] responseBytes = new byte[4096];
        ByteBuffer responseBuffer = ByteBuffer.wrap(responseBytes);
        datagramChannel = communicator.selectReadChannel();
        if(datagramChannel == null) return;
        datagramChannel.receive(responseBuffer);
        System.out.println("length: " + responseBytes.length);
        System.out.println(Arrays.toString(responseBytes));
        System.out.println("--------------------");
        String response = new String(responseBytes);
        response = response.substring(7);
        response = response.trim();
        System.out.println(response);
    }

    public void exit(){
        //System.out.println("Client: exit!");
        System.exit(0);
    }

    public void clear() throws IOException {
        //System.out.println("Client: Sent ClearCommand");
        SerializedSimplyCommand serializedSimplyCommand = new SerializedSimplyCommand(new ClearCommand());
        setWriteChannel();
        sender.sendObject(serializedSimplyCommand);
        byte[] responseBytes = new byte[4096];
        ByteBuffer responseBuffer = ByteBuffer.wrap(responseBytes);
        datagramChannel = communicator.selectReadChannel();
        if(datagramChannel == null) return;
        datagramChannel.receive(responseBuffer);
        //System.out.println("length: " + responseBytes.length);
        //System.out.println(Arrays.toString(responseBytes));
        String response = new String(responseBytes);
        response = response.substring(7);
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

    public void add() throws IOException {
        //System.out.println("Client: Sent AddCommand");
        SerializedObjectCommand serializedObjectCommand =  new SerializedObjectCommand(new AddCommand(),
                commandAsker.createPerson());
        setWriteChannel();
        sender.sendObject(serializedObjectCommand);
        byte[] responseBytes = new byte[4096];
        ByteBuffer responseBuffer = ByteBuffer.wrap(responseBytes);
        datagramChannel = communicator.selectReadChannel();
        if(datagramChannel == null) return;
        datagramChannel.receive(responseBuffer);
        //System.out.println("length: " + responseBytes.length);
        //System.out.println(Arrays.toString(responseBytes));
        String response = new String(responseBytes);
        response = response.substring(7);
        response = response.trim();
        System.out.println(response);
    }

    public void countLessThanBirthDay(String args) throws IOException {
        //System.out.println("Client: Sent CountLessThanBirthdayCommand");
        if(!commandAsker.birthdayValidCheck(args)){
            System.out.println("Birthday is inserted in incorrect format! Please check for validness!");
            return;
        }
        SerializedArgumentCommand serializedArgumentCommand = new SerializedArgumentCommand(
                new CountLessThanBirthdayCommand(), args);
        setWriteChannel();
        sender.sendObject(serializedArgumentCommand);
        byte[] responseBytes = new byte[4096];
        ByteBuffer responseBuffer = ByteBuffer.wrap(responseBytes);
        datagramChannel = communicator.selectReadChannel();
        if(datagramChannel == null) return;
        datagramChannel.receive(responseBuffer);
        //System.out.println("length: " + responseBytes.length);
        //System.out.println(Arrays.toString(responseBytes));
        String response = new String(responseBytes);
        response = response.substring(7);
        response = response.trim();
        System.out.println(response);
    }

    public void groupCountingByID() throws IOException {
        //System.out.println("Client: Sent groupCountingByIDCommand");
        SerializedSimplyCommand serializedSimplyCommand = new SerializedSimplyCommand(new GroupCountingByIDCommand());
        setWriteChannel();
        sender.sendObject(serializedSimplyCommand);
        byte[] responseBytes = new byte[4096];
        ByteBuffer responseBuffer = ByteBuffer.wrap(responseBytes);
        datagramChannel = communicator.selectReadChannel();
        if(datagramChannel == null) return;
        datagramChannel.receive(responseBuffer);
        //System.out.println("length: " + responseBytes.length);
        //System.out.println(Arrays.toString(responseBytes));
        String response = new String(responseBytes);
        response = response.substring(7);
        response = response.trim();
        System.out.println(response);
    }

    public void printFieldAscendingHeight() throws IOException {
        SerializedSimplyCommand serializedSimplyCommand = new SerializedSimplyCommand(new PrintFieldAscendingHeightCommand());
        setWriteChannel();
        sender.sendObject(serializedSimplyCommand);
        byte[] responseBytes = new byte[4096];
        ByteBuffer responseBuffer = ByteBuffer.wrap(responseBytes);
        datagramChannel = communicator.selectReadChannel();
        if(datagramChannel == null) return;
        datagramChannel.receive(responseBuffer);
        //System.out.println("length: " + responseBytes.length);
        //System.out.println(Arrays.toString(responseBytes));
        String response = new String(responseBytes);
        response = response.substring(7);
        response = response.trim();
        System.out.println(response);
    }

    public void removeByID(String arg) throws IOException {
        SerializedArgumentCommand serializedArgumentCommand = new SerializedArgumentCommand(new RemoveByIdCommand(), arg);
        setWriteChannel();
        sender.sendObject(serializedArgumentCommand);
        byte[] responseBytes = new byte[4096];
        ByteBuffer responseBuffer = ByteBuffer.wrap(responseBytes);
        datagramChannel = communicator.selectReadChannel();
        if(datagramChannel == null) return;
        datagramChannel.receive(responseBuffer);
        //System.out.println("length: " + responseBytes.length);
        //System.out.println(Arrays.toString(responseBytes));
        String response = new String(responseBytes);
        response = response.substring(7);
        response = response.trim();
        System.out.println(response);
    }

    public void removeGreater() throws IOException {
        SerializedObjectCommand serializedObjectCommand = new SerializedObjectCommand( new RemoveGreaterCommand(),
                commandAsker.createPerson());
        setWriteChannel();
        sender.sendObject(serializedObjectCommand);
        byte[] responseBytes = new byte[4096];
        ByteBuffer responseBuffer = ByteBuffer.wrap(responseBytes);
        datagramChannel = communicator.selectReadChannel();
        if(datagramChannel == null) return;
        datagramChannel.receive(responseBuffer);
        //System.out.println("length: " + responseBytes.length);
        //System.out.println(Arrays.toString(responseBytes));
        String response = new String(responseBytes);
        response = response.substring(7);
        response = response.trim();
        System.out.println(response);
    }

    public void removeLower() throws IOException {

        SerializedObjectCommand serializedObjectCommand = new SerializedObjectCommand(new RemoveLowerCommand(),
                commandAsker.createPerson());
        setWriteChannel();
        sender.sendObject(serializedObjectCommand);
        byte[] responseBytes = new byte[4096];
        ByteBuffer responseBuffer = ByteBuffer.wrap(responseBytes);
        datagramChannel = communicator.selectReadChannel();
        if(datagramChannel == null) return;
        datagramChannel.receive(responseBuffer);
        String response = new String(responseBytes);
        response = response.substring(7);
        response = response.trim();
        System.out.println(response);
    }

    public void update() throws IOException{
        SerializedCombinedCommand serializedCombinedCommand = new SerializedCombinedCommand(new UpdateCommand(),
                commandAsker.idAsker(), commandAsker.createPerson());
        setWriteChannel();
        sender.sendObject(serializedCombinedCommand);
        byte[] responseBytes = new byte[4096];
        ByteBuffer responseBuffer = ByteBuffer.wrap(responseBytes);
        datagramChannel = communicator.selectReadChannel();
        if(datagramChannel == null) return;
        datagramChannel.receive(responseBuffer);
        String response = new String(responseBytes);
        response = response.substring(7);
        response = response.trim();
        System.out.println(response);
    }
}