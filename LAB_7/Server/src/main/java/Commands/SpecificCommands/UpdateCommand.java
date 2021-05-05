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

public class UpdateCommand extends Command implements Serializable {

    private static final long serialVersionUID = 1234567L;

    @Override
    public void execute(Object o, DatagramChannel datagramChannel, SocketAddress socketAddress) throws IOException, SQLException, ParseException {
        SerializedCombinedCommand serializedCombinedCommand = (SerializedCombinedCommand) o;
        String arg = serializedCombinedCommand.getArg();
        Object obj = serializedCombinedCommand.getObject();
        Receiver receiver = new Receiver(datagramChannel);
        Logging.log(Level.INFO, "Server is executing UpdateCommand....");
        receiver.update(arg, obj, socketAddress);

    }

}
