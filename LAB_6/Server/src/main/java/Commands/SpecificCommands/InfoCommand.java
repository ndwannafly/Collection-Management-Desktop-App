package Commands.SpecificCommands;

import Commands.Command;
import Ulties.Logging;
import Ulties.Receiver;
import jdk.jfr.internal.Logger;

import java.io.IOException;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;
import java.util.logging.Level;

public class InfoCommand extends Command implements Serializable {

    private static final long serialVersionUID = 1234567L;


    public InfoCommand(){

    }

    @Override
    public void execute(Object o, DatagramSocket datagramSocket, DatagramPacket datagramPacket) throws IOException {
        Logging.log(Level.INFO, "Server is executing InfoCommand....");
        Receiver receiver = new Receiver(datagramSocket, datagramPacket);
        receiver.info();
    }

}
