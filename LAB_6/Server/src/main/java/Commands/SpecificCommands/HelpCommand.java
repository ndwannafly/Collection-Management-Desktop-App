package Commands.SpecificCommands;

import Commands.Command;
import Ulties.Receiver;

import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;

public class HelpCommand extends Command implements Serializable {

    private Receiver receiver;

    public HelpCommand(){

    }

    public HelpCommand(Receiver receiver){
        this.receiver = receiver;
    }


    @Override
    public void execute(Object o, DatagramSocket datagramSocket, DatagramPacket datagramPacket) {

    }

}
