package lab8.Commands.SpecificCommands;

import lab8.Client.Receiver;
import lab8.Commands.Command;

import java.io.IOException;
import java.io.Serializable;

public class LoginCommand extends Command implements Serializable {

    private static final long serialVersionUID = 1234567L;

    private Receiver receiver;

    public LoginCommand(){

    }

    public LoginCommand(Receiver receiver){
        this.receiver = receiver;
    }


    @Override
    public String aboutCommand() {
        return "login:\n";
    }

    @Override
    public void execute(String[] args) throws IOException {
        if(args.length != 3){
            System.out.println("Client: Invalid command's format! Fail to execute LoginCommand!");
        }
        else{
            receiver.login(args[1], args[2]);
        }
    }
}
