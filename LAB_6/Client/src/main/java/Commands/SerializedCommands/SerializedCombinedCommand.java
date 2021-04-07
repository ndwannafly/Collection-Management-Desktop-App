package Commands.SerializedCommands;

import Commands.Command;

import java.io.Serializable;

public class SerializedCombinedCommand implements Serializable {

    private Command command;

    public SerializedCombinedCommand (Command command){
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
