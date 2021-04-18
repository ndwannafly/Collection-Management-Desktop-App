package Ulties;

import Commands.Command;
import Commands.SerializedCommands.SerializedArgumentCommand;
import Commands.SerializedCommands.SerializedCombinedCommand;
import Commands.SerializedCommands.SerializedObjectCommand;
import Commands.SerializedCommands.SerializedSimplyCommand;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.text.ParseException;

public class CommandDecoder {
    private DatagramSocket datagramSocket;
    private DatagramPacket datagramPacket;

    public CommandDecoder(DatagramSocket datagramSocket, DatagramPacket datagramPacket){
        this.datagramSocket = datagramSocket;
        this.datagramPacket = datagramPacket;
    }

    public void decode(Object o) throws IOException, ParseException {
        if(o instanceof SerializedSimplyCommand){
            SerializedSimplyCommand simplyCommand = (SerializedSimplyCommand) o;
            Command command = simplyCommand.getCommand();
            String arg = "";
            command.execute(arg, datagramSocket, datagramPacket);
        }

        if(o instanceof SerializedArgumentCommand){
            SerializedArgumentCommand argumentCommand = (SerializedArgumentCommand) o;
            Command command = argumentCommand.getCommand();
            String arg = argumentCommand.getArg();
            command.execute(arg, datagramSocket, datagramPacket);
        }

        if(o instanceof SerializedObjectCommand){
            SerializedObjectCommand objectCommand = (SerializedObjectCommand) o;
            Command command = objectCommand.getCommand();
            Object object = objectCommand.getObject();
            command.execute(object, datagramSocket, datagramPacket);
        }

        if(o instanceof SerializedCombinedCommand){
            SerializedCombinedCommand combinedCommand = (SerializedCombinedCommand) o;
            Command command = combinedCommand.getCommand();
            command.execute(combinedCommand, datagramSocket, datagramPacket);
        }
    }

}
