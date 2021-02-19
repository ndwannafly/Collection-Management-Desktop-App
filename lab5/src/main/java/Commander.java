import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

/**
 * @author Ngoc Duc Nguyen
 * This class builds interactive mode
 */
public class Commander {
    private Deque<String> recorder = new ArrayDeque<String>(14);
    private CollectionManager collectionManager;
    private CommandAsker commandAsker = new CommandAsker();
    private String outputFile;
    public Commander(CollectionManager C, String outFile){
        this.collectionManager = C;
        this.outputFile = outFile;
    }

    public void interactiveMode() throws ParseException {
        Scanner scanner =   new Scanner(System.in);
        while(true) {
            String userCommand = scanner.nextLine();
            if(recorder.size() == 14 ) recorder.removeFirst();
            recorder.addLast(userCommand);
            if(userCommand.equals("execute_script")){
                System.out.println("here");
                executeFile(commandAsker.fileNameAsker());
            }
            else {
                CategorizeCommand(userCommand);
            }
        }
    }

    private void executeFile(String script){
        System.out.println(script);
        String line;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(script));
            while((line = bufferedReader.readLine()) != null){
                if(recorder.size() == 14 ) recorder.removeFirst();
                recorder.addLast(line);
                if(line.equals("execute_script")){
                    executeFile(line);
                }
                else CategorizeCommand(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void CategorizeCommand(String userCommand) throws ParseException {
        switch (userCommand){
            case "help":
                collectionManager.help();
                break;
            case "info":
                collectionManager.info();
                break;
            case "show":
                collectionManager.show();
                break;
            case "add":
                collectionManager.add(commandAsker.createPerson());
                break;
            case "update_id":
                collectionManager.update_id(commandAsker.IdAsker());
                break;
            case "remove_by_id":
                collectionManager.remove_by_id(commandAsker.IdAsker());
                break;
            case "clear":
                collectionManager.clear();
                break;
            case "save":
                collectionManager.save(outputFile);
                break;
            case "exit":
                collectionManager.exit();
                break;
            case "remove_greater":
                collectionManager.remove_greater(commandAsker.createPerson());
                break;
            case "remove_lower":
                collectionManager.remove_lower(commandAsker.createPerson());
                break;
            case "history":
                collectionManager.history((ArrayDeque<String>) recorder);
                break;
            case "group_counting_by_id":
                collectionManager.group_counting_by_id();
                break;
            case "count_less_than_birthday":
                System.out.println(collectionManager.count_less_than_birthday(commandAsker.BirthdayAsker()));
                break;
            case "print_field_ascending_height":
                collectionManager.print_field_ascending_height();
        }
    }

}
