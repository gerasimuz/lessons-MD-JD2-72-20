
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
    
    public Controller() {
    	super();
        // TODO Auto-generated constructor stub
    }

	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
		response.setContentType("text/html");
		String name = request.getParameter("firstname");
		String secondName = request.getParameter("secondName");
		String thirdName = request.getParameter("thirdName");
		String email = request.getParameter("email");
		String mobile = request.getParameter("mobile");
		
		User user = new User(name, secondName, thirdName, email, mobile);
		
		request.setAttribute("user", user);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/second.jsp");
		requestDispatcher.forward(request, response);
	}
}
