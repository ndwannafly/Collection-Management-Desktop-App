package Ulties;

import Commands.SerializedCommands.SerializedSimplyCommand;
import Commands.SpecificCommands.InfoCommand;

import java.io.IOException;
import java.nio.channels.DatagramChannel;

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
        serializedSimplyCommand.setID("DUC DEP TRAI!");
        sender.sendObject(serializedSimplyCommand);
    }
}
