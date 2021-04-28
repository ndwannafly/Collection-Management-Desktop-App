package Ulties;

import Commands.SerializedCommands.SerializedArgumentCommand;
import Commands.SerializedCommands.SerializedCombinedCommand;
import Commands.SerializedCommands.SerializedObjectCommand;
import Commands.SerializedCommands.SerializedSimplyCommand;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class Sender {
    private DatagramChannel datagramChannel;
    private SocketAddress socketAddress;

    public Sender(SocketAddress socketAddress){
        this.socketAddress = socketAddress;
    }

    public void setDatagramChannel(DatagramChannel datagramChannel) {
        this.datagramChannel = datagramChannel;
    }

    public void setSocketAddress(SocketAddress socketAddress) {
        this.socketAddress = socketAddress;
    }

    public void sendObject(SerializedArgumentCommand serializedCommand) throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream(4096);
        ObjectOutputStream out = new ObjectOutputStream(byteStream);
        out.writeObject(serializedCommand);
        out.flush();
        byte[] dataBytes = byteStream.toByteArray();
        ByteBuffer dataBuffer = ByteBuffer.wrap(dataBytes);
        datagramChannel.send(dataBuffer, socketAddress);
        //System.out.println("Client: message sent " + dataBytes);
    }

    public void sendObject(SerializedCombinedCommand serializedCommand) throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream(4096);
        ObjectOutputStream out = new ObjectOutputStream(byteStream);
        out.writeObject(serializedCommand);
        out.flush();
        byte[] dataBytes = byteStream.toByteArray();
        ByteBuffer dataBuffer = ByteBuffer.wrap(dataBytes);
        datagramChannel.send(dataBuffer, socketAddress);
    }

    public void sendObject(SerializedObjectCommand serializedCommand) throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream(4096);
        ObjectOutputStream out = new ObjectOutputStream(byteStream);
        out.writeObject(serializedCommand);
        out.flush();
        byte[] dataBytes = byteStream.toByteArray();
        ByteBuffer dataBuffer = ByteBuffer.wrap(dataBytes);
        datagramChannel.send(dataBuffer, socketAddress);
    }

    public void sendObject(SerializedSimplyCommand serializedCommand) throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream(4096);
        ObjectOutputStream out = new ObjectOutputStream(byteStream);
        out.writeObject(serializedCommand);
        out.flush();
        byte[] dataBytes = byteStream.toByteArray();
        ByteBuffer dataBuffer = ByteBuffer.wrap(dataBytes);
        datagramChannel.send(dataBuffer, socketAddress);
    }
}
