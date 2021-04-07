package Commands;

import Core.CollectionManager;
import Core.CommandAsker;
import Core.InputChecker;

public class UpdateCommand extends AbstractCommand{
    private final CollectionManager collectionManager;
    private final InputChecker inputChecker;
    private final CommandAsker commandAsker;
    public UpdateCommand(CollectionManager C, InputChecker checker, CommandAsker asker){
        this.collectionManager = C;
        this.inputChecker = checker;
        this.commandAsker = asker;
    }
    @Override
    public boolean execute(String argument) {
        if(inputChecker.longValidCheck(argument,(long)0,Long.MAX_VALUE)){
            Long id = Long.parseLong(argument);
            if(collectionManager.removeById(id)){
                System.out.println("Id doesn't exist. Please insert the existing id!");
                return false;
            }
            collectionManager.add(commandAsker.createPerson());
            return true;
        }
        System.out.println("The inserting ID is not in valid range! Please insert Id greater than 0!");
        return false;
    }
}
