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

public class RemoveByIdCommand extends Command implements Serializable {

    private static final long serialVersionUID = 1234567L;

    public RemoveByIdCommand(){

    }



    @Override
    public void execute(Object o, DatagramSocket datagramSocket, DatagramPacket datagramPacket) throws IOException {
        Receiver receiver = new Receiver(datagramSocket, datagramPacket);
        long id =  Long.parseLong((String) o);
        Logging.log(Level.INFO, "Server is executing RemoveByIDCommand....");
        receiver.removeByID(id);
    }

}
