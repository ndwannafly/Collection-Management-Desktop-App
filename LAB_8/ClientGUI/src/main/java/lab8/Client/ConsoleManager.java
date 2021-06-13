package lab8.Client;

import lab8.Commands.SpecificCommands.*;

import java.io.IOException;

public class ConsoleManager {

    public static Invoker invoker = new Invoker();


    public void start(String host, String port){
        Communicator communicator = new Communicator(host, Integer.parseInt(port));
        communicator.startCommunication();
        Sender sender = new Sender(communicator);
        Receiver receiver = new Receiver(invoker, sender );

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
        invoker.register("login", new LoginCommand(receiver));
        invoker.register("register", new RegisterCommand(receiver));
        invoker.register("visualize", new VisualizeCommand(receiver));
    }

    public static void invoke(String command) throws IOException {
        invoker.executeCommand(command.split(" "));
    }

}
