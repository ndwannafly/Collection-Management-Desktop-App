package Ulties;

import Commands.Command;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

public class Invoker {
    private HashMap<String, Command> commands = new HashMap<>();

    private Deque<String> commandHistory = new ArrayDeque<>();

    public void register(String name, Command command){
        commands.put(name, command);
    }

    public void executeCommand(String[] name) throws IOException {
        if(name.length > 0){
            Command command = commands.get(name[0]);
            System.out.println("CLIENT: Command's name: " + name[0]);
            command.execute(name);
            addCommandToHistory(name[0]);
        }
        else{
            System.out.println("Client: Error! You didn't invert command!");
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
