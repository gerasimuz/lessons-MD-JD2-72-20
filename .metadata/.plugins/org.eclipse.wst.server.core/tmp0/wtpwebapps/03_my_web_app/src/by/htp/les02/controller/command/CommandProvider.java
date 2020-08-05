package by.htp.les02.controller.command;

import java.util.HashMap;
import java.util.Map;

import by.htp.les02.controller.command.impl.SaveNewUserCommand;
import by.htp.les02.controller.command.impl.WelcomeNewUserCommand;

public class CommandProvider {
	
	private Map<ParameterName, Command> commands = new HashMap<ParameterName, Command>();
	
	public CommandProvider() {
		commands.put(ParameterName.SAVE_NEW_USER, new SaveNewUserCommand());
		commands.put(ParameterName.WELCOME_NEW_USER, new WelcomeNewUserCommand());
	}
	
	public Command getCommand(String commandName) {
		Command command;
		ParameterName valueName;
		
		commandName = commandName.toUpperCase();
		valueName = ParameterName.valueOf(commandName);
		
		command = commands.get(valueName);
		
		return command;		
	}

}
