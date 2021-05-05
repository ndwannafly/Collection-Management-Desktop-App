package Utils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.SocketAddress;
import java.sql.SQLException;
import java.text.ParseException;

public class Client extends Thread{

    private final ObjectInputStream objectInputStream;
    private final SocketAddress socketAddress;
    private final CommandDecoder commandDecoder;

    public Client(ObjectInputStream objectInputStream, SocketAddress socketAddress, CommandDecoder commandDecoder){
        this.objectInputStream = objectInputStream;
        this.socketAddress = socketAddress;
        this.commandDecoder = commandDecoder;
    }

    @Override
    public void run() {
        try {
            Object object = objectInputStream.readObject();
            commandDecoder.decode(object);
        } catch (IOException | ClassNotFoundException | ParseException | SQLException e) {
            e.printStackTrace();
        }

    }
}
