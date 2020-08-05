package by.htp.les02.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.les02.controller.command.Command;

public class SaveNewUserCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// save in DB
		System.out.println("Save in database");

		response.sendRedirect("controller?command=welcome_new_user");

	}

}
