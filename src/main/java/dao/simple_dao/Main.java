package dao.simple_dao;

import dao.simple_dao.dao.CarDAO;
import dao.simple_dao.dao.DAOFactory;
import dao.simple_dao.dao.IDAOFactory;
import dao.simple_dao.entity.Car;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        IDAOFactory factory = DAOFactory.getInstance();
        CarDAO carDAO = factory.getCarDAO();

        carDAO.remove("AUDI");

    }
}
