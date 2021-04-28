package Ulties;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;

public class Communicator {
    private DatagramChannel datagramChannel;
    private SocketAddress socketAddress;
    private String host;
    private int port;
    private Selector selector;

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
            selector = Selector.open();
            datagramChannel = DatagramChannel.open();
            datagramChannel.bind(null);
            datagramChannel.configureBlocking(false);
            datagramChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public DatagramChannel selectWriteChannel() throws IOException{
        long startTime = System.currentTimeMillis();

        while(true){
            if(System.currentTimeMillis() - startTime > 5000){
                System.out.println("Cannot connect to Server! Please make sure that Server is ready!");
                return null;
            }
            selector.select();
            Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
            while(keys.hasNext()){
                SelectionKey key = keys.next();
                keys.remove();
                if(key.isValid()){
                    if(key.isWritable()) {
                        return (DatagramChannel) key.channel();
                    }
                }
            }
        }
    }

    public DatagramChannel selectReadChannel() throws IOException {
        long startTime = System.currentTimeMillis();

        while(true){
            if(System.currentTimeMillis() - startTime > 5000){
                System.out.println("Cannot connect to Server! Please make sure that Server is ready!");
                return null;
            }
            selector.select();
            Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
            while(keys.hasNext()){
                SelectionKey key = keys.next();
                keys.remove();
                if(key.isValid()){
                    if(key.isReadable())  return (DatagramChannel) key.channel();
                }
            }
        }
    }
    public void endCommunication() throws IOException {
        datagramChannel.close();
    }
}