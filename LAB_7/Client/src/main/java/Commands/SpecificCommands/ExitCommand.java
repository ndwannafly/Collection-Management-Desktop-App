package Commands.SpecificCommands;

import Commands.Command;
import Utils.Receiver;

import java.io.Serializable;

public class ExitCommand extends Command implements Serializable {

    private final Receiver receiver;
    private static final long serialVersionUID = 1234567L;


    public ExitCommand(Receiver receiver){
        this.receiver = receiver;
    }

    @Override
    public void aboutCommand() {
        System.out.println("exit                        - exit the program without saving to file");
    }

    @Override
    public void execute(String[] args) {
        if(args.length != 1) {
            System.out.println("Client: Invalid command's format! Fail to execute InfoCommand!");
        }
        else{
            receiver.exit();
        }
    }
}
