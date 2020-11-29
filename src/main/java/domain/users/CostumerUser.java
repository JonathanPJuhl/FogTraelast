package domain.users;

public class CostumerUser extends User{


    private final String address;
    private final String city;


    public CostumerUser(String name, String phone, String email, String password, String role, String address, String city) {
        super(name, phone, email, password, role);
        this.address = address;
        this.city = city;

    }


    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }









    @Override
    public String toString() {
        return "User{" +
                ", adresse='" + address + '\'' +
                ", by='" + city + '\'' +
                '}';
    }
}
