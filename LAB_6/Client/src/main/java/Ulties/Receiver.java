package Ulties;

import Commands.Command;
import Commands.SerializedCommands.SerializedArgumentCommand;
import Commands.SerializedCommands.SerializedCombinedCommand;
import Commands.SerializedCommands.SerializedObjectCommand;
import Commands.SerializedCommands.SerializedSimplyCommand;
import Commands.SpecificCommands.*;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Arrays;

public class Receiver {
    private Invoker invoker;
    private Sender sender;
    private DatagramChannel datagramChannel;
    private CommandAsker commandAsker = new CommandAsker(new InputChecker());
    public Receiver(Invoker invoker, Sender sender, DatagramChannel datagramChannel){
        this.invoker = invoker;
        this.sender = sender;
        this.datagramChannel = datagramChannel;
    }

    public void help(){
        //System.out.println("Information of commands");
        invoker.getCommands().forEach((name, command) -> command.aboutCommand());
    }

    public void info() throws IOException {
        //System.out.println("Client: Sent Info command");
        SerializedSimplyCommand serializedSimplyCommand = new SerializedSimplyCommand(new InfoCommand());
        sender.sendObject(serializedSimplyCommand);
        byte[] responseBytes = new byte[4096];
        ByteBuffer responseBuffer = ByteBuffer.wrap(responseBytes);
        datagramChannel.receive(responseBuffer);
        //System.out.println("length: " + responseBytes.length);
        //System.out.println(Arrays.toString(responseBytes));
        String response = new String(responseBytes);
        response = response.substring(7);
        System.out.println(response);
    }

    public void show() throws IOException {
        SerializedSimplyCommand serializedSimplyCommand = new SerializedSimplyCommand(new ShowCommand());
        sender.sendObject(serializedSimplyCommand);
        byte[] responseBytes = new byte[4096];
        ByteBuffer responseBuffer = ByteBuffer.wrap(responseBytes);
        datagramChannel.receive(responseBuffer);
        String response = new String(responseBytes);
        response = response.substring(7);
        System.out.println(response);
    }

    public void exit(){
        //System.out.println("Client: exit!");
        System.exit(0);
    }

    public void clear() throws IOException {
        //System.out.println("Client: Sent ClearCommand");
        SerializedSimplyCommand serializedSimplyCommand = new SerializedSimplyCommand(new ClearCommand());
        sender.sendObject(serializedSimplyCommand);
        byte[] responseBytes = new byte[4096];
        ByteBuffer responseBuffer = ByteBuffer.wrap(responseBytes);
        datagramChannel.receive(responseBuffer);
        String response = new String(responseBytes);
        response = response.substring(7);
        System.out.println(response);
    }

    public void add() throws IOException {
        //System.out.println("Client: Sent AddCommand");
        SerializedObjectCommand serializedObjectCommand =  new SerializedObjectCommand(new AddCommand(),
                commandAsker.createPerson());
        sender.sendObject(serializedObjectCommand);
        byte[] responseBytes = new byte[4096];
        ByteBuffer responseBuffer = ByteBuffer.wrap(responseBytes);
        datagramChannel.receive(responseBuffer);
        String response = new String(responseBytes);
        response = response.substring(7);
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
        sender.sendObject(serializedArgumentCommand);
        byte[] responseBytes = new byte[4096];
        ByteBuffer responseBuffer = ByteBuffer.wrap(responseBytes);
        datagramChannel.receive(responseBuffer);
        String response = new String(responseBytes);
        response = response.substring(7);
        System.out.println(response);
    }

    public void groupCountingByID() throws IOException {
        //System.out.println("Client: Sent groupCountingByIDCommand");
        SerializedSimplyCommand serializedSimplyCommand = new SerializedSimplyCommand(new GroupCountingByIDCommand());
        sender.sendObject(serializedSimplyCommand);
        byte[] responseBytes = new byte[4096];
        ByteBuffer responseBuffer = ByteBuffer.wrap(responseBytes);
        datagramChannel.receive(responseBuffer);
        String response = new String(responseBytes);
        response = response.substring(7);
        System.out.println(response);
    }

    public void printFieldAscendingHeight() throws IOException {
        SerializedSimplyCommand serializedSimplyCommand = new SerializedSimplyCommand(new PrintFieldAscendingHeightCommand());
        sender.sendObject(serializedSimplyCommand);
        byte[] responseBytes = new byte[4096];
        ByteBuffer responseBuffer = ByteBuffer.wrap(responseBytes);
        datagramChannel.receive(responseBuffer);
        String response = new String(responseBytes);
        response = response.substring(7);
        System.out.println(response);
    }

    public void removeByID(String arg) throws IOException {
        SerializedArgumentCommand serializedArgumentCommand = new SerializedArgumentCommand(new RemoveByIdCommand(), arg);
        sender.sendObject(serializedArgumentCommand);
        byte[] responseBytes = new byte[4096];
        ByteBuffer responseBuffer = ByteBuffer.wrap(responseBytes);
        datagramChannel.receive(responseBuffer);
        String response = new String(responseBytes);
        response = response.substring(7);
        System.out.println(response);
    }

    public void removeGreater() throws IOException {
        SerializedObjectCommand serializedObjectCommand = new SerializedObjectCommand( new RemoveGreaterCommand(),
                commandAsker.createPerson());
        sender.sendObject(serializedObjectCommand);
        byte[] responseBytes = new byte[4096];
        ByteBuffer responseBuffer = ByteBuffer.wrap(responseBytes);
        datagramChannel.receive(responseBuffer);
        String response = new String(responseBytes);
        response = response.substring(7);
        System.out.println(response);
    }

    public void removeLower() throws IOException {

        SerializedObjectCommand serializedObjectCommand = new SerializedObjectCommand(new RemoveLowerCommand(),
                commandAsker.createPerson());
        sender.sendObject(serializedObjectCommand);
        byte[] responseBytes = new byte[4096];
        ByteBuffer responseBuffer = ByteBuffer.wrap(responseBytes);
        datagramChannel.receive(responseBuffer);
        String response = new String(responseBytes);
        response = response.substring(7);
        System.out.println(response);
    }

    public void update() throws IOException{
        SerializedCombinedCommand serializedCombinedCommand = new SerializedCombinedCommand(new UpdateCommand(),
                commandAsker.idAsker(), commandAsker.createPerson());
        sender.sendObject(serializedCombinedCommand);
        byte[] responseBytes = new byte[4096];
        ByteBuffer responseBuffer = ByteBuffer.wrap(responseBytes);
        datagramChannel.receive(responseBuffer);
        String response = new String(responseBytes);
        response = response.substring(7);
        System.out.println(response);
    }
}
