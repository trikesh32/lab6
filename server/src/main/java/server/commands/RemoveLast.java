package server.commands;

import server.managers.CollectionManager;
import common.network.requests.Request;
import common.utils.ExecutionResponse;

/**
 * Убирает последний элемент коллекции
 * @author trikesh
 */
public class RemoveLast extends Command{

    private CollectionManager collectionManager;

    public RemoveLast(CollectionManager collectionManager) {
        super("remove_last", "удаляет последний элемент");
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду
     * @return Успешность выполнения команды.
     */
    @Override
    public ExecutionResponse apply(Request req) {
        collectionManager.removeLast();
        return new ExecutionResponse("Последний Vehicle удален успешно!");
    }
}
