package Ulties;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Arrays;

public class Receiver {

    private DatagramSocket datagramSocket;
    private DatagramPacket datagramPacket;

    public Receiver(DatagramSocket datagramSocket, DatagramPacket datagramPacket){
        this.datagramSocket = datagramSocket;
        this.datagramPacket = datagramPacket;
    }
    public void info() throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream(4096);
        ObjectOutputStream out = new ObjectOutputStream(byteStream);
        out.writeObject(CollectionManager.info());
        out.flush();
        byte[] b = byteStream.toByteArray();
        System.out.println("Server: byteStream" + Arrays.toString(b));
        DatagramPacket answerPacket = new DatagramPacket(b, b.length, datagramPacket.getAddress(), datagramPacket.getPort());
        datagramSocket.send(answerPacket);
        System.out.println("Server: sent response to Client!");
    }

    public void show() throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream(4096);
        ObjectOutputStream out = new ObjectOutputStream(byteStream);
        out.writeObject(CollectionManager.show());
        out.flush();
        byte[] b = byteStream.toByteArray();
        DatagramPacket answerPacket = new DatagramPacket(b, b.length, datagramPacket.getAddress(), datagramPacket.getPort());
        datagramSocket.send(answerPacket);
        System.out.println("Server: sent response to Client!");
    }

    public void clear(){
        CollectionManager.clear();
    }
}
