package Main;

import Commands.*;
import Core.*;

import java.util.Scanner;

/**
 * @author Nguyen Ngoc Duc
 * This work is inspired by https://www.youtube.com/watch?v=7Pj5kAhVBlg
 * Generally, I build the structure of lab by consulting from several sources of design pattern and code.
 */
public class Main {
    static final String FILE_PATH = "src/main/java/File/";
    static String fileName;

    public static void main(String[] args) {
        try {
            fileName = args[0];
        } catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Please insert file input via command line argument!");
            System.exit(-1);
        }
        System.out.println("Welcome to my program!");
        System.out.println(" +\"\"\"\"\"+ ");
        System.out.println("[| o o |]");
        System.out.println(" |  ^  | ");
        System.out.println(" | '-' | ");
        System.out.println(" +-----+ ");
        System.out.println("-----------------------");
        System.out.println("If you have problems, help command will save your life!");
        CollectionManager collectionManager = new CollectionManager();
        collectionManager.readInputFromJsonFile(FILE_PATH + fileName);
        InputChecker inputChecker = new InputChecker();
        CommandAsker commandAsker = new CommandAsker(inputChecker);
        CommandManager commandManager = new CommandManager(
                new AddCommand(collectionManager, commandAsker),
                new ClearCommand(collectionManager),
                new CountLessThanBirthdayCommand(collectionManager, inputChecker),
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
        Commander commander = new Commander(commandManager, new Scanner(System.in),FILE_PATH + fileName);
        commander.interactiveMode();
    }
}
