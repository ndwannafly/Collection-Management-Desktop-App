package lab8.Utils;

import lab8.Commands.Command;
import lab8.Commands.SerializedCommands.SerializedArgumentCommand;
import lab8.Commands.SerializedCommands.SerializedCombinedCommand;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;

public class CommandDecoder {

    private final DatagramChannel datagramChannel;
    private final SocketAddress socketAddress;
    private final ExecutorService executor = Executors.newCachedThreadPool();
    //private final ForkJoinPool forkJoinPool = new ForkJoinPool(2);
    //private final ThreadPoolExecutor executor = (ThreadPoolExecutor)Executors.newFixedThreadPool(5);

    public CommandDecoder(DatagramChannel datagramChannel, SocketAddress socketAddress){
        this.datagramChannel = datagramChannel;
        this.socketAddress = socketAddress;
    }

    public void decode(Object o){
        //System.out.println("Duc");
        executor.execute(() -> {
            if (o instanceof SerializedArgumentCommand) {
                Logging.log(Level.INFO, "Receiving data is argument command!");
                SerializedArgumentCommand argumentCommand = (SerializedArgumentCommand) o;
                Command command = argumentCommand.getCommand();
                String arg = argumentCommand.getArg();
                //Logging.log(Level.INFO, "arg = " + arg);
                try {
                    command.execute(arg, datagramChannel, socketAddress);
                } catch (IOException | ParseException | SQLException e) {
                    e.printStackTrace();
                }
            }
            if (o instanceof SerializedCombinedCommand) {
                Logging.log(Level.INFO, "Receiving data is combined command!");
                SerializedCombinedCommand combinedCommand = (SerializedCombinedCommand) o;
                Command command = combinedCommand.getCommand();
                try {
                    command.execute(combinedCommand, datagramChannel, socketAddress);
                } catch (IOException | ParseException | SQLException e) {
                    e.printStackTrace();
                }
            }
        });
/*        forkJoinPool.submit(() -> {
*//*        if(o instanceof SerializedSimplyCommand){
            SerializedSimplyCommand simplyCommand = (SerializedSimplyCommand) o;
            Command command = simplyCommand.getCommand();
            String arg = "";
            command.execute(arg, datagramChannel, socketAddress);
        }*//*
            if (o instanceof SerializedArgumentCommand) {
                Logging.log(Level.INFO, "Receiving data is argument command!");
                SerializedArgumentCommand argumentCommand = (SerializedArgumentCommand) o;
                Command command = argumentCommand.getCommand();
                String arg = argumentCommand.getArg();
                //Logging.log(Level.INFO, "arg = " + arg);
                try {
                    command.execute(arg, datagramChannel, socketAddress);
                } catch (IOException | ParseException | SQLException e) {
                    e.printStackTrace();
                }
            }
*//*        if(o instanceof SerializedObjectCommand){
            SerializedObjectCommand objectCommand = (SerializedObjectCommand) o;
            Command command = objectCommand.getCommand();
            Object object = objectCommand.getObject();
            command.execute(object, datagramChannel, socketAddress);
        }*//*
            if (o instanceof SerializedCombinedCommand) {
                Logging.log(Level.INFO, "Receiving data is combined command!");
                SerializedCombinedCommand combinedCommand = (SerializedCombinedCommand) o;
                Command command = combinedCommand.getCommand();
                try {
                    command.execute(combinedCommand, datagramChannel, socketAddress);
                } catch (IOException | ParseException | SQLException e) {
                    e.printStackTrace();
                }
            }
        });*/
    }
}
