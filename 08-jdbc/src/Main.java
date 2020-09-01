import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
    public static void main	(String args[]) {
    Connection con = null;
    PreparedStatement ps = null;

	    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/jd2-first-db?useSSL=false&serverTimezone=UTC","root", "123456");

        String sql = "INSERT INTO users(login, password, name, surname) VALUES(?,?,?,?)";
        ps = con.prepareStatement(sql);

        ps.setString(1, "121322");
        ps.setString(2, "www");
        ps.setString(3, "Petr");
        ps.setString(4, "Petrov");

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
}
}
