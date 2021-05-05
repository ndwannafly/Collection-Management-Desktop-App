package Commands.SpecificCommands;

import Commands.Command;
import Utils.Logging;
import Utils.Receiver;

import java.io.IOException;
import java.io.Serializable;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;
import java.sql.SQLException;
import java.util.logging.Level;

public class ShowCommand extends Command implements Serializable {

    private static final long serialVersionUID = 1234567L;

    @Override
    public void execute(Object o, DatagramChannel datagramChannel, SocketAddress socketAddress) throws IOException, SQLException {
        Receiver receiver = new Receiver(datagramChannel);
        Logging.log(Level.INFO, "Server is executing ShowCommand....");
        receiver.show((String) o, socketAddress);
    }


}
