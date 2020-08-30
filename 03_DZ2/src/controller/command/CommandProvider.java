package controller.command;

import controller.commandImpl.SaveNewUserConmmand;
import controller.commandImpl.WelcomeNewUserCommand;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private Map<ParameterName, Command> commands = new HashMap<>();

    public CommandProvider() {

        commands.put(ParameterName.SAVE_NEW_USER, new SaveNewUserConmmand());
        commands.put(ParameterName.WELCOME_NEW_USER, new WelcomeNewUserCommand());
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
