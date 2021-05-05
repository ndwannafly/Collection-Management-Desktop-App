package Commands.SerializedCommands;

import Commands.Command;

import java.io.Serializable;

public class SerializedArgumentCommand implements Serializable {
    private final Command command;
    private final String arg;
    private static final long serialVersionUID = 1234567L;

    public SerializedArgumentCommand(Command command, String arg){
        this.command = command;
        this.arg = arg;
    }

    public Command getCommand(){
        return command;
    }

    public String getArg(){
        return arg;
    }
}
