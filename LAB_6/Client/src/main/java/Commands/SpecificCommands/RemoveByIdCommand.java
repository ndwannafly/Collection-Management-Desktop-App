package Commands.SpecificCommands;

import Commands.Command;
import Ulties.Receiver;

import java.io.Serializable;

public class RemoveByIdCommand extends Command implements Serializable {

    private Receiver receiver;

    public RemoveByIdCommand(){

    }

    public RemoveByIdCommand(Receiver receiver){
        this.receiver = receiver;
    }
    @Override
    public void aboutCommand() {

    }

    @Override
    public void execute(String[] args) {

    }
}
