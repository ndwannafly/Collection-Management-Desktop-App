package Commands.SpecificCommands;

import Commands.Command;
import Commands.SerializedCommands.SerializedCombinedCommand;
import Utils.Logging;
import Utils.Receiver;

import java.io.IOException;
import java.io.Serializable;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;

public class CountLessThanBirthdayCommand extends Command implements Serializable {

    private static final long serialVersionUID = 1234567L;

    @Override
    public void execute(Object o, DatagramChannel datagramChannel, SocketAddress socketAddress) throws IOException, ParseException, SQLException {
        SerializedCombinedCommand combinedCommand = (SerializedCombinedCommand) o;
        String arg = combinedCommand.getArg();
        Object obj = combinedCommand.getObject();
        Receiver receiver = new Receiver(datagramChannel);
        Logging.log(Level.INFO, "Server is executing CountLessThanBirthDayCommand....");
        receiver.countLessThanBirthday(arg, obj, socketAddress);
    }

}
