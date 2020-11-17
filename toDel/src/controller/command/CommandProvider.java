package controller.command;

import controller.command.impl.*;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private Map<ParameterName, Command> commands = new HashMap<>();

    public CommandProvider() {

    	commands.put(ParameterName.NEWS_SELECT_ALL, new NewsSelectAllCommand());
        commands.put(ParameterName.NEWS_CREATE, new NewsCreateCommand());
        commands.put(ParameterName.NEWS_SELECT_BY_ID, new NewsSelectByIdCommand());
        commands.put(ParameterName.NEWS_UPDATE_BY_ID, new NewsUpdateByIdCommand());
        commands.put(ParameterName.NEWS_DELETE_BY_ID, new NewsDeleteByIdCommand());
        commands.put(ParameterName.NEWS_DELETE_SOME, new NewsDeleteSomeCommand());
        commands.put(ParameterName.CHANGE_LOCALE, new ChangeLocaleCommand());
    }
    
    public Command getCommand(String commandName){
        Command command;
        ParameterName valueName;
        commandName = commandName.toUpperCase();
        valueName = ParameterName.valueOf(commandName);
        command= commands.get(valueName);
        return command;
    }
}
