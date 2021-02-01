package dao.simple_dao.dao;

import jdbc.carsshop.result_set.Client;

import java.util.List;

public interface ClientDAO {

    void add(Client car);

    List<Client> getAll();

    Client getById(int id);

    void updatePrice(int age, int clientId);

    void remove(String name);
}
