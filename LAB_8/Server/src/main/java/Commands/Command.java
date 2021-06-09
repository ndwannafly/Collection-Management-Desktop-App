package Commands;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;
import java.sql.SQLException;
import java.text.ParseException;

public abstract class Command {
    public abstract void execute(Object o, DatagramChannel datagramChannel, SocketAddress socketAddress) throws IOException, ParseException, SQLException;
}
