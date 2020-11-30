package domain.users;

public class User {
    private final int salesmanID;
    private final String name;
    private final String phone;
    private final String email;
    private final String password;


    public User(int salesmanID, String name, String phone, String email, String password) {
        this.salesmanID=salesmanID;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    public int getSalesmanID() {
        return salesmanID;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User{" +
                "salesmanID=" + salesmanID +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
