package Commands.SpecificCommands;

import Commands.Command;

import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class HelpCommand extends Command implements Serializable {

    private static final long serialVersionUID = 1234567L;

    @Override
    public void execute(Object o, DatagramSocket datagramSocket, DatagramPacket datagramPacket) {

    }

}
