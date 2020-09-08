import org.w3c.dom.ls.LSOutput;

import java.sql.*;

public class Program {
    public static void main(String[] args) throws Exception{
        String dbUser = "mysql";
        String user = "root";
        String password = "123456";
        String connectionUrl = "jdbc:mysql://localhost:3306/jd2-first-db";
        Connection connection = DriverManager.getConnection(connectionUrl, user, password);

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM fix_user");
        while (resultSet.next()){
            System.out.println(resultSet.getString("name"));
            System.out.println(resultSet.getString("password"));

        }
    }
}

