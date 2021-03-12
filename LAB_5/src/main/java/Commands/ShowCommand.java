package Commands;

import Core.CollectionManager;

public class ShowCommand extends AbstractCommand{
    private final CollectionManager collectionManager;

    public ShowCommand(CollectionManager C){
        this.collectionManager = C;
    }
    @Override
    public boolean execute() {
        collectionManager.PrintCollection();
        return true;
    }
}
