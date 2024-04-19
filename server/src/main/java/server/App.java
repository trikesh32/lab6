package server;
import com.google.j2objc.annotations.ReflectionSupport;
import server.handlers.CommandHandler;
import server.managers.CollectionManager;
import server.managers.CommandManager;
import server.managers.DumpManager;
import server.commands.*;
import server.network.UDPDatagramServer;

import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.*;

public class App {
    public static final int PORT = 4308;
    public static Logger logger = Logger.getLogger("ServerLogger");

    public static void main(String[] args){
        try {
            FileHandler fileHandler = new FileHandler("server_log_%u.%g.log", 1024 * 1024, 10, false);
            fileHandler.setFormatter(new SimpleFormatter());
            fileHandler.setLevel(Level.ALL);
            logger.addHandler(fileHandler);
            logger.setLevel(Level.ALL);
        } catch (Exception e){
            System.out.println("Неудалось создать логи(");
            System.exit(1);
        }
        if (args.length != 1){
            System.out.println("Введите имя загружаемого файла как аргумент коммандной строки");
            System.exit(1);
        }
        var dumpManager = new DumpManager(args[0]);
        var collectionManager = new CollectionManager(dumpManager);
        if (!collectionManager.init()){
            logger.severe("Не удалось загрузить коллекцию");
            System.exit(1);
        }
        Runtime.getRuntime().addShutdownHook(new Thread(collectionManager::saveCollection));
        CommandManager commandManager = new CommandManager();
        commandManager.register("add", new Add(collectionManager));
        commandManager.register("show", new Show(collectionManager));
        commandManager.register("help", new Help(commandManager));
        commandManager.register("info", new Info(collectionManager));
        commandManager.register("update", new Update(collectionManager));
        commandManager.register("remove_by_id", new RemoveById(collectionManager));
        commandManager.register("clear", new Clear(collectionManager));
        commandManager.register("remove_last", new RemoveLast(collectionManager));
        commandManager.register("sort", new Sort(collectionManager));
        commandManager.register("remove_lower", new RemoveLower(collectionManager));
        commandManager.register("sum_of_capacity", new SumOfCapacity(collectionManager));
        commandManager.register("filter_by_capacity", new FilterByCapacity(collectionManager));
        commandManager.register("filter_less_than_type", new FilterLessThanType(collectionManager));
        try {
            var server = new UDPDatagramServer(InetAddress.getLocalHost(), PORT, new CommandHandler(commandManager));
            server.setAfterHook(collectionManager::saveCollection);
            server.run();
        } catch (SocketException e){
            logger.severe("Ошибка сокета" + e);
        } catch (UnknownHostException e){
            logger.severe("Неизвестный хост");
        }
    }

}
