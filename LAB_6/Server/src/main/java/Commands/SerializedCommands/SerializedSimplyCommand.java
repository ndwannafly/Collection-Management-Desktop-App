package Commands.SerializedCommands;

import Commands.Command;

import java.io.Serializable;

public class SerializedSimplyCommand implements Serializable {

    private final Command command;
    private static final long serialVersionUID = 1234567L;


    public SerializedSimplyCommand(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }

}
