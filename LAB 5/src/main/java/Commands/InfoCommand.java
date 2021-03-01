package Commands;

import Core.CollectionManager;

public class InfoCommand extends AbstractCommand{
    private CollectionManager collectionManager;

    public InfoCommand(CollectionManager C){
        this.collectionManager = C;
    }
    @Override
    public boolean execute() {
        System.out.println("Collection's type : HashSet");
        System.out.println("Initialization date: " + collectionManager.getCreationDate());
        System.out.println("Number of elements " + collectionManager.size());
        return true;
    }
}
