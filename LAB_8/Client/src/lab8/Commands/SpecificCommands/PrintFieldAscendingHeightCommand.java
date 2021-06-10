package lab8.Commands.SpecificCommands;

import lab8.Commands.Command;
import lab8.Client.Receiver;

import java.io.IOException;
import java.io.Serializable;

public class PrintFieldAscendingHeightCommand extends Command implements Serializable {

    private Receiver receiver;
    private static final long serialVersionUID = 1234567L;

    public PrintFieldAscendingHeightCommand(){

    }

    public PrintFieldAscendingHeightCommand(Receiver receiver){
        this.receiver = receiver;
    }
    @Override
    public String aboutCommand() {
        return ("print_field_ascending_height- print field height of all elements in ascending order");

    }

    @Override
    public void execute(String[] args) throws IOException {
        if(args.length != 1){
            System.out.println("Client: Invalid command's format! Fail to execute InfoCommand!");
        }
        else{
            receiver.printFieldAscendingHeight();
        }
    }
}
