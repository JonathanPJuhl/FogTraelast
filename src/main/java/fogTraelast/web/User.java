package fogTraelast.web;

public class User {

    int mailNr;
    String role;
    int id;
    String password;
    String name;
    String address;
    String city;
    String phoneNumber;
    String email;

    public User(String name, String address, String city, String phoneNumber, String email, String password, int mailNr, String role)
    {
        this.name = name;
        this.address = address;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.id = id;
        this.password = password;
        this.role = role;
        this.mailNr = mailNr;
    }

    public int getMailNr() {
        return mailNr;
    }

    public String getRole() {
        return role;
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setMailNr(int mailNr) {
        this.mailNr = mailNr;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "postnr=" + mailNr +
                ", rolle='" + role + '\'' +
                ", id=" + id +
                ", password='" + password + '\'' +
                ", navn='" + name + '\'' +
                ", adresse='" + address + '\'' +
                ", by='" + city + '\'' +
                ", telefon='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
