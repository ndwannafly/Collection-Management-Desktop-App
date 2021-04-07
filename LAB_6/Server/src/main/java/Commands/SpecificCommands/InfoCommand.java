package Commands.SpecificCommands;

import Commands.Command;
import Ulties.Receiver;

import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;

public class InfoCommand extends Command implements Serializable {

    private static final long serialVersionUID = 1234567L;

    private Receiver receiver;

    public InfoCommand(){

    }

    public InfoCommand(Receiver receiver){
        this.receiver = receiver;
    }


    @Override
    public void execute(Object o, DatagramSocket datagramSocket, DatagramPacket datagramPacket) {

    }
    @Override
    public String toString() {
        return "Info command!";
    }
}
