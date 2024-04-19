package server.commands;

import server.managers.CollectionManager;
import common.network.requests.Request;
import common.utils.ExecutionResponse;

/**
 * Выводит элементы коллекции.
 * @author trikesh
 */
public class Show extends Command{
    private CollectionManager collectionManager;
    public Show(CollectionManager collectionManager){
        super("show", "выводит информацию о коллекции");
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду
     * @return Успешность выполнения команды.
     */
    @Override
    public ExecutionResponse apply(Request req) {
        if (collectionManager.isEmpty()) return new ExecutionResponse("Коллекция пустая!");
        return new ExecutionResponse(collectionManager.sorted().toString());
    }
}
