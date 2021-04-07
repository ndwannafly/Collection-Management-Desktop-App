package Ulties;

import Commands.SpecificCommands.*;

import java.io.*;
import java.net.*;
import java.nio.*;
import java.nio.channels.DatagramChannel;
import java.util.Scanner;

public class ConsoleManager {


    public void interactive(String host, String p) throws IOException {
        Communicator communicator = null;
        try {
            communicator = new Communicator(host, Integer.parseInt(p));
            communicator.startCommunication();
        } catch (NumberFormatException e){
            System.out.println("Client: Error! Port is invalid!");
            System.exit(0);
        }


        Sender sender = new Sender(communicator);
        Invoker invoker = new Invoker();
        Receiver receiver = new Receiver(invoker, sender, communicator.getDatagramChannel());

        invoker.register("add", new AddCommand(receiver));
        invoker.register("clear", new ClearCommand(receiver));
        invoker.register("count_less_than_birthday", new CountLessThanBirthdayCommand(receiver));
        invoker.register("exit", new ExitCommand(receiver));
        invoker.register("group_counting_by_id", new GroupCountingByIDCommand(receiver));
        invoker.register("help", new HelpCommand(receiver));
        invoker.register("info", new InfoCommand(receiver));
        invoker.register("print_field_ascending_height", new PrintFieldAscendingHeightCommand(receiver));
        invoker.register("remove_by_id", new RemoveByIdCommand(receiver));
        invoker.register("remove_greater", new RemoveGreaterCommand(receiver));
        invoker.register("remove_lower", new RemoveLowerCommand(receiver));
        invoker.register("show", new ShowCommand(receiver));
        invoker.register("update", new UpdateCommand(receiver));

        Scanner userInput = new Scanner(System.in);
        while(userInput.hasNextLine()){
            invoker.executeCommand(userInput.nextLine().trim().split(" "));
        }

        communicator.endCommunication();
    }

}
