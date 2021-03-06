package jdbc.carsshop.result_set;

public class Client {

    private int id;
    private int age;
    private String name;
    private String phone;

    public Client(int id, int age, String name, String phone) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
