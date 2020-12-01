package controller;

import controller.command.Command;
import controller.command.CommandProvider;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class Controller extends HttpServlet {



    private static final long serialVersionUID = 1L;

	private final CommandProvider provider = new CommandProvider();

    private static final String COMMAND_NAME = "command";

    public Controller() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       process(req, resp);
    }

	
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
        String currentCommandName;
        Command command;
        currentCommandName = req.getParameter(COMMAND_NAME);
        command = provider.getCommand(currentCommandName);
        command.execute(req,resp);
	}
}