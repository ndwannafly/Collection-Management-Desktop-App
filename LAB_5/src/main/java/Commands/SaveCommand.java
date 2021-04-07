package Commands;

import Core.CollectionManager;

public class SaveCommand extends AbstractCommand{
    private final CollectionManager collectionManager;

    public SaveCommand(CollectionManager C){
        this.collectionManager = C;
    }
    @Override
    public boolean execute(String fileName) {
        collectionManager.save(fileName);
        return true;
    }
}
