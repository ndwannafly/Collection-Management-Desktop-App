package Commands;

import Core.CollectionManager;
import Core.CommandAsker;

public class RemoveLowerCommand extends AbstractCommand{
    private CollectionManager collectionManager;
    private CommandAsker commandAsker;
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
