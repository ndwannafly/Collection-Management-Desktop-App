package Ulties;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;

public class Communicator {
   private DatagramChannel datagramChannel;
   private SocketAddress socketAddress;
   private String host;
   private int port;

   public Communicator(String host, int port){
       this.host = host;
       this.port = port;
       this.socketAddress = new InetSocketAddress(host, port);
   }

    public SocketAddress getSocketAddress() {
        return socketAddress;
    }

    public DatagramChannel getDatagramChannel(){
       return datagramChannel;
   }


   public void startCommunication(){
       try{
           datagramChannel = DatagramChannel.open();
           datagramChannel.bind(null);
           //datagramChannel.configureBlocking(false);
       } catch (IOException e) {
           e.printStackTrace();
       }
   }

   public void endCommunication() throws IOException {
       datagramChannel.close();
   }
}
