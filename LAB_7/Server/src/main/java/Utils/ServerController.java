package Utils;


import java.io.*;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.logging.Level;

public class ServerController {

    private final int port;
    private SocketAddress socketAddress;

    public ServerController(String port){
        this.port = Integer.parseInt(port);
    }

    public void run() throws InterruptedException {
        try {
            SocketAddress addr = new InetSocketAddress(port);
            DatagramChannel datagramChannel = DatagramChannel.open();
            datagramChannel.configureBlocking(false);
            datagramChannel.socket().bind(addr);
            Logging.log(Level.INFO, "Server has started listening on port " + port);

            byte[] ib = new byte[4096];
            while(true){
                socketAddress = datagramChannel.receive(ByteBuffer.wrap(ib));
                if(socketAddress != null){
                    try {
                        Logging.log(Level.INFO, "Server: Received data!");
                        Client client = new Client(new ObjectInputStream(new ByteArrayInputStream(ib)), socketAddress,
                                new CommandDecoder(datagramChannel, socketAddress));
                        client.start();
                        Thread.currentThread().sleep(500);
                    } catch (EOFException | SocketException e){
                        Logging.log(Level.INFO, "Problem occurred from Client-side!");
                    }
                }
            }
        } catch (IOException e) {
            Logging.log(Level.INFO, "Problem occurred with channel!");
        }


    }
}
