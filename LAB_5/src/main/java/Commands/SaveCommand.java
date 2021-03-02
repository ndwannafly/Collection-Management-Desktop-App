package Commands;

import Core.CollectionManager;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class SaveCommand extends AbstractCommand{
    private CollectionManager collectionManager;

    public SaveCommand(CollectionManager C){
        this.collectionManager = C;
    }
    @Override
    public boolean execute(String fileName) {
        collectionManager.save(fileName);
        return true;
    }
}
