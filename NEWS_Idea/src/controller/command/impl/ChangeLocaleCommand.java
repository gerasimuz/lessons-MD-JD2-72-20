package controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.command.Command;

public class ChangeLocaleCommand implements Command {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getSession().setAttribute("local", req.getParameter("local"));

		RequestDispatcher requestDispatcher = req.getRequestDispatcher(req.getParameter("fromPage"));
		requestDispatcher.forward(req,resp);
	}

}