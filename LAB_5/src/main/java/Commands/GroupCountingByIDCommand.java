package Commands;

import Core.CollectionManager;

public class GroupCountingByIDCommand extends AbstractCommand{
    private CollectionManager collectionManager;

    public GroupCountingByIDCommand(CollectionManager C){
        this.collectionManager = C;
    }
    @Override
    public boolean execute() {
        collectionManager.groupCountingById();
        return true;
    }
}
