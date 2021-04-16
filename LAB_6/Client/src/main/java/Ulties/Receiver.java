package Ulties;

import Commands.SerializedCommands.SerializedSimplyCommand;
import Commands.SpecificCommands.ClearCommand;
import Commands.SpecificCommands.InfoCommand;
import Commands.SpecificCommands.ShowCommand;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Arrays;

public class Receiver {
    private Invoker invoker;
    private Sender sender;
    private DatagramChannel datagramChannel;

    public Receiver(Invoker invoker, Sender sender, DatagramChannel datagramChannel){
        this.invoker = invoker;
        this.sender = sender;
        this.datagramChannel = datagramChannel;
    }

    public void help(){
        System.out.println("Information of commands");
        invoker.getCommands().forEach((name, command) -> command.aboutCommand());
    }

    public void info() throws IOException {
        System.out.println("Client: Sent Info command");
        SerializedSimplyCommand serializedSimplyCommand = new SerializedSimplyCommand(new InfoCommand());
        sender.sendObject(serializedSimplyCommand);
        byte[] responseBytes = new byte[4096];
        ByteBuffer responseBuffer = ByteBuffer.wrap(responseBytes);
        datagramChannel.receive(responseBuffer);
        //System.out.println("length: " + responseBytes.length);
        //System.out.println(Arrays.toString(responseBytes));
        String response = new String(responseBytes);
        System.out.println(response);
        System.out.println("Client: received response from Server!");
    }

    public void show() throws IOException {
        SerializedSimplyCommand serializedSimplyCommand = new SerializedSimplyCommand(new ShowCommand());
        sender.sendObject(serializedSimplyCommand);
        byte[] responseBytes = new byte[4096];
        ByteBuffer responseBuffer = ByteBuffer.wrap(responseBytes);
        datagramChannel.receive(responseBuffer);
        String response = new String(responseBytes);
        System.out.println(response);
    }

    public void exit(){
        System.out.println("Client: exit!");
        System.exit(0);
    }

    public void clear() throws IOException {
        System.out.println("Client: Sent ClearCommand");
        SerializedSimplyCommand serializedSimplyCommand = new SerializedSimplyCommand(new ClearCommand());
        sender.sendObject(serializedSimplyCommand);
    }
}
