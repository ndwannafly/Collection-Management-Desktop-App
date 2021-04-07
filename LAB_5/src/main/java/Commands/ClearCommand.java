package Commands;

import Core.CollectionManager;

public class ClearCommand extends AbstractCommand{
    private final CollectionManager collectionManager;

    public ClearCommand(CollectionManager C){
        this.collectionManager = C;
    }
    @Override
    public boolean execute() {
        collectionManager.clear();
        return true;
    }
}
