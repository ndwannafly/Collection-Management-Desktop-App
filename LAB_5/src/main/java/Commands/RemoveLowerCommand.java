package Commands;

import Core.CollectionManager;
import Core.CommandAsker;

public class RemoveLowerCommand extends AbstractCommand{
    private final CollectionManager collectionManager;
    private final CommandAsker commandAsker;
    public RemoveLowerCommand(CollectionManager C, CommandAsker asker){
        this.collectionManager = C;
        this.commandAsker = asker;
    }
    @Override
    public boolean execute() {
        collectionManager.removeLower(commandAsker.createPerson());
        return true;
    }
}
