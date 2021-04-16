package Commands.SpecificCommands;

import Commands.Command;
import Ulties.Receiver;

import java.io.IOException;
import java.io.Serializable;

public class ClearCommand extends Command implements Serializable {

    private Receiver receiver;

    public ClearCommand(){

    }

    public ClearCommand(Receiver receiver){
        this.receiver = receiver;
    }


    @Override
    public void aboutCommand() {

    }

    @Override
    public void execute(String[] args) throws IOException {
        if(args.length != 1) {
            System.out.println("Client: Invalid command's format! Fail to execute InfoCommand!");
        }
        else{
            receiver.clear();
        }
    }
}
