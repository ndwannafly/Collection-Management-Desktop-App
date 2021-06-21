package lab8.Commands.SpecificCommands;

import lab8.Client.Receiver;
import lab8.Commands.Command;

import java.io.IOException;

public class ExecuteScript extends Command {

    private final Receiver receiver;


    public ExecuteScript(Receiver receiver){
        this.receiver = receiver;
    }


    @Override
    public String aboutCommand() {
        return "execute_script file_name";
    }

    @Override
    public void execute(String[] args) throws IOException {
        try{
            System.out.println(args.length);
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
