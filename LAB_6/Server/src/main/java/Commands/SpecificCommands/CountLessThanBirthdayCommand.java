package Commands.SpecificCommands;

import Commands.Command;
import Ulties.Logging;
import Ulties.Receiver;

import java.io.IOException;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;

public class CountLessThanBirthdayCommand extends Command implements Serializable {

    private static final long serialVersionUID = 1234567L;

    @Override
    public void execute(Object o, DatagramSocket datagramSocket, DatagramPacket datagramPacket) throws IOException, ParseException {
        SimpleDateFormat birthdayFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        Date birthday = birthdayFormatter.parse((String) o);
        Receiver receiver = new Receiver(datagramSocket, datagramPacket);
        Logging.log(Level.INFO, "Server is executing CountLessThanBirthDayCommand....");
        receiver.countLessThanBirthday(birthday);
    }

}
