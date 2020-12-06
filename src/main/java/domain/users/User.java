package domain.users;

public class User {
    private final int ID;
    private final String name;
    private final String phone;
    private final String email;
    private final String password;
    private final int roleID;


    public User(int ID, String name, String phone, String email, String password, int roleID) {
        this.ID =ID;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.roleID = roleID;
    }

    public int getID() {
        return ID;
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

    public int getRoleID() {
        return roleID;
    }

    @Override
    public String toString() {
        return "User{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + roleID +
                '}';
    }
}
