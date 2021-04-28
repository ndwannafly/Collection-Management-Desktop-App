package Commands.SerializedCommands;

import Commands.Command;

import java.io.Serializable;

public class SerializedCombinedCommand implements Serializable {

    private final Command command;
    private final Object object;
    private final String arg;
    private static final long serialVersionUID = 1234567L;

    public SerializedCombinedCommand (Command command, Object object, String arg){
        this.command = command;
        this.object = object;
        this.arg = arg;
    }

    public Command getCommand() {
        return command;
    }

    public Object getObject(){
        return object;
    }

    public String getArg(){
        return arg;
    }
}
