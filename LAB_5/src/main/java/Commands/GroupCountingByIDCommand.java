package Commands;

import Core.CollectionManager;

public class GroupCountingByIDCommand extends AbstractCommand{
    private final CollectionManager collectionManager;

    public GroupCountingByIDCommand(CollectionManager C){
        this.collectionManager = C;
    }
    @Override
    public boolean execute() {
        collectionManager.groupCountingById();
        return true;
    }
}
