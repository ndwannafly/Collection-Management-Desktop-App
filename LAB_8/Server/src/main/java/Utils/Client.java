package Utils;

import java.io.IOException;
import java.io.ObjectInputStream;

public class Client implements Runnable {

    private final ObjectInputStream objectInputStream;
    private final CommandDecoder commandDecoder;

    public Client(ObjectInputStream objectInputStream, CommandDecoder commandDecoder){
        this.objectInputStream = objectInputStream;
        this.commandDecoder = commandDecoder;
    }

    @Override
    public void run() {
        try {
            Object object = objectInputStream.readObject();
            commandDecoder.decode(object);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
