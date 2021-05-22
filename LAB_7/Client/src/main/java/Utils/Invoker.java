package Utils;

import Commands.Command;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

public class Invoker {
    private final HashMap<String, Command> commands = new HashMap<>();

    private final Deque<String> commandHistory = new ArrayDeque<>();

    public void register(String name, Command command){
        commands.put(name, command);
    }

    public void executeCommand(String[] userCommand) throws IOException {
        try {
            //System.out.println("here");
            if(userCommand[0].equals("logout")){
                Receiver.isLogin = false;
                Receiver.handle = "";
                Receiver.password = "";
                System.out.println("Logged out!");
                return ;
            }
            if(Receiver.isLogin || userCommand[0].equals("login") ||
                    userCommand[0].equals("register") || userCommand[0].equals("exit")){
                if(userCommand[0].equals("history")){
                    commandHistory.forEach(System.out::println);
                }
                else {
                    Command command = commands.get(userCommand[0]);
                    //System.out.println(userCommand.length + " " + userCommand[0] + " " + userCommand[1] + " " + userCommand[2]);
                    command.execute(userCommand);
                    addCommandToHistory(userCommand[0]);
                }
            }
            else{
                System.out.println("You are not logged in!");
            }
        } catch(NullPointerException e){
            System.out.println("Command is not supported! Insert help to see the guideline!");
            //e.printStackTrace();
        }
    }

    public void addCommandToHistory(String onlyName){
        if(commandHistory.size() == 14) commandHistory.removeFirst();
        commandHistory.addLast(onlyName);
    }

    public HashMap<String, Command> getCommands(){
        return this.commands;
    }
}
