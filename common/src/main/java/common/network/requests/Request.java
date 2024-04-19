package common.network.requests;

import java.io.Serializable;

public class Request implements Serializable {
    private String commandName;
    public Request(String name){
        commandName = name;
    }

    public String getCommandName() {
        return commandName;
    }
}
