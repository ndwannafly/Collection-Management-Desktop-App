package Commands.SerializedCommands;

import Commands.Command;

import java.io.Serializable;

public class SerializedCombinedCommand implements Serializable {

    private Command command;
    private static final long serialVersionUID = 1234567L;
    private String arg;
    private Object object;

    public SerializedCombinedCommand (Command command, String arg, Object object){
        this.command = command;
        this.arg = arg;
        this.object = object;
    }

    public Command getCommand() {
        return command;
    }
}
