package Commands;

public class ExitCommand extends AbstractCommand{
    @Override
    public boolean execute() {
        System.exit(0);
        return true;
    }
}
