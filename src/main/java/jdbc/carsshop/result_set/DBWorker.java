package jdbc.carsshop.result_set;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBWorker {

    private static final String URL = "jdbc:mysql://localhost:3305/jdbc.carsshop?useUnicode=true&serverTimezone=UTC&";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "admin";

    public DBWorker() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loading success!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<Client> getAllClients() {
        Connection connection = null;
        Statement statement = null;
        List<Client> clients = new ArrayList<Client>();

        try {
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM clients");

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                int age = resultSet.getInt("age");
                String name = resultSet.getString(2);
                String phone = resultSet.getString("phone");
                Client client = new Client(id, age, name, phone);
                clients.add(client);
            }

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

        return clients;
    }

}
