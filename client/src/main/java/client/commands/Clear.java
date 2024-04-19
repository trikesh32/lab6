package client.commands;

import client.network.UDPClient;
import client.utils.TimeoutException;
import common.network.requests.Request;
import common.utils.ExecutionResponse;

import java.io.IOException;

/**
 * Очищает коллекцию.
 * @author trikesh
 */
public class Clear extends Command{
    private UDPClient client;

    public Clear(UDPClient client) {
        super("clear", "очищает коллекцию");
        this.client = client;
    }

    /**
     * Выполняет команду
     * @return Успешность выполнения команды.
     */
    @Override
    public ExecutionResponse apply(String[] args) {
        if(!args[1].isEmpty()) return new ExecutionResponse(false, "Команда используется не верно!");
        try {
            var response = client.sendAndReceiveCommand(new Request("clear"));
            return new ExecutionResponse(response.getExitCode(), response.getMessage());
        } catch (IOException e){
            return new ExecutionResponse(false, "Ошибка взаимодействия с сервером");
        }catch (TimeoutException e) {
            return new ExecutionResponse(false, "Сервер не отвечает");
        }
    }
}
