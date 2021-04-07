package Ulties;

import Commands.SerializedCommands.SerializedArgumentCommand;
import Commands.SerializedCommands.SerializedCombinedCommand;
import Commands.SerializedCommands.SerializedObjectCommand;
import Commands.SerializedCommands.SerializedSimplyCommand;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class Sender {
    private final DatagramChannel datagramChannel;
    private final Communicator communicator;
    private final ByteArrayOutputStream byteStream = new ByteArrayOutputStream(4096);
    public Sender(Communicator communicator){
        this.communicator = communicator;
        this.datagramChannel = communicator.getDatagramChannel();
    }

    public void sendObject(SerializedArgumentCommand serializedCommand) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(byteStream);
        out.writeObject(serializedCommand);
        out.flush();
        byte[] dataBytes = byteStream.toByteArray();
        ByteBuffer dataBuffer = ByteBuffer.wrap(dataBytes);
        datagramChannel.send(dataBuffer, communicator.getSocketAddress());
        //System.out.println("Client: message sent " + dataBytes);
    }

    public void sendObject(SerializedCombinedCommand serializedCommand) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(byteStream);
        out.writeObject(serializedCommand);
        out.flush();
        byte[] dataBytes = byteStream.toByteArray();
        ByteBuffer dataBuffer = ByteBuffer.wrap(dataBytes);
        datagramChannel.send(dataBuffer, communicator.getSocketAddress());
    }

    public void sendObject(SerializedObjectCommand serializedCommand) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(byteStream);
        out.writeObject(serializedCommand);
        out.flush();
        byte[] dataBytes = byteStream.toByteArray();
        ByteBuffer dataBuffer = ByteBuffer.wrap(dataBytes);
        datagramChannel.send(dataBuffer, communicator.getSocketAddress());
    }

    public void sendObject(SerializedSimplyCommand serializedCommand) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(byteStream);
        out.writeObject(serializedCommand);
        out.flush();
        byte[] dataBytes = byteStream.toByteArray();
        ByteBuffer dataBuffer = ByteBuffer.wrap(dataBytes);
        datagramChannel.send(dataBuffer, communicator.getSocketAddress());
    }
}
