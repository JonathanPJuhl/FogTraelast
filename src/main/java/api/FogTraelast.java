package api;

import domain.orders.NoSuchOrderExists;
import domain.orders.Order;
import domain.orders.OrderRepository;
import domain.users.NoSuchUserExists;
import domain.users.User;
import domain.users.UserRepository;

import java.util.List;

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



    public Order findOrder(int id) throws NoSuchOrderExists {
        return orderLists.findSpecificOrder(id);
    }

    public Order createOrder(double length, double width, String customerPhone, String customerEmail) {
        return orderLists.insertOrderIntoDB(length, width, customerPhone, customerEmail);
    }

    public User loginSalesman(String salesmanEmail, String password) throws NoSuchUserExists {
        return userLists.loginSalesman(salesmanEmail, password);
    }

    public User findSalesman(int id) throws NoSuchUserExists{
        return userLists.findUser(id);
    }

    public List<Order> findAllOrders() throws NoSuchOrderExists {
        return orderLists.findAllOrders();
    }
    public void editPrice(double columnValue, int orderID) throws NoSuchOrderExists {
        orderLists.editPrice(columnValue, orderID);
    }
    public void editSalesman(int columnValue, int orderID) throws NoSuchOrderExists {
        orderLists.editSalesman(columnValue, orderID);
    }
    public void editStatus(String columnValue, int orderID) throws NoSuchOrderExists {
        orderLists.editStatus(columnValue, orderID);
    }
}
