package Commands;

import java.io.IOException;

public abstract class Command {
    public abstract void aboutCommand();

    public abstract void execute(String[] args) throws IOException;
}
