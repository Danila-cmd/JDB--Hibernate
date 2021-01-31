package jdbc.carsshop.delete_from_dish;

import java.sql.*;


public class Main {

    private static final String URL = "jdbc:mysql://localhost:3305/jdbc.carsshop?useUnicode=true&serverTimezone=UTC&";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "admin";

    private static final String DELETE = "DELETE FROM dish WHERE id = ? AND title = ?";

    public static void main(String[] args) {

        registerDriver();

        Connection connection = null;
        PreparedStatement statement = null; // интерфейс взаимодействия с бд

        try {

            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            statement = connection.prepareStatement(DELETE);

            statement.setString(2, "title");
            statement.setInt(1, 1);

            int res = statement.executeUpdate();
            System.out.println(res);

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

    private static void registerDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded success");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
