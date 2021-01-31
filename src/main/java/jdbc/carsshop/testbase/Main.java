package jdbc.carsshop.testbase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class Main {

    private static final String URL = "jdbc:mysql://localhost:3305/jdbc.carsshop.testbase?useUnicode=true&serverTimezone=UTC&";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "admin";

    private static void registerDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded success");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        registerDriver();

        Connection connection = null;
        Statement statement = null;

        try {
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            statement = connection.createStatement();

            statement.execute("INSERT INTO testtable(name, sername) VALUES ('Irina', 'Feduleeva')");

            boolean closed = statement.isClosed();
            System.out.println(closed);
 
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
