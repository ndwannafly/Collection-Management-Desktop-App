package Commands;

import Core.CollectionManager;
import Core.CommandAsker;

public class RemoveGreaterCommand extends AbstractCommand{
    private final CollectionManager collectionManager;
    private final CommandAsker commandAsker;
    public RemoveGreaterCommand(CollectionManager C, CommandAsker asker){
        this.collectionManager = C;
        this.commandAsker = asker;
    }
    @Override
    public boolean execute() {
        collectionManager.removeGreater(commandAsker.createPerson());
        return true;
    }
}
