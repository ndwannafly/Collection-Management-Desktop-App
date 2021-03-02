package Commands;

import Core.CollectionManager;

import java.util.ArrayList;

public class PrintFieldAscendingHeightCommand extends AbstractCommand{
    private CollectionManager collectionManager;

    public PrintFieldAscendingHeightCommand(CollectionManager C){
        this.collectionManager = C;
    }
    @Override
    public boolean execute() {
        ArrayList<Long> heightArray = collectionManager.getArrayHeight();
        heightArray.forEach(p -> System.out.println(p));
        return true;
    }
}
