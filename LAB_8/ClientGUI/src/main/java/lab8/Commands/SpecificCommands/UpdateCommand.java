package lab8.Commands.SpecificCommands;

import lab8.Client.Receiver;
import lab8.Commands.Command;

import java.io.IOException;
import java.io.Serializable;

public class UpdateCommand extends Command implements Serializable {

    private Receiver receiver;
    private static final long serialVersionUID = 1234567L;

    public UpdateCommand(){

    }

    public UpdateCommand(Receiver receiver){
        this.receiver = receiver;
    }
    @Override
    public String aboutCommand() {
        return "update {id}";
    }

    @Override
    public void execute(String[] args) throws IOException {
        if(args.length != 2){
            System.out.println("Client: Invalid command's format! Fail to execute UpdateCommand!");
        }
        else{
            receiver.update(args[1]);
        }
    }
}
