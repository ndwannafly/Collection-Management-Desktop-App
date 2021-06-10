package lab8.Commands.SerializedCommands;

import lab8.Commands.Command;

import java.io.Serializable;

public class SerializedCombinedCommand implements Serializable {

    private final Command command;
    private static final long serialVersionUID = 1234567L;
    private final String arg;
    private final Object object;

    public SerializedCombinedCommand (Command command, String arg, Object object){
        this.command = command;
        this.arg = arg;
        this.object = object;
    }

    public Command getCommand() {
        return command;
    }
}
