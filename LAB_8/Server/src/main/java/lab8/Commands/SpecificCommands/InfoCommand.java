package lab8.Commands.SpecificCommands;

import lab8.Commands.Command;
import lab8.Utils.Logging;
import lab8.Utils.Receiver;

import java.io.Serializable;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;
import java.util.logging.Level;

public class InfoCommand extends Command implements Serializable {

    private static final long serialVersionUID = 1234567L;

    @Override
    public void execute(Object o, DatagramChannel datagramChannel, SocketAddress socketAddress){
        Logging.log(Level.INFO, "Server is executing InfoCommand....");
        Receiver receiver = new Receiver(datagramChannel);
        receiver.info((String) o, socketAddress);
    }

}
