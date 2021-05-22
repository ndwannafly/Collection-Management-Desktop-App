package Utils;

import Commands.SpecificCommands.*;

import java.io.*;
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
        Receiver receiver = new Receiver(invoker, sender);

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
        invoker.register("execute_script", new ExecuteScript(receiver));
        invoker.register("login", new LoginCommand(receiver));
        invoker.register("register", new RegisterCommand(receiver));

        System.out.println("Insert 'login' if you are not logged in!");
        System.out.println("Insert 'register' if you don't have account yet");

        Scanner userInput = new Scanner(System.in);
        while(true){
            if(!userInput.hasNextLine()){
                communicator.endCommunication();
                System.exit(0);
            }
            String strCommand = userInput.nextLine();
            if(strCommand.contains(";") | strCommand.contains("'") | strCommand.contains("\"")){
                System.out.println("Please insert request without character ; ' \" ");
                System.out.println("----------------------------------------");
                continue;
            }
            String[] userCommand = strCommand.trim().split(" ");
            invoker.executeCommand(userCommand);
            System.out.println("----------------------------------------");
        }

    }

}