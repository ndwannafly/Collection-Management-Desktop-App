package Commands.SpecificCommands;

import Commands.Command;
import Ulties.Receiver;

import java.io.IOException;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CountLessThanBirthdayCommand extends Command implements Serializable {

    private static final long serialVersionUID = 1234567L;

    public CountLessThanBirthdayCommand(){

    }



    @Override
    public void execute(Object o, DatagramSocket datagramSocket, DatagramPacket datagramPacket) {
        SimpleDateFormat birthdayFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        Date birthday = null;
        try{
            birthday = birthdayFormatter.parse((String) o);
            Receiver receiver = new Receiver(datagramSocket, datagramPacket);
            receiver.countLessThanBirthday(birthday);
        }catch (ParseException | IOException e){
            e.printStackTrace();
        }
    }

}
