package by.gera;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

@WebServlet("/temp")
public class ThreadServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        String one = request.getParameter("one");
        String two = request.getParameter("two");
        pw.write("<html>");
        pw.write("<head>");
        pw.write("</head>");
        pw.write("<body>" +
                "<form action='temp' method = 'post'>" +
                "<input type= 'text' name = 'one' />" + one +
                "<input type= 'text' name = 'two' />" + two +
                "<input type= 'submit' name = 'button' value='PushIt' />" +
                "</form>"
                + "</body>");
        pw.write("</html>");

    }


}
