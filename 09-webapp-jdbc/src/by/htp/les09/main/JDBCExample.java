package by.htp.les09.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCExample {
	
	public static void main(String[] args) throws ClassNotFoundException {
		
		Connection con = null;
	    PreparedStatement ps = null;
	    
	    try {
	      Class.forName("com.mysql.cj.jdbc.Driver");
//	      Class.forName("oracle.jdbc.driver.OracleDriver");
//	      Class.forName("com.mysql.jdbc.Driver");
	      con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/jd2-firstdb?useSSL=false","root", "24041987");

	      String sql = "INSERT INTO users(login, name, surname) VALUES(?,?,?)";
	      ps = con.prepareStatement(sql);
	      
	      ps.setString(1, "qqq");
	      ps.setString(2, "Petr");
	      ps.setString(3, "Petrov");

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
