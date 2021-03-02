package Main;

import Commands.*;
import Core.*;

import java.text.ParseException;
import java.util.Scanner;

/**
 * @author Nguyen Ngoc Duc
 * This work is inspired by https://www.youtube.com/watch?v=7Pj5kAhVBlg
 * Generally, I build the structure of lab by consulting from several sources of design pattern and code.
 */
public class Main {
    static final String FILE_PATH = "D:\\first course 2020-2021\\semester 2\\Programming\\LAB_5\\src\\main\\java\\File\\";
    public static void main(String[] args) {
        String inputFile = args[0];
        String outputFile = args[1];

        System.out.println("Welcome to my program!");
        CollectionManager collectionManager = new CollectionManager();
        collectionManager.readInputFromJsonFile(FILE_PATH + inputFile);
        //collectionManager.readInputFromJsonFile(FILE_PATH + "InputData.Json");
        InputChecker inputChecker = new InputChecker();
        CommandAsker commandAsker = new CommandAsker(inputChecker);
        CommandManager commandManager = new CommandManager(
                new AddCommand(collectionManager, commandAsker),
                new ClearCommand(collectionManager),
                new CountLessThanBirthdayCommand(collectionManager, inputChecker),
                new ExecuteScriptCommand(),
                new ExitCommand(),
                new GroupCountingByIDCommand(collectionManager),
                new HelpCommand(),
                new InfoCommand(collectionManager),
                new PrintFieldAscendingHeightCommand(collectionManager),
                new RemoveByIdCommand(collectionManager, inputChecker),
                new RemoveGreaterCommand(collectionManager, commandAsker),
                new RemoveLowerCommand(collectionManager, commandAsker),
                new SaveCommand(collectionManager),
                new ShowCommand(collectionManager),
                new UpdateCommand(collectionManager, inputChecker, commandAsker)
        );
        Commander commander = new Commander(commandManager, new Scanner(System.in),FILE_PATH + outputFile);
        try {
            commander.interactiveMode();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
