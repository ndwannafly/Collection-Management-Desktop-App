package lab8.Commands.SpecificCommands;

import lab8.Commands.Command;
import lab8.Utils.Receiver;

import java.io.Serializable;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;

public class RegisterCommand extends Command implements Serializable {
    private static final long serialVersionUID = 1234567L;

    @Override
    public void execute(Object o, DatagramChannel datagramChannel, SocketAddress socketAddress){
        //System.out.println("here");
        Receiver receiver = new Receiver(datagramChannel);
        receiver.register((String) o, socketAddress);
    }
}
