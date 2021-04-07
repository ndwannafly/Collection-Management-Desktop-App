package Commands.SerializedCommands;

import Commands.Command;

import java.io.Serializable;

public class SerializedArgumentCommand implements Serializable {
    private Command command;

    public SerializedArgumentCommand(Command command){
        this.command = command;
    }

    public Command getCommand(){
        return command;
    }
}
