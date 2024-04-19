package server.commands;

import server.managers.CollectionManager;
import common.models.Vehicle;
import common.network.requests.Request;
import common.network.requests.RequestWithIntAndVehicleArg;
import common.utils.Console;
import common.utils.ExecutionResponse;


/**
 * Обновляет элемент коллекции.
 * @author trikesh
 */
public class Update extends Command {
    private CollectionManager collectionManager;

    public Update(CollectionManager collectionManager) {
        super("update id {element}", "обновляет значение элемента");
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду
     * @return Успешность выполнения команды.
     */
    @Override
    public ExecutionResponse apply(Request request) {
        var req = (RequestWithIntAndVehicleArg) request;
        try {
            int id;
            var old_vehicle = collectionManager.getById(req.getIntegerArg());
            if (old_vehicle == null || !collectionManager.isContain(old_vehicle)) {
                return new ExecutionResponse(false, "Элемент с заданым ID не найден!");
            }
            var new_vehicle = new Vehicle(req.getIntegerArg(), req.getVehicleArg().getName(), req.getVehicleArg().getCoordinates(), req.getVehicleArg().getCreationDate(), req.getVehicleArg().getEnginePower(), req.getVehicleArg().getCapacity(), req.getVehicleArg().getVehicleType(), req.getVehicleArg().getFuelType());
            if (new_vehicle != null && new_vehicle.check_validity()){
                collectionManager.remove(req.getIntegerArg());
                collectionManager.add(new_vehicle);
                return new ExecutionResponse("Vehicle успешно обновлен!");
            } else {
                return new ExecutionResponse(false, "Данные не валидны!");
            }

        } catch (Exception e) {
            return new ExecutionResponse(false, e.toString());
        }

    }
}
