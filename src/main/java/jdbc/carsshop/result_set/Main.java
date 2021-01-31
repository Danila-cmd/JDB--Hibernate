package jdbc.carsshop.result_set;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        DBWorker dbWorker = new DBWorker();
        List<Client> clients = dbWorker.getAllClients();

        for (Client client: clients) {
            System.out.println(client.getId() + " " + client.getName() + " " + client.getAge() + " " + client.getPhone());
        }
    }
}
