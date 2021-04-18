package Ulties;

import Data.Person;
import sun.rmi.runtime.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.Level;

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
        Logging.log(Level.INFO, "Server executed InfoCommand!");
        out.flush();
        byte[] b = byteStream.toByteArray();
        //System.out.println("Server: byteStream" + Arrays.toString(b));
        DatagramPacket answerPacket = new DatagramPacket(b, b.length, datagramPacket.getAddress(), datagramPacket.getPort());
        datagramSocket.send(answerPacket);
    }

    public void show() throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream(4096);
        ObjectOutputStream out = new ObjectOutputStream(byteStream);
        out.writeObject(CollectionManager.show());
        Logging.log(Level.INFO, "Server executed ShowCommand!");
        out.flush();
        byte[] b = byteStream.toByteArray();
        DatagramPacket answerPacket = new DatagramPacket(b, b.length, datagramPacket.getAddress(), datagramPacket.getPort());
        datagramSocket.send(answerPacket);

    }

    public void clear() throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream(4096);
        ObjectOutputStream out = new ObjectOutputStream(byteStream);
        out.writeObject(CollectionManager.clear());
        Logging.log(Level.INFO, "Server executed ClearCommand!");
        out.flush();
        byte[] b = byteStream.toByteArray();
        DatagramPacket answerPacket = new DatagramPacket(b, b.length, datagramPacket.getAddress(), datagramPacket.getPort());
        datagramSocket.send(answerPacket);

    }

    public void add(Object o) throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream(4096);
        ObjectOutputStream out = new ObjectOutputStream(byteStream);
        out.writeObject(CollectionManager.add(o));
        Logging.log(Level.INFO, "Server executed AddCommand!");
        out.flush();
        byte[] b = byteStream.toByteArray();
        DatagramPacket answerPacket = new DatagramPacket(b, b.length, datagramPacket.getAddress(), datagramPacket.getPort());
        datagramSocket.send(answerPacket);
    }

    public void countLessThanBirthday(Date Birthday) throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream(4096);
        ObjectOutputStream out = new ObjectOutputStream(byteStream);
        out.writeObject(CollectionManager.countLessThanBirthDay(Birthday));
        Logging.log(Level.INFO, "Server executed CountLessThanBirthdayCommand!");
        out.flush();
        byte[] b = byteStream.toByteArray();
        DatagramPacket answerPacket = new DatagramPacket(b, b.length, datagramPacket.getAddress(), datagramPacket.getPort());
        datagramSocket.send(answerPacket);
    }

    public void groupByCountingID() throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream(4096);
        ObjectOutputStream out = new ObjectOutputStream(byteStream);
        out.writeObject(CollectionManager.groupByCountingID());
        Logging.log(Level.INFO, "Server executed GroupCountingByIDCommand!");
        out.flush();
        byte[] b = byteStream.toByteArray();
        DatagramPacket answerPacket = new DatagramPacket(b, b.length, datagramPacket.getAddress(), datagramPacket.getPort());
        datagramSocket.send(answerPacket);
    }

    public void printFieldAscendingHeight() throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream(4096);
        ObjectOutputStream out = new ObjectOutputStream(byteStream);
        out.writeObject(CollectionManager.printFieldAscendingHeight());
        Logging.log(Level.INFO, "Server executed PrintFieldAscendingHeightCommand!");
        out.flush();
        byte[] b = byteStream.toByteArray();
        DatagramPacket answerPacket = new DatagramPacket(b, b.length, datagramPacket.getAddress(), datagramPacket.getPort());
        datagramSocket.send(answerPacket);
    }

    public void removeByID(long id) throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream(4096);
        ObjectOutputStream out = new ObjectOutputStream(byteStream);
        out.writeObject(CollectionManager.removeByID(id));
        Logging.log(Level.INFO, "Server executed RemoveByIDCommand!");
        out.flush();
        byte[] b = byteStream.toByteArray();
        DatagramPacket answerPacket = new DatagramPacket(b, b.length, datagramPacket.getAddress(), datagramPacket.getPort());
        datagramSocket.send(answerPacket);
    }

    public void removeGreater(Object o) throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream(4096);
        ObjectOutputStream out = new ObjectOutputStream(byteStream);
        out.writeObject(CollectionManager.removeGreater((Person) o));
        Logging.log(Level.INFO, "Server executed RemoveGreaterCommand!");
        out.flush();
        byte[] b = byteStream.toByteArray();
        DatagramPacket answerPacket = new DatagramPacket(b, b.length, datagramPacket.getAddress(), datagramPacket.getPort());
        datagramSocket.send(answerPacket);
    }

    public void removeLower(Object o) throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream(4096);
        ObjectOutputStream out = new ObjectOutputStream(byteStream);
        out.writeObject(CollectionManager.removeLower((Person) o));
        Logging.log(Level.INFO, "Server executed RemoveLowerCommand!");
        out.flush();
        byte[] b = byteStream.toByteArray();
        DatagramPacket answerPacket = new DatagramPacket(b, b.length, datagramPacket.getAddress(), datagramPacket.getPort());
        datagramSocket.send(answerPacket);
    }

    public void update(long id, Object o) throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream(4096);
        ObjectOutputStream out = new ObjectOutputStream(byteStream);
        out.writeObject(CollectionManager.update(id, o));
        Logging.log(Level.INFO, "Server executed UpdateCommand!");
        out.flush();
        byte[] b = byteStream.toByteArray();
        DatagramPacket answerPacket = new DatagramPacket(b, b.length, datagramPacket.getAddress(), datagramPacket.getPort());
        datagramSocket.send(answerPacket);
    }
}
