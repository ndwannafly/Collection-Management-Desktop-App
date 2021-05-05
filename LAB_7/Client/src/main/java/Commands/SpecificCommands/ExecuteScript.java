package Commands.SpecificCommands;

import Commands.Command;
import Utils.Receiver;

import java.io.IOException;

public class ExecuteScript extends Command {

    private final Receiver receiver;


    public ExecuteScript(Receiver receiver){
        this.receiver = receiver;
    }


    @Override
    public void aboutCommand() {
        System.out.println("execute_script file_name    - read and execute script from corresponding file. The script\n" +
                "                              contains commands in the same form in which the user enters them interactively.");
    }

    @Override
    public void execute(String[] args) throws IOException {
        try{
            if(args.length != 2){
                System.out.println("Client: Invalid command's format! Fail to execute ExecuteScriptCommand!");
            }
            else{
                String path = args[1];
                receiver.executeScript(path);
            }
        } catch(StackOverflowError e){
            System.out.println("Client: Infinite loop when executing script file!");
        }
    }
}
