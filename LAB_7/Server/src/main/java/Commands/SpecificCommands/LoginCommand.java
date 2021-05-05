package Commands.SpecificCommands;

import Commands.Command;
import Utils.Receiver;

import java.io.IOException;
import java.io.Serializable;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;
import java.sql.SQLException;

public class LoginCommand extends Command implements Serializable {
    private static final long serialVersionUID = 1234567L;

    @Override
    public void execute(Object o, DatagramChannel datagramChannel, SocketAddress socketAddress) throws IOException, SQLException {
        Receiver receiver = new Receiver(datagramChannel);
        receiver.login((String) o, socketAddress);
    }
}
