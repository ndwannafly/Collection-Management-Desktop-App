package Commands.SpecificCommands;

import Commands.Command;
import Ulties.Receiver;

import java.io.IOException;
import java.io.Serializable;

public class RemoveByIdCommand extends Command implements Serializable {

    private Receiver receiver;
    private static final long serialVersionUID = 1234567L;

    public RemoveByIdCommand(){

    }

    public RemoveByIdCommand(Receiver receiver){
        this.receiver = receiver;
    }
    @Override
    public void aboutCommand() {
        System.out.println("remove_by_id {id}           - remove element has corresponding id from the collection");

    }

    @Override
    public void execute(String[] args) throws IOException {
        if(args.length != 2){
            System.out.println("Client: Invalid command's format! Fail to execute RemoveByIDCommand!");
        }
        else{
            receiver.removeByID(args[1]);
        }
    }
}
