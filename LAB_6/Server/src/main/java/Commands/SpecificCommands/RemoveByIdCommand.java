package Commands.SpecificCommands;

import Commands.Command;
import Ulties.Receiver;

import java.io.IOException;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;

public class RemoveByIdCommand extends Command implements Serializable {

    private static final long serialVersionUID = 1234567L;

    public RemoveByIdCommand(){

    }



    @Override
    public void execute(Object o, DatagramSocket datagramSocket, DatagramPacket datagramPacket) throws IOException {
        Receiver receiver = new Receiver(datagramSocket, datagramPacket);
        long id =  Long.parseLong((String) o);
        receiver.removeByID(id);
    }

}
