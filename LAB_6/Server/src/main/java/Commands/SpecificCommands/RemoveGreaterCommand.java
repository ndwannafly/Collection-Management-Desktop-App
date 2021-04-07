package Commands.SpecificCommands;

import Commands.Command;
import Ulties.Receiver;

import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;

public class RemoveGreaterCommand extends Command implements Serializable {

    private Receiver receiver;

    public RemoveGreaterCommand(){

    }

    public RemoveGreaterCommand(Receiver receiver){
        this.receiver = receiver;
    }


    @Override
    public void execute(Object o, DatagramSocket datagramSocket, DatagramPacket datagramPacket) {

    }

}
