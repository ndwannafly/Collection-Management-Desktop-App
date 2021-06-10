package lab8.Commands.SpecificCommands;

import lab8.Commands.Command;
import lab8.Client.Receiver;

import java.io.Serializable;

public class ExitCommand extends Command implements Serializable {

    private final Receiver receiver;
    private static final long serialVersionUID = 1234567L;


    public ExitCommand(Receiver receiver){
        this.receiver = receiver;
    }

    @Override
    public String aboutCommand() {
        return "exit                        - exit the program without saving to file\n";
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