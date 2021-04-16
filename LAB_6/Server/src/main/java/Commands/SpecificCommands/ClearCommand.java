package Commands.SpecificCommands;

import Commands.Command;
import Ulties.Receiver;

import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;

public class ClearCommand extends Command implements Serializable {


    public ClearCommand(){

    }



    @Override
    public void execute(Object o, DatagramSocket datagramSocket, DatagramPacket datagramPacket) {
        System.out.println("Server: execute clear command!");
        Receiver receiver = new Receiver(datagramSocket, datagramPacket);
        receiver.clear();
    }


}
