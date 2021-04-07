package Commands.SerializedCommands;

import Commands.Command;

import java.io.Serializable;

public class SerializedObjectCommand implements Serializable {

    private Command command;

    public SerializedObjectCommand(Command command){
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
