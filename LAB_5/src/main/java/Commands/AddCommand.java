package Commands;

import Core.*;

public class AddCommand extends AbstractCommand{
    private CollectionManager collectionManager;
    private CommandAsker commandAsker;

    public AddCommand(CollectionManager cm, CommandAsker ca){
        this.collectionManager = cm;
        this.commandAsker = ca;
    }

    @Override
    public boolean execute() {
        collectionManager.add(commandAsker.createPerson());
        return true;
    }
}
