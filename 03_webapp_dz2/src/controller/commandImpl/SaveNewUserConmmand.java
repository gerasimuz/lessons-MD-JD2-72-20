package controller.commandImpl;

import controller.command.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SaveNewUserConmmand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //save in DB

        System.out.printf("Save in database ");

        resp.sendRedirect("controller?command=welcome_new_user");
    }
}
