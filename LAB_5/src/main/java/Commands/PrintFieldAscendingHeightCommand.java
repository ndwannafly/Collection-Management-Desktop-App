package Commands;

import Core.CollectionManager;

import java.util.ArrayList;

public class PrintFieldAscendingHeightCommand extends AbstractCommand{
    private final CollectionManager collectionManager;

    public PrintFieldAscendingHeightCommand(CollectionManager C){
        this.collectionManager = C;
    }
    @Override
    public boolean execute() {
        ArrayList<Long> heightArray = collectionManager.getArrayHeight();
        heightArray.forEach(System.out::println);
        return true;
    }
}
