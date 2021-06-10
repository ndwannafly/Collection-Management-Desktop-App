package lab8.Client;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Communicator {

    private String host;
    private int port;
    private DatagramSocket datagramSocket;

    public Communicator(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public DatagramSocket getDatagramSocket(){
        return datagramSocket;
    }

    public int getPort(){
        return port;
    }

    public void startCommunication(){
        try {
            datagramSocket = new DatagramSocket();
            datagramSocket.connect(InetAddress.getByName(host), port);
            System.out.println("Connecting......!");
        } catch (SocketException e) {
            System.out.println("Unsuccessfully connected!");
            e.printStackTrace();
        } catch (UnknownHostException e) {
            System.out.println("Something's wrong wirth host");
            System.exit(0);
        }


    }


    public void endCommunication() {
        if(datagramSocket != null){
            datagramSocket.close();
        }
    }

}
