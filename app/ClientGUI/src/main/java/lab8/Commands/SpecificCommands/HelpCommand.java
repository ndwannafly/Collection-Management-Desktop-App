package lab8.Commands.SpecificCommands;

import lab8.Client.Receiver;
import lab8.Commands.Command;

import java.io.Serializable;

public class HelpCommand extends Command implements Serializable {

    private final Receiver receiver;
    private static final long serialVersionUID = 1234567L;


    public HelpCommand(Receiver receiver){
        this.receiver = receiver;
    }

    @Override
    public String aboutCommand() {
        return "help";
    }

    @Override
    public void execute(String[] args) {
        if(args.length != 1){
            System.out.println("Client: Invalid command's format! Fail to execute HelpCommand!");
        }
        else{
            receiver.help();
        }
    }
}
