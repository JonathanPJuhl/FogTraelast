package fogTraelast.web;

public class User {
    private final String name;
    private final String phone;
    private final String email;
    private final String password;
    private final String role;

    public User(String name, String phone, String email, String password, String role) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
