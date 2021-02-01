package dao.simple_dao.dao;

import dao.simple_dao.entity.Car;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarJDBCDao implements CarDAO {
    @Override
    public void add(Car car) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        connection = getConnection();

        try {
            int markId = getMarkId(car.getMark(), connection);

            if (markId == -1) {
                preparedStatement = connection.prepareStatement("INSERT INTO marks(mark) VALUES (?)");
                preparedStatement.setString(1, car.getMark());
                preparedStatement.execute();
                ResultSet rs = preparedStatement.executeQuery("SELECT MAX(id) FROM marks");
                rs.next();
                markId = rs.getInt(1);
            }

            preparedStatement = connection.prepareStatement("INSERT INTO cars(mark_id, model, price) VALUES (?, ?, ?)");

            preparedStatement.setInt(1, markId);
            preparedStatement.setString(2, car.getModel());
            preparedStatement.setInt(3, car.getPrice());

            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null && preparedStatement != null) {
                try {
                    connection.close();
                    preparedStatement.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        }

    }

    private int getMarkId(String markName, Connection connection) {

        try {

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id FROM marks WHERE mark = ?");
            preparedStatement.setString(1, markName);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
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

    @Override
    public List<Car> getAll() {

        List<Car> allCars = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement("SELECT c.id, m.mark, c.model, c.price FROM cars as c " +
                    "INNER JOIN marks as m ON m.id = c.mark_id ");

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                long id = rs.getLong(1);
                String mark = rs.getString(2);
                String model = rs.getString(3);
                int price = rs.getInt(4);

                Car car = new Car();

                car.setId(id);
                car.setMark(mark);
                car.setModel(model);
                car.setPrice(price);

                allCars.add(car);

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null && preparedStatement != null) {
                try {
                    connection.close();
                    preparedStatement.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        }

        return allCars;
    }

    @Override
    public Car getById(int id) {

        Connection connection;
        PreparedStatement preparedStatement = null;

        connection = getConnection();

        try {
            preparedStatement = connection.prepareStatement("SELECT m.mark, c.model, c.price FROM cars as c " +
                    "INNER JOIN marks as m ON m.id = c.mark_id WHERE c.id = ? ");

            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {

                String mark = rs.getString(1);
                String model = rs.getString(2);
                int price = rs.getInt(3);

                Car car = new Car();
                car.setId(id);
                car.setMark(mark);
                car.setModel(model);
                car.setPrice(price);

                return car;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null && preparedStatement != null) {
                try {
                    connection.close();
                    preparedStatement.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        }

        return null;
    }

    @Override
    public void updatePrice(int price, int carId) {

        Connection connection;
        PreparedStatement preparedStatement = null;

        connection = getConnection();

        try {
            preparedStatement = connection.prepareStatement("UPDATE cars SET price = ? WHERE id = ?");

            preparedStatement.setInt(1, price);
            preparedStatement.setInt(2, carId);

            int updatedValues = preparedStatement.executeUpdate();

            System.out.println("Values updated: " + updatedValues);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (connection != null && preparedStatement != null) {
                try {
                    connection.close();
                    preparedStatement.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        }

    }

    @Override
    public void remove(String mark) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        connection = getConnection();

        try {

            int markId = getMarkId(mark, connection);

            preparedStatement = connection.prepareStatement("DELETE FROM cars WHERE mark_id = ?");

            preparedStatement.setInt(1, markId);

            int deletedValues = preparedStatement.executeUpdate();

            System.out.println("Values deleted: " + deletedValues);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (connection != null && preparedStatement != null) {
                try {
                    connection.close();
                    preparedStatement.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        }

    }
}
