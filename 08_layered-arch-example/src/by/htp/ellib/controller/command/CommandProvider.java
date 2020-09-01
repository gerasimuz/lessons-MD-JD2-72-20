package by.htp.ellib.controller.command;

import java.util.HashMap;
import java.util.Map;

import by.htp.ellib.controller.command.impl.AuthorizationCommand;
import by.htp.ellib.controller.command.impl.ChangeLocale;
import by.htp.ellib.controller.command.impl.FindBookCommand;
import by.htp.ellib.controller.command.impl.GoToIndexPageCommand;
import by.htp.ellib.controller.command.impl.GoToRegistrationCommand;
import by.htp.ellib.controller.command.impl.RegistrationCommand;

public class CommandProvider {
	private Map<String, Command> commands = new HashMap<>();

	
	public CommandProvider() {
		commands.put("authorization", new AuthorizationCommand());
		commands.put("goToRegistratioPage", new GoToRegistrationCommand());
		commands.put("registration", new RegistrationCommand());
		commands.put("find_book", new FindBookCommand());
		commands.put("go_to_index", new GoToIndexPageCommand());
		commands.put("change_locale", new ChangeLocale());
	}
	
	public Command getCommand(String commandName) {
		return commands.get(commandName);
	}
}
