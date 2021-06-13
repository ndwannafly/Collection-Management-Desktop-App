package lab8.Commands.SpecificCommands;

import lab8.Client.Receiver;
import lab8.Commands.Command;

import java.io.IOException;
import java.io.Serializable;

public class CountLessThanBirthdayCommand extends Command implements Serializable {

    private Receiver receiver;
    private static final long serialVersionUID = 1234567L;

    public CountLessThanBirthdayCommand(){

    }

    public CountLessThanBirthdayCommand(Receiver receiver){
        this.receiver = receiver;
    }

    @Override
    public String aboutCommand() {
        return "count_less_than_birthday    - print number of elements, which have field birthday less than " +
                "specific birthday\n";
    }

    @Override
    public void execute(String[] args) throws IOException {
        if(args.length != 2){
            System.out.println("Client: Invalid command's format! Fail to execute InfoCommand!");
        }
        else{
            //receiver.countLessThanBirthDay(args[1]);
        }
    }
}
