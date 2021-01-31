package jdbc.carsshop.prepared_statement;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Calendar;

public class Main {

    private static final String URL = "jdbc:mysql://localhost:3305/jdbc.carsshop?useUnicode=true&serverTimezone=UTC&";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "admin";

    public static void main(String[] args) {
        registerDriver();

        Connection connection = null;
        PreparedStatement statement = null;

        try {

            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            statement = connection.prepareStatement("INSERT INTO dish(title, description, rating, published, created, icon) VALUES(?,?,?,?,?,?)");

            Date date = new Date(Calendar.getInstance().getTimeInMillis());

            statement.setString(1, "Title");
            statement.setString(2, "Description");
            statement.setDouble(3, 4.5);
            statement.setBoolean(4, false);
            statement.setDate(5, date);
            statement.setBlob(6, new FileInputStream("chair.png"));


        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }

    }

    private static void registerDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded success");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
