package Commands.SpecificCommands;

import Commands.Command;
import Utils.Logging;
import Utils.Receiver;

import java.io.Serializable;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;
import java.util.logging.Level;

public class ClearCommand extends Command implements Serializable {

    private static final long serialVersionUID = 1234567L;


    @Override
    public void execute(Object o, DatagramChannel datagramChannel, SocketAddress socketAddress){
        Logging.log(Level.INFO, "Server is executing ClearCommand....");
        Receiver receiver = new Receiver(datagramChannel);
        receiver.clear((String) o, socketAddress);
    }


}
