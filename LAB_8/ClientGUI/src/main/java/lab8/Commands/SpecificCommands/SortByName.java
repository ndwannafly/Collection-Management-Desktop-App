package lab8.Commands.SpecificCommands;

import lab8.Client.Receiver;
import lab8.Commands.Command;

import java.io.IOException;
import java.io.Serializable;

public class SortByName extends Command implements Serializable {

    private Receiver receiver;
    private static final long serialVersionUID = 1234567L;

    public SortByName(){

    }

    public SortByName(Receiver receiver){
        this.receiver = receiver;
    }
    @Override
    public String aboutCommand() {
        return "sort_by_height";
    }

    @Override
    public void execute(String[] args) throws IOException {
        if(args.length != 1) {
            System.out.println("Client: Invalid command's format! Fail to execute SortByHeightCommand!");
        }
        else{
            receiver.sortByName();
        }
    }
}
