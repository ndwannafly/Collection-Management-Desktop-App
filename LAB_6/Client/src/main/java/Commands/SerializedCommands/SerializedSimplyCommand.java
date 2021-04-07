package Commands.SerializedCommands;

import Commands.Command;

import java.io.Serializable;

public class SerializedSimplyCommand implements Serializable {

    private String ID;
    private Command command;


    public void setID(String id){
        this.ID = id;
    }

    public String getID(){
        return this.ID;
    }

    public SerializedSimplyCommand(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
