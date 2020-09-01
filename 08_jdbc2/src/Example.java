import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Example {
public static void main(String[] args) {
	PreparedStatement ps = null;
	Statement st = null;
	Connection conn = null;
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/jd2-first-db?useSSL=false&serverTimezone=UTC",
				"root",
				"123456");
		
		// insert into base
		//preparedStatement-executeUpdate
		String insertIntoBase = "INSERT INTO users (login, password, name, surname) VALUES (?,?,?,?)";
		
		ps = conn.prepareStatement(insertIntoBase);
		
		ps.setString (1,"Lyuda");
		ps.setString (2,"Ivanova");
		ps.setString (3,"Lyudmila");
		ps.setString (4,"Ivanovna");
		
		ps.executeUpdate();
		
		
	
	} catch (ClassNotFoundException e) {		
		e.printStackTrace();
	} catch (SQLException e) {
		e.printStackTrace();
	}finally {

		}
		if(ps!=null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} if (conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
