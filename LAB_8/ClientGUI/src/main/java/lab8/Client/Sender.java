package lab8.Client;

import lab8.Commands.SerializedCommands.SerializedArgumentCommand;
import lab8.Commands.SerializedCommands.SerializedCombinedCommand;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Sender {
    private Communicator communicator;
    private DatagramSocket datagramSocket;


    public Sender(Communicator communicator) {
        this.communicator = communicator;
        this.datagramSocket = communicator.getDatagramSocket();
    }

    public DatagramSocket getDatagramSocket(){
        return datagramSocket;
    }

    public void sendObject(SerializedArgumentCommand serializedCommand) throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream(4096);
        ObjectOutputStream out = new ObjectOutputStream(byteStream);
        out.writeObject(serializedCommand);
        out.flush();
        byte[] dataBytes = byteStream.toByteArray();
        DatagramPacket datagramPacket = new DatagramPacket(dataBytes, dataBytes.length, datagramSocket.getInetAddress(),
                communicator.getPort());
        datagramSocket.send(datagramPacket);
        //System.out.println("Client: message sent " + dataBytes);
    }

    public void sendObject(SerializedCombinedCommand serializedCommand) throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream(4096);
        ObjectOutputStream out = new ObjectOutputStream(byteStream);
        out.writeObject(serializedCommand);
        out.flush();
        byte[] dataBytes = byteStream.toByteArray();
        DatagramPacket datagramPacket = new DatagramPacket(dataBytes, dataBytes.length, datagramSocket.getInetAddress(),
                communicator.getPort());
        datagramSocket.send(datagramPacket);
    }

}
