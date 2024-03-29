package lab8.Client;

import lab8.Commands.SpecificCommands.*;

import java.io.IOException;

public class ConsoleManager {

    public static Invoker invoker = new Invoker();


    public void start(String host, String port){
        Communicator communicator = new Communicator(host, Integer.parseInt(port));
        communicator.startCommunication();
        Sender sender = new Sender(communicator);
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
        invoker.register("login", new LoginCommand(receiver));
        invoker.register("register", new RegisterCommand(receiver));
        invoker.register("visualize", new VisualizeCommand(receiver));
        invoker.register("history", new HistoryCommand(receiver));
        invoker.register("execute_script", new ExecuteScript(receiver));
        invoker.register("sort_by_id", new SortByID(receiver));
        invoker.register("sort_by_height", new SortByHeight(receiver));
        invoker.register("sort_by_weight", new SortByWeight(receiver));
        invoker.register("sort_by_birthday", new SortByBirthday(receiver));
        invoker.register("sort_by_date", new SortByDate(receiver));
        invoker.register("sort_by_name", new SortByName(receiver));
        invoker.register("sort_by_name_location", new SortByNameLocation(receiver));
        invoker.register("sort_by_nationality", new SortByNationality(receiver));
        invoker.register("sort_by_owner", new SortByOwner(receiver));
        invoker.register("sort_by_weight", new SortByWeight(receiver));
        invoker.register("sort_by_x", new SortByX(receiver));
        invoker.register("sort_by_xl", new SortByXLocation(receiver));
        invoker.register("sort_by_y", new SortByY(receiver));
        invoker.register("sort_by_yl", new SortByYLocation(receiver));

    }

    public static void invoke(String command) throws IOException {
        invoker.executeCommand(command.split(" "));
    }

}
