package controller.commandImpl;

import controller.command.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SaveNewUserConmmand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    	Connection con = null;
        PreparedStatement ps = null;

    	    try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3333/jd2-first-db?useSSL=false&serverTimezone=UTC","root", "pass");

            String sql = "INSERT INTO users(login, password, name, surname) VALUES(?,?,?,?)";
            ps = con.prepareStatement(sql);

            ps.setString(1, "login");
            ps.setString(2, "password");
            ps.setString(3, req.getParameter("name"));
            ps.setString(4, req.getParameter("surname"));

            ps.executeUpdate();

        }catch(ClassNotFoundException e) {
            e.printStackTrace();
        }catch(SQLException e) {
            e.printStackTrace();
        }finally {
            if(ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if(con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    
    
        resp.sendRedirect("controller?command=welcome_new_user");
    }
}
