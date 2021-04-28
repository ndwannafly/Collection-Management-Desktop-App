package Commands;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.text.ParseException;

public abstract class Command {
    public abstract void execute(Object o, DatagramSocket datagramSocket, DatagramPacket datagramPacket) throws IOException, ParseException;
}
