import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet ("/MyServlet")
public class MyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Integer> persons = new HashMap<>();
        persons.put ("Gerik", 34);
        persons.put ("Oksik", 31);
        persons.put ("Valery", 26);
        req.setAttribute("persons",persons);

        req.getRequestDispatcher("persons.jsp").forward(req,resp);

    }
}
