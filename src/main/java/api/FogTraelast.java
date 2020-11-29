package api;

import domain.orders.NoSuchOrderExists;
import domain.orders.Order;
import domain.orders.OrderRepository;
import domain.users.NoSuchUserExists;
import domain.users.User;
import domain.users.UserRepository;

public class FogTraelast {
    private String VERSION = "0.1";
    private final UserRepository userLists;
    private final OrderRepository orderLists;

    public FogTraelast(UserRepository userLists, OrderRepository orderLists) {
        this.userLists = userLists;
        this.orderLists = orderLists;
    }

    public Object getVERSION() {
        return VERSION;
    }

    public User createUser(String name, String password) {

        return userLists.createUsr(name, password);
    }

    public User loginUser(String email, String pass) throws NoSuchUserExists {
        return userLists.loginUsr(email, pass);
    }



    public Order findOrder(int id) throws NoSuchOrderExists {
        return orderLists.findSpecificOrder(id);
    }

    public Order createOrder(double length, double width, String customerPhone, String customerEmail) {
        return orderLists.insertOrderIntoDB(length, width, customerPhone, customerEmail);
    }
}
