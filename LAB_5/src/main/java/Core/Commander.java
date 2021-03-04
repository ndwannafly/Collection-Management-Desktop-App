package Core;

import Commands.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.*;

public class Commander {
    private  HashMap<String, Boolean> inStack = new HashMap<>();
    private CommandManager commandManager;
    private String outputFile;
    private Scanner userScanner;
    private Deque<String> historyRecorder = new ArrayDeque<>(14);

    static final String FILE_PATH = "D:\\first course 2020-2021\\semester 2\\Programming\\LAB_5\\src\\main\\java\\File\\";
    public Commander(CommandManager C, Scanner sc, String oFile){
        this.commandManager = C;
        this.outputFile = oFile;
        this.userScanner = sc;
    }

    public void interactiveMode() throws ParseException {
        while(true) {
            String[] userCommand  = userScanner.nextLine().trim().split(" ");
            if(userCommand.length > 2 ){
                System.out.println("Invalid command! Valid command should contain 1 or 2 arguments." +
                        " Please insert again!");
                continue;
            }

            if(!categorizeCommand(userCommand)){
                System.out.println("Command is invalid. Can't execute!");
                continue;
            }
            updateHistory(userCommand);
            System.out.println("----------------------");
        }
    }

    public void updateHistory(String[] newCommand){
        String s;
        s = String.join(" ",newCommand);

        if(historyRecorder.size() == 14 ) historyRecorder.removeFirst();
        historyRecorder.addLast(s);
    }

    private boolean scriptMode(String fileName){

        System.out.println("Executing script file " + fileName);
        if(inStack.get(fileName) != null){
            if(inStack.get(fileName)){
                System.out.println("To avoid infinite recursion. File " + fileName + " can't be executed.");
                return false;
            }
        }
        inStack.put(fileName, true);
        File scriptFile = new File(FILE_PATH + fileName);
        Scanner fileScanner;
        try {
            fileScanner = new Scanner(scriptFile);
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("Script file doesn't exist. Please check the file path!");
            return false;
        }
        while(true){
            if(!fileScanner.hasNextLine()) break;
            String[] userCommand = fileScanner.nextLine().trim().split(" ");
            if(userCommand.length > 2){
                System.out.println("Can't execute! Invalid command! Valid command should contain 1 or 2 arguments.");
                continue;
            }
            if(!categorizeCommand(userCommand)){
                System.out.println("Command is invalid. Can't execute!");
                continue;
            }
            updateHistory(userCommand);
            System.out.println("------------");
        }
        inStack.put(fileName, false);
        return true;
    }

    private boolean categorizeCommand(String[] userCommand){
        switch (userCommand[0]){
            case "execute_script":
                if(userCommand.length == 1){
                    System.out.println("Please insert script_file!");
                    return false;
                }
                return scriptMode(userCommand[1]);
            case "help":
                if(userCommand.length != 1) {
                    return false;
                }
                return commandManager.help();
            case "info":
                if(userCommand.length != 1) {
                    System.out.println("This command doesn't support argument!");
                    return false;
                }
                return commandManager.info();
            case "show":
                if(userCommand.length != 1){
                    System.out.println("This command doesn't support argument!");
                    return false;
                }
                return commandManager.show();
            case "add":
                if(userCommand.length != 1){
                    System.out.println("To add new person, you should insert name of command 'add' only!");
                    return false;
                }
                return commandManager.add();
            case "clear":
                if(userCommand.length != 1){
                    System.out.println("This command doesn't support argument!");
                    return false;
                }
                return commandManager.clear();
            case "count_less_than_birthday":
                if(userCommand.length == 1){
                    System.out.println("Please insert the date in the same line with the command!");
                    return false;
                }
                return commandManager.countLessThanBirthday(userCommand[1]);
            case "exit":
                if(userCommand.length != 1) {
                    System.out.println("This command doesn't support argument!");
                    return false;
                }
                return commandManager.exit();
            case "group_counting_by_id":
                if(userCommand.length != 1){
                    System.out.println("This command doesn't support argument!");
                    return false;
                }
                return commandManager.groupCountingById();
            case "history":
                if(userCommand.length != 1){
                    System.out.println("This command doesn't support argument!");
                    return false;
                }
                for (String cm : historyRecorder){
                    System.out.println(cm);
                }
                return true;
            case "print_field_ascending_height":
                if(userCommand.length != 1){
                    System.out.println("This command doesn't support argument!");
                    return false;
                }
                return commandManager.printFieldAscendingHeight();
            case "remove_greater":
                if(userCommand.length != 1){
                    System.out.println("Please insert the name of command only, " +
                            "after that insert fields for object");
                    return false;
                }
                return commandManager.removeGreater();
            case "remove_by_id":
                if(userCommand.length == 1){
                    System.out.println("Please insert the Id and the command in the same line");
                    return false;
                }
                return commandManager.removeById(userCommand[1]);
            case "remove_lower":
                if(userCommand.length != 1){
                    System.out.println("Please insert the name of command only, " +
                            "after that insert fields for object");
                    return false;
                }
                return commandManager.removeLower();
            case "update":
                if(userCommand.length == 1){
                    System.out.println("Please insert thte Id and the command in the same line");
                    return false;
                }
                return commandManager.update(userCommand[1]);
            case "save":
                if(userCommand.length != 1){
                    System.out.println("This command doesn't support argument!");
                    return false;
                }
                return commandManager.save(outputFile);
            default:
                System.out.println("Your command is not supported. Please insert correct name!\n" +
                        "Use help command to show the guideline.");
                return false;
        }
    }


}
