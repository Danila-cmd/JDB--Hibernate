package dao.simple_dao.dao;

import jdbc.carsshop.result_set.Client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class ClientJDBCDao implements ClientDAO {
    @Override
    public void add(Client car) {

    }

    @Override
    public List<Client> getAll() {
        return null;
    }

    @Override
    public Client getById(int id) {
        return null;
    }

    @Override
    public void updatePrice(int age, int clientId) {

    }

    @Override
    public void remove(String name) {

    }

    private Connection getConnection() {
        Connection connection = null;

        final String URL = "jdbc:mysql://localhost:3305/carsshop?useUnicode=true&serverTimezone=UTC&";
        final String LOGIN = "root";
        final String PASSWORD = "admin";

        try {
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}
