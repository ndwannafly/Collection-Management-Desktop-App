package Ulties;

import Commands.SerializedCommands.SerializedSimplyCommand;

import javax.xml.crypto.Data;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class ServerController {

    private int port;
    public ServerController(String p){
        this.port = Integer.parseInt(p);
    }

    public void run(){
        try {
            DatagramSocket serverSocket = new DatagramSocket(port);
            while(true) {
                byte[] receivedBuffer = new byte[512];
                DatagramPacket receivedPacket = new DatagramPacket(receivedBuffer, receivedBuffer.length);
                System.out.println("Server: start listening! Number of Person: " + CollectionManager.getPerson());
                serverSocket.receive(receivedPacket);
                //String msg = new String(receivedPacket.getData(), StandardCharsets.ISO_8859_1);
                //System.out.println("SERVER: RECEIVED " + msg);
                ByteArrayInputStream receivedByteArray = new ByteArrayInputStream(receivedPacket.getData());
                ObjectInputStream in = new ObjectInputStream(receivedByteArray);
                Object object = in.readObject();
                SerializedSimplyCommand receivedCommand = (SerializedSimplyCommand) object;
                System.out.println(receivedCommand.getID());
                //CommandDecoder commandDecoder = new CommandDecoder(serverSocket, receivedPacket);
                //commandDecoder.decode(object);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
