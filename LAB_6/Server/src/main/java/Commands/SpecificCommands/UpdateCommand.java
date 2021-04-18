package Commands.SpecificCommands;

import Commands.Command;
import Commands.SerializedCommands.SerializedCombinedCommand;
import Ulties.Logging;
import Ulties.Receiver;

import java.io.IOException;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;
import java.util.logging.Level;

public class UpdateCommand extends Command implements Serializable {

    private static final long serialVersionUID = 1234567L;

    public UpdateCommand(){

    }


    @Override
    public void execute(Object o, DatagramSocket datagramSocket, DatagramPacket datagramPacket) throws IOException {
        SerializedCombinedCommand serializedCombinedCommand = (SerializedCombinedCommand) o;
        String arg = serializedCombinedCommand.getArg();
        Object obj = serializedCombinedCommand.getObject();
        Receiver receiver = new Receiver(datagramSocket, datagramPacket);
        long id = Long.parseLong(arg);
        Logging.log(Level.INFO, "Server is executing UpdateCommand....");
        receiver.update(id, obj);
    }

}
