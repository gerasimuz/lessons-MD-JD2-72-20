package controller.commandImpl;

import controller.command.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WelcomeNewUserCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("/n");
        System.out.println("WElCOME NEW USER");
        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
                rd.forward(req,resp);
                
                
    }
}
