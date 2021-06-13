package lab8.Commands;

import java.io.IOException;

public abstract class Command {
    public abstract String aboutCommand();

    public abstract void execute(String[] args) throws IOException;
}
