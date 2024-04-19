package server.commands;

import server.managers.CollectionManager;
import common.network.requests.Request;
import common.utils.ExecutionResponse;

/**
 * Очищает коллекцию.
 * @author trikesh
 */
public class Clear extends Command{
    private CollectionManager collectionManager;

    public Clear(CollectionManager collectionManager) {
        super("clear", "очищает коллекцию");
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду
     * @return Успешность выполнения команды.
     */
    @Override
    public ExecutionResponse apply(Request request) {
        collectionManager.clear();
        return new ExecutionResponse("Коллекция очищена успешно!");
    }
}
