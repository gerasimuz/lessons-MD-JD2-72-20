package controller.command;

import java.util.HashMap;
import java.util.Map;

import controller.command.impl.ChangeLocaleCommand;
import controller.command.impl.NewsCreateCommand;
import controller.command.impl.NewsSelectAllCommand;
import controller.command.impl.NewsSelectByIdCommand;
import controller.command.impl.NewsDeleteByIdCommand;
import controller.command.impl.NewsDeleteSomeCommand;
import controller.command.impl.NewsUpdateByIdCommand;

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
