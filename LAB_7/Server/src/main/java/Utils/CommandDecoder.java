package Utils;

import Commands.Command;
import Commands.SerializedCommands.SerializedArgumentCommand;
import Commands.SerializedCommands.SerializedCombinedCommand;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;

public class CommandDecoder {

    private final DatagramChannel datagramChannel;
    private final SocketAddress socketAddress;

    public CommandDecoder(DatagramChannel datagramChannel, SocketAddress socketAddress){
        this.datagramChannel = datagramChannel;
        this.socketAddress = socketAddress;
    }

    public void decode(Object o) throws IOException, ParseException, SQLException {
/*        if(o instanceof SerializedSimplyCommand){
            SerializedSimplyCommand simplyCommand = (SerializedSimplyCommand) o;
            Command command = simplyCommand.getCommand();
            String arg = "";
            command.execute(arg, datagramChannel, socketAddress);
        }*/
        if(o instanceof SerializedArgumentCommand){
            Logging.log(Level.INFO, "Receiving data is argument command!");
            SerializedArgumentCommand argumentCommand = (SerializedArgumentCommand) o;
            Command command = argumentCommand.getCommand();
            String arg = argumentCommand.getArg();
            Logging.log(Level.INFO, "arg = " + arg);
            command.execute(arg, datagramChannel, socketAddress);
        }
/*        if(o instanceof SerializedObjectCommand){
            SerializedObjectCommand objectCommand = (SerializedObjectCommand) o;
            Command command = objectCommand.getCommand();
            Object object = objectCommand.getObject();
            command.execute(object, datagramChannel, socketAddress);
        }*/
        if(o instanceof SerializedCombinedCommand){
            Logging.log(Level.INFO, "Receiving data is combined command!");
            SerializedCombinedCommand combinedCommand = (SerializedCombinedCommand) o;
            Command command = combinedCommand.getCommand();
            command.execute(combinedCommand, datagramChannel, socketAddress);
        }
    }
}
