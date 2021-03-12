package Core;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Commander {
    private final HashMap<String, Boolean> inStack = new HashMap<>();
    private final CommandManager commandManager;
    private final String outputFile;
    private final Scanner userScanner;
    private final Deque<String> historyRecorder = new ArrayDeque<>(14);

    static final String FILE_PATH = "src/main/java/File/";
    public Commander(CommandManager C, Scanner sc, String oFile){
        this.commandManager = C;
        this.outputFile = oFile;
        this.userScanner = sc;
    }

    public void interactiveMode(){
        while(true) {
            String[] userCommand  = userScanner.nextLine().trim().split(" ");
            if(userCommand.length > 2 ){
                System.out.println("Invalid command! Valid command should contain 1 or 2 arguments." +
                        " Please insert again!");
                continue;
            }
            if(userCommand[0].equals("Exit")) {
                System.exit(0);
            }
            if(categorizeCommand(userCommand)){
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
        while (fileScanner.hasNextLine()) {
            String[] userCommand = fileScanner.nextLine().trim().split(" ");
            if (userCommand.length > 2) {
                System.out.println("Can't execute! Invalid command! Valid command should contain 1 or 2 arguments.");
                continue;
            }
            if (categorizeCommand(userCommand)) {
                System.out.println("Command is invalid. Can't execute!");
                continue;
            }
            updateHistory(userCommand);
            System.out.println("------------");
        }
        inStack.put(fileName, false);
        return true;
    }

    /**
     *  to categorize user's command and try to launch it.
     * @param userCommand user command
     * @return true if the command is not executable. Otherwise, false if the command is executable
     */
    private boolean categorizeCommand(String[] userCommand){
        switch (userCommand[0]){
            case "execute_script":
                if(userCommand.length != 1){
                    return !scriptMode(userCommand[1]);
                }
                System.out.println("Please insert script_file!");
                return true;
            case "help":
                if(userCommand.length == 1) {
                    return !commandManager.help();
                }
                return true;
            case "info":
                if(userCommand.length == 1) {
                    return !commandManager.info();
                }
                System.out.println("This command doesn't support argument!");
                return true;
            case "show":
                if(userCommand.length == 1){
                    return !commandManager.show();
                }
                System.out.println("This command doesn't support argument!");
                return true;
            case "add":
                if(userCommand.length == 1){
                    return !commandManager.add();
                }
                System.out.println("To add new person, you should insert name of command 'add' only!");
                return true;
            case "clear":
                if(userCommand.length == 1){
                    return !commandManager.clear();
                }
                System.out.println("This command doesn't support argument!");
                return true;
            case "count_less_than_birthday":
                if(userCommand.length == 2){
                    return !commandManager.countLessThanBirthday(userCommand[1]);
                }
                System.out.println("Please insert the date in the same line with the command!");
                return true;
            case "exit":
                if(userCommand.length == 1) {
                    return !commandManager.exit();
                }
                System.out.println("This command doesn't support argument!");
                return true;
            case "group_counting_by_id":
                if(userCommand.length == 1){
                    return !commandManager.groupCountingById();
                }
                System.out.println("This command doesn't support argument!");
                return true;
            case "history":
                if(userCommand.length == 1){
                    for (String cm : historyRecorder){
                        System.out.println(cm);
                    }
                    return false;
                }
                System.out.println("This command doesn't support argument!");
                return true;
            case "print_field_ascending_height":
                if(userCommand.length == 1){
                    return !commandManager.printFieldAscendingHeight();
                }
                System.out.println("This command doesn't support argument!");
                return true;
            case "remove_greater":
                if(userCommand.length == 1){
                    return !commandManager.removeGreater();
                }
                System.out.println("Please insert the name of command only, " +
                        "after that insert fields for object");
                return true;
            case "remove_by_id":
                if(userCommand.length == 2){
                    return !commandManager.removeById(userCommand[1]);

                }
                System.out.println("Please insert the Id and the command in the same line");
                return true;
            case "remove_lower":
                if(userCommand.length == 1){
                    return !commandManager.removeLower();
                }
                System.out.println("Please insert the name of command only, " +
                        "after that insert fields for object");
                return true;
            case "update":
                if(userCommand.length == 2){
                    return !commandManager.update(userCommand[1]);
                }
                System.out.println("Please insert the Id and the command in the same line");
                return true;
            case "save":
                if(userCommand.length ==1){
                    return !commandManager.save(outputFile);
                }
                System.out.println("This command doesn't support argument!");
                return true;
            default:
                System.out.println("Your command is not supported. Please insert correct name!\n" +
                        "Use help command to show the guideline.");
                return true;
        }
    }


}
