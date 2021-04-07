package Commands.SpecificCommands;

import Commands.Command;
import Ulties.Receiver;

import java.io.Serializable;

public class RemoveLowerCommand extends Command implements Serializable {

    private Receiver receiver;

    public RemoveLowerCommand(){

    }

    public RemoveLowerCommand(Receiver receiver){
        this.receiver = receiver;
    }
    @Override
    public void aboutCommand() {

    }

    @Override
    public void execute(String[] args) {

    }
}
