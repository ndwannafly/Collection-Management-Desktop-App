package lab8.Commands.SpecificCommands;

import lab8.Commands.Command;
import lab8.Client.Receiver;

import java.io.IOException;
import java.io.Serializable;

public class RemoveGreaterCommand extends Command implements Serializable {

    private Receiver receiver;
    private static final long serialVersionUID = 1234567L;

    public RemoveGreaterCommand(){

    }

    public RemoveGreaterCommand(Receiver receiver){
        this.receiver = receiver;
    }
    @Override
    public String aboutCommand() {
        return "remove_greater {element}    - remove all elements from the collection, which are greater than " +
                "specific element\n";
    }

    @Override
    public void execute(String[] args) throws IOException {
        if(args.length != 1){
            System.out.println("Client: Invalid command's format! Fail to execute RemoveGreaterCommand!");
        }
        else{
            receiver.removeGreater();
        }
    }
}