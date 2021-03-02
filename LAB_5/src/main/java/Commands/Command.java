package Commands;

public interface Command {
    boolean execute(String argument);
    boolean execute();
}
