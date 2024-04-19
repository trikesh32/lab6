package server.commands;

import server.managers.CollectionManager;
import common.network.requests.Request;
import common.network.requests.RequestWithIntArg;
import common.utils.ExecutionResponse;

/**
 * Убирает элемент по id.
 * @author trikesh
 */
public class RemoveById extends Command{
    private CollectionManager collectionManager;

    public RemoveById(CollectionManager collectionManager) {
        super("remove_by_id id", "удаляет элемент по id");
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду
     * @return Успешность выполнения команды.
     */
    @Override
    public ExecutionResponse apply(Request request) {
        var req = (RequestWithIntArg)request;
        if (collectionManager.getById(req.getIntegerArg()) == null || !collectionManager.isContain(collectionManager.getById(req.getIntegerArg()))) {
            return new ExecutionResponse(false, "Элемент с заданым ID не найден!");
        }
        collectionManager.remove(req.getIntegerArg());
        return new ExecutionResponse("Vehicle успешно удален!");
    }


}
