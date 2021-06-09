package Commands.SpecificCommands;

import Commands.Command;
import Commands.SerializedCommands.SerializedCombinedCommand;
import Utils.Logging;
import Utils.Receiver;

import java.io.Serializable;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;
import java.util.logging.Level;

public class AddCommand extends Command implements Serializable {

    private static final long serialVersionUID = 1234567L;


    @Override
    public void execute(Object o, DatagramChannel datagramChannel, SocketAddress socketAddress){
        SerializedCombinedCommand combinedCommand = (SerializedCombinedCommand) o;
        Object obj = combinedCommand.getObject();
        String arg = combinedCommand.getArg();
        Logging.log(Level.INFO, "Server is executing AddCommand....");
        Receiver receiver = new Receiver(datagramChannel);
        receiver.add(arg, obj, socketAddress);
    }

}
