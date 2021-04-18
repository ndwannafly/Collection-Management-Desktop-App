package Commands.SpecificCommands;

import Commands.Command;
import Ulties.Logging;
import Ulties.Receiver;

import java.io.IOException;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;
import java.util.logging.Level;

public class RemoveGreaterCommand extends Command implements Serializable {

    private static final long serialVersionUID = 1234567L;

    public RemoveGreaterCommand(){

    }



    @Override
    public void execute(Object o, DatagramSocket datagramSocket, DatagramPacket datagramPacket) throws IOException {
        Receiver receiver = new Receiver(datagramSocket, datagramPacket);
        Logging.log(Level.INFO, "Server is executing RemoveGreaterCommand....");
        receiver.removeGreater(o);
    }

}
