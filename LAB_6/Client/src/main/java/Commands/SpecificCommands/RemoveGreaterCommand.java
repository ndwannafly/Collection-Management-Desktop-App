package Commands.SpecificCommands;

import Commands.Command;
import Ulties.Receiver;

import java.io.Serializable;

public class RemoveGreaterCommand extends Command implements Serializable {

    private Receiver receiver;

    public RemoveGreaterCommand(){

    }

    public RemoveGreaterCommand(Receiver receiver){
        this.receiver = receiver;
    }
    @Override
    public void aboutCommand() {

    }

    @Override
    public void execute(String[] args) {

    }
}
