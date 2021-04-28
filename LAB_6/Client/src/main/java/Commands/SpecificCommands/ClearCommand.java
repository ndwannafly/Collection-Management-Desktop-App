package Commands.SpecificCommands;

import Commands.Command;
import Ulties.Receiver;

import java.io.IOException;
import java.io.Serializable;

public class ClearCommand extends Command implements Serializable {

    private Receiver receiver;
    private static final long serialVersionUID = 1234567L;

    public ClearCommand(){

    }

    public ClearCommand(Receiver receiver){
        this.receiver = receiver;
    }


    @Override
    public void aboutCommand() {
        System.out.println("clear                       - clear collection");
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
