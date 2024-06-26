package client.commands;

import client.managers.CommandManager;
import client.network.UDPClient;
import client.utils.TimeoutException;
import common.network.requests.Request;
import common.utils.ExecutionResponse;

import java.io.IOException;

/**
 * Выводит информацию о командах.
 *
 * @author trikesh
 */
public class Help extends Command {
    private CommandManager commandManager;
    private UDPClient client;

    public Help(CommandManager commandManager, UDPClient client) {
        super("help", "выводит информацию о доступных командах");
        this.commandManager = commandManager;
        this.client = client;
    }

    /**
     * Выполняет команду
     *
     * @return Успешность выполнения команды.
     */
    @Override
    public ExecutionResponse apply(String[] args) {
        try {
            if (!args[1].isEmpty()) return new ExecutionResponse(false, "Команда используется не верно!");
            var response = client.sendAndReceiveCommand(new Request("help"));
            return new ExecutionResponse(response.getExitCode(), response.getMessage());
        } catch (IOException e){
            return new ExecutionResponse(false, "Ошибка взаимодействия с сервером");
        }catch (TimeoutException e) {
            return new ExecutionResponse(false, "Сервер не отвечает");
        }
    }
}
