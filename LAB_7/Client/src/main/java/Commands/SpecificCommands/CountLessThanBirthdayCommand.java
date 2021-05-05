package Commands.SpecificCommands;

import Commands.Command;
import Utils.Receiver;

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
    public void aboutCommand() {
        System.out.println("count_less_than_birthday    - print number of elements, which have field birthday less than " +
                "specific birthday");
    }

    @Override
    public void execute(String[] args) throws IOException {
        if(args.length != 2){
            System.out.println("Client: Invalid command's format! Fail to execute InfoCommand!");
        }
        else{
            receiver.countLessThanBirthDay(args[1]);
        }
    }
}
