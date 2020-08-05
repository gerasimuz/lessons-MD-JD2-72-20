package by.htp.les02.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.les02.controller.command.Command;
import by.htp.les02.controller.command.CommandProvider;
import by.htp.les02.entity.User;

public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final CommandProvider provider = new CommandProvider();
	
	private static final String COMMAND_NAME = "command";

	public Controller() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html");

		String currentCommandName;
		Command command;
		
		currentCommandName = request.getParameter(COMMAND_NAME);
		command = provider.getCommand(currentCommandName);
		
		command.execute(request, response);
		
	}

}
